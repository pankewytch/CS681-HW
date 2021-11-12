package edu.umb.cs681.hw8;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem implements Runnable{
    private static FileSystem fsInstance = null;
    private LinkedList<Directory> rootDirs = new LinkedList<>();
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (fsInstance == null) {
                fsInstance = new FileSystem();
            }
            return fsInstance;
        } finally {
            lock.unlock();
        }
    }

    public void appendRootDir(Directory root) {
        lock.lock();
        try {
            rootDirs.add(root);
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs() {
        lock.lock();
        try {
            return rootDirs;
        } finally {
            lock.unlock();
        }
    }

    public static void resetFileSystem() {
        lock.lock();
        try {
            fsInstance = null;
        } finally {
            lock.unlock();
        }
    }

    private static void printNames(Directory root) {
        for(File file:root.getFiles()) {
            System.out.println(file.getName());
        }
        for(Link link:root.getLinks()) {
            System.out.println(link.getName());
        }
        for(Directory directory:root.getSubDirectories()) {
            System.out.println(directory.getName());
            printNames(directory);
        }
    }

    @Override
    public void run() {
       FileSystem fileSystem = FileSystem.getFileSystem();
       LinkedList<Directory> roots = fileSystem.getRootDirs();
       for(Directory directory : roots)
           printNames(directory);
       fileSystem.resetFileSystem();
    }

    public static void main(String[] args) {
        FSElement root = new Directory(null, "root", LocalDateTime.now());
        FSElement home = new Directory((Directory) root, "home", LocalDateTime.now());
        FSElement applications = new Directory((Directory) root, "applications", LocalDateTime.now());
        FSElement code = new Directory((Directory) home, "code", LocalDateTime.now());
        FSElement pics = new Directory((Directory) home, "pics", LocalDateTime.now());
        FSElement a = new File((Directory) applications, "a", 10, LocalDateTime.now());
        FSElement b = new File((Directory) home, "b", 20, LocalDateTime.now());
        FSElement c = new File((Directory) code, "c", 30, LocalDateTime.now());
        FSElement d = new File((Directory) code, "d", 40, LocalDateTime.now());
        FSElement e = new File((Directory) pics, "e", 50, LocalDateTime.now());
        FSElement f = new File((Directory) pics, "f", 60, LocalDateTime.now());
        FSElement x = new Link((Directory) home, "x", LocalDateTime.now(), applications);
        FSElement y = new Link((Directory) code, "y", LocalDateTime.now(), a);
        FileSystem fileSystem = getFileSystem();
        fileSystem.appendRootDir((Directory) root);
        FileSystem.printNames((Directory) root);
        Thread t1 = new Thread(fileSystem);
        Thread t2 = new Thread(fileSystem);
        Thread t3 = new Thread(fileSystem);
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {}
    }
}

