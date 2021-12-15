package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionMonitor {
    private int currentVisitors = 0;
    private ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock staticLock = new ReentrantLock();
    private Condition tooManyVisitorsCondition = lock.newCondition();
    private Condition tooFewVisitorsCondition = lock.newCondition();
    private static AdmissionMonitor instance = null;

    private AdmissionMonitor(){}

    public static AdmissionMonitor getInstance() {
        staticLock.lock();
        try {
            if(instance == null) instance = new AdmissionMonitor();
            return instance;
        } finally {
            staticLock.unlock();
        }
    }

    public void enter() {
        lock.lock();
        try {
            while(currentVisitors >= 10) {
                System.out.println("Too many visitors, please wait...");
                tooManyVisitorsCondition.await();
            }
            currentVisitors++;
            tooFewVisitorsCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void exit() {
        lock.lock();
        try {
            while(currentVisitors <= 10) {
                System.out.println("Too few visitors, a very odd occurrence...");
                tooFewVisitorsCondition.await();
            }
            currentVisitors--;
            tooManyVisitorsCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void vistorCount() {
        lock.lock();
        try {
            System.out.println("Current visotr count is: " + currentVisitors);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EnterHandler d1 = new EnterHandler(AdmissionMonitor.getInstance());
        ExitHandler w1 = new ExitHandler(AdmissionMonitor.getInstance());
        Thread td1 = new Thread(d1);
        Thread tw1 = new Thread(w1);
        EnterHandler d2 = new EnterHandler(AdmissionMonitor.getInstance());
        ExitHandler w2 = new ExitHandler(AdmissionMonitor.getInstance());
        Thread td2 = new Thread(d2);
        Thread tw2 = new Thread(w2);
        EnterHandler d3 = new EnterHandler(AdmissionMonitor.getInstance());
        ExitHandler w3 = new ExitHandler(AdmissionMonitor.getInstance());
        Thread td3 = new Thread(d3);
        Thread tw3 = new Thread(w3);

        td1.start();
        tw1.start();
        td2.start();
        tw2.start();
        td3.start();
        tw3.start();

        d1.setDone();
        w1.setDone();
        d2.setDone();
        w2.setDone();
        d3.setDone();
        w3.setDone();

        td1.interrupt();
        tw1.interrupt();
        td2.interrupt();
        tw2.interrupt();
        td3.interrupt();
        tw3.interrupt();

        try {
            td1.join();
            tw1.join();
            td2.join();
            tw2.join();
            td3.join();
            tw3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AdmissionMonitor.getInstance().vistorCount();
    }
}
