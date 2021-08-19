/*
 * 文件名：ImplAsset.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetInfo;

/**
 * 资产信息实现类
 * 
 * @author 裴家俊
 * @version 1.0 2019年10月2日
 * @see ImplAsset
 * @since 1.0
 */
public class ImplAsset extends FinProduct implements IAssetInfo {

    /**
     * 投资性质
     */
    private String investNature;

    /**
     * 分类4（wind一级类型）
     */
    private String type4;

    /**
     * 分类5（wind二级类型）
     */
    private String type5;

    /**
     * 分类6（中信资产大类）
     */
    private String type6;

    /**
     * 分类7（中信资产二级类型）
     */
    private String type7;

    /**
     * wind行业一级分类
     */
    private String industry;

    /**
     * wind行业二级分类
     */
    private String industry2;

    /**
     * 监管一级分类
     */
    private String repAssetDept;

    /**
     * 监管二级分类
     */
    private String repAssetDept2;

    /**
     * 债项(外部)评级结果
     */
    private String  bondGradeType;

    /**
     * 主体(外部)评级结果
     */
    private String  bodyGradeType;

    /**
     * 行内二级类型
     */
    private String  type9;

    /**
     * 行内三级类型
     */
    private String  type10;

    /**
     * 名单类型
     */
    private String listLevel;

    /**
     * 发行机构(托管人，融资人，管理人等)
     */
    private String issuerCode;

    /**
     * 投管二级类型
     */
    private String typeSeven;

    private String isInAdvance;

    /**
     * 是否未上市股权
     */
    private String isUnEquity;

    public String getIsInAdvance() {
		return isInAdvance;
	}

	public void setIsInAdvance(String isInAdvance) {
		this.isInAdvance = isInAdvance;
	}

	/**
     * 是否有关联方(Y或N)
     */
    private String isCorOrg;

    @Override
    public String getAssetType() {
        return this.type6;
    }

    @Override
    public String getAssetCode() {
        return this.getFinprodId();
    }

    @Override
    public String getAssetName() {
        return this.getFinprodAbbr();
    }

    @Override
    public String getAssetType2() {
        return type7;
    }

    @Override
    public String getType1() {
        return repAssetDept;
    }

    @Override
    public String getType2() {
        return repAssetDept2;
    }

    @Override
    public String getWindType1() {
        return type4;
    }

    @Override
    public String getWindType2() {
        return type5;
    }

    @Override
    public String getInvestType() {
        return investNature;
    }

    @Override
    public String getCalendarCode() {
        return this.getCalendarId();
    }

    @Override
    public String getMarketCode() {
        return this.getFinprodMarketId();
    }

    @Override
    public String getTradePlace() {
        return this.getTradeMarket();
    }

    public void setInvestNature(String investNature) {
        this.investNature = investNature;
    }

    public void setType4(String type4) {
        this.type4 = type4;
    }

    public void setType5(String type5) {
        this.type5 = type5;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public void setType7(String type7) {
        this.type7 = type7;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndustry() {
        return industry;
    }

    public void setIndustry2(String industry2) {
        this.industry2 = industry2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndustry2() {
        return industry2;
    }

    public void setRepAssetDept(String repAssetDept) {
        this.repAssetDept = repAssetDept;
    }

    public void setRepAssetDept2(String repAssetDept2) {
        this.repAssetDept2 = repAssetDept2;
    }

    @Override
    public String getBondGradeType() {
        return bondGradeType;
    }

    public void setBondGradeType(String bondGradeType) {
        this.bondGradeType = bondGradeType;
    }

    @Override
    public String getBodyGradeType() {
    	if(bodyGradeType==null) {
    		return "00";
    	}
        return bodyGradeType;
    }

    public void setBodyGradeType(String bodyGradeType) {
        this.bodyGradeType = bodyGradeType;
    }

    @Override
    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    @Override
    public String getType9() {
        return type9;
    }

    @Override
    public String getType10() {
        return type10;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public void setType10(String type10) {
        this.type10 = type10;
    }

    @Override
    public String getListLevel() {
        return listLevel;
    }

    public void setListLevel(String listLevel) {
        this.listLevel = listLevel;
    }

    @Override
    public String getIsCorOrg() {
        return isCorOrg;
    }

    public void setIsCorOrg(String isCorOrg) {
        this.isCorOrg = isCorOrg;
    }

    // add by zhangrui1 增加资产属性 start
    /**
     * 最终利率调整期开始日
     */
    private Date vdateLast;

    /**
     * 设置 最终利率调整期开始日
     * 
     * @param vdateLast 最终利率调整期开始日
     */
    public void setVdateLast(Date vdateLast) {
        this.vdateLast = vdateLast;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getVdateLast() {
        return vdateLast;
    }

    /**
     * 利率类型
     */
    private String calcType;

    /**
     * 设置 利率类型
     * 
     * @param calcType 利率类型
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalcType() {
        return calcType;
    }
    // add by zhangrui1 增加资产属性 end

    // add by etliu 增加资产属性:是否可提前支取 20200821 start
    /**
     * 是否可提前支取
     */
    private String isCanDrawInAdvance;

    /**
     * 取得 是否可提前支取
     *
     * @return 是否可提前支取
     */
    @Override
    public String getIsCanDrawInAdvance() {
        return isCanDrawInAdvance;
    }

    /**
     * 设置是否可提前支取
     * @param isCanDrawInAdvance
     */
    public void setIsCanDrawInAdvance(String isCanDrawInAdvance) {
        this.isCanDrawInAdvance = isCanDrawInAdvance;
    }
    // add by etliu 增加资产属性:是否可提前支取 20200821 end
    // add by zhangrui1 增加资产属性:是否流动性受限资产 start
    /**
     * 是否流动性受限
     */
    private String isFlowLimited;

    /**
     * 获取 是否流动性受限
     * 
     * @return 是否流动性受限.
     */
    public String getIsFlowLimited() {
        return isFlowLimited;
    }

    /**
     * 设置 是否流动性受限
     * 
     * @param isFlowLimited 是否流动性受限
     */
    public void setIsFlowLimited(String isFlowLimited) {
        this.isFlowLimited = isFlowLimited;
    }
    // add by zhangrui1 增加资产属性:是否流动性受限 end
    // add by zhangrui1 增加资产属性:机构主体类型 start
    /**
     * 机构主体类型
     */
    private String customerObjType;

    /**
     * 获取 机构主体类型
     * 
     * @return 机构主体类型.
     */
    public String getCustomerObjType() {
        return customerObjType;
    }

    /**
     * 设置 机构主体类型
     * 
     * @param customerObjType 机构主体类型
     */
    public void setCustomerObjType(String customerObjType) {
        this.customerObjType = customerObjType;
    }
    // add by zhangrui1 增加资产属性:机构主体类型 end

    public String getTypeSeven() {
        return typeSeven;
    }

    public void setTypeSeven(String typeSeven) {
        this.typeSeven = typeSeven;
    }

    public String getIsUnEquity() {
        return isUnEquity;
    }

    public void setIsUnEquity(String isUnEquity) {
        this.isUnEquity = isUnEquity;
    }
}
