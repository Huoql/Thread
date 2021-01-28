package com.study.thread1.thread;
/**
 * 线程优先级
 * 线程启动后就纳入到线程调度器中统一管理。线程只能被动的
 * 被分配时间片来并发运行，至于何时获取时间片，以及时间片
 * 分配给哪个线程都是由线程调度器决定的。
 * 线程调度器分配时间片并非完全均匀的“一人一次”顺序分配，
 * 但是整体来看，每个线程获取时间片的概率是一致的。
 * 
 * 修改线程的优先级可以最大程度的干涉线程调度器分配时间片
 * 的概率，理论上:线程优先级越高的线程获取时间片的次数越多
 * @author ta
 *
 */
public class PriorityDemo {
	public static void main(String[] args) {
		Thread max = new Thread() {
			public void run() {
				for(int i=0;i<10000;i++) {
					System.out.println("max");
				}
			}
		};
		Thread min = new Thread() {
			public void run() {
				for(int i=0;i<10000;i++) {
					System.out.println("min");
				}
			}
		};
		Thread norm = new Thread() {
			public void run() {
				for(int i=0;i<10000;i++) {
					System.out.println("nor");
				}
			}
		};
		
		max.setPriority(Thread.MAX_PRIORITY);
		min.setPriority(Thread.MIN_PRIORITY);
		
		min.start();
		norm.start();
		max.start();
	}
}










