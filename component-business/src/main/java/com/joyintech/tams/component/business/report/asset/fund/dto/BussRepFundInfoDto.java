/*
 * 文件名：BussRepFundInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.fund.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepFund;
import com.joyintech.tams.component.business.report.asset.fund.valid.BussRepFundInfoValidator;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 基金资产中债报送信息组件Dto
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月18日
 * @see BussRepFundInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussRepFundInfoValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class BussRepFundInfoDto extends RepFund {
    /*
     *二级资产负债类别 
     */
    private String repAssetType;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;
    
    /**
     * 二级资产类型
     */
    private String type6;
    /**
     * 基金资产报送信息
     */
    private RepFund repFund;
    /**
     * 资产负债报送信息dto
     */
    private RepAssetDept repAssetDept;
    /**
     * 交易流通场所
     */
    private String repTradeMarket;

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

    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String repPrivateprdFlag;

    /**
     * 人行机构规模
     */
    private String repInstitSize;

    /**
     * 受让形式
     */
    private String repAssignee;
    
    /**
     * 是否由金融资产投资公司发行  中债新增字段 20200407
     */
    private String repFinancialComissue;
    
    public RepAssetDept getRepAssetDept() {
        return repAssetDept;
    }
    
    public void setRepAssetDept(RepAssetDept repAssetDept) {
        this.repAssetDept = repAssetDept;
    }

    
    public RepFund getRepFund() {
        return repFund;
    }

    
    public void setRepFund(RepFund repFund) {
        this.repFund = repFund;
    }

    public String getRepAssetType() {
        return repAssetType;
    }

    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }
    public boolean isGovInvest() {
        return !StringUtil.equals(DictCodeList.是否_否.getValue(), super.getRepIsGovInvest());
    }
    
    @Length(fieldName = "BussRepFundInfo.repFundInvestAsset", max = 200, groups = {ValidateGroupEdit.class})
    public String getRepFundInvestAsset(){
        return super.getRepFundInvestAsset();
    }
    
    @Length(fieldName = "BussRepFundInfo.repFundCompName", max = 100, groups = {ValidateGroupEdit.class})
    public String getRepFundCompName(){
        return super.getRepFundCompName();
    }
    
    @Length(fieldName = "BussRepFundInfo.repTrustPartyName", max = 100, groups = {ValidateGroupEdit.class})
    public String getRepTrustPartyName(){
        return super.getRepTrustPartyName();
    }

    
    /**
     * 获取 finprodType2
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    
    /**
     * 设置 finprodType2
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }

    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    
    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
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

    public String getRepPrivateprdFlag() {
        return repPrivateprdFlag;
    }

    public void setRepPrivateprdFlag(String repPrivateprdFlag) {
        this.repPrivateprdFlag = repPrivateprdFlag;
    }

    public String getRepInstitSize() {
        return repInstitSize;
    }

    public void setRepInstitSize(String repInstitSize) {
        this.repInstitSize = repInstitSize;
    }

    public String getRepAssignee() {
        return repAssignee;
    }

    public void setRepAssignee(String repAssignee) {
        this.repAssignee = repAssignee;
    }

	/**
	 * @return the repFinancialComissue
	 */
	public String getRepFinancialComissue() {
		return repFinancialComissue;
	}

	/**
	 * @param repFinancialComissue the repFinancialComissue to set
	 */
	public void setRepFinancialComissue(String repFinancialComissue) {
		this.repFinancialComissue = repFinancialComissue;
	}
}
