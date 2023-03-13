package ru.javawebinar.basejava;

public class DeadLock {
    private static final String lock1 = "lock1";
    private static final String lock2 = "lock2";

    public static void main(String[] args) {
        createThread(DeadLock.lock1, DeadLock.lock2);
        createThread(DeadLock.lock2, DeadLock.lock1);
    }

    private static void createThread(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println(getThreadName() + " Попытка захватить монитор " + lock1);
            synchronized (lock1) {
                System.out.println(getThreadName() + " монитор " + lock1 + " захвачен");
                System.out.println(getThreadName() + " Попытка захватить монитор " + lock2);
                synchronized (lock2) {
                    System.out.println(getThreadName() + " монитор " + lock2 + " захвачен");
                }
            }
        }).start();
    }

    private static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
