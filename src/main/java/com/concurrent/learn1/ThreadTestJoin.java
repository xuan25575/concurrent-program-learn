package com.concurrent.learn1;

/**
 * @Author huang_2
 * @Date 2020/3/15 8:23 下午
 * @Description join 方法测试
 */
public class ThreadTestJoin {


    public static void main(String[] args)  {

        // 线程A（main线程）调用线程B（one线程）的join方法后会被阻塞，
        // 当其他线程（tow线程）调用了线程A的interrupt（）方法中断了线程A时，线程A会抛出InterruptedException异常而返回。
        Thread  threadOne = new Thread(new Runnable() {

            public void run() {

                System.out.println("thread one is run .");
                for(;;){} // 死循环
            }
        });
        final Thread mainThread = Thread.currentThread();
        Thread threadTow = new Thread( ()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt();

        });

        threadOne.start();
        threadTow.start();

        try { // 等待one执行结束
            threadOne.join();
        } catch (InterruptedException e) {
            System.out.println("thread main :"+ e);
        }


        // 测试join 方法
//        Thread threadThree = new Thread(()->{
//            System.out.println("three is run");
//        });
//
//        threadThree.start();
//        try {
//            threadThree.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("main run");



    }
}
