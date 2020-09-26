/*
 * 文件名：InterfTaskConfiguration.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年5月11日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.report.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 上游接口定时任务配置
 *
 * @author 刘二通
 * @version 1.0 2018年5月11日
 * @see InterfTaskConfiguration
 * @since 1.0
 */
@Configuration
@ImportResource("classpath:interf-task.xml")
public class InterfTaskConfiguration {

}
