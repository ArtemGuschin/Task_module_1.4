package com.artem.javacore.taskfrommodule1_4;

public class Foo {
    private boolean firstExecuted;
    private boolean secondExecuted;

    public Foo() {
        firstExecuted = false;
        secondExecuted = false;
    }

    public synchronized void first(Runnable r) {
        r.run();
        firstExecuted = true;
        notifyAll();
        System.out.print("first");
    }

    public synchronized void second(Runnable r) throws InterruptedException {
        while (!firstExecuted) {
            wait();
        }
        r.run();
        secondExecuted = true;
        notifyAll();
        System.out.print("second");
    }

    public synchronized void third(Runnable r) throws InterruptedException {
        while (!secondExecuted) {
            wait();
        }
        r.run();
        System.out.print("third");
    }

}
