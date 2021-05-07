/*
 * 文件名：BussFinBondRepInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset.SvrBussFinBondRepInfoValidator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  资产基本信息_同业拆放拆入类信息DTO
 * 〈功能详细描述〉
 * @author
 * @version 1.0
 * @see SvrBussRepPlacementInfoDto
 * @since 1.0
 */
public class SvrBussRepPlacementInfoDto extends RepAssetBond{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     *行内资产/负债编码
     */
    private String assetCode;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 交易流通场所
     */
    private String repTradeMarket;

    /**
     * 币种 参考数据字典：CURRENCY_CHB
     */
    private String ccy;

    /**
     * 对手方
     */
    private String rivalParty;

    /**
     * 对手方类型
     */
    private String rivalPartyType;

    /**
     * 收益率/年利率
     */
    private BigDecimal assetYield;


    /**
     * 计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360
     */
    private String basis;


    /**
     * 回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他
     */
    private String targetType1;


    /**
     * 回购标的金额
     */
    private BigDecimal targetAmt;

    /**
     * 交易对手代码
     */
    private String rivalPartyCode;

    /**
     * 新人行资产一级分类
     */
    private String pbcAssetType1;

    /**
     * 新人行资产二级分类
     */
    private String pbcAssetType2;

    /**
     * 新人行资产三级分类
     */
    private String pbcAssetType3;

    public String getRivalPartyCode() {
        return rivalPartyCode;
    }

    public void setRivalPartyCode(String rivalPartyCode) {
        this.rivalPartyCode = rivalPartyCode;
    }

    public String getPbcAssetType1() {
        return pbcAssetType1;
    }

    public void setPbcAssetType1(String pbcAssetType1) {
        this.pbcAssetType1 = pbcAssetType1;
    }

    public String getPbcAssetType2() {
        return pbcAssetType2;
    }

    public void setPbcAssetType2(String pbcAssetType2) {
        this.pbcAssetType2 = pbcAssetType2;
    }

    public String getPbcAssetType3() {
        return pbcAssetType3;
    }

    public void setPbcAssetType3(String pbcAssetType3) {
        this.pbcAssetType3 = pbcAssetType3;
    }

    /**
     * 备注
     */
    private String remark;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getRivalParty() {
        return rivalParty;
    }

    public void setRivalParty(String rivalParty) {
        this.rivalParty = rivalParty;
    }

    public String getRivalPartyType() {
        return rivalPartyType;
    }

    public void setRivalPartyType(String rivalPartyType) {
        this.rivalPartyType = rivalPartyType;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getTargetType1() {
        return targetType1;
    }

    public void setTargetType1(String targetType1) {
        this.targetType1 = targetType1;
    }

    public BigDecimal getTargetAmt() {
        return targetAmt;
    }

    public void setTargetAmt(BigDecimal targetAmt) {
        this.targetAmt = targetAmt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
