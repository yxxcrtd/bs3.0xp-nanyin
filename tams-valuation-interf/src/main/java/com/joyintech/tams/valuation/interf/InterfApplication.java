/*
 * 文件名：ValuationApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf;

import org.springframework.context.annotation.Import;

import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.config.WebApplication;
import com.joyintech.tams.valuation.interf.conf.InterfTaskConfiguration;

/**
 * 接口启动进程
 * 
 * @author 王波
 * @version 1.0 2018年6月7日
 * @see InterfApplication
 * @since 1.0
 */
@Import({InterfTaskConfiguration.class})
public class InterfApplication extends WebApplication {

    /**
     * 程序入口
     * 
     * @param args 入口参数
     */
    public static void main(String[] args) {
        JoyinApplication.run(InterfApplication.class, args);
    }
}
