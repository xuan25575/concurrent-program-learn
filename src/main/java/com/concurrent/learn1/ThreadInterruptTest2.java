package com.concurrent.learn1;

/**
 * @Author huang_2
 * @Date 2020/3/15 9:20 下午
 * @Description 中断
 *
 * interrupted()
 * 检测当前线程是否被中断，如果是返回true，否则返回false。
 * 与isInterrupted不同的是，该方法如果发现当前线程被中断，则会清除中断标志
 * isInterrupted（） 不清除中断标志标志。
 */
public class ThreadInterruptTest2 {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne  = new Thread(()->{
            // 中断为true 并清除标志。
            while(!Thread.currentThread().interrupted()){
                System.out.println("111111");
            }
            System.out.println(Thread.currentThread().getName() +" :"+Thread.currentThread().isInterrupted());
        });

        Thread threadTwo  = new Thread(()->{
            // 中断为true 并不清除标志。
            while(!Thread.currentThread().isInterrupted()){
            }
            System.out.println(Thread.currentThread().getName() +" :"+Thread.currentThread().isInterrupted());
        });

        threadOne.start();
//        Thread.sleep(1000);
        threadOne.interrupt(); // 设置中断标志
        threadOne.join();

        threadTwo.start();
        threadTwo.interrupt();
        threadTwo.join();

        System.out.println("main thread is over");







    }
}
