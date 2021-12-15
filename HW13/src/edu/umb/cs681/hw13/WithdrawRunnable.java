package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;
    private ThreadSafeBankAccount account;

    public WithdrawRunnable(ThreadSafeBankAccount account) {
        this.account = account;
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
                if (done) {
                    System.out.println("Withdraw runnable has been terminated!");
                    break;
                }
            } finally {
                lock.unlock();
            }
            account.withdraw(100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
