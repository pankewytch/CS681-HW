package edu.umb.cs681.hw16;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableRequestHandler implements Runnable {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private Random random = new Random();
    private AccessCounter accessCounter = AccessCounter.getInstance();

    public RunnableRequestHandler(){}

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    private void HandleRequest() {
        while(true) {
            lock.lock();
            try {
                if(done) {
                    System.out.println("Thread is done as request counter");
                    break;
                }
            } finally {
                lock.unlock();
            }
            lock.lock();
            try {
                int randomNumber = random.nextInt(5);
                Path path = PathList.pathsArrayList.get(randomNumber);
                accessCounter.increment(path);
                accessCounter.getCount(path);
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void run() {
        HandleRequest();
    }

    public static void main(String[] Args) {
        HashMap<Thread, RunnableRequestHandler> threads = new HashMap<>();
        for(int i = 0; i < 5; i++) {
            RunnableRequestHandler handler = new RunnableRequestHandler();
            Thread thread = new Thread(handler);
            thread.start();
            threads.put(thread, handler);
        }
        for(Thread thread : threads.keySet()) {
            RunnableRequestHandler handler = threads.get(thread);
            try {
                thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.setDone();
            thread.interrupt();
        }
        for(Thread thread : threads.keySet()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        AccessCounter accessCounter = AccessCounter.getInstance();
        accessCounter.printAllCounts();
    }
}
