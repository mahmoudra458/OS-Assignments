package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

class SJF {
    ArrayList<process>processes;
    Queue<process>processQueue=new PriorityQueue<>();
    ArrayList<String>executionOrder=new ArrayList<>();
    int clock=-1;
    int process_started=0;
    public SJF(ArrayList<process> processes){
        this.processes=processes;

    }
    public void start(){
        while(process_started < processes.size()){
            //start process execution for first process and adding it in process queue
            clock++;
            for(process p:processes){
                if(clock==p.arrivalTime){
                    processQueue.add(p);
                    p.inQueue=true;
                }
            }
            if(processQueue.size()!=0){
                // the condition above to avoid run time error when the priority queue is empty
                // in case of the process is idle in some cases
                process tempProcess=processQueue.poll();
                String tempProcess_name=tempProcess.ProcessName;
                //Here increasing the waiting time for each process and the process is not done
                for(process p:processes){
                    if(p.ProcessName == tempProcess_name){
                        continue;
                    }
                    if(p.inQueue && p.done==false){
                        p.wating++;
                    }
                }
                //Here adding the temp process in execution order and decrease the burst time by one
                // And chek if the burst time reach to 0 then the process will be done
                executionOrder.add(tempProcess_name);
                tempProcess.burstTime--;
                if(tempProcess.burstTime==0){
                    tempProcess.turndown=clock-tempProcess.arrivalTime;
                    tempProcess.done=true;
                    process_started++;
                }
                else{
                    processQueue.add(tempProcess);
                }
            }
        }
        show_process_executionOrder();
        show_process_Info();
        calc_waiting_AVG();
        calc_turnaround_Avg();
    }
    //Here show the execution interval for each process
    public void show_process_executionOrder(){
        System.out.println("Process Execution Order: ");
        for(int i=0;i<executionOrder.size();i++){
            System.out.print(i+"-"+executionOrder.get(i)+" ");
        }
        System.out.println();
    }
    //Here show information about each process
    public void show_process_Info(){
        for(process p:processes){
            System.out.println(p.ProcessName+" WaitingTime: "+p.wating+" Turnaround Time: "+p.turndown);
        }
    }
    public void calc_turnaround_Avg(){
        double turnaround_Total=0;
        for(process p:processes){
            turnaround_Total+=p.turndown;
        }
        System.out.println("Turnaround AVG: "+turnaround_Total/processes.size());
    }
    public void calc_waiting_AVG(){
        double waiting_Total=0;
        for(process p:processes){
            waiting_Total+=p.wating;
        }
        System.out.println("Waiting time AVG: "+waiting_Total/processes.size());
    }
}
