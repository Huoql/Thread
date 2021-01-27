package juc;

import java.util.concurrent.*;

/**
 * 线程池
 *
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
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
