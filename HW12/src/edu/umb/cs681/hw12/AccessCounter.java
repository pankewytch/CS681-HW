package edu.umb.cs681.hw12;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance;

    private boolean done = false;
    private ReentrantLock instanceLock = new ReentrantLock();
    private HashMap<java.nio.file.Path, Integer> countTracker;

    private AccessCounter(){
        countTracker = new HashMap<>();
        for(Path path : PathList.pathsArrayList) {
            countTracker.put(path, 0);
        }
    };

    public static AccessCounter getInstance() {
        staticLock.lock();
        try {
            if (instance == null) instance = new AccessCounter();
            return instance;
        } finally {
            staticLock.unlock();
        }
    }

    public void increment(Path path) {
        instanceLock.lock();
        try {
            int currentCount = countTracker.get(path);
            countTracker.replace(path, ++currentCount);
        } finally {
            instanceLock.unlock();
        }
    }

    public int getCount(Path path) {
        instanceLock.lock();
        try {
            return countTracker.get(path);
        } finally {
            instanceLock.unlock();
        }
    }

    public void printAllCounts() {
        System.out.println("File Name : Count");
        System.out.println("-----------------------");
        for(Path path : PathList.pathsArrayList) {
            instanceLock.lock();
            try {
                int count = countTracker.get(path);
                System.out.println(path.getFileName() + " : " + count);
            } finally {
                instanceLock.unlock();
            }
        }
    }
}
