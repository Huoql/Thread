package com.study.thread1.thread;

import java.util.Scanner;

/**
 * 线程提供了一个静态方法:
 * static void sleep(long ms)
 * 该方法可以让运行该方法的线程处于阻塞状态指定毫秒，超时
 * 后线程会自动回到RUNNABLE状态等待再次并发运行。
 * @author ta
 *
 */
public class SleepDemo {
	public static void main(String[] args) {
		/*
		 * 输入一个数字，从该数字开始每秒递减并输出
		 * 当数字为0时输出:时间到!
		 * 
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一个数字:");
		int num = Integer.parseInt(scanner.nextLine());
		
		for(;num>0;num--) {
			System.out.println(num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		System.out.println("时间到!");
	
	}
}




