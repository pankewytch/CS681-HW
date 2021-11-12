package edu.umb.cs681.hw8;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private LinkedList<FSElement> children = new LinkedList<>();
    private LinkedList<Directory> subdirectories = new LinkedList<>();
    private LinkedList<File> files = new LinkedList<>();
    private LinkedList<Link> links = new LinkedList<>();

    public Directory(Directory parent, String name, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
        if (parent != null) {
            parent.appendChildren(this);
        }
    }

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try {
            return children;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            return this.subdirectories;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            return this.files;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Link> getLinks() {
        lock.lock();
        try {
            return this.links;
        } finally {
            lock.unlock();
        }
    }

    public void appendChildren(FSElement child) {
        lock.lock();
        try {
            this.children.add(child);
            if (child.isDirectory()) {
                this.subdirectories.add((Directory) child);
            } else if (child.isLink()) {
                this.links.add((Link) child);
            } else {
                this.files.add((File) child);
            }
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();
        try {
            return this.children.size();
        } finally {
            lock.unlock();
        }
    }

    public int getTotalSize() {
        int totalSize = 0;
        LinkedList<File> localFiles;
        LinkedList<Directory> localSubdirectories;
        lock.lock();
        try {
            localFiles = files;
            localSubdirectories = subdirectories;
        } finally {
            lock.unlock();
        }
        for(File f : localFiles) {
            totalSize += f.getSize();
        }
        for(Directory d : localSubdirectories) {
            totalSize += d.getTotalSize();
        }
        //Do nothing for the links!
        return totalSize;
    }

    /*protected void appendSubDirectory(Directory subdirectory) {
        this.subdirectories.add(subdirectory);
    }*/

    /*protected void appendFile(File file) {
        this.files.add(file);
    }*/

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isLink() { return false; }
}
