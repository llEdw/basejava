package com.urise.webapp;

public class DeadLock {

    public static void main(String[] args) {
        A a = new A();
        A b = new A();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.execute(b);
            }
        }).start();

        for (int i = 0; i < 1000; i++) {
            b.execute(a);
        }
    }
}

class A {
    public synchronized void execute(A a) {
        a.execute2();
    }

    public synchronized void execute2() {
        System.out.println(0);
    }
}