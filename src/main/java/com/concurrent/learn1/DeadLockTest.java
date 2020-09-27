package com.concurrent.learn1;

/**
 * @Author huang_2
 * @Date 2020/3/16 8:58 下午
 * @Description 死锁
 */
public class DeadLockTest {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(()->{

            synchronized (resourceA){
                System.out.println(Thread.currentThread().getName() + " wait resource B ");
                synchronized (resourceB){
                    System.out.println(Thread.currentThread().getName() + " get resource B ");

                }
            }

        });

        Thread threadB = new Thread(()->{
            synchronized(resourceB){
                System.out.println(Thread.currentThread().getName() + " wait resourceA ");

                synchronized (resourceA){
                    System.out.println(Thread.currentThread().getName() + " get resource A ");

                }

            }
        });

        threadA.start();
        threadB.start();


    }


}
