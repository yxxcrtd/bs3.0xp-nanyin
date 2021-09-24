package com.joyintech.tams.invest.interf.trade.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 
 * @author yflv
 * @version 1.0 2021年09月24日
 * @see InterfThreadPool
 * @since 1.0
 */
public class InterfThreadPool {
	private ExecutorService service;
	private ThreadPoolExecutor pool;
	private int size;
	private int queueMaxSize = 2000;
	
	public InterfThreadPool(int size) {
		if (size<=0) {
			throw new RuntimeException("线程数量必须大于0");
		}
		this.size = size;
	}
	
	public InterfThreadPool(int size,int queueSize) {
		if (size<=0) {
			throw new RuntimeException("线程数量必须大于0");
		}
		if (size<=100) {
			throw new RuntimeException("队列大小必须大于100");
		}
		this.size = size;
		this.queueMaxSize = queueSize;
	}

	public void submit(Runnable task) {
		if(null==pool) {
			pool = new ThreadPoolExecutor(this.size, this.size, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(queueMaxSize),new ThreadPoolExecutor.CallerRunsPolicy());
		}
		pool.execute(task);
	}
	
	public void close() {
		if (null!=service) {
			service.shutdown();
		}
		if (null!=pool) {
			pool.shutdown();
		}
	}
}
