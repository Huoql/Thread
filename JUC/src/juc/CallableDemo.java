package juc;

import java.util.concurrent.Callable;

/**
 * 多线程中，第3种创建多线程的方式
 *
 * Callable 和 Runnable 的区别？
 * 1.有无返回值
 * 2.是否抛异常
 * 3.需要重写的方法不一样，一个run()，一个call()
 */
public class CallableDemo {

    public static void main(String[] args) {

    }
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
