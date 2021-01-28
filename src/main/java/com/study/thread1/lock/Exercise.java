package com.study.thread1.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个银行账户
 * 两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 */
class Account { //账户
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    //存钱
    public void deposit(double amt) {
        if(amt > 0) {
            balance += amt;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "存钱成功，余额：" + balance);
        }
    }
}

class Customer implements Runnable{//储户

    private Account acc;

    private ReentrantLock lock = new ReentrantLock();

    public Customer(Account acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            lock.lock();
            acc.deposit(1000);
            lock.unlock();
        }
    }
}

public class Exercise {

    public static void main(String[] args) {
        Account acc = new Account(0);
        Customer c = new Customer(acc);

        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}
