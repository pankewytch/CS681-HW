package edu.umb.cs681.hw1;

public class DJIAQuoteObservable extends Observable{
    private float quote;

    public DJIAQuoteObservable(float quote) {
        super();
        this.quote = quote;
    }

    public void ChangeQuote(float modifiedQuote) {
        this.quote = modifiedQuote;
        SetChanged();
        NotifyObservers(new DJIAEvent(modifiedQuote));
    }
}
