package ru.javawebinar.basejava;

public class Deadlock {
    final static Object FIRST_RESOURCE = new Object();
    final static Object SECOND_RESOURCE = new Object();

    public static void main(String[] args) {
        startNewThread(FIRST_RESOURCE, SECOND_RESOURCE);
        startNewThread(SECOND_RESOURCE, FIRST_RESOURCE);
    }

    private static void startNewThread(Object o1, Object o2) {
        new Thread(() -> {
            System.out.printf("%s is about to take %s%n", Thread.currentThread().getName(), o1);
            synchronized (o1) {
                System.out.printf("%s took %s%n", Thread.currentThread().getName(), o1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.printf("%s took %s%n", Thread.currentThread().getName(), o2);
                }
            }
            System.out.printf("%s passed all synchronized blocks and about to finish its job",
                    Thread.currentThread().getName());
        }).start();
    }
}