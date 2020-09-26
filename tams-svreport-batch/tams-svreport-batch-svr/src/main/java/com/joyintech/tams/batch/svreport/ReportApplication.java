/*
 * 文件名：ReportApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.svreport;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.joyintech.batch.config.BatchApplication;
import com.joyintech.framework.JoyinApplication;

/**
 * @author 何阳阳
 * @version 1.0 2018年5月2日
 * @see ReportApplication
 * @since 1.0
 */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}, excludeName = {
	    "org.activiti.spring.boot.SecurityAutoConfiguration",
	    "org.activiti.spring.boot.JpaProcessEngineAutoConfiguration"})
public class ReportApplication extends BatchApplication {

    public static void main(String[] args) {
        JoyinApplication.run(ReportApplication.class, args);
    }
}
