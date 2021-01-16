/*
 * 文件名：BussRepAssetMngProdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;

import java.math.BigDecimal;

/**
 * 资产管理计划报送信息组件Dto
 *
 * @author liuwei
 * @version 1.0 2018年9月18日
 * @see BussRepAssetMngProdInfo
 * @since 1.0
 */
public class SvrBussRepAssetMngProdInfoDto extends BaseEntity {

    private boolean needValid = true;

    /**
     * 获取 needValid
     *
     * @return needValid
     */
    public boolean isNeedValid() {
        return needValid;
    }

    /**
     * 设置 needValid
     *
     * @param needValid needValid
     */
    public void setNeedValid(boolean needValid) {
        this.needValid = needValid;
    }

    /**
     * 交易流通场所
     */
    private String repTradeMarket       ;
    /**
     * 二级资产类别
     */
    private String repAssetType         ;
    /**
     * 资管计划发起人机构编码
     */
    private String repPartyCode         ;
    /**
     * 资管计划登记编码
     */
    private String repRegistCode        ;
    /**
     * 资金实际投向
     */
    private String repCapiInvest        ;
    /**
     * 资金运用方式
     */
    private String repCapUseWay         ;
    /**
     * 资金运用行业
     */
    private String repCapUseIndu        ;
    /**
     * 资金运用行业二级分类
     */
    private String repCapUseInduLevel2  ;
    /**
     * 资管计划属性
     */
    private String repPlanProp          ;
    /**
     * 是否有预期收益率
     */
    private String repYieldFlag ;

    /**
     * 是否投向金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    private String repPrivatefundFlag;

    /**
     * 公共PPP
     */
    private String repPppflag;

    /**
     * 是否小微企业信贷资产流转和收益权转让
     */
    private String repSmallcredit;

    /**
     * 是否投向投向市场化债转股相关产品的部分
     */
    private String repBondtostockFlag;


    public boolean isRepYield(){return StringUtil.equals(DictCodeList.是否_是.getValue(),this.getRepYieldFlag());}
/*新加字段 add by zhuyongkang 2019/10/08 start*/
    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag ;

    //public boolean isRepPrivateprdFlag(){return StringUtil.equals(DictCodeList.是否_是.getValue(),this.getRepPrivateprdFlag());}

    /**
     * 获取 repPrivateprdFlag
     *
     * @return repPrivateprdFlag
     */
    public String getRepPrivateprdFlag() {
        
            return repPrivateprdFlag;
        
    }
    /**
     * 设置 repPrivateprdFlag
     *
     * @param repPrivateprdFlag repPrivateprdFlag
     */
    public void setRepPrivateprdFlag(String repPrivateprdFlag) {
        this.repPrivateprdFlag = repPrivateprdFlag;
    }
    /*新加字段 add by zhuyongkang 2019/10/08 END*/
    /**
     * 预期最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxRate		;
    /**
     * 预期最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinRate 		;
    /**
     * 购买结构
     */

    private String repBuyStruct 		;
    /**
     * 管理方式
     */
    private String repManageType 		;
    /**
     * 是否掌控计划底层资产详情
     */

    private String repIsCtrlBottom  	;

    public boolean isRepIsCtrlBottomFlag(){return StringUtil.equals(DictCodeList.是否_是.getValue(),this.getRepIsCtrlBottom());}

    /**
     * 管理费率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal repMgrFeeRate 	;
    /**
     * 托管费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTruFeeRate  	;
    /**
     * 交易相关合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTradeFeeRate  ;
    /**
     * 中介服务机构合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAgencyFeeRate ;
    /**
     * 其他合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherFeeRate  ;
    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmt ;
    /**
     * 备注
     */
    private String repRemark ;
    
    /**
     * 是否由金融资产投资公司发行
     */
    private String repFinancialComissue;

    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
    }

    public String getRepAssetType() {
        return repAssetType;
    }

    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    public String getRepPartyCode() {
        return repPartyCode;
    }

    public void setRepPartyCode(String repPartyCode) {
        this.repPartyCode = repPartyCode;
    }

    public String getRepRegistCode() {
        return repRegistCode;
    }

    public void setRepRegistCode(String repRegistCode) {
        this.repRegistCode = repRegistCode;
    }

    public String getRepCapiInvest() {
        return repCapiInvest;
    }

    public void setRepCapiInvest(String repCapiInvest) {
        this.repCapiInvest = repCapiInvest;
    }

    public String getRepCapUseWay() {
        return repCapUseWay;
    }

    public void setRepCapUseWay(String repCapUseWay) {
        this.repCapUseWay = repCapUseWay;
    }

    public String getRepCapUseIndu() {
        return repCapUseIndu;
    }

    public void setRepCapUseIndu(String repCapUseIndu) {
        this.repCapUseIndu = repCapUseIndu;
    }

    public String getRepCapUseInduLevel2() {
        return repCapUseInduLevel2;
    }

    public void setRepCapUseInduLevel2(String repCapUseInduLevel2) {
        this.repCapUseInduLevel2 = repCapUseInduLevel2;
    }

    public String getRepPlanProp() {
        return repPlanProp;
    }

    public void setRepPlanProp(String repPlanProp) {
        this.repPlanProp = repPlanProp;
    }

    public String getRepYieldFlag() {
        return repYieldFlag;
    }

    public void setRepYieldFlag(String repYieldFlag) {
        this.repYieldFlag = repYieldFlag;
    }

    public String getRepPrivatefundFlag() {
        return repPrivatefundFlag;
    }

    public void setRepPrivatefundFlag(String repPrivatefundFlag) {
        this.repPrivatefundFlag = repPrivatefundFlag;
    }

    public String getRepPppflag() {
        return repPppflag;
    }

    public void setRepPppflag(String repPppflag) {
        this.repPppflag = repPppflag;
    }

    public String getRepSmallcredit() {
        return repSmallcredit;
    }

    public void setRepSmallcredit(String repSmallcredit) {
        this.repSmallcredit = repSmallcredit;
    }

    public String getRepBondtostockFlag() {
        return repBondtostockFlag;
    }

    public void setRepBondtostockFlag(String repBondtostockFlag) {
        this.repBondtostockFlag = repBondtostockFlag;
    }

    public BigDecimal getRepMaxRate() {
        return repMaxRate;
    }

    public void setRepMaxRate(BigDecimal repMaxRate) {
        this.repMaxRate = repMaxRate;
    }

    public BigDecimal getRepMinRate() {
        return repMinRate;
    }

    public void setRepMinRate(BigDecimal repMinRate) {
        this.repMinRate = repMinRate;
    }

    public String getRepBuyStruct() {
        return repBuyStruct;
    }

    public void setRepBuyStruct(String repBuyStruct) {
        this.repBuyStruct = repBuyStruct;
    }

    public String getRepManageType() {
        return repManageType;
    }

    public void setRepManageType(String repManageType) {
        this.repManageType = repManageType;
    }

    public String getRepIsCtrlBottom() {
        return repIsCtrlBottom;
    }

    public void setRepIsCtrlBottom(String repIsCtrlBottom) {
        this.repIsCtrlBottom = repIsCtrlBottom;
    }

    public BigDecimal getRepMgrFeeRate() {
        return repMgrFeeRate;
    }

    public void setRepMgrFeeRate(BigDecimal repMgrFeeRate) {
        this.repMgrFeeRate = repMgrFeeRate;
    }

    public BigDecimal getRepTruFeeRate() {
        return repTruFeeRate;
    }

    public void setRepTruFeeRate(BigDecimal repTruFeeRate) {
        this.repTruFeeRate = repTruFeeRate;
    }

    public BigDecimal getRepTradeFeeRate() {
        return repTradeFeeRate;
    }

    public void setRepTradeFeeRate(BigDecimal repTradeFeeRate) {
        this.repTradeFeeRate = repTradeFeeRate;
    }

    public BigDecimal getRepAgencyFeeRate() {
        return repAgencyFeeRate;
    }

    public void setRepAgencyFeeRate(BigDecimal repAgencyFeeRate) {
        this.repAgencyFeeRate = repAgencyFeeRate;
    }

    public BigDecimal getRepOtherFeeRate() {
        return repOtherFeeRate;
    }

    public void setRepOtherFeeRate(BigDecimal repOtherFeeRate) {
        this.repOtherFeeRate = repOtherFeeRate;
    }

    public BigDecimal getRepAmt() {
        return repAmt;
    }

    public void setRepAmt(BigDecimal repAmt) {
        this.repAmt = repAmt;
    }

    public String getRepRemark() {
        return repRemark;
    }

    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark;
    }

    public String getRepFinancialComissue() {
        return repFinancialComissue;
    }

    public void setRepFinancialComissue(String repFinancialComissue) {
        this.repFinancialComissue = repFinancialComissue;
    }
}
