/*
 * 文件名：RiskRefreshRedisService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.service;

import com.joyintech.tams.invest.common.redisinit.dto.IParaRds;
import org.springframework.stereotype.Service;

/**
 * redis数据初始化接口
 * 
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see RiskRefreshRedisService
 * @since 1.0
 */
@Service
public interface RiskRefreshRedisService<T extends IParaRds> {

    /**
     * 获取参数待处理数据
     * @param var 传递参数
     */
    public void refreshParamRedis(T var) throws Exception;

    /**
     * 获取模块待处理数据
     * @param var 传递参数
     */
    public void refreshModelRedis(T var) ;

}
