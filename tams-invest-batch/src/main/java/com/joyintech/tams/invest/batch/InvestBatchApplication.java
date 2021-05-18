/*
 * 文件名：InvestBatchApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.batch;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.joyintech.batch.config.BatchApplication;
import com.joyintech.batch.config.BatchConfiguration;
import com.joyintech.framework.JoyinApplication;

import java.util.Locale;

/**
 * 批处理启动类
 *
 * @author 何阳阳
 * @version 1.0 2018年5月21日
 * @see InvestBatchApplication
 * @since 1.0
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class })
@Import({ BatchConfiguration.class })
public class InvestBatchApplication extends BatchApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale local = new Locale("zh", "CN");
		Locale.setDefault(local);
	    JoyinApplication.run(InvestBatchApplication.class, args);
	}

}
