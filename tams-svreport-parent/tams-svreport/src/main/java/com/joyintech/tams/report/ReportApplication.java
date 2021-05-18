/*
 * 文件名：ReportApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年5月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.WebApplication;

import java.util.Locale;

/**
 * 中债报送系统启动类
 * 
 * @author 何阳阳
 * @version 1.0 2018年5月2日
 * @see ReportApplication
 * @since 1.0
 */
public class ReportApplication extends WebApplication {

    public static void main(String[] args) {
        Locale local = new Locale("zh", "CN");
        Locale.setDefault(local);
        JoyinApplication.run(ReportApplication.class, args);
    }
}
