package ru.javawebinar.basejava;

public class MainConcurrency {
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getState());
        }).start();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    increment();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(counter);

    }

    private static synchronized void increment() {
        counter++;
    }
}
