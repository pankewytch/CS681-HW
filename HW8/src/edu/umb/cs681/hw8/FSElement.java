package edu.umb.cs681.hw8;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    protected Directory parent;
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected ReentrantLock lock = new ReentrantLock();

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        this.name = name;
        this.parent = parent;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent() {
        lock.lock();
        try {
            return this.parent;
        } finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
            return this.size;
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try {
            return this.name;
        } finally {
            lock.unlock();
        }
    }

    public LocalDateTime getCreationTime() {
        lock.lock();
        try {
            return this.creationTime;
        } finally {
            lock.unlock();
        }
    }

    public abstract boolean isDirectory();

    public abstract boolean isLink();
}

