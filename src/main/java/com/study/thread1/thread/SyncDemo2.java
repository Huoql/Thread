package com.study.thread1.thread;
/**
 *
 * 同步块:
 * synchronized(同步监视器对象){
 * 	需要同步运行的代码片段
 * }
 *
 *
 * 说明：1.操作共享数据的代码，即为需要被同步的代码。
 *      2.共享数据：多个线程共同操作的变量。
 *      3.同步监视器，也就是“锁”。任何一个类的对象，都可以充当锁。
 *          要求：多个线程必须要共用同一把锁
 *
 *
 * 同步块可以更准确的控制需要同步运行的代码片段，有效的
 * 缩小同步范围可以在保证并发安全的前提下尽可能提高并发
 * 的效率
 *
 * 同步的方式解决了线程安全的问题，但是操作同步代码时，只能一个线程参与，
 * 其他线程等待，相当于一个线程的过程，效率低。
 *
 * @author ta
 *
 */
public class SyncDemo2 {
    public static void main(String[] args) {
        final Shop shop = new Shop();
        Thread t1 = new Thread() {
            public void run() {
                shop.buy();
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                shop.buy();
            }
        };

        t1.start();
        t2.start();
    }
}

class Shop{
    /**
     * 成员方法上若直接使用synchronized修饰，那么同步监视
     * 器对象无需指定，使用的就是该方法所属对象，即:方法中
     * 看到的this
     */
//	public synchronized void buy() {
    public void buy() {
        Thread t = Thread.currentThread();
        try {
            System.out.println(t.getName()+":正在选衣服...");
            Thread.sleep(5000);

            //试衣服这个环节模拟会出现并发安全问题的环节
            /*
             * 多个线程要想在同步块这里同步执行代码
             * 必须保证这里指定的同步监视器对象(上锁的对象)
             * 是【同一个】
             */
            synchronized (this) {//此时的this：唯一的Shop的对象
                System.out.println(t.getName()+":正在试衣服...");
                Thread.sleep(5000);
            }

            System.out.println(t.getName()+":结账离开");

        } catch (Exception e) {

        }

    }
}










