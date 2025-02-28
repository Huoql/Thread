package com.study.test;

import java.util.Date;

public class test1 {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(new Date());
            }
        });

        t.start();
    }
}
