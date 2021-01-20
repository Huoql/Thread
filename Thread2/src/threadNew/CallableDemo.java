package threadNew;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * JDK 5.0 新增的创建线程方式一：实现Callable接口
 *
 * 1.创建一个实现Callable的实现类
 * 2.实现call()方法，将此线程需要执行的操作声明在call()中
 * 3.创建Callable接口实现类的对象
 * 4.将3的对象作为参数递到FutureTask构造器中，创建FutureTask对象
 * 5.将FutureTask对象为参数递到Thread类构造器中，创建Thread类的对象，并调用start()
 * 6.如有需要，可以使用FutureTask对象调用get()方法获取重写的call()的返回值
 *
 *
 * 实现Callable接口比实现Runnable接口创建多线程方式更强大
 * 1.call()可以有返回值
 * 2.call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3.Callable是支持泛型的
 */
class NumThread implements Callable<Integer> {

    /**
     * 遍历100以内偶数计算总和并返回
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if(i % 2 ==0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(numThread);

        Thread t = new Thread(futureTask);
        t.start();

        try {
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()方法的返回值。
            System.out.println("总和：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
