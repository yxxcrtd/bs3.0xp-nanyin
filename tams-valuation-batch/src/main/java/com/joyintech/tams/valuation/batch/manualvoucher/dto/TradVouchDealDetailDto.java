/*
 * 文件名：TradVouchDealDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chenggaoling
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.manualvoucher.dto;

import com.joyintech.tams.common.base.entity.TrdVouchDealDetail;

/**
 * 
 * 手工凭证交易明细DTO〉
 * 
 * @author chenggaoling
 * @version 1.0 2018年8月14日
 * @see TradVouchDealDetailDto
 * @since 1.0
 */
public class TradVouchDealDetailDto extends TrdVouchDealDetail {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4814109223609287548L;
    
    /**
     * 基础科目性质
     */
    private String baseSubjectNature;
    
    /**
     * 科目余额方向
     */
    private String balFlag;
    /**
     * 科目类型
     */
    private String subjectType;

    
    /**
     * 获取 baseSubjectNature
     * @return baseSubjectNature.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    
    /**
     * 设置 baseSubjectNature
     * @param baseSubjectNature baseSubjectNature
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }


	public String getBalFlag() {
		return balFlag;
	}


	public void setBalFlag(String balFlag) {
		this.balFlag = balFlag;
	}


	public String getSubjectType() {
		return subjectType;
	}


	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
    
}
