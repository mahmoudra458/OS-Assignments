package com.company;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class main {
    private JFrame frame;
    private JTextField txt_n;
    private JTextField txt_outfile;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    main window = new main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 547, 375);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txt_n = new JTextField();
        txt_n.setBounds(28, 30, 176, 29);
        frame.getContentPane().add(txt_n);
        txt_n.setColumns(10);

        txt_outfile = new JTextField();
        txt_outfile.setBounds(28, 75, 176, 29);
        frame.getContentPane().add(txt_outfile);
        txt_outfile.setColumns(10);

        JButton btn1 = new JButton("start Producer");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    int n= Integer.parseInt( txt_n.getText() );
                    String path=txt_outfile.getText();
                    Vector sharedQueue = new Vector();
                    Thread prodThread = new Thread(new Producer(sharedQueue, n,textField_1,textField_2,textField_3), "Producer");
                    Thread consThread = new Thread(new Consumer(sharedQueue, n,path), "Consumer");
                    prodThread.start();
                    consThread.start();



                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        btn1.setBounds(25, 125, 126, 23);
        frame.getContentPane().add(btn1);

        JLabel lblNewLabel = new JLabel("N");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(229, 30, 46, 22);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblOutputFile = new JLabel("Output File");
        lblOutputFile.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblOutputFile.setBounds(214, 76, 94, 22);
        frame.getContentPane().add(lblOutputFile);

        JLabel lblTheLargestPrime = new JLabel("the largest prime number");
        lblTheLargestPrime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTheLargestPrime.setBounds(28, 173, 198, 22);
        frame.getContentPane().add(lblTheLargestPrime);

        JLabel lblofElementsprime = new JLabel("#of elements (prime number)generated");
        lblofElementsprime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblofElementsprime.setBounds(28, 221, 302, 22);
        frame.getContentPane().add(lblofElementsprime);

        JLabel lblTimeElapsedSince = new JLabel("time elapsed since the start of processing");
        lblTimeElapsedSince.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTimeElapsedSince.setBounds(28, 269, 280, 22);
        frame.getContentPane().add(lblTimeElapsedSince);

        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBounds(320, 172, 156, 29);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(320, 220, 156, 29);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBounds(318, 268, 158, 29);
        frame.getContentPane().add(textField_3);
    }
}
