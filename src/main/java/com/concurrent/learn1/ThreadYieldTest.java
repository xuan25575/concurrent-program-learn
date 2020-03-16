package com.concurrent.learn1;


/**
 * @Author huang_2
 * @Date 2020/3/15 9:00 下午
 * @Description yield 方法
 */
public class ThreadYieldTest implements Runnable {


    public ThreadYieldTest() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if(i% 5==0){
                System.out.println(Thread.currentThread().getName() + "yield cup . ");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is over");


    }

    public static void main(String[] args) {
        new ThreadYieldTest();
        new ThreadYieldTest();
        new ThreadYieldTest();
    }
}
