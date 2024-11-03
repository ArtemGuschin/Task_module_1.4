package com.artem.javacore.taskfrommodule1_4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    ReentrantLock lock = new ReentrantLock();
    Condition firstMethodCalled = lock.newCondition();
    Condition secondMethodCalled = lock.newCondition();

    public void first(Runnable r) {
        lock.lock();
        try {
            r.run();
            System.out.println("First");
            firstMethodCalled.signal();
        } finally {
            lock.unlock();
        }

    }

    public void second(Runnable r) {
        lock.lock();
        try {
            firstMethodCalled.await();
            r.run();
            System.out.println("second");
            secondMethodCalled.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    public void third(Runnable r) {
        lock.lock();
        try {
            secondMethodCalled.await();
            r.run();
            System.out.println("third");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}