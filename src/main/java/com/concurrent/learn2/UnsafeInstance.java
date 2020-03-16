package com.concurrent.learn2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author huang_2
 * @Date 2020/3/16 4:29 下午
 * @Description TODO
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe(){

        Field field= null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
