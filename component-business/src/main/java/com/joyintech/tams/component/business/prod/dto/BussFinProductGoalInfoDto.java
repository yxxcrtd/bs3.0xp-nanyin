/*
 * 文件名：BussFinProductGoalInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.prod.validator.BussFinProductGoalInfoValidator;

/**
 * 产品投向业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see BussFinProductGoalInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductGoalInfoValidator.class)
public class BussFinProductGoalInfoDto extends PtlGoal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6929498311648247411L;

    /**
     * 主画面传入的pageId
     */
    private String pageId;
    
    /**
     * 投向列表    
     */
    @Valid
    private List<BussFinProductGoalInfoDto> finProductPtlGoalDtoList;

    @Override
    @Length(fieldName = "BussFinProductGoalInfo.assetClassify", max=100)
    public String getAssetClassify() {
        return super.getAssetClassify();
    }

    @Override
//    @Rate(fieldName = "BussFinProductGoalInfo.investMax", maxDecBit=4)
    @Amount(fieldName = "BussFinProductGoalInfo.investMax", maxIntBit=3, maxDecBit=4)
    public BigDecimal getInvestMax() {
        return super.getInvestMax();
    }

    @Override
//    @Rate(fieldName = "BussFinProductGoalInfo.investMin", maxDecBit=4)
    @Amount(fieldName = "BussFinProductGoalInfo.investMin", maxIntBit=3, maxDecBit=4)
    public BigDecimal getInvestMin() {
        return super.getInvestMin();
    }

    
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
    /**
     * 获取 finProductPtlGoalDtoList
     * @return finProductPtlGoalDtoList.
     */
    public List<BussFinProductGoalInfoDto> getFinProductPtlGoalDtoList() {
        return finProductPtlGoalDtoList;
    }
    
    /**
     * 设置 finProductPtlGoalDtoList
     * @param finProductPtlGoalDtoList finProductPtlGoalDtoList
     */
    public void setFinProductPtlGoalDtoList(List<BussFinProductGoalInfoDto> finProductPtlGoalDtoList) {
        this.finProductPtlGoalDtoList = finProductPtlGoalDtoList;
    }
}
