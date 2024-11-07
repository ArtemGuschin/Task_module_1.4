package com.artem.javacore.taskfrommodule1_4;

import java.util.concurrent.Semaphore;

public class FooSemaphore {
    Semaphore betweenFirstAndSecond = new Semaphore(0);
    Semaphore betweenSecondAndThird = new Semaphore(0);

    public void first(Runnable r) {
        System.out.println("first");
        r.run();
        betweenFirstAndSecond.release();
    }

    public void second(Runnable r) {
        try {
            betweenFirstAndSecond.acquire();
            System.out.println("second");
            r.run();
            betweenSecondAndThird.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void third(Runnable r) {
        try {
            betweenSecondAndThird.acquire();
            System.out.println("third");
            r.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
