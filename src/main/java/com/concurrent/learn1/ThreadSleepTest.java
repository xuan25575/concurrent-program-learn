package com.concurrent.learn1;

/**
 * @Author huang_2
 * @Date 2020/3/15 8:53 下午
 * @Description sleep 不释放锁。
 */
public class ThreadSleepTest {

    public static void main(String[] args) throws InterruptedException {


        //如果在睡眠期间其他线程调用了该线程的interrupt（）方法中断了该线程，
        // 则该线程会在调用sleep方法的地方抛出InterruptedException异常而返回。

        Thread thread = new Thread(()-> {
            System.out.println("child thread is sleeping .");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threa is awake");
        });

        thread.start();

        // 主线程睡 2 秒。
        Thread.sleep(2000);
        thread.interrupt();




    }
}
