package com.study.thread4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int number = 0;

    public void add() {
        number = 60;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 并发编程模型的分类
 * 在并发编程中，我们需要处理两个关键问题：线程之间如何通信及线程之间如何同步（这里的线程是指并发执行的活动实体）。
 * 通信是指线程之间以何种机制来交换信息。在命令式编程中，线程之间的通信机制有两种：共享内存和消息传递。
 *
 * 在共享内存的并发模型里，线程之间共享程序的公共状态，线程之间通过写-读内存中的公共状态来隐式进行通信。
 * 在消息传递的并发模型里，线程之间没有公共状态，线程之间必须通过明确的发送消息来显式进行通信。
 *
 * 同步是指程序用于控制不同线程之间操作发生相对顺序的机制。在共享内存并发模型里，同步是显式进行的。程序员必须显式指定某个方法或某段代码需要在线程之间互斥执行。
 * 在消息传递的并发模型里，由于消息的发送必须在消息的接收之前，因此同步是隐式进行的。
 *
 * Java的并发采用的是共享内存模型，Java线程之间的通信总是隐式进行，整个通信过程对程序员完全透明。如果编写多线程程序的Java程序员不理解隐式进行的线程之间通信的工作机制，很可能会遇到各种奇怪的内存可见性问题。
 *
 * Java内存模型的抽象
 * 在java中，所有实例域、静态域和数组元素（共享变量）存储在堆内存中，堆内存在线程之间共享。
 * 局部变量（Local variables），方法定义参数（java语言规范称之为formal method parameters）和异常处理器参数（exception handler parameters）不会在线程之间共享，它们不会有内存可见性问题，也不受内存模型的影响。
 *
 * Java线程之间的通信由Java内存模型（本文简称为JMM）控制，JMM决定一个线程对共享变量的写入何时对另一个线程可见。
 * 从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读/写共享变量的副本。
 * 本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化之后的一个数据存放位置。
 *
 * JMM三大特征或者说并发编程三大特性
 * 1.可见性
 * 2.原子性
 * 3.有序性
 *
 * 看图：
 * 线程间通信的步骤：
 * 首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。
 * 然后，线程B到主内存中去读取线程A之前已更新过的共享变量。
 * 从整体来看，这两个步骤实质上是线程A在向线程B发送消息，而且这个通信过程必须要经过主内存。JMM通过控制主内存与每个线程的本地内存之间的交互，来为java程序员提供内存可见性保证。
 *
 *
 */

/**
 * volatile是java虚拟机提供的轻量级的同步机制(synchronized是重量级锁)， 它保证可见性，不保证原子性，禁止指令重排(保证有序性)
 * 1.验证volatile保证可见性
 * 2.验证volatile不保证原子性
 */
public class VolatileDemo {

    public static void main(String[] args) {
        //MyData myData = new MyData();
        //for (int i = 1; i <= 20; i++) {
        //    new Thread(() -> {
        //        for (int j = 1; j <= 1000; j++) {
        //            myData.add();
        //            myData.addAtomic();
        //        }
        //    }, String.valueOf(i)).start();
        //}
        //
        //while (Thread.activeCount() > 2) {
        //    Thread.yield();
        //}
        //System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
        //System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.atomicInteger);
        visibleByVolatile();
    }

    private static void visibleByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);
        }, "A").start();

        while (myData.number == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t over");
    }
}
