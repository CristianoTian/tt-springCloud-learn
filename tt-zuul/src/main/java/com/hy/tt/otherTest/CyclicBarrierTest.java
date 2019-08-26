package com.hy.tt.otherTest;

import java.util.Map;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther thy
 * @date 2019/8/19
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        int num=5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num ; i++) {
            new MyThread(cyclicBarrier).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("cyclicBarrier 重用");
        for (int i = 0; i < num ; i++) {
            new MyThread(cyclicBarrier).start();
        }
    }

    static class MyThread extends Thread{
        private CyclicBarrier cyclicBarrier;
        public MyThread(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName() + "正在操作数据...");
            try{
                Thread.sleep(1000);
                System.out.println("线程"+Thread.currentThread().getName() + "操作数据完成");
                cyclicBarrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
