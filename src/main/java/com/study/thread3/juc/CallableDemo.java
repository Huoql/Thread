package com.study.thread3.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 多线程中，第3种创建多线程的方式
 *
 * Callable 和 Runnable 的区别？
 * 1.有无返回值
 * 2.是否抛异常
 * 3.需要重写的方法不一样，一个run()，一个call()
 *
 *
 * futureTask.get()一般放在最后一行
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 方法论，整体的架构思想
         * 1.new Thread(Runnable target, String name)  默认构造方法
         * 2.new Thread(RunnableFuture target, String name)  传入子接口
         * 3.new Thread(FutureTask target, String name)  传入RunnableFuture的实现类，此类同时实现了Runnable接口
         * 4.new FutureTask(Callable<V> callable)  此实现类的构造方法-----------------穿连起来
         */
        MyThread2 myThread2 = new MyThread2();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(myThread2);

        //A,B两个线程，但是调用的是同一个对象，第一个线程执行完毕，结果会复用
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        System.out.println(Thread.currentThread().getName() + "计算完成");
        System.out.println(futureTask.get());
    }
}

class MyThread implements Runnable {

    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("*****call*****");
        try { TimeUnit.SECONDS.sleep(4); }catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}
