package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Round_Robin {
    private Queue<process> queue = new LinkedList<process>();
    private int Quantum_Time;
    private int ContextSwitch;
    private int num_Of_process;
    public Round_Robin(Queue<process> queue,int QuantmTime,int ContectSwitch,int numOfprocess)
    {
        this.queue = queue;
        this.Quantum_Time = QuantmTime;
        this.ContextSwitch = ContextSwitch;
        this.num_Of_process = numOfprocess;
    }
    private ArrayList<Integer> Turnaround_time = new ArrayList<Integer>(num_Of_process);
    private ArrayList<Integer> WaitingTime = new ArrayList<Integer>(num_Of_process);
    private ArrayList<String> TurnOverTimeProcessName = new ArrayList<String>(num_Of_process);
    private Queue<Integer> queue_waiting = new LinkedList<Integer>();
    void setButestArray()
    {
        for(int i = 0 ; i < num_Of_process;i++)
        {
            process num = queue.poll();
            queue_waiting.add(num.getBurstTime());
            queue.add(num);
        }
    }
    public void algorithm_rr()
    {
        System.out.print("The Process ExecutionOrder Is  : \n");
        int Time =0 ;
        int index = 0;
        this.setButestArray();
        while(!queue.isEmpty())
        {
            process obj = queue.poll();
            int Temp = 0 ;
            //Here check if queue waiting is empty or not
            if(!queue_waiting.isEmpty())
                Temp = queue_waiting.poll();
            if(obj.getBurstTime() >= Quantum_Time)
                Time += Quantum_Time;
            else
                Time += obj.getBurstTime();
            //Here update burst time
            obj.setBurstTime(obj.getBurstTime()- Quantum_Time);
            //Here to check if the burst time reach to zero or not
            //To end this process
            if(obj.getBurstTime() > 0)
            {
                queue.add(obj);
                queue_waiting.add(Temp);
            }
            //Here to get the turnaround time by subtract time by arrival time of the process
            else
            {
                Turnaround_time.add(index, Time - obj.getArrival_Time());
                TurnOverTimeProcessName.add(index, obj.getName());
                WaitingTime.add(index, Time - obj.getArrival_Time() - Temp);
                index++;
            }
            System.out.print(obj.getName() + "\n");
        }
    }
    public void print()
    {
        double sum = 0;
        System.out.print("Turnover Time For Each Process Is : \n");
        for(int i = 0; i < Turnaround_time.size() ; i++)
        {
            sum += Turnaround_time.get(i);
            System.out.print(TurnOverTimeProcessName.get(i) + " : "+ Turnaround_time.get(i) +  "\n" );
        }
        System.out.print("Average TurnOverTime Is =  " + sum / num_Of_process + " \n");
        sum = 0;
        System.out.print("Waiting Time For Each Process Is : \n");
        for(int i = 0; i < Turnaround_time.size() ; i++)
        {
            sum += WaitingTime.get(i);
            System.out.print(TurnOverTimeProcessName.get(i) + " : "+ WaitingTime.get(i) +  "\n" );

        }
        System.out.print("Average WaitingTime  Is =  " + sum / num_Of_process +"\n");

    }

}