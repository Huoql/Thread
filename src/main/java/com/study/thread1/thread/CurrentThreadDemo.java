package com.study.thread1.thread;
/**
 * 线程有一个静态的方法:
 * static Thread currentThread()
 * 该方法会获取运行这个方法的线程
 * 
 * API:ThreadLocal中以此来实现相关功能。
 * @author ta
 *
 */
public class CurrentThreadDemo {
	public static void main(String[] args) {
		Thread main = Thread.currentThread();
		System.out.println("运行main方法的线程是:"+main);
		dosome();
		Thread t = new Thread() {
			public void run() {
				Thread t = Thread.currentThread();
				System.out.println("自定义线程:"+t);
				dosome();
			}
		};
		t.start();
	}	
	public static void dosome() {
		Thread t = Thread.currentThread();
		System.out.println("运行dosome方法的线程是:"+t);
	}
}










