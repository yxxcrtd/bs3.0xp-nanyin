/*
 * 文件名：BussFinIntPayDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.valuation.component.element.validator.ValBussFinIntPayValidator;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月20日
 * @see ValBussFinIntPayDto
 * @since 1.0
 */
@ValidLogic(targetClass = ValBussFinIntPayValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class ValBussFinIntPayDto extends FinCashPayRule{
    /**
     * 基金类型
     */
    private String fundType;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    @NotEmpty(fieldName = "BussFinIntPay.payType", groups = {ValidateGroupEdit.class})
    public String getPayType(){
        return super.getPayType();
    }
    @NotEmpty(fieldName = "BussFinIntPay.payCycle", groups = {ValidateGroupEdit.class})
    public String getPayCycle(){
        return super.getPayCycle();
    }
    
    public String getFundType() {
        return fundType;
    }
    
    public void setFundType(String fundType) {
        this.fundType = fundType;
    }
    
}
