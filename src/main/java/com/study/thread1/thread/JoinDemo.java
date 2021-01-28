package com.study.thread1.thread;
/**
 * void join()
 * join方法是用来协调线程之间同步运行的。
 * 同步运行:执行有先后顺序
 * 异步运行:各干各的
 * 
 * 
 * join方法允许调用该方法的线程等待该方法所属线程，直到
 * 方法所属线程运行完毕后才使调用方法的线程继续后续工作。
 * 等待的过程就是进入了阻塞状态。
 *
 * 即在线程a中调用线程b的join()方法，线程a进入阻塞状态，直到线程b执行完毕，
 * 线程a结束阻塞状态，等待cpu分配资源继续执行。
 * 
 * @author ta
 *
 */
public class JoinDemo {
	//表示图片是否下载完毕
	private static boolean isFinish = false;
	
	public static void main(String[] args) {
		/*
		 * JDK8之前，有一个强制要求:
		 * 当一个方法的局部内部类中引用了这个方法的其他
		 * 局部变量时，该变量必须声明为final的
		 * 这实际上是因为JVM内存分配问题导致的。
		 */
		final Thread download = new Thread() {
			public void run() {
				System.out.println("down:开始下载图片");
				for(int i=1;i<=100;i++) {
					System.out.println("down:"+i+"%");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}					
				}
				System.out.println("down:图片下载完毕!");
				isFinish = true;
			}
		};
		
		Thread show = new Thread() {
			public void run() {
				try {
					System.out.println("show:开始显示文字");			
					Thread.sleep(1500);				
					System.out.println("show:文字显示完毕");
				
					//显示完文字后在这里等待下载线程将图片下完
					download.join();
					
					
					System.out.println("show:开始显示图片");
					if(!isFinish) {
						throw new RuntimeException("图片加载失败!");
					}
					System.out.println("show:图片显示完毕");
				
				} catch (InterruptedException e) {
				}
			}
		};
		
		
		
		download.start();
		show.start();
	}
	
}








