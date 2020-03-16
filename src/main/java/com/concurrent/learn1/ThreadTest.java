package com.concurrent.learn1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author huang_2
 * @Date 2020/3/14 7:44 下午
 * @Description TODO
 */
public class ThreadTest {
    public static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("I am a child thread");
        }
    }

    public static class RunnableTask implements Runnable{

        public void run() {
            System.out.println("I am child thread By runnable");
        }
    }

    public static class CallableTask implements Callable<String>{

        public String call() throws Exception {

            return "string";
        }
    }

    public static void main(String[] args) {
        //第一种方式启动线程
        MyThread myThread = new MyThread();
        myThread.start();

        // 方式二启动线程
        RunnableTask runnableTask  = new RunnableTask();
        new Thread(runnableTask).start();

        // 方式三启动线程
        CallableTask callableTask = new CallableTask();
        FutureTask<String> futureTask = new FutureTask<String>(callableTask);
        new Thread(futureTask).start();
        try {
            String  s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
