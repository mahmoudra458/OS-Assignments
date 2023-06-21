package com.company;



import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

class Producer implements Runnable {
    private final Vector sharedQueue;
    private final int SIZE;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public Producer(Vector sharedQueue, int size,JTextField textField_1,JTextField textField_2,JTextField textField_3) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
        this.textField_1=textField_1;
        this.textField_2=textField_2;
        this.textField_3=textField_3;

    }
    public boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    @Override
    public void run() {
        int large=0,counter=0;

        long startTime = System.nanoTime();

        for (int i = 0; i < SIZE; i++) {
            if (isPrime(i)) {
                System.out.println("Produced: " + i);
                try {
                    produce(i);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                large=i;
                counter++;
            }

        }

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        this.textField_1.setText(String.valueOf(large));
        this.textField_2.setText(String.valueOf(counter));
        this.textField_3.setText(String.valueOf(totalTime)+"ns");




    }

    private void produce(int i) throws InterruptedException {

        //wait if the queue is full
        while (sharedQueue.size() == SIZE) {
            synchronized (sharedQueue) {
                System.out.println("The queue is full " + Thread.currentThread().getName()
                        + " is waiting , size: " + sharedQueue.size());

                sharedQueue.wait();
            }
        }

        //producing element and notify consumers
        synchronized (sharedQueue) {
            sharedQueue.add(i);
            sharedQueue.notifyAll();
        }
    }
}

class Consumer implements Runnable {

    private final Vector sharedQueue;
    private final int SIZE;
    private String path;


    public Consumer(Vector sharedQueue, int size,String path) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
        this.path=path;
    }

    @Override
    public void run() {

        if(!Files.exists(Paths.get(this.path)))
        {
            try {
                File myObj = new File(this.path);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.print("");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }




        while (true) {
            try {
                String s=String.valueOf(consume());
                System.out.println("Consumed: " + s);
                try {
                    FileWriter myWriter = new FileWriter(this.path,true);

                    myWriter.write(s+"\", \"");
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }

    private int consume() throws InterruptedException {
        //wait if the queue is empty
        while (sharedQueue.isEmpty()) {
            synchronized (sharedQueue) {
                System.out.println("The queue is empty " + Thread.currentThread().getName()
                        + " is waiting , size: " + sharedQueue.size());

                sharedQueue.wait();
            }
        }

        //Otherwise consume element and notify the waiting producer
        synchronized (sharedQueue) {
            sharedQueue.notifyAll();
            return (Integer) sharedQueue.remove(0);
        }
    }
}
