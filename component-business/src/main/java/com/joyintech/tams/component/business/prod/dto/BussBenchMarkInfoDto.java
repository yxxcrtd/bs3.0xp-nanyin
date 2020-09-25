/*
 * 文件名：BussBenchMarkInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈卓
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.prod.validator.BussBenchMarkInfoValidator;

/**
 * 业绩基准Dto
 * 
 * @author 陈卓
 * @version 1.0 2018年9月17日
 * @see BussBenchMarkInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussBenchMarkInfoValidator.class)
public class BussBenchMarkInfoDto extends FinBaseRuleInfo {

    /**
     */
    private static final long serialVersionUID = -6988283539747988052L;

    /**
     * 主画面传入的pageId
     */
    private String pageId;

    private Date vdate;
    
    
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
    
    public Date getVdate() {
		return vdate;
	}

    @JsonDeserialize(using = CustomDateDeserialize.class)
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
//    @NotEmpty(fieldName = "BussBenchMarkInfo.rateType", groups = {ValidateGroupEdit.class})
    public String getRateType(){
        return super.getRateType();
    }
//    @NotEmpty(fieldName = "BussBenchMarkInfo.resetType", groups = {ValidateGroupEdit.class})
    public String getResetType(){
        return super.getResetType();
    }

    @Amount(fieldName = "BussBenchMarkInfo.rate", maxDecBit = 14)
    public BigDecimal getRate(){
        return super.getRate();
    }

    @Amount(fieldName = "BussBenchMarkInfo.msg.coefficient", maxDecBit = 6)
    public BigDecimal getCoefficient() {
        return super.getCoefficient();
    }

    @Amount(fieldName = "BussBenchMarkInfo.msg.spreadRate", maxDecBit = 6)
    public BigDecimal getSpreadRate() {
        return super.getSpreadRate();
    }
}
