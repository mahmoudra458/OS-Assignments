package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<partition> partitions = new ArrayList<>();
        List<process> processes = new ArrayList<>();
        /*Scanner input = new Scanner(System.in);
        Scanner inputs = new Scanner(System.in);
        System.out.println("Enter number of partitions :");
        int part_number = input.nextInt();
        for(int i=0; i<part_number; i++){
            partition new_part = new partition();
            String partitionName;
            System.out.println("Enter partition number :");
            partitionName = inputs.nextLine();
            System.out.println("Enter partition size :");
            int part_size = input.nextInt();
            new_part.partitionName = partitionName;
            new_part.partitionSize = part_size;
            partitions.add(i, new_part);
        }
        System.out.println("Enter number of processes :");
        int process_number = input.nextInt();
        for(int i=0; i<process_number; i++){
            process new_process = new process();
            System.out.println("Enter process name :");
            String proc_name = inputs.nextLine();
            System.out.println("Enter process size :");
            int proc_size = input.nextInt();
            new_process.processName = proc_name;
            new_process.processSize = proc_size;
            processes.add(i, new_process);
        }
        FirstFit firstFit = new FirstFit();
        //firstFit.First_fit(partitions, processes, process_number, part_number);
        BestFit bestFit = new BestFit();
        bestFit.Best_fit(partitions, processes, process_number, part_number);*/
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l1.add(0, 5);
        l1.add(1, 2);
        l2 = l1;
        System.out.println(l2.get(0));

    }
}
