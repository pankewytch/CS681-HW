package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {
    private ConcurrentSingleton(){}
    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<>();

    public static AtomicReference<ConcurrentSingleton> getInstance() {
        instance.compareAndSet(null, new ConcurrentSingleton());
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            new Thread(
                    () -> {System.out.println(ConcurrentSingleton.getInstance().get());}
            ).start();
        }
    }
}
