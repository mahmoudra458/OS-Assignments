package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        scheduler s = new scheduler();
        s.first.add(new process("p1", 17, 0,  4, 7));
        s.first.add(new process("p2", 6,  2,  7, 9));
        s.first.add(new process("p3", 11, 5,  3, 4));
        s.first.add(new process("p4", 4,  15, 6, 6));
        s.starting();
    }
}
