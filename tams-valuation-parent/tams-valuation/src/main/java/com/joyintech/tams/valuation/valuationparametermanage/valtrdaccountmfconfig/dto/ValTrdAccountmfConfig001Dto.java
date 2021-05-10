/*
 * 文件名：ValTrdAccountmfConfig001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2021年3月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.validator.ValTrdAccountmfConfig001DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 费用预提信息配置表 dto
 * 
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001Dto
 * @since 1.0
 */
@PageId("ValTrdAccountmfConfig002")
@ValidLogic(targetClass = ValTrdAccountmfConfig001DtoValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ValTrdAccountmfConfig001Dto extends BaseEntity implements ProcessEntity {

	private static final long serialVersionUID = 1L;

	/**
     * 流水号
     */
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "流水号")
    private String seqNo;

    /**
     * 费用品种
     */
    @NotEmpty(fieldName = "费用品种")
    private String feeType;

    /**
     * 账套代码
     */
    @NotEmpty(fieldName = "账套代码")
    private String booksetId;

    /**
     * 登记托管机构
     */
    @NotEmpty(fieldName = "登记托管机构")
    private String registOrg;

    /**
     * 支付频率
     */
    @NotEmpty(fieldName = "支付频率")
    private String payCycle;

    /**
     * 计提开始日
     */
    @NotEmpty(fieldName = "计提开始日")
    private String positionStartDate;

    /**
     * 计提结束日
     */
    @NotEmpty(fieldName = "计提结束日")
    private String positionEndDate;

    /**
     * 区间费用金额
     */
    @NotEmpty(fieldName = "区间费用金额")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmtTotal;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 费用品种
     * @return 费用品种
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用品种
     * @param feeType 费用品种
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }
    
    /**
     * 获取 费用品种数据字典
     * 
     * @return 费用品种数据字典
     */
    public String getFeeTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型, feeType))?
    			SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型, feeType):feeType;
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }
    
    /**
     * 获取 登记托管机构数据字典
     * 
     * @return 登记托管机构数据字典
     */
    public String getRegistOrgFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, registOrg))?
    			SysDataUtil.getNameFromDataDict(DictCode.登记托管机构, registOrg):registOrg;
    }

    /**
     * 取得 支付频率
     * @return 支付频率
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 支付频率
     * @param payCycle 支付频率
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle == null ? null : payCycle.trim();
    }
    
    /**
     * 获取 支付频率数据字典
     * 
     * @return 支付频率数据字典
     */
    public String getPayCycleFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.频率, payCycle))?
    			SysDataUtil.getNameFromDataDict(DictCode.频率, payCycle):payCycle;
    }

    /**
     * 取得 计提开始日
     * @return 计提开始日
     */
    public String getPositionStartDate() {
        return positionStartDate;
    }

    /**
     * 设置 计提开始日
     * @param positionStartDate 计提开始日
     */
    public void setPositionStartDate(String positionStartDate) {
        this.positionStartDate = positionStartDate;
    }

    /**
     * 取得 计提结束日
     * @return 计提结束日
     */
    public String getPositionEndDate() {
        return positionEndDate;
    }

    /**
     * 设置 计提结束日
     * @param positionEndDate 计提结束日
     */
    public void setPositionEndDate(String positionEndDate) {
        this.positionEndDate = positionEndDate;
    }

    /**
     * 取得 区间费用金额
     * @return 区间费用金额
     */
    public BigDecimal getFeeAmtTotal() {
        return feeAmtTotal;
    }

    /**
     * 设置 区间费用金额
     * @param feeAmtTotal 区间费用金额
     */
    public void setFeeAmtTotal(BigDecimal feeAmtTotal) {
        this.feeAmtTotal = feeAmtTotal;
    }
    
    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }
}
