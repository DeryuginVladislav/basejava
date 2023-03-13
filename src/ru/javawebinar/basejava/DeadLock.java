package ru.javawebinar.basejava;

public class DeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Thread1 Попытка захватить монитор lock1");
            synchronized (DeadLock.lock1) {
                System.out.println("Thread1 монитор lock1 захвачен");
                System.out.println("Thread1 Попытка захватить монитор lock2");
                synchronized (DeadLock.lock2) {
                    System.out.println("Thread1 монитор lock2 захвачен");
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("Thread2 Попытка захватить монитор lock2");
            synchronized (DeadLock.lock2) {
                System.out.println("Thread2 монитор lock2 захвачен");
                System.out.println("Thread2 Попытка захватить монитор lock1");
                synchronized (DeadLock.lock1) {
                    System.out.println("Thread2 монитор lock1 захвачен");
                }
            }
        }).start();
    }
}
