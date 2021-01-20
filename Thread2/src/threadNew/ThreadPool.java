package threadNew;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * JDK 5.0 新增的创建线程方式二：使用线程池
 *
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
 * 3.便于线程管理：
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：线程没有任务时最多保持多长时间后终止
 *      ...
 */

class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}

class NumberThread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 !=0) {
                System.out.println(i);
            }
        }
    }
}
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        //设置线程池属性
//        service1.setCorePoolSize(5);
//        service1.setMaximumPoolSize();

        service.execute(new NumberThread()); //适合适用于Runnable
        service.execute(new NumberThread1()); //适合适用于Runnable
//        service.submit();  //适合适用于Callable

        //关闭连接池
        service.shutdown();
    }
}
