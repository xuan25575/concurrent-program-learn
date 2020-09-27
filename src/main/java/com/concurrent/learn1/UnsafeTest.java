package com.concurrent.learn1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author huang_2
 * @Date 2020/3/19 9:28 下午
 * @Description TODO
 */
public class UnsafeTest {

    //不能通过该方式获取，会报错
//    Unsafe unsafe = Unsafe.getUnsafe();

    static final Unsafe unsafe;

    static final long stateOffset;

    private volatile long state = 0;

    static{
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new Error(e);
        }
    }

    public static void main(String[] args) {
        UnsafeTest unsafeTest = new UnsafeTest();
        boolean b = unsafe.compareAndSwapLong(unsafeTest, stateOffset, 0, 1);

        System.out.println(b);
    }

}
