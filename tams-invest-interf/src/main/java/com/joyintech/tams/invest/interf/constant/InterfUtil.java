/*
 * 文件名：InterfServiceApplication.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.interf.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 常量类
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月24日
 * @see InterfUtil
 * @since 1.0
 */
public class InterfUtil implements GlobalConstant {

    private InterfUtil() {
        // 私有化构造
    }

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(InterfUtil.class);

    /**
     * 接口
     */
    public static final String INTERF_SERVICE_NAME = "http://interfService";

    /**
     * Key:调用模块
     */
    public static final String CALLER = "caller";

    /**
     * KEY:esb请求数据
     */
    public static final String ESB_DATA = "esbData";

    /**
     * KEY:产品列表
     */
    public static final String DATA_LIST = "dataList";

    /**
     * 获取接口服务处理Url
     * 
     * @param modelType 调用接口模块类别
     * @return 接口服务处理Url
     * @see
     * @since 1.0
     */
    public static String getUrl(String modelType) {
        String url = CacheUtil.getSysParam(modelType);
        if (!StringUtil.isNotEmpty(url)) {
            logger.info("{}对应的系统参数不存在或缓存加载失败", modelType);
            return StringUtil.EMPTY;
        }
        return url;
    }

    /**
     * 获取调用模块要返回的数据类型
     * 
     * @param modelType 调用模块类别
     * @return 返回的数据类型
     * @see
     * @since 1.0
     */
    public static Class<?> getReturnDto(String modelType) {
        for (InterfServiceCaller e : InterfServiceCaller.values()) {
            if (e.code().equals(modelType)) {
                return e.dto();
            }
        }
        return BaseEntity.class;
    }
}
