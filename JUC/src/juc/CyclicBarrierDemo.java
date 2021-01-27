package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * JUC辅助类
 * 回环栅栏
 * 与CountDownLatch区别，一个做减法，一个做加法
 *
 * 举例：所有人到齐了，才能开始开会
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        //CyclicBarrier(int parties)
        //CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println("开始开会");
        });
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t到会议室");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
