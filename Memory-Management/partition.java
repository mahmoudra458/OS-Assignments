package com.company;

public class partition {
    String partitionName;
    int partitionSize;
    int processNumber;
    boolean take;
    public partition(){
        take = false;
    }
    public void setPartitionName(String partitionName){
        this.partitionName = partitionName;
    }
    public String getPartitionName(){
        return partitionName;
    }
    public void setPartitionSize(int partitionSize){
        this.partitionSize = partitionSize;
    }
    public int getPartitionSize(){
        return partitionSize;
    }
    public void setProcessNumber(int processNumber){
        this.processNumber = processNumber;
    }
    public int getProcessNumber(){
        return processNumber;
    }
}
