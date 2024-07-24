package org.hidoc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _1_Question {

    private static final List<Integer> list1 = new LinkedList<>();
    private static final List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) {
        // Creating two threads
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());

        // Starting both threads
        thread1.start();
        thread2.start();
    }

    // Task for the first thread
    static class Task1 implements Runnable {
        @Override
        public void run() {
            synchronized (list1) {
                System.out.println("Thread 1: Holding list1 lock...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Waiting for list2 lock...");

                synchronized (list2) {
                    System.out.println("Thread 1: Holding list1 and list2 locks...");
                }
            }
        }
    }

    // Task for the second thread
    static class Task2 implements Runnable {
        @Override
        public void run() {
            synchronized (list2) {
                System.out.println("Thread 2: Holding list2 lock...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Waiting for list1 lock...");

                synchronized (list1) {
                    System.out.println("Thread 2: Holding list2 and list1 locks...");
                }
            }
        }
    }
}
