package com.study.thread4;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * 单例模式在多线程环境下可能存在线程安全问题
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    //加synchronized可以解决单例模式在多线程下的问题，但是太重了
    //DCL Double Check Lock 双端检索机制
    public static SingletonDemo getInstance() {
        //if (instance == null) {
        //    instance = new SingletonDemo();
        //}

        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程没问题
        //System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        //System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        //System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //多线程中
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
