package com.study.thread4;

/**
 * 指令重排，多线程环境中线程交替执行，由于编译器优化重排的存在，
 * 两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测
 * 下面例子：
 * a = 1;
 * flag = true;
 * 可能会因为指令重排变成
 * flag = true;
 * a = 1;
 * 当一个线程执行到flag = true;时马上另一个线程执行method2方法，此时结果
 * 会变成5而不是预想的6
 * volatile关键字修饰变量可以禁止指令重排
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method1() {
        a = 1;
        flag = true;
    }

    public void method2() {
        if (flag) {
            a = a + 5;
            System.out.println(a);
        }
    }
}
