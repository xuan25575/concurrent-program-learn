package com.concurrent.learn1;

/**
 * @Author huang_2
 * @Date 2020/3/15 9:20 下午
 * @Description 中断
 *
 * interrupted()
 * 检测当前线程是否被中断，如果是返回true，否则返回false。
 * 与isInterrupted不同的是，该方法如果发现当前线程被中断，则会清除中断标志
 *
 */
public class ThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne  = new Thread(()->{
            for(;;){}
        });

        threadOne.start();
        threadOne.interrupt(); // 设置中断标志

        System.out.println("isInterrupted : " + threadOne.isInterrupted());

        // 获取中断标志并重置
        System.out.println("isInterrupted : " + threadOne.interrupted());

        // 获取中断标志并重置 main线程, main线程没有被打断。但是main 标志应该是false
        System.out.println("isInterrupted : " + Thread.interrupted());

        System.out.println("isInterrupted : " + threadOne.isInterrupted());

        threadOne.join();


        System.out.println("main thread is over");







    }
}
