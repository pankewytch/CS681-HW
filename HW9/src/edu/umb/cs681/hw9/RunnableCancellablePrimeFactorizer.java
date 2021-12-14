package edu.umb.cs681.hw9;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer(long dividend) {
        super(dividend);
    }

    public void setDone(){
        lock.lock();
        try {
            done = true;
        }
        finally {
            lock.unlock();
        }
    }

    //Created a local dividend to avoid any race conditions
    public void generatePrimeFactors() {
        long divisor = 2;
        long localDividend = 0;
        lock.lock();
        try{
            localDividend = dividend;
        }
        finally {
            lock.unlock();
        }
        while( localDividend != 1 && divisor <= to ){
            lock.lock();
            try {
                if(done){
                    System.out.println("Stopped generating prime factors.");
                    this.factors.clear();
                    break;
                }
                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                    localDividend = dividend;
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
        }
    }

//    public static void main(String[] args) {
//        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(504000007000000566l);
//        Thread thread = new Thread(gen);
//        thread.start();
//        gen.setDone();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        gen.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
//        System.out.println("\n" + gen.getPrimeFactors().size() + " prime factors are found.");
//    }
}
