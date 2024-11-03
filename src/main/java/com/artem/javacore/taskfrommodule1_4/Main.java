package com.artem.javacore.taskfrommodule1_4;

public class Main {
    public static void main(String[] args)  {
        FooReent foo = new FooReent();
        Thread a = new Thread(() -> foo.first(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        Thread b = new Thread(() -> foo.second(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        Thread c = new Thread(() -> foo.third(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        a.start();
        b.start();
        c.start();


        try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
