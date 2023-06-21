package com.company;

import java.util.Vector;
import static java.lang.Math.ceil;

public class scheduler {
    public Vector <process> AllProcesses = new Vector <>(); //while moving process btwn objects its saved here
    Vector <process> first = new Vector <>();// FCFS for just
    Vector <process> queue = new Vector <>(); //Non-Preemptive Priority
    Vector <process> second = new Vector <>(); //Non-Preemptive Priority
    Vector <process> sorted_ = new Vector <>(); //Non-Preemptive Priority
    Vector<process> third = new Vector <>(); //   Preemptive SJF
    int Time = 0;

    void setWhole() {
        int count=0;
        for (process proc : first) {
            process.idxAtAll = 0;
            AllProcesses.add(proc);
            count++;
        }
        float[] arr=new float[first.size()];
        int[] sort = ArgSort.sort(arr);
        for (int i: sort) {
        }
        for (int i=0;i<first.size();i++) {
            sorted_.add(first.get(sort[i]));
        }

    }
    public void starting() {
        setWhole();
        process p = sorted_.get(3), last=new process();
        sorted_.remove(3);
        int time=0,t=0;
        while(sorted_.size()!=0 || queue.size()!=0) {
            System.out.println("Name:"+p.getName()+" time:"+ time+"  state:"+p.getState()+" Burst time:"+p.getBurst_time()+" Quantum time:"+p.getQuantum_time()+"\n");
            if(p.getState()>0) {

                int t2 = (int) ceil(p.getQuantum_time()*0.5);
                if(p.getBurst_time()<=t2-t) {
                    t2= p.getBurst_time();
                    time += t2;
                    t=0;
                }else {time-=t;time += t2;}

                p.setBurst_time(p.getBurst_time()-(t2-t));
                p.upState();
                p.setRemining(t2);
            }else {

                t= (int)ceil(p.getQuantum_time()*0.25);
                if(p.getBurst_time()<=t) {
                    t= p.getBurst_time();
                }
                time+=t;
                p.setBurst_time((p.getBurst_time()-t));


                p.upState();
                p.setRemining(t);}




            if(p.getBurst_time()<=0) {
                queue.remove(p);
                p = new process();
                p= null;

            }else if(p.getremain()<=0)
            {p.setQuantum_time(p.getQuantum_time()+2);
                queue.add(p);
                p = new process();
                p= null;


            }

            for (int i=0;i<sorted_.size();i++) {
                process process = sorted_.get(i);
                if(process.getArrival_time()<=time) {
                    queue.add(process);
                    sorted_.remove(i);
                }
            }

            if (p == null) { //FCFS
                p = queue.get(0);
                p.setState(0);
                queue.remove(0);
                last = new process(p.getName(), p.getBurst_time(),p.getArrival_time(), p.getPriority(),p.getQuantum_time());
                last.setState(p.getState());
                last.remainSetter(p.getremain());

            }else {
                last = new process(p.getName(), p.getBurst_time(),p.getArrival_time(), p.getPriority(),p.getQuantum_time());
                last.setState(p.getState());
                last.remainSetter(p.getremain());

                for (process pr :queue) {

                    if(last.getState()==1) {//Priority
                        if(p.getPriority()>pr.getPriority() && pr.getBurst_time()>0) {
                            p = pr;
                        }
                    }
                    else if(last.getState()==2) {// SJF
                        if(p.getBurst_time()>pr.getBurst_time() && pr.getBurst_time()>0) {
                            p = pr;
                        }
                    }
                }
                if(last.getState()==1 && !p.getName().equals(last.getName())) {

                    last.setQuantum_time(last.getQuantum_time()+(int)last.getremain()/2);

                    last.setState(0);

                    queue.remove(p);
                    queue.add(last);
                }
                if(last.getState()==2 && !p.getName().equals(last.getName()))  {
                    last.setState(0);
                    last.setQuantum_time(last.getQuantum_time()+last.getremain());
                    queue.remove(p);
                    queue.add(last);
                }}
        }
        time+=p.getBurst_time();
        p.setBurst_time(0);
        //states:
        //0 = FCFS
        //1 = Priority
        //2 = SJF
        System.out.println("Name:"+p.getName()+" time:"+ time+"  state:"+p.getState()+" Burst time:"+p.getBurst_time()+" Quantum time:"+p.getQuantum_time()+"\n");
    }



}
class ArgSort {
    public static int[] sort(float[] input)
    {
        int h = input.length;
        int[] index = new int[h];

        for (int i = 0; i < h; ++i)
            index[i] = i;

        for (int i = h - 1; i > 0; --i)
        {
            int min = 0;
            for (int j = 1; j <= i; ++j)
                if (input[index[j]] < input[index[min]])
                    min = j;

            int temp = index[i];
            index[i] = index[min];
            index[min] = temp;
        }
        return index;
    }
}
