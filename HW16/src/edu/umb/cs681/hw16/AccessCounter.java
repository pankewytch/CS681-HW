package edu.umb.cs681.hw16;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static AtomicReference<AccessCounter> instance;

    private ConcurrentHashMap<Path, Integer> countTracker;

    private AccessCounter(){
        countTracker = new ConcurrentHashMap<>();
        for(Path path : PathList.pathsArrayList) {
            countTracker.put(path, 0);
        }
    };

    public static AccessCounter getInstance() {
        if (instance == null) instance = new AtomicReference<>(new AccessCounter());
        return instance.get();
    }

    public void increment(Path path) {
        int currentCount = countTracker.get(path);
        countTracker.replace(path, ++currentCount);
    }

    public int getCount(Path path) {
            return countTracker.get(path);
    }

    public void printAllCounts() {
        System.out.println("File Name : Count");
        System.out.println("-----------------------");
        for(Path path : PathList.pathsArrayList) {
            int count = countTracker.get(path);
            System.out.println(path.getFileName() + " : " + count);
        }
    }
}
