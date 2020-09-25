/*
 * 文件名：ReportStartupListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */

package com.joyintech.service.disclosure.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.joyintech.framework.listener.AppStartupListener;
import com.joyintech.service.disclosure.common.socket.server.SocketServer;

/**
 * 系统启动完成监听 系统启动完成后，可在afterStartup()中启动后续必要的程序
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年12月24日
 * @see ReportStartupListener
 * @since 1.0
 */
@Component(value="serviceReportStartupListener")
public class ReportStartupListener implements AppStartupListener {
	/**
	 * 日志
	 */
	private final Logger logger = LoggerFactory.getLogger(ReportStartupListener.class);

	@Override
	public void onApplicationStartup(ContextRefreshedEvent event) {

	}

	@Override
	public void afterStartup(ApplicationContext applicationContext) {
		logger.info("系统启动完成，开始启动后续程序...");

		// 启动socket服务端
		socketServerRun();
	}

	/**
	 * 开启线程：启动socket服务端
	 * 
	 * @see
	 * @since 1.0
	 */
	private void socketServerRun() {
		logger.info("开启线程：启动socket服务端...开始");
		Thread socketThread = new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("进入线程：启动socket服务端...");
				SocketServer socketServer = new SocketServer();
				socketServer.startSocketServer();
			}
		});
		socketThread.setDaemon(true);// 守护线程
		socketThread.start();
		logger.info("开启线程：启动socket服务端...结束");

	}
}
