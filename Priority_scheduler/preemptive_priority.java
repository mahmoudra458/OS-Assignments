package com.company;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class preemptive_priority {
    ArrayList<process>processes;
    Queue<process> processQueue=new PriorityQueue<process>();
    ArrayList<String>executionOrder=new ArrayList<>();
    int clock=-1;
    int processDone=0;
    public preemptive_priority(ArrayList<process> processes){
        this.processes=processes;

    }
    public void start(){
        while(processDone<processes.size()){
            //we increase the clock value
            //then before anything we add the processes
            // which their arrival time has come to the priority queue

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
                String tempProcessname=tempProcess.ProcessName;
                //we increase the waiting time for every process else inQueue== true
                for(process p:processes){
                    if(p.ProcessName==tempProcessname){
                        continue;
                    }
                    if(p.inQueue&&p.done==false){
                        p.wating++;
                    }
                }
                executionOrder.add(tempProcessname);
                tempProcess.burstTime--;
                if(tempProcess.burstTime==0){
                    tempProcess.turndown=clock-tempProcess.arrivalTime;
                    tempProcess.done=true;
                    processDone++;
                }
                else{
                    processQueue.add(tempProcess);
                }
            }
        }
        showProcessExecutionOrder();
        showEveryProcessInfo();
        calcWaitingAVG();
        calcTurnDownAvg();
    }
    public void showProcessExecutionOrder(){
        System.out.println("Process Execution Order: ");
        for(int i=0;i<executionOrder.size();i++){
            System.out.print(i+"-"+executionOrder.get(i)+" ");
        }
        System.out.println();
    }
    public void showEveryProcessInfo(){
        for(process p:processes){
            System.out.println(p.ProcessName+" WaitingTime: "+p.wating+" Turndown Time: "+(p.turndown+1));
        }
    }
    public void calcTurnDownAvg(){
        double turndownTotal=0;
        for(process p:processes){
            turndownTotal+=p.turndown;
        }
        System.out.println("Turndown AVG: "+turndownTotal/processes.size());
    }
    public void calcWaitingAVG(){
        double waitingTotal=0;
        for(process p:processes){
            waitingTotal+=p.wating;
        }
        System.out.println("Waiting time AVG: "+waitingTotal/processes.size());
    }
}
