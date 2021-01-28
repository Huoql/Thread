package com.study.thread3.juc;

import java.util.concurrent.*;

/**
 * 线程池
 * 实际工作中下面三种创建线程池的方法都不用，而应该手动创建线程池
 *
 * 问：在手动创建线程池时指定线程数（maximumPoolSize）是怎么考量的？
 *      需要考虑业务是cpu密集型还是io密集型，假设运行应用的机器cpu核心数是N（Runtime.getRuntime().availableProcessors()获取到），
 *      前者可以给到N+1或N+2，后者可以给到2N试试。
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService pool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            //模拟有10个顾客来银行办理业务，目前池子有5个窗口受理业务办理
            for (int i = 1; i <= 10; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }
    }

    private static void initPool() {
        /*Executors---------------线程池的工具类，类似Collection接口的Collections工具类*/
//        ExecutorService service = Executors.newFixedThreadPool(5); //一池5个受理线程，类似一个银行有5个受理窗口，执行长期任务性能好，线程数固定
//        ExecutorService service = Executors.newSingleThreadExecutor(); //一池1个受理线程，类似一个银行只有1个受理窗口
        /* 一池N个受理线程，类似一个银行有N个受理窗口，适合执行很多短期异步任务，线程池根据需要创建新线程，但在先前创建的线程在可用时仍将重用它们，可伸缩可扩容 */
        ExecutorService service = Executors.newCachedThreadPool();

        try {
            //模拟有10个顾客来银行办理业务，目前池子有5个窗口受理业务办理
            for (int i = 1; i <= 10; i++) {
                try { TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e) { e.printStackTrace(); }
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
//                service.submit(new FutureTask<String>(() -> {
//                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
//                    return "";
//                }));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            service.shutdown();
        }
    }
}
