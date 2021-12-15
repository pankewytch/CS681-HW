package edu.umb.cs681.hw17;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
    private ConcurrentHashMap<String, Float> tickers;

    public StockQuoteObservable(ConcurrentHashMap<String, Float> tickers) {
        super();
        this.tickers = tickers;
    }

    public void changeQuote(String ticker, float quote) {
        StockEvent event = new StockEvent(ticker, quote);
        tickers.replace(ticker, quote);
        SetChanged();
        NotifyObservers(event);
    }

}
