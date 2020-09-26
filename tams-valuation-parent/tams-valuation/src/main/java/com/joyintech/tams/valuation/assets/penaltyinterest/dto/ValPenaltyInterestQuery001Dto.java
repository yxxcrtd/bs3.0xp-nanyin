/*
 * 文件名：ValPenaltyInterestQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.penaltyinterest.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;

/**
 * 逾期罚息类资产查询DTO
 * 
 * @author zhangquan
 * @version 1.0 2019年12月24日
 * @see ValPenaltyInterestQuery001Dto
 * @since 1.0
 */
public class ValPenaltyInterestQuery001Dto extends FinProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 生效日
     */
    private String effDate;
    
    /**
     * 罚息开始日
     */
    private String vdate1;

    /**
     * 罚息截止日
     */
    private String mdate1;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 分支类型
     */
    private String branchType;

    /**
     * 分支序号
     */
    private Long branchNo;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 罚息利率
     */
    private BigDecimal yield;
    
    /**
     * 是否罚息计提
     */
    private String ifPenalty;
    
    
    /**
     * 截止日是否冲回
     */
    private String ifRushBack;


	public String getEffDate() {
		return effDate;
	}


	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}


	public String getVdate1() {
		return vdate1;
	}


	public void setVdate1(String vdate1) {
		this.vdate1 = vdate1;
	}


	public String getMdate1() {
		return mdate1;
	}


	public void setMdate1(String mdate1) {
		this.mdate1 = mdate1;
	}


	public String getFinprodId() {
		return finprodId;
	}


	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}


	public String getFinprodName() {
		return finprodName;
	}


	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}


	public String getFinprodType() {
		return finprodType;
	}
	
	public String getFinprodTypeFmt() {
		return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.getFinprodType());
	}
	
	public String getBranchTypeFmt() {
		return SysDataUtil.getNameFromDataDict(DictCode.分支类型, branchType);
	}
	
	public String getBasisFmt() {
		return SysDataUtil.getNameFromDataDict(DictCode.计息基础, basis);
	}
	
	public String getIfPenaltyFmt() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否, ifPenalty);
	}
	
	public String getIfRushBackFmt() {
		return SysDataUtil.getNameFromDataDict(DictCode.是否, ifRushBack);
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}


	public String getBranchType() {
		return branchType;
	}


	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}


	public Long getBranchNo() {
		return branchNo;
	}


	public void setBranchNo(Long branchNo) {
		this.branchNo = branchNo;
	}


	public String getBasis() {
		return basis;
	}


	public void setBasis(String basis) {
		this.basis = basis;
	}


	public BigDecimal getYield() {
		return yield;
	}


	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}


	public String getIfPenalty() {
		return ifPenalty;
	}


	public void setIfPenalty(String ifPenalty) {
		this.ifPenalty = ifPenalty;
	}


	public String getIfRushBack() {
		return ifRushBack;
	}


	public void setIfRushBack(String ifRushBack) {
		this.ifRushBack = ifRushBack;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}