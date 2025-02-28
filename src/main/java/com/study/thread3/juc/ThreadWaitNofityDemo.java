package com.study.thread3.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Number {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //public void increment() throws InterruptedException {
    //    lock.lock();
    //    try {
    //        //1.判断
    //        while (number != 0) {
    //            condition.await();
    //        }
    //        //2.干活
    //        number++;
    //        System.out.println(Thread.currentThread().getName() + ":" + number);
    //        //3.通知
    //        condition.signalAll();
    //    }catch (Exception e) {
    //        e.printStackTrace();
    //    }finally {
    //        lock.unlock();
    //    }
    //}
    //
    //public void decrement() throws InterruptedException {
    //    lock.lock();
    //    try {
    //        //1.判断
    //        while (number == 0) {
    //            condition.await();
    //        }
    //        //2.干活
    //        number--;
    //        System.out.println(Thread.currentThread().getName() + ":" + number);
    //        //3.通知
    //        condition.signalAll();
    //    }catch (Exception e) {
    //        e.printStackTrace();
    //    }finally {
    //        lock.unlock();
    //    }
    //
    //}
    public synchronized void increment() throws InterruptedException {
        //1.判断
        if(number != 0) {
            wait();
        }
//        while (number != 0) {
//            wait();
//        }
        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName() + ":" + number);
        //3.通知
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //1.判断
        if(number == 0) {
            wait();
        }
        //while (number == 0) {
        //    wait();
        //}
        //2.干活
        number--;
        System.out.println(Thread.currentThread().getName() + ":" + number);
        //3.通知
        notifyAll();
    }
}
/**
 * 两个线程，可以操作初始值为0的一个变量，
 * 实现一个线程加1，一个线程减1，
 * 实现交替10轮。
 *
 * 1.高内聚低耦合前提下，线程   操作   资源类
 * 2.判断/干活/通知
 * 3.多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 * 流程：A线程增加操作---B线程进行判断进入阻塞状态---A线程又做增加操作也进入阻塞状态---C线程减少操作唤醒线程
 * 如果用if判断，只会判断一次，直接唤醒进行增加操作（系统优化被阻塞的线程先执行），需要用while拉回来重新做判断。
 * 4.lock解决线程安全问题，做线程通信方法为：
 *      Condition condition = lock.newCondition();
 *      condition.await();
 *      condition.signal();
 *      condition.signalAll();
 */
public class ThreadWaitNofityDemo {
    public static void main(String[] args) {
        Number number = new Number();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    number.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    number.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        //
        //多加两个线程看输出结果，问题：虚假唤醒
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    number.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    number.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
