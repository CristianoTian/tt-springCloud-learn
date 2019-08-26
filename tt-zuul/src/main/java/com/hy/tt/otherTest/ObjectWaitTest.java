package com.hy.tt.otherTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @auther thy
 * @date 2019/8/23
 */
public class ObjectWaitTest {

    //    private static CountDownLatch countDownLatch = new CountDownLatch(2);
//    private static volatile Integer count = 100;
//
//    static class MyThread extends Thread{
//
//        private Integer num;
//        private String name;
//
//        public MyThread( Integer num,String name) {
//            this.num = num;
//            this.name = name;
//        }
//
//        @Override
//        public void run() {
//            System.out.println("start "+ name);
//            for (int i = 0; i < 50; i++) {
//                count = num = num -1;
//                System.out.println(name + "count = " + count);
//            }
//            countDownLatch.countDown();
////            try {
////                Thread.sleep(5000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            System.out.println("end " +name);
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//
//        MyThread myThread1 = new MyThread(count,"A");
//
//        myThread1.start();
////        myThread1.join();
//        MyThread myThread2 = new MyThread(count,"B");
//        myThread2.start();
//        countDownLatch.await();
//        System.out.println("最后的数字:"+count);
//    }
    public static void test() throws Exception {
        Object obj = new Object();

        CountDownLatch latch = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                    System.out.println(Thread.currentThread().getName()
                            + " be notify first");
                    latch.countDown();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()
                            + " be interrupt first");
                }
            }
        });
        t1.start();

        new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                    if (latch.getCount() > 0) {
                        System.out.println(Thread.currentThread().getName()
                                + " count down latch");
                        latch.countDown();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (Exception ignore) {

            }
            t1.interrupt();
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (Exception ignore) {

                }
                obj.notify();
            }
        }).start();

        try {
            latch.await(5, TimeUnit.SECONDS);
            System.out.println("latch can exit");
        } catch (Exception e) {
            // will never got here
            System.err.println("latch can not exit");
        }

        synchronized (obj) {
            obj.notifyAll();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }
}
