package com.artem.javacore.taskfrommodule1_4;

public class FooEx {
    private boolean firstExecuted;
    private boolean secondExecuted;
    public FooEx(){
        firstExecuted = false;
        secondExecuted = false;
    }
    public synchronized void first(Runnable r){
        System.out.println("first");
        r.run();
        firstExecuted = true;
        notifyAll();
    }
    public synchronized void second(Runnable r ) throws InterruptedException {
        while (!firstExecuted){
            wait();
        }
        System.out.println("second");
        r.run();
        secondExecuted = true;
        notifyAll();
    }
    public synchronized void third(Runnable r) throws InterruptedException {
        while (!secondExecuted){
            wait();
        }
        System.out.println("third");
        r.run();
    }

}
