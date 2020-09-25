package com.joyintech.service.riskrdsinf.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.batchList.dao.BatchListMapper;
import com.joyintech.tams.invest.common.batchList.service.BatchListService;

public class TaskFactory<T> {

	/**
	 * 默认间隔处理队列时间
	 */
	private static final int DEFAULT_TIME = 1000;

	/**
	 * 默认队列处理长度
	 */
	private static final int DEFAULT_CNT = 50000;

	/**
	 * 设置队列处理时间
	 */
	private long handleTm;

	/**
	 * 设置队列处理长度
	 */
	private int handleLn;

	/**
	 * 阻塞队列
	 */
	private LinkedBlockingQueue<T> taskQueue = new LinkedBlockingQueue<>();

	private BatchListService<T> service;

	private BatchListMapper<T> mapper;

	/**
	 * 控制台日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 存放从队列里取出来的数据
	 */
	private List<T> dataList;

	public void add(T t){
		taskQueue.add(t);
	}

	public void addAll(Collection<T> tList){
        taskQueue.addAll(tList);
	}

	public void clearDataList(){
		if(!StringUtil.isEmptyList(dataList) && taskQueue.containsAll(dataList)){
			// do nothing
			taskQueue.removeAll(dataList);
		}
	    dataList = null;
        dataList = new ArrayList<>();
	}

	public TaskFactory(BatchListService<T> service, BatchListMapper<T> mapper){
		this(DEFAULT_TIME, DEFAULT_CNT, service, mapper);
	}

	public TaskFactory(long handleTm, int handleLn, BatchListService<T> service, 
			BatchListMapper<T> mapper){
		this.handleTm = handleTm;
		this.handleLn = handleLn;
		this.service = service;
		this.mapper = mapper;
		start();
	}

	private void start(){
		clearDataList();
		DataListener listener = new DataListener();
		new Thread(listener).start();
	}

	class DataListener implements Runnable {

		@Override
		public void run() {
			long startTime = System.currentTimeMillis();
			T t = null;
			while(true) {
				try{
					if (service == null) {
						break;
					}
					t = taskQueue.take();
					if (null != t) {
						dataList.add(t);
					}
					long currentTime = System.currentTimeMillis();
					if (dataList.size() > handleLn || currentTime - startTime > handleTm) {
						startTime = callback(dataList);
					}
				}
				catch(Exception e) {
					logger.error("队列处理异常", e);
					// do nothing
				}
			}
		}

	}

	protected final long callback(List<T> dataList) {
		try{
			service.insertBatchList(mapper, dataList, 500);
		}
		finally {
			clearDataList();
		}
		return System.currentTimeMillis();
	}
}
