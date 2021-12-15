package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount implements BankAccount{
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void deposit(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (d): current balance: " + balance);
            while(balance >= 300){
                System.out.println(Thread.currentThread().getId() +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().getId() +
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void withdraw(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().getId() +
                    " (w): current balance: " + balance);
            while(balance <= 0){
                System.out.println(Thread.currentThread().getId() +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getId() +
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void printBalance() {
        lock.lock();
        try {
            System.out.println("The final balance is: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();

        DepositRunnable d1 = new DepositRunnable(bankAccount);
        WithdrawRunnable w1 = new WithdrawRunnable(bankAccount);
        Thread td1 = new Thread(d1);
        Thread tw1 = new Thread(w1);
        DepositRunnable d2 = new DepositRunnable(bankAccount);
        WithdrawRunnable w2 = new WithdrawRunnable(bankAccount);
        Thread td2 = new Thread(d2);
        Thread tw2 = new Thread(w2);
        DepositRunnable d3 = new DepositRunnable(bankAccount);
        WithdrawRunnable w3 = new WithdrawRunnable(bankAccount);
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

        bankAccount.printBalance();
    }
}
