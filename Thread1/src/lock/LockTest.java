package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁 ----- JDK5.0新增
 *
 * 面试题：synchronized 与 lock 的异同？
 * 相同：二者都可以解决线程安全问题。
 * 不同：synchronized机制在执行完相应的同步代码以后，自动释放锁
 *      Lock需要手动启动和手动结束同步
 *
 */
class Window implements Runnable {

    private int ticket = 100;

    /**
     * new ReentrantLock(true);  创建公平锁  即在竞争环境下，先到临界区的线程比后到的线程一定更快的获取到锁
     * new ReentrantLock(false); 创建非公平锁 先到临界区的线程未必比后到的线程更快的获取到锁
     */
    //1.实例化一个ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true) {
            try {
                //2.调用锁定方法lock()
                lock.lock();

                if (ticket > 0) {

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：售票，票号为" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }finally {
                //3.调用解锁方法unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Runnable r = new Window();

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
