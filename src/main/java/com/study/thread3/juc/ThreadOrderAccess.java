package com.study.thread3.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {

    private int flag = 1; //1:A  2:B  3:C
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            //1.判断
            while (flag != 1) {
                condition1.await();
            }
            //2.干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            flag = 2;
            condition2.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            //1.判断
            while (flag != 2) {
                condition2.await();
            }
            //2.干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            flag = 3;
            condition3.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //1.判断
            while (flag != 3) {
                condition3.await();
            }
            //2.干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            flag = 1;
            condition1.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 多线程之间按顺序调用，实现A-B-C
 * 三个线程启动，要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 重复10轮
 *
 *
 * 1.高内聚低耦合前提下，线程   操作   资源类
 * 2.判断/干活/通知
 * 3.多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 *      流程：A线程增加操作---B线程进行判断进入阻塞状态---A线程又做增加操作也进入阻塞状态---C线程减少操作唤醒线程
 *      如果用if判断，只会判断一次，直接唤醒进行增加操作（系统优化被阻塞的线程先执行），需要用while拉回来重新做判断。
 * 4.lock解决线程安全问题，做线程通信方法为：
 *      Condition condition = lock.newCondition();
 *      condition.await();
 *      condition.signal();
 *      condition.signalAll();
 * 5.标志位
 */
public class ThreadOrderAccess {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.printC();
            }
        }, "C").start();
    }
}
