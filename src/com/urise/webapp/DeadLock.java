package com.urise.webapp;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        A b = new A();
        new Thread(() -> {
            try {
                a.execute(b);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        })
                .start();

        new Thread(() -> {
            try {
                b.execute(a);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        })
                .start();
    }
}

class A {
    public synchronized void execute(A b) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "  execute");
        Thread.sleep(2000);
        b.execute2(b);
    }

    public synchronized void execute2(A a) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "  execute2");
        Thread.sleep(2000);
        a.execute(a);
    }
}