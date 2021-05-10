/*
 * 文件名：BussFinIntPayDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.asset.dto;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.component.asset.validator.BussFinIntPayValidator;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月20日
 * @see BussFinIntPayDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinIntPayValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class BussFinIntPayDto extends FinCashPayRule{
    /**
     * 基金类型
     */
    private String fundType;
    /**
     * 是否按日复利
     */
    private String isCompoundInt;

    /**
     * 收益类型
     */
    private String profitType;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
//    @NotEmpty(fieldName = "BussFinIntPay.payType", groups = {ValidateGroupEdit.class})
    public String getPayType(){
        return super.getPayType();
    }
//    @NotEmpty(fieldName = "BussFinIntPay.payCycle", groups = {ValidateGroupEdit.class})
    public String getPayCycle(){
        return super.getPayCycle();
    }
//    @NotEmpty(fieldName = "BussFinIntPay.dayValue", groups = {ValidateGroupEdit.class})
    public String getDayValue(){
        return super.getDayValue();
    }
//    @NotEmpty(fieldName = "BussFinIntPay.endTdayRule", groups = {ValidateGroupEdit.class})
    public String getEndTdayRule(){
        return super.getEndTdayRule();
    }

//    @NotEmpty(fieldName = "BussFinIntPay.isCompoundInt", groups = {ValidateGroupEdit.class})
    public String getIsCompoundInt() {
        return isCompoundInt;
    }

    public void setIsCompoundInt(String isCompoundInt) {
        this.isCompoundInt = isCompoundInt;
    }

    public String getFundType() {
        return fundType;
    }
    
    public void setFundType(String fundType) {
        this.fundType = fundType;
    }


    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
}
