package com.artem.javacore.taskfrommodule1_4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooReent {
    ReentrantLock lock = new ReentrantLock();
    Condition firstMethodCalled = lock.newCondition();
    Condition secondMethodCalled = lock.newCondition();

    public void first(Runnable r) {
        lock.lock();
        try {
            System.out.print("First");
            firstMethodCalled.signal();
        } finally {
            r.run();
            lock.unlock();
        }

    }

    public void second(Runnable r) {
        lock.lock();
        try {
            firstMethodCalled.await();
            System.out.print("second");
            secondMethodCalled.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            r.run();
            lock.unlock();
        }

    }

    public void third(Runnable r) {
        lock.lock();
        try {
            secondMethodCalled.await();
            System.out.print("third");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            r.run();
            lock.unlock();
        }

    }
}