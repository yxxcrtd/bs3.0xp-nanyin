/*
 * 文件名：FinProductDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basicdata.asset.depo.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductBaseInfoDto;
import com.joyintech.service.valuation.component.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.common.base.entity.PaFinProductBuyoutRate;
import com.joyintech.tams.common.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 标的类金融产品DTO
 * @author zhangnian
 * @version 1.0 2018年9月6日
 * @see FinProduct
 * @since 1.0
 */
public class FinDepositTargetDto extends FinProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    private String elementId;

    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    
    /**
     * 资产一级分类
     */
    private String type6;
    
    /**
     * 资产二级分类
     */
    private String type7;
    // 增加type9、type10、type11、type12 赵东东 2019.7.23 start
   // 增加注释 赵东东 2019.7.26 start
    /**
     * 行内资产二级分类
     */
   // 增加注释 赵东东 2019.7.26 end
    private String type9;
 // 增加注释 赵东东 2019.7.26 start
    /**
     * 行内资产三级分类
     */
 // 增加注释 赵东东 2019.7.26 end
    private String type10;
 // 增加注释 赵东东 2019.7.26 start
    /**
     * 行内资产四级分类
     */
 // 增加注释 赵东东 2019.7.26 end
    private String type11; 
 // 增加注释 赵东东 2019.7.26 start 
    /**
     * 行内资产五级分类
     */
 // 增加注释 赵东东 2019.7.26 end
    private String type12;
    // 增加type9、type10、type11、type12 赵东东 2019.7.23 end
    /**
     * 日历名称
     */
    private String calendarName;
    
    /**
     * 交易对手类型---分行、通道
     */
    private String counterType;
    
    /**
     * 是否交易日历    
     */
    private String isTradeCalendar;
    /**
     * 资产提供行
     */
    private String assetProvider;
    
    /**
     * 资产提供行名称
     */
    private String assetProviderName;
    
    /**
     * 存款机构名
     */
    private String issuerName;
    
    /**
     * 资产推荐费率
     */
    private BigDecimal referRate;
    
    /**
     * 利率
     */
    private BigDecimal rate;
    
    /**
     * 计息要素
     */
    private FinInterestFactorDto finInterestFactorDto;
    /**
     *金融产品分类表Entity
     */
    private FinProductType finProductType;
    /**
     * 金融产品扩展Entity
     */
    private PaFinProduct paFinProduct;
    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 授信配置代码
     */
    private String creditMstId;

    /**
     * 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    private String customerId;

    /**
     * 批复流水号
     */
    private String pflsh;
    /**
     * 已占用额度，初始值为0
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal hapCreditAmt;

    /**
     * 实际授信主体
     */
    private String actualCreditProvid;
    /**
     * 是否占用主体授信
     */
    private String creditFlag;
    /**
     * 法审编号
     */
    private String trialNo;

    // 增加推荐方与推荐费率 add by renping 20191014 start
    /**
     * 推荐方
     */
    private String recommender;

    private String sponsorName;

	/**
     * 资产来源
     */
	private String assetSource;


    public String getAssetSource() {
		return assetSource;
	}

	public void setAssetSource(String assetSource) {
		this.assetSource = assetSource;
	}
	
    /**
     * 投后管理机构
     */
    private String postOrg;

    /**
     * 监管评级
     */
    private String regulatoryRating;


    /**
     * 取得 推荐方
     * @return 推荐方
     */
    public String getRecommender() {
        return recommender;
    }

    /**
     * 设置 推荐方
     * @param recommender 推荐方
     */
    public void setRecommender(String recommender) {
        this.recommender = recommender == null ? null : recommender.trim();
    }
    /**
     * 资产推荐方名称
     */
    private String recommenderName;

    /**
     * 买断利率
     */
    private BigDecimal buyOutRate;

    /**
     * 金融产品买断利率(平安个性)
     */
    private PaFinProductBuyoutRate paFinProductBuyoutRate;

    /**
     * 源起息日
     */
    private Date oldVdate;

    /**
     * 取得 资产推荐方名称
     * @return 资产推荐方名称
     */
    public String getRecommenderName() {
        return recommenderName;
    }

    /**
     * 设置 资产推荐方名称
     * 
     * @param recommenderName 资产推荐方名称
     */
    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommender == null ? null : recommenderName.trim();
    }
    // 增加推荐方与推荐费率 add by renping 20191014 end

    /**
     * 获取 creditFlag
     * @return creditFlag.
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    
    /**
     * 设置 creditFlag
     * @param creditFlag creditFlag
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    /**
     * 选中的记录
     */
    private transient List<FinDepositTargetDto> selectedItem;
    
    
    


    /**
     * 获取 paFinProduct
     * @return paFinProduct.
     */
    public PaFinProduct getPaFinProduct() {
        return paFinProduct;
    }




    /**
     * 设置 paFinProduct
     * @param paFinProduct paFinProduct
     */
    public void setPaFinProduct(PaFinProduct paFinProduct) {
        this.paFinProduct = paFinProduct;
    }



    /**
     * 获取 finInterestFactorDto
     * @return finInterestFactorDto.
     */
    public FinInterestFactorDto getFinInterestFactorDto() {
        return finInterestFactorDto;
    }



    /**
     * 设置 finInterestFactorDto
     * @param finInterestFactorDto finInterestFactorDto
     */
    public void setFinInterestFactorDto(FinInterestFactorDto finInterestFactorDto) {
        this.finInterestFactorDto = finInterestFactorDto;
    }


    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    /**
     * 获取 baseFinProductBaseInfoList
     * @return baseFinProductBaseInfoList.
     */
    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }

    
    /**
     * 设置 baseFinProductBaseInfoList
     * @param baseFinProductBaseInfoList baseFinProductBaseInfoList
     */
    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
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
    // 增加获取设置type9、获取设置type10、获取设置type11、获取设置type12 赵东东 2019.7.23 start
 // 增加注释 赵东东 2019.7.26 start
    /**
     * 获取 type9
     * @return type9.
     */
 // 增加注释 赵东东 2019.7.26 end
    public String getType9() {
        return type9;
    }
 // 增加注释 赵东东 2019.7.26 start
    /**
     * 设置 type9
     * @param type9 type9
     */
 // 增加注释 赵东东 2019.7.26 end
    public void setType9(String type9) {
        this.type9 = type9;
    }
 // 增加注释 赵东东 2019.7.26 start   
    /**
     * 获取 type10
     * @return type10.
     */
 // 增加注释 赵东东 2019.7.26 end
    public String getType10() {
        return type10;
    }
 // 增加注释 赵东东 2019.7.26 start
    /**
     * 设置 type10
     * @param type10 type10
     */
 // 增加注释 赵东东 2019.7.26 end
    public void setType10(String type10) {
        this.type10 = type10;
    }
 // 增加注释 赵东东 2019.7.26 start 
    /**
     * 获取 type11
     * @return type11.
     */
 // 增加注释 赵东东 2019.7.26 end
    public String getType11() {
        return type11;
    }
 // 增加注释 赵东东 2019.7.26 start
    /**
     * 设置 type11
     * @param type11 type11
     */
 // 增加注释 赵东东 2019.7.26 end
    public void setType11(String type11) {
        this.type11 = type11;
    }
 // 增加注释 赵东东 2019.7.26 start 
    /**
     * 获取 type12
     * @return type12.
     */
 // 增加注释 赵东东 2019.7.26 end
    public String getType12() {
        return type12;
    }
 // 增加注释 赵东东 2019.7.26 start
    /**
     * 设置 type12
     * @param type12 type12
     */
 // 增加注释 赵东东 2019.7.26 end
    public void setType12(String type12) {
        this.type12 = type12;
    }
 // 增加获取设置type9、获取设置type10、获取设置type11、获取设置type12 赵东东 2019.7.23 end
    /**
     * 获取 calendarName
     * @return calendarName.
     */
    public String getCalendarName() {
        return calendarName;
    }

    
    /**
     * 设置 calendarName
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    
    /**
     * 获取 counterType
     * @return counterType.
     */
    public String getCounterType() {
        return counterType;
    }

    
    /**
     * 设置 counterType
     * @param counterType counterType
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

    
    /**
     * 获取 isTradeCalendar
     * @return isTradeCalendar.
     */
    public String getIsTradeCalendar() {
        return isTradeCalendar;
    }

    
    /**
     * 设置 isTradeCalendar
     * @param isTradeCalendar isTradeCalendar
     */
    public void setIsTradeCalendar(String isTradeCalendar) {
        this.isTradeCalendar = isTradeCalendar;
    }

    
    /**
     * 获取 assetProvider
     * @return assetProvider.
     */
    public String getAssetProvider() {
        return assetProvider;
    }

    
    /**
     * 设置 assetProvider
     * @param assetProvider assetProvider
     */
    public void setAssetProvider(String assetProvider) {
        this.assetProvider = assetProvider;
    }

    
    /**
     * 获取 assetProviderName
     * @return assetProviderName.
     */
    public String getAssetProviderName() {
        return assetProviderName;
    }

    
    /**
     * 设置 assetProviderName
     * @param assetProviderName assetProviderName
     */
    public void setAssetProviderName(String assetProviderName) {
        this.assetProviderName = assetProviderName;
    }

    
    /**
     * 获取 issuerName
     * @return issuerName.
     */
    public String getIssuerName() {
        return issuerName;
    }

    
    /**
     * 设置 issuerName
     * @param issuerName issuerName
     */
    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    
    /**
     * 获取 finProductType
     * @return finProductType.
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    
    /**
     * 设置 finProductType
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinDepositTargetDto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinDepositTargetDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 referRate
     * @return referRate.
     */
    public BigDecimal getReferRate() {
        return referRate;
    }




    
    /**
     * 设置 referRate
     * @param referRate referRate
     */
    public void setReferRate(BigDecimal referRate) {
        this.referRate = referRate;
    }




    





    
    /**
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }




    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }




    
    /**
     * 获取 seqno
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }




    
    /**
     * 设置 seqno
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }




    
    /**
     * 获取 creditMstId
     * @return creditMstId.
     */
    public String getCreditMstId() {
        return creditMstId;
    }




    
    /**
     * 设置 creditMstId
     * @param creditMstId creditMstId
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId;
    }




    
    /**
     * 获取 customerId
     * @return customerId.
     */
    public String getCustomerId() {
        return customerId;
    }




    
    /**
     * 设置 customerId
     * @param customerId customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }




    
    /**
     * 获取 pflsh
     * @return pflsh.
     */
    public String getPflsh() {
        return pflsh;
    }




    
    /**
     * 设置 pflsh
     * @param pflsh pflsh
     */
    public void setPflsh(String pflsh) {
        this.pflsh = pflsh;
    }




    
    /**
     * 获取 hapCreditAmt
     * @return hapCreditAmt.
     */
    public BigDecimal getHapCreditAmt() {
        return hapCreditAmt;
    }




    
    /**
     * 设置 hapCreditAmt
     * @param hapCreditAmt hapCreditAmt
     */
    public void setHapCreditAmt(BigDecimal hapCreditAmt) {
        this.hapCreditAmt = hapCreditAmt;
    }




    
    /**
     * 获取 actualCreditProvid
     * @return actualCreditProvid.
     */
    public String getActualCreditProvid() {
        return actualCreditProvid;
    }




    
    /**
     * 设置 actualCreditProvid
     * @param actualCreditProvid actualCreditProvid
     */
    public void setActualCreditProvid(String actualCreditProvid) {
        this.actualCreditProvid = actualCreditProvid;
    }

    public String getTrialNo() {
        return trialNo;
    }

    public void setTrialNo(String trialNo) {
        this.trialNo = trialNo;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getPostOrg() {
        return postOrg;
    }

    public void setPostOrg(String postOrg) {
        this.postOrg = postOrg;
    }

    public String getRegulatoryRating() {
        return regulatoryRating;
    }

    public void setRegulatoryRating(String regulatoryRating) {
        this.regulatoryRating = regulatoryRating;
    }

    public BigDecimal getBuyOutRate() {
        return buyOutRate;
    }

    public void setBuyOutRate(BigDecimal buyOutRate) {
        this.buyOutRate = buyOutRate;
    }

    public PaFinProductBuyoutRate getPaFinProductBuyoutRate() {
        return paFinProductBuyoutRate;
    }

    public void setPaFinProductBuyoutRate(PaFinProductBuyoutRate paFinProductBuyoutRate) {
        this.paFinProductBuyoutRate = paFinProductBuyoutRate;
    }

    public Date getOldVdate() {
        return oldVdate;
    }

    public void setOldVdate(Date oldVdate) {
        this.oldVdate = oldVdate;
    }


    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 数据来源
     */
    private String dataSource;


    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getIntType() {
        return intType;
    }

    public void setIntType(String intType) {
        this.intType = intType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * 核算分类
     */
    private String accountintType;

    /**
     * 税务分类
     */
    private String taxType;

    /**
     * 机构核算分类
     */
    private String orgClass;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 核算分类名称
     */
    private String accountingClassName;


    public String getAccountintType() {
        return accountintType;
    }

    public void setAccountintType(String accountintType) {
        this.accountintType = accountintType;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getOrgClass() {
        return orgClass;
    }

    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getAccountingClassName() {
        return accountingClassName;
    }

    public void setAccountingClassName(String accountingClassName) {
        this.accountingClassName = accountingClassName;
    }
}
