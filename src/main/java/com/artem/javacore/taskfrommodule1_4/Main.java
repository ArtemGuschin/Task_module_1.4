package com.artem.javacore.taskfrommodule1_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        //  FooReent foo = new FooReent();
        // FooEx foo = new FooEx();
        FooSemaphore foo = new FooSemaphore();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> foo.first(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        executorService.submit(() -> {
            foo.second(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        executorService.submit(() -> {
            foo.third(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        executorService.shutdown();

    }
}
