package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.Random;

public class Driver {
    private static Observer observer1;
    private static Observer observer2;
    private static Observer observer3;
    private static Random random = new Random();

    public static void main(String[] args) {
        observer1 = (Observable observable, Object object) -> {
            if(object instanceof StockEvent) {
                System.out.println("The stock quote with ticker " + ((StockEvent) object).getTicker()
                        + " now has a value of $" + ((StockEvent) object).getQuote());
            }
            else if (object instanceof DJIAEvent) {
                System.out.println("The new value of the DJIAQuote is $" + ((DJIAEvent) object).getDjia());
            }
        };
        observer2 = (Observable observable, Object object) -> {
            if(object instanceof StockEvent) {
                System.out.println("Observer 2 now has a new stock event! The ticker " + ((StockEvent) object).getTicker()
                        + " now has a value of $" + ((StockEvent) object).getQuote());
            }
            else if (object instanceof DJIAEvent) {
                System.out.println("This observer ignores DJIA Events!");
            }
        };
        observer3 = (Observable observable, Object object) -> {
            if(object instanceof StockEvent) {
                System.out.println("Observer 3 ignores Stock Quote Events!");
            }
            else if (object instanceof DJIAEvent) {
                System.out.println("Observer 3 is recording the new value of the DJIAQuote as $"
                        + ((DJIAEvent) object).getDjia()+ "... thank you!");
            }
        };

        HashMap<String, Float> tickers = new HashMap<>();
        tickers.put("1", 0.347f);
        tickers.put("2", 243.281f);
        tickers.put("3", 26.2f);
        tickers.put("4", 0.0034f);
        tickers.put("0", 1000f);
        StockQuoteObservable stockQuoteObservable = new StockQuoteObservable(tickers);
        stockQuoteObservable.AddObserver(observer1);
        stockQuoteObservable.AddObserver(observer2);
        stockQuoteObservable.AddObserver(observer3);

        DJIAQuoteObservable djiaQuoteObservable = new DJIAQuoteObservable(1f);
        djiaQuoteObservable.AddObserver(observer1);
        djiaQuoteObservable.AddObserver(observer2);
        djiaQuoteObservable.AddObserver(observer3);

        for(int i = 0; i < 20; i++) {
            String randomTicker = ((Integer) random.nextInt(5)).toString();
            Thread thread = new Thread(
                    () -> {
                        stockQuoteObservable.changeQuote(randomTicker, random.nextFloat());
                        djiaQuoteObservable.ChangeQuote(random.nextFloat());
                    }
            );
            thread.start();
        }
    }
}
