package com.urise.webapp;

public class DeadLock {

    public static void main(String[] args) {
        DeadLock a = new DeadLock();
        DeadLock b = new DeadLock();
        new Thread(() -> {
            try {
                execute(a, b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })
                .start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
                execute(b, a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })
                .start();
    }

    public static void execute(DeadLock a, DeadLock b) throws InterruptedException {
        synchronized (a) {
            System.out.println(Thread.currentThread().getName() + " a");
            Thread.sleep(1000);
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " b");
            }
        }
    }
}
