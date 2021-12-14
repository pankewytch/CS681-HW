package edu.umb.cs681.hw9;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {

    private boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend) {
        super(dividend);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            lock.lock();
            if(done) {
                System.out.println("Stopped generating prime factors.");
                this.factors.clear();
                break;
            }
            try {
                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                } else {
                    if (divisor == 2) {
                        divisor++;
                    } else {
                        divisor += 2;
                    }
                }
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

    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(504000007000000566l);
        Thread thread = new Thread(gen);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.setDone();
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + gen.getPrimeFactors().size() + " prime factors are found.");
    }
}
