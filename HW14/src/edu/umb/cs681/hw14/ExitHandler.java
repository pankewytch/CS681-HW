package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class ExitHandler implements Runnable {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private AdmissionMonitor admissionMonitor;

    public ExitHandler(AdmissionMonitor admissionMonitor) {
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
                    System.out.println("Exit handler is done");
                    break;
                }
            } finally {
                lock.unlock();
            }
            admissionMonitor.exit();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
