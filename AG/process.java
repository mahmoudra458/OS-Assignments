package com.company;

public class process {
    private String name;
    private int burst_time;
    private int arrival_time;
    private int priority;
    private int quantum_time;
    int turnaround_Time;
    static int idxAtAll;
    int state=0;
    int remain=0;

    @Override
    public String toString() {
        return String.valueOf(getName() + " : " + getPriority());
    }
    public process(String name, int burst_time, int arrival_time, int priority, int quantum_time) {
        this.name = name;
        this.setBurst_time(burst_time);
        this.arrival_time = arrival_time;
        this.setPriority(priority);
        this.setQuantum_time(quantum_time);
        this.setTurnaround_Time(burst_time);
    }
    public process() {

    }
    public void remainSetter(int x) {
        remain=x;
    }

    public String getName() {
        return name;
    }
    public void setState(int s) {
        this.state=s;
    }

    public int getState() {
        return state;
    }


    public int getBurst_time() {
        return burst_time;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public int getPriority() {
        return priority;
    }

    public int getQuantum_time() {
        return quantum_time;
    }

    public void setQuantum_time(int quantum_time) {
        this.quantum_time = quantum_time;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }
    public void upState() {
        this.state +=1;
    }
    public void setRemining(int x) {
        remain = quantum_time-x;
    }
    public int getremain() {
        return remain;
    }

    public int getTurnaround_Time() {
        return turnaround_Time;
    }

    public void setTurnaround_Time(int turnaround_Time) {
        this.turnaround_Time = turnaround_Time;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
