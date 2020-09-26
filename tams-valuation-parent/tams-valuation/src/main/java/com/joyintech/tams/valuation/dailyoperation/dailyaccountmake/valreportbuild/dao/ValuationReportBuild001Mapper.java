/*
 * 文件名：ValuationReportBuild001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valreportbuild.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;

/**
 * 估值表生成
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see ValuationReportBuild001
 * @since 1.0
 */
public interface ValuationReportBuild001Mapper {

    /**
     * 根据条件查询列表
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<BaseEntity> selectList(Map<String, Object> params);
    
    
    
}
