/*
 * 文件名：ValPenaltyInterestQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.penaltyinterest.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.penaltyinterest.dao.ValPenaltyInterestQuery002Mapper;
import com.joyintech.service.valuation.assets.penaltyinterest.dto.ValPenaltyInterestQuery001Dto;

/**
 * 逾期罚息类资产详细页面查看
 * @author zhangquan
 * @version 1.0 2019年12月25日
 * @see ValPenaltyInterestQuery002Service
 * @since 1.0
 */
@Service(value="serviceValPenaltyInterestQuery002Service")
@Transactional(rollbackFor = Exception.class)
public class ValPenaltyInterestQuery002Service {
    /**
     * mapper
     */
    @Autowired
    private ValPenaltyInterestQuery002Mapper mapper;

    /**
     * 罚息利率信息查询
     * @param map map
     * @return List<ValPenaltyInterestQuery001Dto>
     * @see
     * @since 1.0
     */
    public List<ValPenaltyInterestQuery001Dto> queryCollateralinfo(Map<String, Object> map) {       
        return mapper.queryCollateralinfo(map);
    }
    
    /**
     * 罚息计划信息查询
     * @param map map
     * @return List<ValPenaltyInterestQuery001Dto>
     * @see
     * @since 1.0
     */
    public List<ValPenaltyInterestQuery001Dto> queryFenaltyInterestInfo(Map<String, Object> map) {       
        return mapper.queryFenaltyInterestInfo(map);
    }

    /**
     * 根据产品findprodid获取回购类资产详细页面
     * @param map map
     * @return ValPenaltyInterestQuery001Dto
     * @see
     * @since 1.0
     */
    public ValPenaltyInterestQuery001Dto queryRepassetByfinprodId(Map<String, Object> map) {
        
        return mapper.queryRepassetByfinprodId(map);
    }
    
    /**
     * 获取交易日历下拉框
     * @param map map
     * @return ValPenaltyInterestQuery001Dto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCalendarInfoList() {
        return mapper.getCalendarInfoList();
    }

}
