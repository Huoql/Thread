package com.study.thread3.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值，ex: update sql ok");
        });
        completableFuture.get();

        //异步回调
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回值，ex: insert sql ok");
            int a = 10/0;
            return 1;
        });

        System.out.println(completableFuture1.whenComplete((t, u) -> {
            System.out.println("*****t:" + t);
            System.out.println("*****u:" + u);
        }).exceptionally((f) -> {
            System.out.println("*****exception:" + f.getMessage());
            return 404;
        }).get());
    }
}
