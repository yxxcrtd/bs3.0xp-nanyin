/*
 * 文件名：JoyinThreadPoolExecutor.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月20日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.common.framework.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.joyintech.framework.util.DateUtil;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author 何阳阳
 * @version 1.0 2017年11月20日
 * @see JoyinThreadPoolExecutor
 * @since 1.0
 */
public class JoyinThreadPoolExecutor extends ThreadPoolExecutor/* implements Runnable */{

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg4
     * @param arg5
     */
    public JoyinThreadPoolExecutor() {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    public JoyinThreadPoolExecutor(ThreadFactory factory) {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), factory);
    }

    public JoyinThreadPoolExecutor(int arg) {
        super(arg, arg, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    public JoyinThreadPoolExecutor(int arg, ThreadFactory factory) {
        super(arg, arg, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeExecute(Thread arg0, Runnable arg1) {
        // TODO Auto-generated method stub
        System.err.println(arg1.hashCode() + " start time:" + DateUtil.getCurrentDateTime());
        super.beforeExecute(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void afterExecute(Runnable arg0, Throwable arg1) {
        // TODO Auto-generated method stub
        System.err.println(arg0.hashCode() + " end time:" + DateUtil.getCurrentDateTime());
        super.afterExecute(arg0, arg1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> arg0) {
        // TODO Auto-generated method stub
        return super.newTaskFor(arg0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void terminated() {
        // TODO Auto-generated method stub
        super.terminated();
    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void run() {
//        final JoyinThreadPoolExecutor that = this;
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("总线程数(taskCount): " + that.getTaskCount()
//                    + " 当前激活线程数(activeCount): " + that.getActiveCount()
//                    + " 已运行线程数(completedTaskCount): " + that.getCompletedTaskCount()
//                    + " 最大线程数量(largestPoolSize): " + that.getLargestPoolSize()
//                    + " 线程池数量(poolSize): " + that.getPoolSize());
//            }
//        }, 1000, 1000);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> arg0)
//        throws InterruptedException {
//        Thread thread = new Thread(this);
//        thread.start();
//        return super.invokeAll(arg0);
//    }

}
