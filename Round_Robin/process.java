package com.company;

public class process {
    private String Name;
    private int Arrival_Time;
    private int BurstTime;
    private int priority;
    public process(String Name,int Arrival_Time,int BurstTime,int priority)
    {
        this.Name = Name;
        this.Arrival_Time = Arrival_Time;
        this.BurstTime = BurstTime;
        this.priority = priority;
    }
    public void setNme(String Name)
    {
        this.Name = Name;
    }
    public void setBurstTime(int BurstTime)
    {
        this.BurstTime = BurstTime;
    }
    public void setpriority(int priority)
    {
        this.priority = priority;
    }
    public String getName()
    {
        return this.Name;
    }
    public int getArrival_Time()
    {
        return this.Arrival_Time;
    }
    public int getBurstTime()
    {
        return this.BurstTime;
    }
    public int getpriority()
    {
        return this.priority;
    }
}
