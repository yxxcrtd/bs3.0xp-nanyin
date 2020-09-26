/*
 * 文件名：ValuationReportBuild001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valreportbuild.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valreportbuild.dao.ValuationReportBuild001Mapper;

/**
 * 估值表生成
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see ValuationReportBuild001Service
 * @since 1.0
 */
@Service
public class ValuationReportBuild001Service extends CommonExportService<BaseEntity>{

    @Autowired
    private ValuationReportBuild001Mapper valuationReportBuild001Mapper;
    
    /**
     * 根据条件查询列表
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<BaseEntity> findPage(Map<String, Object> params) throws Exception {
        return new Page<>();
//        return super.queryForPage(()-> valuationReportBuild001Mapper.selectList(params));
    }
    
    
    
}
