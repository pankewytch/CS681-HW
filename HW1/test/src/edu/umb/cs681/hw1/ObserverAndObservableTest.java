package edu.umb.cs681.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw1.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class ObserverAndObservableTest {
    private Observer observer1;
    private Observer observer2;
    private Observer observer3;

    @BeforeEach
    public void CreateObserversForTesting() {
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
    }

    @Test
    public void StockQuoteObservableTest() {
        HashMap<String, Float> tickers = new HashMap<>();
        tickers.put("test1", 0.347f);
        tickers.put("test2", 243.281f);
        tickers.put("test3", 26.2f);
        tickers.put("test4", 0.0034f);
        tickers.put("test5", 1000f);
        StockQuoteObservable cut = new StockQuoteObservable(tickers);
        cut.AddObserver(observer1);
        cut.AddObserver(observer2);
        cut.AddObserver(observer3);
        cut.changeQuote("test1", 1.3f);
        assertFalse(cut.HasChanged());
        assertEquals(3, cut.NumberOfObservers());
    }

    @Test
    public void DJIAQuoteObservableTest() {
        DJIAQuoteObservable cut = new DJIAQuoteObservable(1f);
        cut.AddObserver(observer1);
        cut.AddObserver(observer2);
        cut.AddObserver(observer3);
        cut.ChangeQuote(2f);
        assertFalse(cut.HasChanged());
        assertEquals(3, cut.NumberOfObservers());
    }

    @Test
    public void RemovingObserverTest() {
        DJIAQuoteObservable cut = new DJIAQuoteObservable(1f);
        cut.AddObserver(observer1);
        cut.AddObserver(observer2);
        cut.AddObserver(observer3);
        assertEquals(3, cut.NumberOfObservers());
        cut.DeleteObserver(observer1);
        assertEquals(2, cut.NumberOfObservers());
    }
}
