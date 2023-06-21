package com.company;
import java.util.*;

class process implements Comparable<process> {
    public String ProcessName;
    public int arrivalTime;
    public int burstTime;
    public int processPriority;
    public boolean inQueue=false;
    public int wating=0;
    public int turndown=0;
    public boolean done=false;
    public process(){}
    public int compareTo(process p){
        return Integer.compare(burstTime,p.burstTime);
    }
}