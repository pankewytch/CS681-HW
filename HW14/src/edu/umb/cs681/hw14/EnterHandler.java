package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class EnterHandler implements Runnable {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private AdmissionMonitor admissionMonitor;

    public EnterHandler(AdmissionMonitor admissionMonitor) {
        this.admissionMonitor = admissionMonitor;
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                if(done) {
                    System.out.println("Enter handler is done");
                    break;
                }
            } finally {
                lock.unlock();
            }
            admissionMonitor.enter();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
