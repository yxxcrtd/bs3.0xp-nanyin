/*
 * 文件名：ValuationLossModelApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.lossmodel;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.WebApplication;

/**
 * 减值模型系统启动类
 * 
 * @author yuzj
 * @version 1.0 2021年8月3日
 * @see ValuationLossModelApplication
 * @since 1.0
 */
public class ValuationLossModelApplication extends WebApplication {

	public static void main(String[] args) {
		JoyinApplication.run(ValuationLossModelApplication.class, args);
	}

}
