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

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset.SvrBussFinBondRepInfoValidator;

import java.math.BigDecimal;

/**
 *  资产基本信息_QDII结构性票据类信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see SvrBussRepQDIIStructureInfoDto
 * @since 1.0
 */
public class SvrBussRepQDIIStructureInfoDto extends RepAssetBond{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    private String countryArea;

    /**
     * 合约名称
     */
    private String contractName;

    /**
     * 票面利率
     */
    private BigDecimal assetYield;

    /**
     * 付息频率（个月/次）
     */
    private Long intpayCycle;

    /**
     * 固定收益部分所占比例
     */
    private BigDecimal incomeRatio;

    /**
     * 衍生金融工具所占比例
     */
    private BigDecimal derInstrRatio;

    /**
     * 衍生金融工具具体投资方式
     */
    private String derInstrInvType;

    /**
     * 衍生金融工具挂钩标的资产
     */
    private String derInstrTarget;

    /**
     * 利息结算方式
     */
    private String acctMode;

    /**
     * 含权情况说明
     */
    private String specialCmt1;

    /**
     * 结构性票据最高收益率
     */
    private BigDecimal maxYield;

    /**
     * 结构性票据最低收益率
     */
    private BigDecimal minYield;

    /**
     * 挂钩标的资产基准价格
     */
    private BigDecimal basicPrice;

    /**
     * 挂钩标的资产登记日价格
     */
    private BigDecimal regDatePrice;

    /**
     * 交易费
     */
    private String tradeFee;

    /**
     * 备注
     */
    private String  remark;

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    public Long getIntpayCycle() {
        return intpayCycle;
    }

    public void setIntpayCycle(Long intpayCycle) {
        this.intpayCycle = intpayCycle;
    }

    public BigDecimal getIncomeRatio() {
        return incomeRatio;
    }

    public void setIncomeRatio(BigDecimal incomeRatio) {
        this.incomeRatio = incomeRatio;
    }

    public BigDecimal getDerInstrRatio() {
        return derInstrRatio;
    }

    public void setDerInstrRatio(BigDecimal derInstrRatio) {
        this.derInstrRatio = derInstrRatio;
    }

    public String getDerInstrInvType() {
        return derInstrInvType;
    }

    public void setDerInstrInvType(String derInstrInvType) {
        this.derInstrInvType = derInstrInvType;
    }

    public String getDerInstrTarget() {
        return derInstrTarget;
    }

    public void setDerInstrTarget(String derInstrTarget) {
        this.derInstrTarget = derInstrTarget;
    }

    public String getAcctMode() {
        return acctMode;
    }

    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    public String getSpecialCmt1() {
        return specialCmt1;
    }

    public void setSpecialCmt1(String specialCmt1) {
        this.specialCmt1 = specialCmt1;
    }

    public BigDecimal getMaxYield() {
        return maxYield;
    }

    public void setMaxYield(BigDecimal maxYield) {
        this.maxYield = maxYield;
    }

    public BigDecimal getMinYield() {
        return minYield;
    }

    public void setMinYield(BigDecimal minYield) {
        this.minYield = minYield;
    }

    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    public BigDecimal getRegDatePrice() {
        return regDatePrice;
    }

    public void setRegDatePrice(BigDecimal regDatePrice) {
        this.regDatePrice = regDatePrice;
    }

    public String getTradeFee() {
        return tradeFee;
    }

    public void setTradeFee(String tradeFee) {
        this.tradeFee = tradeFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
