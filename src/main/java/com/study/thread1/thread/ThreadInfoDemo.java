package com.study.thread1.thread;
/**
 * 获取线程相关信息的方法
 * @author ta
 *
 */
public class ThreadInfoDemo {
	public static void main(String[] args) {
		Thread main = Thread.currentThread();
		//获取线程的唯一标识
		long id = main.getId();
		System.out.println("id:"+id);
		
		//获取名字
		String name = main.getName();
		System.out.println("name:"+name);
		
		//获取优先级
		int priority = main.getPriority();
		System.out.println("优先级:"+priority);
		
		
		boolean isAlive = main.isAlive();
		System.out.println("是否处于活动状态:"+isAlive);
		boolean isDaemon = main.isDaemon();
		System.out.println("是否为守护线程:"+isDaemon);
		boolean isInter = main.isInterrupted();
		System.out.println("是否被中断:"+isInter);
		
	}
}





