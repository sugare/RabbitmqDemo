package com.example.aba;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {

   static private  CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws Exception{
        new Thread(()->{
            System.out.println("1");
            countDownLatch.countDown();
            System.out.println("2");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }).start();

        countDownLatch.await(5, TimeUnit.SECONDS);
        System.out.println("3");

    }
}
