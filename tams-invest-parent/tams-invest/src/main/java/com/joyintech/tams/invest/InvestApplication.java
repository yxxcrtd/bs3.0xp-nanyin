/*
 * 文件名：InvestApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.WebApplication;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Locale;

/**
 * 平安银行系统启动类
 *
 * @author 何阳阳
 * @version 1.0 2018年5月21日
 * @see InvestApplication
 * @since 1.0
 */
@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class InvestApplication extends WebApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale local = new Locale("zh", "CN");
		Locale.setDefault(local);
		JoyinApplication.run(InvestApplication.class, args);
	}

}
