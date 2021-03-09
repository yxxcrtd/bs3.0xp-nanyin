/*
 * 文件名：BussRepEquityInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dto;

import com.joyintech.tams.common.base.entity.RepAssetEquity;

/**
 *  权益类--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月31日
 * @see BussRepEquityInfoDto
 * @since 1.0
 */
public class BussRepEquityInfoDto extends RepAssetEquity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private String type6;
    
    private String type7;
    
    /**
     * 资产/负债类别
     */
    private String repAssetType;
    
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
     * 是否政府出资产业投资基金
     */
    private String isUnlisted;
    /**
     * 获取 repAssetType
     * @return repAssetType.
     */
    public String getRepAssetType() {
        return repAssetType;
    }

    
    /**
     * 设置 repAssetType
     * @param repAssetType repAssetType
     */
    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    
    /**
     * 获取 repTradeMarket
     * @return repTradeMarket.
     */
    public String getRepTradeMarket() {
        return repTradeMarket;
    }

    
    /**
     * 设置 repTradeMarket
     * @param repTradeMarket repTradeMarket
     */
    public void setRepTradeMarket(String repTradeMarket) {
        this.repTradeMarket = repTradeMarket;
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


    
    /**
     * 获取 type7
     * @return type7.
     */
    public String getType7() {
        return type7;
    }


    
    /**
     * 设置 type7
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }

    /**
     * 重写父类这几个方法，让finStockInfo能够存值,必填项校验
     * 作者：叶刚
     * 时间：2019/7/23
     */
    private String repSharesId;
    private String repPartyType1;
    private String repPartyType2;
    private String repPartyType3;
    private String repRemark;
    /**
     * 股票类型
     */
    private String repSharesType;

    /**
     * 行业
     */
    private String repIndustry;

    /**
     * 行业二级分类
     */
    private String repIndustryLevel2;
    
    /**
     * 是否为债转股
     */
    private String repDeptEquity;
    
    /**
     * 商业银行优先股
     */
    private String repBankSecPrefer;
    
    public String getRepDeptEquity() {
		return repDeptEquity;
	}

	public void setRepDeptEquity(String repDeptEquity) {
		this.repDeptEquity = repDeptEquity;
	}

	public String getRepBankSecPrefer() {
		return repBankSecPrefer;
	}

	public void setRepBankSecPrefer(String repBankSecPrefer) {
		this.repBankSecPrefer = repBankSecPrefer;
	}


    @Override
    public String getRepSharesId() {
        return repSharesId;
    }

    @Override
    public void setRepSharesId(String repSharesId) {
        this.repSharesId = repSharesId;
    }

    @Override
    public String getRepPartyType1() {
        return repPartyType1;
    }

    @Override
    public void setRepPartyType1(String repPartyType1) {
        this.repPartyType1 = repPartyType1;
    }

    @Override
    public String getRepPartyType2() {
        return repPartyType2;
    }

    @Override
    public void setRepPartyType2(String repPartyType2) {
        this.repPartyType2 = repPartyType2;
    }

    @Override
    public String getRepPartyType3() {
        return repPartyType3;
    }

    @Override
    public void setRepPartyType3(String repPartyType3) {
        this.repPartyType3 = repPartyType3;
    }

    @Override
    public String getRepRemark() {
        return repRemark;
    }

    @Override
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark;
    }

    @Override
    public String getRepSharesType() {
        return repSharesType;
    }

    @Override
    public void setRepSharesType(String repSharesType) {
        this.repSharesType = repSharesType;
    }

    @Override
    public String getRepIndustry() {
        return repIndustry;
    }

    @Override
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry;
    }

    @Override
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    @Override
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2;
    }
    /**
     * 以上为股票标的（finStockInfo）页面check，获取值的重写父类方法
     */
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


	public String getIsUnlisted() {
		return isUnlisted;
	}


	public void setIsUnlisted(String isUnlisted) {
		this.isUnlisted = isUnlisted;
	}
}
