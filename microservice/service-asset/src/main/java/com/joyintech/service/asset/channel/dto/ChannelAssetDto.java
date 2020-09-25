/*
 * 文件名：ChannelAssetQueryDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.channel.dto;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 通道资产查询
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月13日
 * @see ChannelAssetDto
 * @since 1.0
 */
public class ChannelAssetDto extends FinProduct{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4181973935250373700L;
    

    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 通道名称
     */
    private String secAcctName;

    /**
     * 持仓类型
     */
    private String holdingType;

    /**
     * 日期
     */
    private String cdate;
    
    /**
     * 资产一级类型
     */
    private String type8;
    
    /**
     * 资产二级类型
     */
    private String type6;
    
    /**
     * 持仓：持仓表中的数量
     */
    private String amount;
    
    /**
     * 划款方式
     */
    private String payType;

    /**
     * 收取方式
     */
    private String collectType;

    /**
     * 资金账户
     */
    private String busiAcctNo;
    
    /**
     * 旧资金账户
     */
    private String oldBusiAcctNo;
    
    /**
     * 登记编号
     */
    private String prodRegistCode;
    
    /**
     * 基础组件-标的金融产品
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    
    /**
     * 金融产品账户
     */
    private StlFinProductAcct stlFinProductAcct;
    
    /**
     * 资产负债报送信息entity
     */
    private RepAssetDept repAssetDept;
    /**
     * 资产管理计划报送信息entity
     */
    private RepAssetMngPlan repAssetMngPlan;
    
    /**
     * 管理人名称
     */
    private String managerName;
    
    /**
     * 通道费用类型
     */
    private String feeName;
    
    /**
     * 托管人名称
     */
    private String trusteeName;

    // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
    /**
     * 增值税税率
     */
    private BigDecimal vatTaxRate;

    /**
     * 附加税税率
     */
    private BigDecimal adtTaxRate;

    /**
     * 获取 vatTaxRate
     * 
     * @return vatTaxRate.
     */
    public BigDecimal getVatTaxRate() {
        return vatTaxRate;
    }

    /**
     * 设置 vatTaxRate
     * 
     * @param vatTaxRate vatTaxRate
     */
    public void setVatTaxRate(BigDecimal vatTaxRate) {
        this.vatTaxRate = vatTaxRate;
    }

    /**
     * 获取 adtTaxRate
     * 
     * @return adtTaxRate.
     */
    public BigDecimal getAdtTaxRate() {
        return adtTaxRate;
    }

    /**
     * 设置 adtTaxRate
     * 
     * @param adtTaxRate adtTaxRate
     */
    public void setAdtTaxRate(BigDecimal adtTaxRate) {
        this.adtTaxRate = adtTaxRate;
    }
    // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end

    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
 
    /**
     * 获取 type8
     * @return type8.
     */
    public String getType8() {
        return type8;
    }

    /**
     * 设置 type8
     * @param type8 type8
     */
    public void setType8(String type8) {
        this.type8 = type8;
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
     * 获取 amount
     * @return amount.
     */
    public String getAmount() {
        return amount;
    }
    
    /**
     * 设置 amount
     * @param amount amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }


    /**
     * 获取 secAcctName
     * @return secAcctName.
     */
    public String getSecAcctName() {
        return secAcctName;
    }


    /**
     * 设置 secAcctName
     * @param secAcctName secAcctName
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }


    
    


    
    
    /**
     * 获取 payType
     * @return payType.
     */
    public String getPayType() {
        return payType;
    }


    
    /**
     * 设置 payType
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }


    /**
     * 获取 busiAcctNo
     * @return busiAcctNo.
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }


    
    /**
     * 设置 busiAcctNo
     * @param busiAcctNo busiAcctNo
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }


    
    /**
     * 获取 prodRegistCode
     * @return prodRegistCode.
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }


    
    /**
     * 设置 prodRegistCode
     * @param prodRegistCode prodRegistCode
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
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
     * 获取 stlFinProductAcct
     * @return stlFinProductAcct.
     */
    public StlFinProductAcct getStlFinProductAcct() {
        return stlFinProductAcct;
    }


    
    /**
     * 设置 stlFinProductAcct
     * @param stlFinProductAcct stlFinProductAcct
     */
    public void setStlFinProductAcct(StlFinProductAcct stlFinProductAcct) {
        this.stlFinProductAcct = stlFinProductAcct;
    }


    
    /**
     * 获取 oldBusiAcctNo
     * @return oldBusiAcctNo.
     */
    public String getOldBusiAcctNo() {
        return oldBusiAcctNo;
    }


    
    /**
     * 设置 oldBusiAcctNo
     * @param oldBusiAcctNo oldBusiAcctNo
     */
    public void setOldBusiAcctNo(String oldBusiAcctNo) {
        this.oldBusiAcctNo = oldBusiAcctNo;
    }


    
    /**
     * 获取 repAssetDept
     * @return repAssetDept.
     */
    public RepAssetDept getRepAssetDept() {
        return repAssetDept;
    }


    
    /**
     * 设置 repAssetDept
     * @param repAssetDept repAssetDept
     */
    public void setRepAssetDept(RepAssetDept repAssetDept) {
        this.repAssetDept = repAssetDept;
    }


    
    /**
     * 获取 repAssetMngPlan
     * @return repAssetMngPlan.
     */
    public RepAssetMngPlan getRepAssetMngPlan() {
        return repAssetMngPlan;
    }


    
    /**
     * 设置 repAssetMngPlan
     * @param repAssetMngPlan repAssetMngPlan
     */
    public void setRepAssetMngPlan(RepAssetMngPlan repAssetMngPlan) {
        this.repAssetMngPlan = repAssetMngPlan;
    }


    
    /**
     * 获取 managerName
     * @return managerName.
     */
    public String getManagerName() {
        return managerName;
    }


    
    /**
     * 设置 managerName
     * @param managerName managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }


    
    /**
     * 获取 trusteeName
     * @return trusteeName.
     */
    public String getTrusteeName() {
        return trusteeName;
    }


    
    /**
     * 设置 trusteeName
     * @param trusteeName trusteeName
     */
    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }


    
    /**
     * 获取 feeName
     * @return feeName.
     */
    public String getFeeName() {
        return feeName;
    }


    
    /**
     * 设置 feeName
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * 获取 cdate
     *
     * @return cdate
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 cdate
     *
     * @param cdate cdate
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 holdingType
     *
     * @return holdingType
     */
    public String getHoldingType() {
        return holdingType;
    }

    /**
     * 设置 holdingType
     *
     * @param holdingType holdingType
     */
    public void setHoldingType(String holdingType) {
        this.holdingType = holdingType;
    }

    /**
     * 获取 collectType
     *
     * @return collectType
     */
    public String getCollectType() {
        return collectType;
    }

    /**
     * 设置 collectType
     *
     * @param collectType collectType
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }
}
