package com.concurrent.learn3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author huang_2
 * @Date 2020/4/19 10:52 上午
 * @Description TODO
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        ThreadLocalRandom random  = ThreadLocalRandom.current();
        for (int i = 0; i < 6; i++) {
            System.out.println("-----"+ random.nextInt(7));
        }
    }
}
