package com.study.thread3.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//    private Lock lock = new ReentrantLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入数据" + key);
            try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
//            lock.unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取数据");
            try { TimeUnit.MILLISECONDS.sleep(300); }catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成" + result);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
//            lock.unlock();
        }
    }
}
/**
 * 读锁：共享锁，两个线程读同一条数据，线程1在读操作的同时做写操作需等线程2读操作完成，线程2在读操作的同时做写操作需等线程1读操作完成，发生死锁
 * 写锁：独占锁，两个线程写(修改)不同数据，线程1在写操作的同时对线程2写操作的数据进行操作需等线程2写操作完成，线程2在写操作的同时对线程1写操作的数据进行操作需等线程1写操作完成，发生死锁
 * 两种锁都可能发生死锁。
 *
 *      多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是如果有一个线程想去写共享资源类，就不应该再有其他线程可以对该资源进行读或写。
 * 即：
 *      读读可共存，读写不可共存，写写不可共存
 *
 * 缺点：1.造成锁饥饿，一直读没有写操作
 *      2.读写不共存，某线程获取读锁后可以再次获取读锁，但不能再次获取写锁，而获取写锁后可以再次获取写锁和读锁(可重入)
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        long l1 = System.currentTimeMillis();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
    }
}
