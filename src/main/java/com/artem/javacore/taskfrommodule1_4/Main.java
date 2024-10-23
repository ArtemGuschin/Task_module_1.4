package com.artem.javacore.taskfrommodule1_4;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        Foo foo = new Foo();


        Thread a = new Thread(() -> foo.first(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));

        Thread b = new Thread(() -> {
            try {
                foo.second(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        Thread c = new Thread(() -> {
            try {
                foo.third(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        a.start();
        b.start();
        c.start();


    }
}
