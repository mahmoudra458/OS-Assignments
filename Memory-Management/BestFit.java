package com.company;

import java.util.ArrayList;
import java.util.List;

public class BestFit {
    int count = 6;
    public void fit_2(List<partition> arr_partition, int number_of_partitions, process proc){

    }
    public void Best_fit(List<partition> partitions, List<process>processes, int number_of_process, int number_of_partitions){
        for(int i=0; i<number_of_process; i++)
        {
            processes.get(i).processNumber = i+1;
            fit_2(partitions, number_of_partitions, processes.get(i));
        }
        for(int i=0; i<partitions.size(); i++)
        {
            if(partitions.get(i).processNumber > 0) {
                System.out.println(partitions.get(i).partitionName + "(" + partitions.get(i).partitionSize + ")" + "=> " + "Process " + partitions.get(i).processNumber);
            }
            else
            {
                System.out.println(partitions.get(i).partitionName + "(" + partitions.get(i).partitionSize + ")" + "=> " + "External fragment");
            }
        }
        for(int i=0; i<number_of_process; i++)
        {
            if(processes.get(i).processDone == false)
            {
                System.out.println(processes.get(i).processName + " can not be allocated");
            }
        }
    }
}
