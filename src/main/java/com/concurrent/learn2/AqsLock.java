package com.concurrent.learn2;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author huang_2
 * @Date 2020/3/16 4:15 下午
 * @Description 手写 AQS 仿照 ReentrantLock 锁不能重入， 来着图灵学院
 */
public class AqsLock {

    private int state;

    private Thread lockHolder;

    // 队列 线程引用
    private ConcurrentLinkedQueue<Thread> waiter  = new ConcurrentLinkedQueue<>();


    public boolean acquire(){
        Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            // 第一次waiter为0 ，
            if((waiter.size() == 0 || current == waiter.peek())&& compareAndSwapState(0,1)){
                setLockHolder(current);
                return true;
            }
        }
        return false;

    }


    public void lock(){

        if(acquire()){
            return;
        }
        // 如果当前线程没有获取锁
        Thread current = Thread.currentThread();
        // 那么线程进入先入队
        waiter.add(current);

        //自旋
        for(;;){
            // 判断是不是头 并尝试获取锁。
            if(current == waiter.peek() && acquire()){
                waiter.poll();
                return;
            }
            // 阻塞当前线程
            LockSupport.park(current);
        }


    }


    public void unLock(){
        if(Thread.currentThread() != lockHolder){
            throw new RuntimeException("current thread is not lockHolder ");
        }
        int state  = getState();
        if(compareAndSwapState(state,0)){
            setLockHolder(null);
            Thread first = waiter.peek();
            // 如果不为空
            if(first != null){
                // 恢复当前线程
                LockSupport.unpark(first);
            }
        }
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    /**
     * 原子操作 CAS
     * @param expect
     * @param update
     * @return
     */
    public final boolean compareAndSwapState(int expect,int update){
       return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
    }



    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static final long  stateOffset;

    static {
        try {
            stateOffset= unsafe.objectFieldOffset(AqsLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
           throw new  Error();
        }
    }



}
