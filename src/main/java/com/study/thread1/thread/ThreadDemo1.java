package com.study.thread1.thread;
/**
 * 多线程
 * 多线程得以让我们执行代码的方式从原有的从上到下一句一句
 * 有序执行改为了多段代码"同时"运行的方式。
 * 即:从串联执行改为了并发执行
 *
 * 线程有两种创建方式
 * 方式一:
 * 定义一个类，继承Thread然后重写其run()方法，并在其中
 * 定义该线程要执行的代码。
 *
 * 1.定义一个类继承Thread
 * 2.重写run()方法
 * 3.创建Thread类的子类对象
 * 4.通过此对象调用start()
 *
 * @author ta
 *
 */

/**
 * 这种创建线程的方式存在两个设计缺点：
 * 1:由于java是单继承的，这导致我们若继承了Thread就不能
 *   再继承其他类来复用方法了，这在实际开发中非常不便。
 *
 * 2:我们重写run方法定义线程任务，这导致将任务直接定义在
 *   线程当中，使得线程与任务存在一个必然的耦合关系，不利
 *   于线程的重用。
 * @author ta
 *
 */
class MyThread1 extends Thread{
	//1.定义一个类继承Thread
	public void run() {  //2.重写run方法
		for(int i=0;i<1000;i++) {
			System.out.println(Thread.currentThread().getName() + "-----Thread1");
		}
	}
}

class MyThread2 extends Thread{
	public void run() {
		for(int i=0;i<1000;i++) {
			System.out.println(Thread.currentThread().getName() + "-----Thread2");
		}
	}
}

public class ThreadDemo1 {
	public static void main(String[] args) {
		//3.创建Thread类的子类对象
		Thread t1 = new MyThread1();
		Thread t2 = new MyThread2();

		//4.通过此对象调用start()。 1）启动当前线程 2）调用当前线程的run()方法
		t1.start();
		t2.start();

		/*
		 * 注意，启动线程要调用start方法，而不是直接
		 * 调用线程的run()方法!
		 * 已经启动的线程不可以再调用start()方法，会抛非法线程状态异常。
		 */

		//如下方法仍然是在main线程中执行的
		for(int i=0;i<1000;i++) {
			System.out.println(Thread.currentThread().getName() + "-----main");
		}
	}
}









