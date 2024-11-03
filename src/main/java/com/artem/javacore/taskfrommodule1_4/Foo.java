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
            System.out.println("First");
            r.run();
            firstMethodCalled.signal();
        } finally {
            lock.unlock();
        }

    }

    public void second(Runnable r) {
        lock.lock();
        try {
            firstMethodCalled.await();
            System.out.println("second");
            r.run();
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
            System.out.println("third");
            r.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }
}