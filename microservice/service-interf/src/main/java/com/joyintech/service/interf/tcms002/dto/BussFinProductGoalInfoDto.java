/*
 * 文件名：BussFinProductGoalInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.framework.validation.annotation.Rate;

/**
 * 产品投向业务组件
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see BussFinProductGoalInfoDto
 * @since 1.0
 */
public class BussFinProductGoalInfoDto extends PtlGoal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -812991360225578120L;

    /**
     * 主画面传入的pageId
     */
    private String pageId;

    /**
     * 投向列表
     */
    private List<BussFinProductGoalInfoDto> finProductPtlGoalDtoList;

    @Override
    @Rate(fieldName = "BussFinProductGoalInfo.investMax", maxValue = 100)
    public BigDecimal getInvestMax() {
        return super.getInvestMax();
    }

    @Override
    @Rate(fieldName = "BussFinProductGoalInfo.investMin", maxValue = 100)
    public BigDecimal getInvestMin() {
        return super.getInvestMin();
    }

    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 finProductPtlGoalDtoList
     * 
     * @return finProductPtlGoalDtoList.
     */
    public List<BussFinProductGoalInfoDto> getFinProductPtlGoalDtoList() {
        return finProductPtlGoalDtoList;
    }

    /**
     * 设置 finProductPtlGoalDtoList
     * 
     * @param finProductPtlGoalDtoList finProductPtlGoalDtoList
     */
    public void setFinProductPtlGoalDtoList(List<BussFinProductGoalInfoDto> finProductPtlGoalDtoList) {
        this.finProductPtlGoalDtoList = finProductPtlGoalDtoList;
    }
}
