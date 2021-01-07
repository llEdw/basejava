package com.urise.webapp;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        A b = new A();
        new Thread(() -> {
            try {
                a.execute();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        })
                .start();
        b.execute2();
    }
}

class A {
    public synchronized void execute() throws InterruptedException {
        Thread.sleep(1000);
        execute3();
    }

    public synchronized void execute2() throws InterruptedException {
        execute3();
    }

    public synchronized void execute3() throws InterruptedException {
        Thread.sleep(1000);
        execute();
    }
}