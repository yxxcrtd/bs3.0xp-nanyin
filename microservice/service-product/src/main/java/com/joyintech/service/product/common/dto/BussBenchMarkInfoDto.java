/*
 * 文件名：BussBenchMarkInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈卓
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;

/**
 * 业绩基准Dto
 * @author 陈卓
 * @version 1.0 2018年9月17日
 * @see BussBenchMarkInfoDto
 * @since 1.0
 */
public class BussBenchMarkInfoDto extends FinBaseRuleInfo {

    /**
     */
    private static final long serialVersionUID = -6988283539747988052L;
    private String indexIdSource;
    
    /**
     * 产品款型
     */
    private String prodStyle;
    /**
     * 投资周期
     */
    private String investmentCycle;
    
    private Date vdate;
    
    
    public Date getVdate() {
		return vdate;
	}

    @JsonDeserialize(using = CustomDateDeserialize.class)
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public String getIndexIdSource() {
        return indexIdSource;
    }
    
    public void setIndexIdSource(String indexIdSource) {
        this.indexIdSource = indexIdSource;
    }

    
    /**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }

    
    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }

	public String getInvestmentCycle() {
		return investmentCycle;
	}

	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}
    
    
}
