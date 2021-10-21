package edu.umb.cs681.hw1;

import java.util.LinkedList;

public abstract class Observable {
    private LinkedList<Observer> observers;
    private boolean isChanged;

    public Observable() {
        observers = new LinkedList<Observer>();
        isChanged = false;
    }

    public int NumberOfObservers() {
        return observers.size();
    }

    public void AddObserver(Observer o) {
        observers.add(o);
    }
    public void DeleteObserver(Observer o) {
        if(observers.contains(o)) observers.remove(o);
    }
    public boolean HasChanged() {
        return isChanged;
    }
    public void NotifyObservers(Object obj) {
        if(HasChanged()) {
            for (Observer observer : observers) {
                observer.Update(this, obj);
            }
        }
        ClearChanged();
    }

    protected void ClearChanged() {
        isChanged = false;
    }

    protected void SetChanged() {
        isChanged = true;
    }

}
