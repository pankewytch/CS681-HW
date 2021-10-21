package edu.umb.cs681.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw3.*;

import static org.junit.jupiter.api.Assertions.*;

public class RunnablePrimeGeneratorThreadTest {

    @Test
    public void RunnablePrimeGeneratorWithOneThread() {
        RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 2000000L);
        Thread t1 = new Thread(gen1);
        long start = System.currentTimeMillis();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {}
        long finish = System.currentTimeMillis();
        long duration = finish-start;
        System.out.println("Total time in milliseconds for 1 thread is "+ duration);
        assertEquals(148933, gen1.getPrimes().size());
    }
    @Test
    public void RunnablePrimeGeneratorWithTwoThread() {
        RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 1000000L);
        RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(1000000L, 2000000L);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}
        long finish = System.currentTimeMillis();
        long duration = finish-start;
        System.out.println("Total time in milliseconds for 2 threads is "+ duration);
        int numberOfPrimes = gen1.getPrimes().size()+gen2.getPrimes().size();
        assertEquals(148933, numberOfPrimes);
    }

    @Test
    public void RunnablePrimeGeneratorWithFourThread() {
        RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 500000L);
        RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(500000L, 1000000L);
        RunnablePrimeGenerator gen3 = new RunnablePrimeGenerator(1000000L, 1500000L);
        RunnablePrimeGenerator gen4 = new RunnablePrimeGenerator(1500000L, 2000000L);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        Thread t3 = new Thread(gen3);
        Thread t4 = new Thread(gen4);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {}
        long finish = System.currentTimeMillis();
        long duration = finish-start;
        System.out.println("Total time in milliseconds for 4 threads is "+ duration);
        int numberOfPrimes = gen1.getPrimes().size()+gen2.getPrimes().size()+gen3.getPrimes().size()+gen4.getPrimes().size();
        assertEquals(148933, numberOfPrimes);
    }

    @Test
    public void RunnablePrimeGeneratorWithEightThread() {
        RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 250000L);
        RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(250000L, 500000L);
        RunnablePrimeGenerator gen3 = new RunnablePrimeGenerator(500000L, 750000L);
        RunnablePrimeGenerator gen4 = new RunnablePrimeGenerator(750000L, 1000000L);
        RunnablePrimeGenerator gen5 = new RunnablePrimeGenerator(1000000L, 1250000L);
        RunnablePrimeGenerator gen6 = new RunnablePrimeGenerator(1250000L, 1500000L);
        RunnablePrimeGenerator gen7 = new RunnablePrimeGenerator(1500000L, 1750000L);
        RunnablePrimeGenerator gen8 = new RunnablePrimeGenerator(1750000L, 2000000L);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        Thread t3 = new Thread(gen3);
        Thread t4 = new Thread(gen4);
        Thread t5 = new Thread(gen5);
        Thread t6 = new Thread(gen6);
        Thread t7 = new Thread(gen7);
        Thread t8 = new Thread(gen8);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
        } catch (InterruptedException e) {}
        long finish = System.currentTimeMillis();
        long duration = finish-start;
        System.out.println("Total time in milliseconds for 8 threads is "+ duration);
        int numberOfPrimes = gen1.getPrimes().size()+gen2.getPrimes().size()+gen3.getPrimes().size()+gen4.getPrimes().size()+gen5.getPrimes().size()+gen6.getPrimes().size()+gen7.getPrimes().size()+gen8.getPrimes().size();
        assertEquals(148933, numberOfPrimes);
    }

    @Test
    public void RunnablePrimeGeneratorWithSixteenThread() {
        RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 125000L);
        RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(125000L, 250000L);
        RunnablePrimeGenerator gen3 = new RunnablePrimeGenerator(250000L, 375000L);
        RunnablePrimeGenerator gen4 = new RunnablePrimeGenerator(375000L, 500000L);
        RunnablePrimeGenerator gen5 = new RunnablePrimeGenerator(500000L, 625000L);
        RunnablePrimeGenerator gen6 = new RunnablePrimeGenerator(625000L, 750000L);
        RunnablePrimeGenerator gen7 = new RunnablePrimeGenerator(750000L, 875000L);
        RunnablePrimeGenerator gen8 = new RunnablePrimeGenerator(875000L, 1000000L);
        RunnablePrimeGenerator gen9 = new RunnablePrimeGenerator(1000000L, 1125000L);
        RunnablePrimeGenerator gen10 = new RunnablePrimeGenerator(1125000L, 1250000L);
        RunnablePrimeGenerator gen11 = new RunnablePrimeGenerator(1250000L, 1375000L);
        RunnablePrimeGenerator gen12 = new RunnablePrimeGenerator(1375000L, 1500000L);
        RunnablePrimeGenerator gen13 = new RunnablePrimeGenerator(1500000L, 1625000L);
        RunnablePrimeGenerator gen14 = new RunnablePrimeGenerator(1625000L, 1750000L);
        RunnablePrimeGenerator gen15 = new RunnablePrimeGenerator(1750000L, 1875000L);
        RunnablePrimeGenerator gen16 = new RunnablePrimeGenerator(1875000L, 2000000L);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        Thread t3 = new Thread(gen3);
        Thread t4 = new Thread(gen4);
        Thread t5 = new Thread(gen5);
        Thread t6 = new Thread(gen6);
        Thread t7 = new Thread(gen7);
        Thread t8 = new Thread(gen8);
        Thread t9 = new Thread(gen9);
        Thread t10 = new Thread(gen10);
        Thread t11 = new Thread(gen11);
        Thread t12 = new Thread(gen12);
        Thread t13 = new Thread(gen13);
        Thread t14 = new Thread(gen14);
        Thread t15 = new Thread(gen15);
        Thread t16 = new Thread(gen16);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
            t11.join();
            t12.join();
            t13.join();
            t14.join();
            t15.join();
            t16.join();
        } catch (InterruptedException e) {}
        long finish = System.currentTimeMillis();
        long duration = finish-start;
        System.out.println("Total time in milliseconds for 16 threads is "+ duration);
        int numberOfPrimes = gen1.getPrimes().size()+gen2.getPrimes().size()+gen3.getPrimes().size()+gen4.getPrimes().size()+gen5.getPrimes().size()+gen6.getPrimes().size()+gen7.getPrimes().size()+gen8.getPrimes().size();
        numberOfPrimes += gen9.getPrimes().size()+gen10.getPrimes().size()+gen11.getPrimes().size()+gen12.getPrimes().size()+gen16.getPrimes().size()+gen13.getPrimes().size()+gen14.getPrimes().size()+gen15.getPrimes().size();
        assertEquals(148933, numberOfPrimes);
    }
}
