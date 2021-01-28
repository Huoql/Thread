package com.study.thread3.juc;

import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendEmail() {
        //暂停一会儿线程
        try { TimeUnit.SECONDS.sleep(4); }catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("*****sendEmail*****");
    }

    public synchronized void sendMessage() {
        System.out.println("*****sendMessage*****");
    }

    public void hello() {
        System.out.println("*****hello*****");
    }

}

/**
 * 多线程8锁
 * 1.标准访问，请问先打印sendEmail还是先打印sendMessage？
 * 2.邮件方法暂停4秒，请问先打印sendEmail还是先打印sendMessage？
 * 3.新增一个普通方法hello()，请问先打印sendEmail还是先打印hello？
 * 4.两部手机，问先打印sendEmail还是先打印sendMessage？
 * 5.同一部手机，两个静态同步方法，请问先打印sendEmail还是先打印sendMessage？
 * 6.两部手机，两个静态同步方法，请问先打印sendEmail还是先打印sendMessage？
 * 7.一部手机，一个静态同步方法，一个普通同步方法，请问先打印sendEmail还是先打印sendMessage？
 * 8.两部手机，一个静态同步方法，一个普通同步方法，请问先打印sendEmail还是先打印sendMessage？
 *
 *
 * 1,2
 * 一个类里面如果有多个synchronized方法，某一时刻内，只要有一个线程去调用其中的一个synchronized方法了，
 * 其他线程只能等待。
 * （换句话说，某一时刻内，只能有唯一一个线程去访问这些synchronized方法，
 * 锁的是当前对象this，被锁定后，其他线程都不能进入到当前对象的其他synchronized方法。）
 *
 * 3
 * 加个普通的hello()方法后发现和同步锁无关。
 * 4
 * 换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 * 5,6
 * 都换成静态同步方法后，情况又变化。
 * 与几个实例对象无关，因为锁是当前类的class对象
 *
 * 7,8
 * 一个锁的是对象，一个锁的是类
 *
 * 所有静态同步方法锁是当前类的class对象。
 * 所有非静态同步方法用的都是同一把锁-----实例对象本身。
 *
 * synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 * 具体表现为以下3中形式：
 * 对于普通同步方法，锁是当前实例对象。
 * 对于静态同步方法，锁是当前类的class对象
 * 对于同步方法块，锁是synchronized括号里配置的对象
 */
public class Lock8 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
//            phone.sendMessage();
//            phone.hello();
            phone2.sendMessage();
        }, "B").start();
    }
}
