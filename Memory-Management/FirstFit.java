package com.company;

import java.util.List;

public class FirstFit {
    int count = 6;
    public void fit(List<partition> arr_partition, int number_of_partitions, process proc){
        for(int i=0; i<count; i++)
        {
            if (proc.processSize <= arr_partition.get(i).partitionSize && arr_partition.get(i).take == false)
            {
                int size_partition = arr_partition.get(i).partitionSize;
                arr_partition.get(i).partitionSize = proc.processSize;
                arr_partition.get(i).processNumber = proc.processNumber;
                arr_partition.get(i).take = true;
                proc.processDone = true;
                if (proc.processSize < size_partition)
                {
                    int new_partition_size = size_partition - proc.processSize;
                    partition new_partition = new partition();
                    new_partition.take = false;
                    new_partition.partitionSize = new_partition_size;
                    String str_cast = Integer.toString(number_of_partitions);
                    new_partition.partitionName = "Partition" + str_cast;
                    arr_partition.add(count, new_partition);
                    count++;

                }
                break;
            }
            else {
                continue;
            }
        }
    }
    public void First_fit(List<partition> partitions, List<process>processes, int number_of_process, int number_of_partitions){
        for(int i=0; i<number_of_process; i++)
        {
            processes.get(i).processNumber = i+1;
            fit(partitions, number_of_partitions, processes.get(i));
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
