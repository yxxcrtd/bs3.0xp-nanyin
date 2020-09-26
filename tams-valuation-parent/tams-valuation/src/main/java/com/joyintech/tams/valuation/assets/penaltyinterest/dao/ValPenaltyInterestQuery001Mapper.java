/*
 * 文件名：ValPenaltyInterestQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.penaltyinterest.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.assets.penaltyinterest.dto.ValPenaltyInterestQuery001Dto;

/**
 * 逾期罚息类资产查询mapper
 * 
 * @author zhangquan
 * @version 1.0 2019年12月25日
 * @see ValPenaltyInterestQuery001Mapper
 * @since 1.0
 */
@Repository
public interface ValPenaltyInterestQuery001Mapper {
    /**
     * 
     * 页面初始化查询列表
     * @param params params
     * @return List<ValPenaltyInterestQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValPenaltyInterestQuery001Dto> queryPageList(Map<String, Object> params);
    /**
     * 
     * 获取资产下拉框数据
     * @param params params
     * @return List<ValPenaltyInterestQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValPenaltyInterestQuery001Dto> getAsset(Map<String, Object> params);
}
