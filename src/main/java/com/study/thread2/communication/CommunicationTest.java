package com.study.thread2.communication;

/**
 * 线程通信
 * 使用两个线程打印1-100。线程1和线程2交替打印
 *
 * 涉及的三个方法：
 * wait()：一旦执行此方法，当前线程进入阻塞状态，并释放同步监视器
 * notify()：一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级高的那个。
 * notifyAll()：一旦执行此方法，就会唤醒所有被wait的线程。
 *
 * 说明：
 * 1.三个方法的必须使用在同步方法或同步代码块中。
 * 2.三个方法的调用者必须是同步方法或同步代码块中的同步监视器，否则出现IllegalMonitorStateException异常。
 * 3.三个方法是定义在java.lang.Object类中。
 *
 * 面试题：
 * sleep() 与 wait() 方法的异同？
 * 1.相同：一旦执行方法，都可以使得当前线程进入阻塞状态。
 * 2.不同：1）两个方法声明的位置不同：Thread类中声明sleep(),Object类中声明wait()
 *        2）调用的范围（要求）不同：sleep可以在任何需要的场景下调用，wait()必须使用在同步方法或同步代码块中。
 *        3）关于是否释放同步监视器：如果两个方法都使用在同步方法或同步代码块中，sleep()不会释放锁，wait()会释放锁。
 */
class Number implements Runnable {

    private int number = 1;
    private Object o = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (number <= 100) {

                    this.notify();  //同步监视器必须是同一个

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：" + number);
                    number++;

                    try {
                        //使得调用如下wait()方法的线程进入阻塞状态
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
        }
    }
}
public class CommunicationTest {
    public static void main(String[] args) {
        Number n = new Number();

        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}
