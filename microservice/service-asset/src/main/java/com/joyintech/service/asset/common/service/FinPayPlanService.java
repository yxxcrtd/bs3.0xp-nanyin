/*
 * 文件名：FinPayPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.asset.common.dao.FinPayPlanMapper;
import com.joyintech.service.asset.common.dto.FinPayPlanDto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月23日
 * @see FinPayPlanService
 * @since 1.0
 */
@Service("backFinPayPlanService")
public class FinPayPlanService {

    @Autowired
    private FinPayPlanMapper finPayPlanMapper;
    
    /**
     * 现金流计划--自动生成
     */
    @Autowired
    private FinCashPlanMapper  finCashPlanMapper;
    
    /**
     * 插入
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void insert(FinPayPlanDto dto){
        //by finprodId 全删现金流计划
        finPayPlanMapper.deleteCashPlanByFinprodId(dto.getFinprodId());
        //插入
        for(FinCashPlan entity:dto.getFinCashPlanList()){
            finCashPlanMapper.insert(entity);
        }
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinPayPlanDto getBasicInfo(Map<String,Object> param){
        return finPayPlanMapper.getBasicInfo(param);
    }
    
    /**
     * 查询是否有后续交易新建流程 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public boolean isNotIng(Map<String, Object> param) {
        return finPayPlanMapper.isNotIng(param) > 0 ? true : false;
    }
}
