/*
 * 文件名：FinAssetConfigPlan001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productplanning.asset.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.AssetConfigPlan;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productplanning.asset.validator.FinAssetConfigPlan002Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;


/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetConfigPlan001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = FinAssetConfigPlan002Validator.class, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
public class FinAssetConfigPlan001Dto extends AssetConfigPlan implements ITableRow, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5745186801613965122L;

    /**
     * 生成日
     */
    private Date genDate;
    /**
     * 基准日
     */
    private Date benchDate;
    /**
     * 配置月份
     */
    private Date configMonth;
    /**
     * 配置月份
     */
    private String configMonth2;
    /**
     * 资产大类
     */
    private String assetType;
    /**
     * 元素Id
     */
    private String elementId;
    
    /**
     * 资产配置编号
     */
    private String assetConfigCode;
    
	@PrimaryKey(value = PrimaryKey.Field.PK_1)
	public String getAssetConfigCode() {
		return assetConfigCode;
	}

	public void setAssetConfigCode(String assetConfigCode) {
		this.assetConfigCode = assetConfigCode;
	}

	/**
	 * 配置月份
	 * @return
	 */
	public String getConfigMonth2() {
		return configMonth2;
	}

	public void setConfigMonth2(String configMonth2) {
		this.configMonth2 = configMonth2;
	}

	/**
	 * 元素Id
	 * @return
	 */
	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**
	 * 总金额
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalAmt;
    
    /**
     * 总比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalRatio;
    
    /**
     * 当前存量金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curPerAmt;
    
    /**
     * 当月到期金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt;
    
    /**
     * 每月新投资金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt1;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt1;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt2;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt2;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt3;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt3;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt4;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt4;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt5;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt5;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt6;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt6;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt7;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt7;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt8;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt8;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt9;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt9;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt10;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt10;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt11;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt11;
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt12;   
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt12;
    
    
    public BigDecimal getCurEndAmt1() {
		return curEndAmt1;
	}

	public void setCurEndAmt1(BigDecimal curEndAmt1) {
		this.curEndAmt1 = curEndAmt1;
	}

	public BigDecimal getCurInvAmt1() {
		return curInvAmt1;
	}

	public void setCurInvAmt1(BigDecimal curInvAmt1) {
		this.curInvAmt1 = curInvAmt1;
	}

	public BigDecimal getCurEndAmt2() {
		return curEndAmt2;
	}

	public void setCurEndAmt2(BigDecimal curEndAmt2) {
		this.curEndAmt2 = curEndAmt2;
	}

	public BigDecimal getCurInvAmt2() {
		return curInvAmt2;
	}

	public void setCurInvAmt2(BigDecimal curInvAmt2) {
		this.curInvAmt2 = curInvAmt2;
	}

	public BigDecimal getCurEndAmt3() {
		return curEndAmt3;
	}

	public void setCurEndAmt3(BigDecimal curEndAmt3) {
		this.curEndAmt3 = curEndAmt3;
	}

	public BigDecimal getCurInvAmt3() {
		return curInvAmt3;
	}

	public void setCurInvAmt3(BigDecimal curInvAmt3) {
		this.curInvAmt3 = curInvAmt3;
	}

	public BigDecimal getCurEndAmt4() {
		return curEndAmt4;
	}

	public void setCurEndAmt4(BigDecimal curEndAmt4) {
		this.curEndAmt4 = curEndAmt4;
	}

	public BigDecimal getCurInvAmt4() {
		return curInvAmt4;
	}

	public void setCurInvAmt4(BigDecimal curInvAmt4) {
		this.curInvAmt4 = curInvAmt4;
	}

	public BigDecimal getCurEndAmt5() {
		return curEndAmt5;
	}

	public void setCurEndAmt5(BigDecimal curEndAmt5) {
		this.curEndAmt5 = curEndAmt5;
	}

	public BigDecimal getCurInvAmt5() {
		return curInvAmt5;
	}

	public void setCurInvAmt5(BigDecimal curInvAmt5) {
		this.curInvAmt5 = curInvAmt5;
	}

	public BigDecimal getCurEndAmt6() {
		return curEndAmt6;
	}

	public void setCurEndAmt6(BigDecimal curEndAmt6) {
		this.curEndAmt6 = curEndAmt6;
	}

	public BigDecimal getCurInvAmt6() {
		return curInvAmt6;
	}

	public void setCurInvAmt6(BigDecimal curInvAmt6) {
		this.curInvAmt6 = curInvAmt6;
	}

	public BigDecimal getCurEndAmt7() {
		return curEndAmt7;
	}

	public void setCurEndAmt7(BigDecimal curEndAmt7) {
		this.curEndAmt7 = curEndAmt7;
	}

	public BigDecimal getCurInvAmt7() {
		return curInvAmt7;
	}

	public void setCurInvAmt7(BigDecimal curInvAmt7) {
		this.curInvAmt7 = curInvAmt7;
	}

	public BigDecimal getCurEndAmt8() {
		return curEndAmt8;
	}

	public void setCurEndAmt8(BigDecimal curEndAmt8) {
		this.curEndAmt8 = curEndAmt8;
	}

	public BigDecimal getCurInvAmt8() {
		return curInvAmt8;
	}

	public void setCurInvAmt8(BigDecimal curInvAmt8) {
		this.curInvAmt8 = curInvAmt8;
	}

	public BigDecimal getCurEndAmt9() {
		return curEndAmt9;
	}

	public void setCurEndAmt9(BigDecimal curEndAmt9) {
		this.curEndAmt9 = curEndAmt9;
	}

	public BigDecimal getCurInvAmt9() {
		return curInvAmt9;
	}

	public void setCurInvAmt9(BigDecimal curInvAmt9) {
		this.curInvAmt9 = curInvAmt9;
	}

	public BigDecimal getCurEndAmt10() {
		return curEndAmt10;
	}

	public void setCurEndAmt10(BigDecimal curEndAmt10) {
		this.curEndAmt10 = curEndAmt10;
	}

	public BigDecimal getCurInvAmt10() {
		return curInvAmt10;
	}

	public void setCurInvAmt10(BigDecimal curInvAmt10) {
		this.curInvAmt10 = curInvAmt10;
	}

	public BigDecimal getCurEndAmt11() {
		return curEndAmt11;
	}

	public void setCurEndAmt11(BigDecimal curEndAmt11) {
		this.curEndAmt11 = curEndAmt11;
	}

	public BigDecimal getCurInvAmt11() {
		return curInvAmt11;
	}

	public void setCurInvAmt11(BigDecimal curInvAmt11) {
		this.curInvAmt11 = curInvAmt11;
	}

	public BigDecimal getCurEndAmt12() {
		return curEndAmt12;
	}

	public void setCurEndAmt12(BigDecimal curEndAmt12) {
		this.curEndAmt12 = curEndAmt12;
	}

	public BigDecimal getCurInvAmt12() {
		return curInvAmt12;
	}

	public void setCurInvAmt12(BigDecimal curInvAmt12) {
		this.curInvAmt12 = curInvAmt12;
	}

	private String assetMonth;
    
    public String getAssetMonth() {
		return assetMonth;
	}

	public void setAssetMonth(String assetMonth) {
		this.assetMonth = assetMonth;
	}

	private String operateType;
	
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	private List<FinAssetConfigPlan001Dto> selectedItem;
    
    
    public List<FinAssetConfigPlan001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<FinAssetConfigPlan001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

    public Date getGenDate() {
		return genDate;
	}

	public void setGenDate(Date genDate) {
		this.genDate = genDate;
	}

	public Date getBenchDate() {
		return benchDate;
	}

	public void setBenchDate(Date benchDate) {
		this.benchDate = benchDate;
	}

	public Date getConfigMonth() {
		return configMonth;
	}

	public void setConfigMonth(Date configMonth) {
		this.configMonth = configMonth;
	}

	public String getGenDateStr() {
		return genDate == null?"":DateUtil.format(genDate);
	}

	public String getBenchDateStr() {
		return benchDate == null?"":DateUtil.format(benchDate);
	}

	public String getConfigMonthStr() {
		return configMonth == null?"":DateUtil.format(configMonth,"yyyy-MM");
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getTotalRatio() {
		return totalRatio;
	}

	public String getTotalRatioStr() {
		return totalRatio == null||BigDecimal.ZERO.compareTo(totalRatio)==0?"":ArithmeticUtil.multiply(totalRatio, new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString()+"%";
	}

	public void setTotalRatio(BigDecimal totalRatio) {
		this.totalRatio = totalRatio;
	}

	public BigDecimal getCurPerAmt() {
		return curPerAmt;
	}

	public void setCurPerAmt(BigDecimal curPerAmt) {
		this.curPerAmt = curPerAmt;
	}

	public BigDecimal getCurEndAmt() {
		return curEndAmt;
	}

	public void setCurEndAmt(BigDecimal curEndAmt) {
		this.curEndAmt = curEndAmt;
	}

	public BigDecimal getCurInvAmt() {
		return curInvAmt;
	}

	public void setCurInvAmt(BigDecimal curInvAmt) {
		this.curInvAmt = curInvAmt;
	}

    public String getAssetTypeStr() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.资产大类, getAssetType())) ? SysDataUtil.getNameFromDataDict(DictCode.资产大类, getAssetType()):"";
    }

}
