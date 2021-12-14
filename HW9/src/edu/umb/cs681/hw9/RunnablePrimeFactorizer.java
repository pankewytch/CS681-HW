package edu.umb.cs681.hw9;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable{

    public RunnablePrimeFactorizer(long dividend) {
        super(dividend);
    }

    @Override
    public void run() {
        generatePrimeFactors();
    }
}
