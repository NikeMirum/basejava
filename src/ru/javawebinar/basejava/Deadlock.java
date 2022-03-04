package ru.javawebinar.basejava;

public class Deadlock {

    public static void main(String[] args) {
        final Object FIRST_RESOURCE = new Object();
        final Object SECOND_RESOURCE = new Object();

        new Thread(() -> {
            System.out.println("First Thread is about to take FIRST_RESOURCE");
            synchronized (FIRST_RESOURCE) {
                System.out.println("First Thread took FIRST_RESOURCE");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (SECOND_RESOURCE) {
                    System.out.println("First Thread took SECOND_RESOURCE");
                }
            }
            System.out.println("First Thread passed all synchronized blocks and about to finish its job");
        }).start();

        new Thread(() -> {
            System.out.println("Second Thread is about to take SECOND_RESOURCE");
            synchronized (SECOND_RESOURCE) {
                System.out.println("Second Thread took FIRST_RESOURCE");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (FIRST_RESOURCE) {
                    System.out.println("Second Thread took FIRST_RESOURCE");
                }
            }
            System.out.println("Second Thread passed all synchronized blocks and about to finish its job");
        }).start();
    }
}