package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class DJIAQuoteObservable extends Observable{
    private float quote;
    private ReentrantLock lock = new ReentrantLock();

    public DJIAQuoteObservable(float quote) {
        super();
        this.quote = quote;
    }

    public void ChangeQuote(float modifiedQuote) {
        lock.lock();
        try {
            this.quote = modifiedQuote;
        } finally {
            lock.unlock();
        }
        SetChanged();
        NotifyObservers(new DJIAEvent(modifiedQuote));
    }
}
