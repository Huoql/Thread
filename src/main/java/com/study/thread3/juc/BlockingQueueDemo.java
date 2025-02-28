package com.study.thread3.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列BlockingQueue
 * Queue的子接口，有7个实现类
 * 主要方法：
 *               抛出异常       特殊值         阻塞          超时
 *          插入：add(e)        offer(e)       put(e)       offer(e,time,unit)
 *          移除：remove()      poll()         take()       poll(time,unit)
 *          检查：element()     peek()         不可用        不可用
 */

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        /**
         *抛出异常
         * 当阻塞队列满时，再执行add插入元素会抛IllegalStateException:Queue fill
         * 当阻塞队列空时，再执行remove移除元素会抛NoSuchElementException
         */
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//
////        System.out.println(blockingQueue.add("d"));
//
//        System.out.println(blockingQueue.element());
//
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//
////        System.out.println(blockingQueue.remove());


        /**
         * 特殊值
         * 插入方法，成功true失败false
         * 移除方法，成功则返回移出队列的元素，队列里没有就返回null
         */
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

//        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.poll());
        */

        /**
         * 阻塞
         * 当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产者线程直到put数据or响应中断退出
         * 当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用
         */
        /*blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
//        blockingQueue.put("a");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        */

        /**
         * 当队列满时，队列会阻塞生产者线程一段时间，超过限时后生产者会退出
         */
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a", 3L, TimeUnit.SECONDS));
    }
}
