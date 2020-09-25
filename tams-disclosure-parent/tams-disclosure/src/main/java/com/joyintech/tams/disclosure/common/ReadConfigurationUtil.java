/*
 * 文件名：ReadConfigurationUtils.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月15日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.disclosure.information.config.reportpage.controller.ReportPageManage002Controller;


/**
 * 读取系统配置文件工具
 * 〈
 * @author 汪敏
 * @version 1.0 2017年9月15日
 * @see ReadConfigurationUtils
 * @since 1.0
 */
public class ReadConfigurationUtil {
    

    /**
     * 接口导入开关项配置
     */
    private static final String SETTING_PROPERTIES = "table_setting.properties";
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ReadConfigurationUtil.class);
    /**
     * 读取配置文件，并传入表名获取对应的value值
     * @param tableName
     * @return
     * @see
     * @since 1.0
     */
    public static String readPropertiesByGetResourceAsStream(String tableName){
        InputStream inStream = ReadConfigurationUtil.class.getClassLoader().getResourceAsStream(SETTING_PROPERTIES);
        Properties properties = new Properties();
        String tabs="";
        try {
            properties.load(inStream);
            tabs = properties.getProperty(tableName);
        }
        catch (IOException e) {
        	logger.error("error:",e);
        }
        finally{
            if(inStream !=null){
                try {
                    inStream.close();
                }
                catch (IOException e) {
                	logger.error("error:",e);
                }
            }
        }
        return tabs;
    }
}
