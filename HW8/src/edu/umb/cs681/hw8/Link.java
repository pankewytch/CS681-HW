package edu.umb.cs681.hw8;

import java.time.LocalDateTime;

public class Link extends FSElement {
    private FSElement target;

    public Link(Directory parent, String name, LocalDateTime creationTime, FSElement target) {
        super(parent, name, 0, creationTime);
        parent.appendChildren(this);
        this.target = target;
    }

    public FSElement getTarget() {
        lock.lock();
        try {
        return target;
        } finally {
            lock.unlock();
        }
    }
    public void setTarget(FSElement target) {
        lock.lock();
        try {
            this.target = target;
        } finally {
            lock.unlock();
        }
    }
    @Override
    public boolean isDirectory() { return false; }

    @Override
    public boolean isLink() { return true; }
}
