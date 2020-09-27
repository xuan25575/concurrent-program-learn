package com.concurrent.learn1;

/**
 * @Author huang_2
 * @Date 2020/3/16 9:46 下午
 * @Description TODO
 */
public class TestThreadLocal {

    //父子关系
    public static InheritableThreadLocal local = new InheritableThreadLocal();

    public static void main(String[] args) {
        local.set("value");

        Thread thread = new Thread(()->{
            System.out.println("子线程："+local.get());
        });
        thread.start();

        System.out.println("main线程： " + local.get());
    }
}
