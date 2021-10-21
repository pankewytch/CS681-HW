package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.HashMap;

public class StockQuoteObservable extends Observable {
    private HashMap<String, Float> tickers;

    public StockQuoteObservable(HashMap<String, Float> tickers) {
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
