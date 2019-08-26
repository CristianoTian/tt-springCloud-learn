package com.hy.tt.otherTest;

import java.util.concurrent.CountDownLatch;

/**
 * @auther thy
 * @date 2019/8/19
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        Thread a = new MyThreadA();
        Thread b = new MyThreadB();
        //输出随机 AB  或者 BA
        //a.start();
        //b.start();

        //如果要让B依赖A输出后才输出即输出AB  使用join
        a.start();
        a.join();
        b.start();

        //也可以使用CountDownLatch
        CountDownLatch c = new CountDownLatch(1);
        a.start();
        c.countDown();
    }

    static class MyThreadA extends Thread{
        @Override
        public void run() {
            System.out.println("print A");
        }
    }

    static class MyThreadB extends Thread{
        @Override
        public void run() {
            System.out.println("print B");
        }
    }
}
