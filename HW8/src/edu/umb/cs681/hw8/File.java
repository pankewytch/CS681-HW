package edu.umb.cs681.hw8;

import java.time.LocalDateTime;

public class File extends FSElement {

    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        parent.appendChildren(this);
    }

    @Override
    public boolean isDirectory() { return false; }

    @Override
    public boolean equals(Object that) {
        lock.lock();
        try {
            if (!(that instanceof FSElement || that instanceof File)) {
                return false;
            } else if (((FSElement) that).isDirectory()) {
                return false;
            } else {
                return this.parent == ((File) that).getParent() && this.size == ((File) that).getSize() &&
                        this.creationTime == ((File) that).getCreationTime() && this.name.equals(((File) that).getName());
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isLink() { return false; }
}
