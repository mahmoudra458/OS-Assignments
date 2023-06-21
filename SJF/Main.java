package com.company;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// Here code for Shortest Job First
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int n;
        n=sc.nextInt();
        ArrayList<process>processes=new ArrayList<>();
        for(int i=0;i<n;i++){
            System.out.println("Enter the process name: ");
            process p=new process();
            p.ProcessName=sc.next();
            System.out.println("Enter the burst time of "+p.ProcessName+" :");
            p.burstTime=sc.nextInt();
            System.out.println("Enter the arrival time of "+p.ProcessName+" :");
            p.arrivalTime=sc.nextInt();
            processes.add(p);
        }
        SJF sjf=new SJF(processes);
        sjf.start();
        //Here code for Round Robin code


    }
}
