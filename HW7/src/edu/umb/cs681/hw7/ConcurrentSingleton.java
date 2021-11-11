package edu.umb.cs681.hw7;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable{
    private static ConcurrentSingleton instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private final double tag;

    private ConcurrentSingleton(){
        tag = Math.random();
    };

    public static ConcurrentSingleton getInstance() {
        lock.lock();
        try {
            if(instance == null)
                instance = new ConcurrentSingleton();
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public double getTag() {
        return tag;
    }

    @Override
    public void run() {
        System.out.println("The tag for this singleton is " + ConcurrentSingleton.getInstance().getTag());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(ConcurrentSingleton.getInstance());
        Thread t2 = new Thread(ConcurrentSingleton.getInstance());
        Thread t3 = new Thread(ConcurrentSingleton.getInstance());
        System.out.println("All the tags below should match!");
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {}
    }
}
