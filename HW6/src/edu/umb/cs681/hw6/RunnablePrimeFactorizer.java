package edu.umb.cs681.hw6;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable{

    public RunnablePrimeFactorizer(long dividend) {
        super(dividend);
    }

    @Override
    public void run() {
        generatePrimeFactors();
    }
}
