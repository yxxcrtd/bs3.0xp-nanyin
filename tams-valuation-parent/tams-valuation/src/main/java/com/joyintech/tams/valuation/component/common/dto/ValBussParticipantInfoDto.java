/*
 * 文件名：ValBussParticipantInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.common.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.valuation.component.common.validator.ValBussParticipantInfoValidator;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 * 参与方信息Dto
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see ValBussParticipantInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = ValBussParticipantInfoValidator.class,groups = {ValidateGroupEdit.class})
public class ValBussParticipantInfoDto extends FinProduct{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private List<ValBussParticipantInfoDto> bussParticipantInfoDtoList;
    
    /**
     * 现金流信息
     */
    private List<FinCashInfo>  finCashInfoList;
    
    /**
     * 现金流计算规则
     */
    private List<FinCashCalcRule> finCashCalcRuleList;
    
    /**
     * 现金流利率信息
     */
    private List<FinRateInfo> finRateInfoList;
    
    /**
     * 资产类别
     */
    private String type6;
    
    /**
     * 交易对手代码
     */
    private String counterId;
    
    /**
     * 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    private String counterName;
    
    /**
     * 计息基础
     */
    private String basis;

    
    /**
     * 现金流二级类型，本金、利息、托管费、固定管理费、浮动管理费、税额、通道托管费、通道管理费等
     */
    private String cashType2;
    
    /**
     * 管理人
     */
    private String managerCustomerName;
    
    /**
     * 通道层级
     */
    private Long chlLevel;
    
    /**
     * 费率
     */
    private BigDecimal yield;
    
    /**
     * 托管费率(%)
     */
    private BigDecimal clientYield;
    
    /**
     * 管理费率(%)
     */
    private BigDecimal managerYield;
    
    /**
     * 其他费率(%)
     */
    private BigDecimal otherYield;
    
    /**
     * 托管账号
     */
    private String busiAcctNo;
    
    /**
     * 托管人
     */
    private String clientCustomerName;
    
    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 联系人
     */
    private String contact;

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
     * 划款方式
     */
    private String payType;

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
     * 获取 managerCustomerName
     * @return managerCustomerName.
     */
    public String getManagerCustomerName() {
        return managerCustomerName;
    }

    
    /**
     * 设置 managerCustomerName
     * @param managerCustomerName managerCustomerName
     */
    public void setManagerCustomerName(String managerCustomerName) {
        this.managerCustomerName = managerCustomerName;
    }

    

    
    
    /**
     * 获取 chlLevel
     * @return chlLevel.
     */
    public Long getChlLevel() {
        return chlLevel;
    }



    
    /**
     * 设置 chlLevel
     * @param chlLevel chlLevel
     */
    public void setChlLevel(Long chlLevel) {
        this.chlLevel = chlLevel;
    }



    /**
     * 获取 clientYield
     * @return clientYield.
     */
    public BigDecimal getClientYield() {
        return clientYield;
    }

    
    /**
     * 设置 clientYield
     * @param clientYield clientYield
     */
    public void setClientYield(BigDecimal clientYield) {
        this.clientYield = clientYield;
    }

    
    /**
     * 获取 managerYield
     * @return managerYield.
     */
    public BigDecimal getManagerYield() {
        return managerYield;
    }

    
    /**
     * 设置 managerYield
     * @param managerYield managerYield
     */
    public void setManagerYield(BigDecimal managerYield) {
        this.managerYield = managerYield;
    }

    
    /**
     * 获取 otherYield
     * @return otherYield.
     */
    public BigDecimal getOtherYield() {
        return otherYield;
    }

    
    /**
     * 设置 otherYield
     * @param otherYield otherYield
     */
    public void setOtherYield(BigDecimal otherYield) {
        this.otherYield = otherYield;
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
     * 获取 clientCustomerName
     * @return clientCustomerName.
     */
    public String getClientCustomerName() {
        return clientCustomerName;
    }

    
    /**
     * 设置 clientCustomerName
     * @param clientCustomerName clientCustomerName
     */
    public void setClientCustomerName(String clientCustomerName) {
        this.clientCustomerName = clientCustomerName;
    }

    
    /**
     * 获取 contactWay
     * @return contactWay.
     */
    public String getContactWay() {
        return contactWay;
    }

    
    /**
     * 设置 contactWay
     * @param contactWay contactWay
     */
    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }


    
    /**
     * 获取 bussParticipantInfoDtoList
     * @return bussParticipantInfoDtoList.
     */
    public List<ValBussParticipantInfoDto> getBussParticipantInfoDtoList() {
        return bussParticipantInfoDtoList;
    }


    
    /**
     * 设置 bussParticipantInfoDtoList
     * @param bussParticipantInfoDtoList bussParticipantInfoDtoList
     */
    public void setBussParticipantInfoDtoList(List<ValBussParticipantInfoDto> bussParticipantInfoDtoList) {
        this.bussParticipantInfoDtoList = bussParticipantInfoDtoList;
    }



    
    /**
     * 获取 finCashInfoList
     * @return finCashInfoList.
     */
    public List<FinCashInfo> getFinCashInfoList() {
        return finCashInfoList;
    }



    
    /**
     * 设置 finCashInfoList
     * @param finCashInfoList finCashInfoList
     */
    public void setFinCashInfoList(List<FinCashInfo> finCashInfoList) {
        this.finCashInfoList = finCashInfoList;
    }



    
    /**
     * 获取 finCashCalcRuleList
     * @return finCashCalcRuleList.
     */
    public List<FinCashCalcRule> getFinCashCalcRuleList() {
        return finCashCalcRuleList;
    }



    
    /**
     * 设置 finCashCalcRuleList
     * @param finCashCalcRuleList finCashCalcRuleList
     */
    public void setFinCashCalcRuleList(List<FinCashCalcRule> finCashCalcRuleList) {
        this.finCashCalcRuleList = finCashCalcRuleList;
    }


    
    
    
    /**
     * 获取 finRateInfoList
     * @return finRateInfoList.
     */
    public List<FinRateInfo> getFinRateInfoList() {
        return finRateInfoList;
    }



    
    /**
     * 设置 finRateInfoList
     * @param finRateInfoList finRateInfoList
     */
    public void setFinRateInfoList(List<FinRateInfo> finRateInfoList) {
        this.finRateInfoList = finRateInfoList;
    }



    /**
     * 获取 counterId
     * @return counterId.
     */
    public String getCounterId() {
        return counterId;
    }



    
    /**
     * 设置 counterId
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }



    
    /**
     * 获取 counterName
     * @return counterName.
     */
    public String getCounterName() {
        return counterName;
    }



    
    /**
     * 设置 counterName
     * @param counterName counterName
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }



    
    /**
     * 获取 contact
     * @return contact.
     */
    public String getContact() {
        return contact;
    }



    
    /**
     * 设置 contact
     * @param contact contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }



    
    /**
     * 获取 yield
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }



    
    /**
     * 设置 yield
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }



    
    /**
     * 获取 cashType2
     * @return cashType2.
     */
    public String getCashType2() {
        return cashType2;
    }



    
    /**
     * 设置 cashType2
     * @param cashType2 cashType2
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }



    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }



    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
