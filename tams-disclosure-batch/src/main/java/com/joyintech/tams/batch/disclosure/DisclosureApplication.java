/*
 * 文件名：ReportApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.disclosure;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.joyintech.batch.config.BatchApplication;
import com.joyintech.framework.JoyinApplication;

import java.util.Locale;

/**
 * @author 何阳阳
 * @version 1.0 2018年5月2日
 * @see DisclosureApplication
 * @since 1.0
 */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}, excludeName = {
	    "org.activiti.spring.boot.SecurityAutoConfiguration",
	    "org.activiti.spring.boot.JpaProcessEngineAutoConfiguration"})
public class DisclosureApplication extends BatchApplication {

    public static void main(String[] args) {
        Locale local = new Locale("zh", "CN");
        Locale.setDefault(local);
        JoyinApplication.run(DisclosureApplication.class, args);
    }
}
