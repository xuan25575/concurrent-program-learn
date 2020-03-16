package com.concurrent.learn2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author huang_2
 * @Date 2020/3/16 5:11 下午
 * @Description 测试
 */
public class MyLockTest {

    private static int stock  = 4;

    final static AqsLock sync = new AqsLock();

    public static void main(String[] args) {
        final int totalThread = 20;
//        final AqsLock sync = new AqsLock();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                test();
            });
        }
        executorService.shutdown();
    }


    public static void test(){
        sync.lock();

        if(stock == 0){
            sync.unLock();
            System.out.println("库存没有了");
            return;
        }
        System.out.println("库存为: "+ stock--);
        sync.unLock();
    }
}
