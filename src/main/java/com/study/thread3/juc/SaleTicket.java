package com.study.thread3.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {//资源类
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {

        lock.lock();

        try {
            if(number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票，还剩下" + number + "张票");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

//class Ticket {//资源类
//    private int number = 30;
//
//    public synchronized void sale() {
//        if(number > 0) {
//            System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票，还剩下" + number + "张票");
//        }else {
//            System.out.println("票卖完了");
//        }
//    }
//}

/*class Ticket implements Runnable {

    @Override
    public void run() {

    }
}*/

/**
 * 卖票问题
 * 三个售票员    卖出    30张票
 *
 * 多线程编程的企业级套路+模板
 *
 * 1.高内聚低耦合的前提下， 线程
 *                        操作（对外暴露的调用方法）
 *                        资源类
 */
public class SaleTicket {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.sale();}, "售票员A").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.sale();}, "售票员B").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.sale();}, "售票员C").start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "售票员A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "售票员B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "售票员C").start();*/
    }
}
