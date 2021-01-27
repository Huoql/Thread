package juc;

import java.util.concurrent.CountDownLatch;

/**
 * JUC辅助类
 *倒计时
 *
 * CountDownLatch主要有两个方法，当一个或多个线程调用countDownLatch.await()方法时，这些线程会阻塞。
 * 其他线程调用countDownLatch.countDown()方法会将计数器减1，这些线程不会阻塞。
 * 当计数器变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 *
 * 举例：所有学生离开教室，才可以关门走人
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
                //计数器减1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //main主线程会被阻塞
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t关门走人");
    }

    private static void closeDoor() {
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "\t关门走人");
    }
}
