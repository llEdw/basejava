package com.urise.webapp;

public class DeadLock {

    public static void main(String[] args) {
        DeadLock a = new DeadLock();
        DeadLock b = new DeadLock();
        new Thread(() -> execute(a, b))
                .start();
        new Thread(() -> execute(b, a))
                .start();
    }

    public static void execute(DeadLock a, DeadLock b) {
        synchronized (a) {
            System.out.println(Thread.currentThread().getName() + " a");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " b");
            }
        }
    }
}
