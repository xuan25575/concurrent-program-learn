package com.concurrent.learn1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author huang_2
 * @Date 2020/3/14 9:02 下午
 * @Description 生产者消费者模型
 */
public class ProducerAndConsumerModel {

    private static final int MAX_SIZE = 10;
    private static List<String> queue = new ArrayList<String>(MAX_SIZE);

     class Producer extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (queue){
                    while(queue.size()== MAX_SIZE){
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add("ele");
                    System.out.println(Thread.currentThread().getName()+"生产了:ele");
                    queue.notifyAll();
                }
            }

        }
    }
     class Consumer extends Thread{
        @Override
        public void run() {
            while(true){
                synchronized (queue){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while(queue.size()== 0){
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String ele = queue.remove(0);
                    System.out.println(Thread.currentThread().getName()+"消费了:"+ele);
                    queue.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumerModel p = new ProducerAndConsumerModel();
        for (int i = 0; i < 3; i++) {
            p.new Consumer().start();
        }
        p.new Producer().start();
    }


}

