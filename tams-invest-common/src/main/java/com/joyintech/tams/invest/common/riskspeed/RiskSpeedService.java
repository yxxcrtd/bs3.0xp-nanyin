/*
 * 文件名：RiskSpeedService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskspeed;

import com.joyintech.tams.invest.common.riskspeed.dto.IRiskBase;

import java.util.List;

/**
 * 风险试算基类
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see RiskSpeedService
 * @since 1.0
 */
public interface RiskSpeedService<V extends IRiskBase>{

    /**
     * 获取可投资与已投金额
     * @param riskList
     * @return
     */
     List<V> getCanCastAmt(V... riskList);

    /**
     * 获取已投金额
     * @param riskList
     * @return
     */
    List<V> getHasCastAmt(V... riskList);

}
