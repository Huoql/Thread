package com.study.thread4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS Compare and Swap或者Compare and Set
 * 比较并交换
 */
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 10) + "\t current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 15) + "\t current data:" + atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
