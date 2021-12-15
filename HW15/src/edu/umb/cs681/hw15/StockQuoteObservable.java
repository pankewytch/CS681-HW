package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
    private HashMap<String, Float> tickers;
    private ReentrantLock lock = new ReentrantLock();

    public StockQuoteObservable(HashMap<String, Float> tickers) {
        super();
        this.tickers = tickers;
    }

    public void changeQuote(String ticker, float quote) {
        StockEvent event = new StockEvent(ticker, quote);
        lock.lock();
        try {
            tickers.replace(ticker, quote);
        } finally {
            lock.unlock();
        }
        SetChanged();
        NotifyObservers(event);
    }

}
