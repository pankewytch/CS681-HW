package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
    private LinkedList<Observer> observers;
    private AtomicBoolean isChanged;
    private ReentrantLock lock = new ReentrantLock();

    public Observable() {
        observers = new LinkedList<Observer>();
        isChanged = new AtomicBoolean(false);
    }

    public int NumberOfObservers() {
        return observers.size();
    }

    public void AddObserver(Observer o) {
        lock.lock();
        try {
            observers.add(o);
        } finally {
            lock.unlock();
        }
    }
    public void DeleteObserver(Observer o) {
        lock.lock();
        try {
            if (observers.contains(o)) observers.remove(o);
        } finally {
            lock.unlock();
        }
    }
    public AtomicBoolean HasChanged() {
        return isChanged;
    }
    public void NotifyObservers(Object obj) {
        LinkedList<Observer> localObserverList;
        lock.lock();
        try {
            localObserverList = observers;
        } finally {
            lock.unlock();
        }
        if(HasChanged().get()) {
            for (Observer observer : localObserverList) {
                observer.Update(this, obj);
            }
        }
        ClearChanged();
    }

    protected void ClearChanged() {
        isChanged.set(false);
    }

    protected void SetChanged() {
        isChanged.set(true);
    }

}
