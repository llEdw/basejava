package com.urise.webapp;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                execute();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        })
                .start();
        execute2();
    }

    public synchronized static void execute() throws InterruptedException {
        Thread.sleep(1000);
        execute2();
    }

    public synchronized static void execute2() throws InterruptedException {
        Thread.sleep(1000);
        execute();
    }
}