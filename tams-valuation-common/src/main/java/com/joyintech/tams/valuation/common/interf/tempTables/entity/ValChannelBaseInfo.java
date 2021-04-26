package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValChannelBaseInfo extends BaseEntity {
    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 通道名称
     */
    private String channelName;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 通道类型
     */
    private String channelType;

    /**
     * 成立日期
     */
    private Date vdate;

    /**
     * 终止日期
     */
    private Date mdate;

    /**
     * 托管人
     */
    private String trustee;

    /**
     * 管理人
     */
    private String managerId;

    /**
     * 本金收益特征
     */
    private String capiProfFeature;

    /**
     * 税务方案
     */
    private String isCompoundInt;

    /**
     * 托管活期利息计提
     */
    private String isCalc;

    /**
     * 估值表模板
     */
    private String valuationTemplate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置 通道代码
     * @param channelId 通道代码
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    /**
     * 取得 通道名称
     * @return 通道名称
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置 通道名称
     * @param channelName 通道名称
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 通道类型
     * @return 通道类型
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * 设置 通道类型
     * @param channelType 通道类型
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    /**
     * 取得 成立日期
     * @return 成立日期
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 成立日期
     * @param vdate 成立日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 终止日期
     * @return 终止日期
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 终止日期
     * @param mdate 终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 托管人
     * @return 托管人
     */
    public String getTrustee() {
        return trustee;
    }

    /**
     * 设置 托管人
     * @param trustee 托管人
     */
    public void setTrustee(String trustee) {
        this.trustee = trustee == null ? null : trustee.trim();
    }

    /**
     * 取得 管理人
     * @return 管理人
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置 管理人
     * @param managerId 管理人
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     * 取得 本金收益特征
     * @return 本金收益特征
     */
    public String getCapiProfFeature() {
        return capiProfFeature;
    }

    /**
     * 设置 本金收益特征
     * @param capiProfFeature 本金收益特征
     */
    public void setCapiProfFeature(String capiProfFeature) {
        this.capiProfFeature = capiProfFeature == null ? null : capiProfFeature.trim();
    }

    /**
     * 取得 税务方案
     * @return 税务方案
     */
    public String getIsCompoundInt() {
        return isCompoundInt;
    }

    /**
     * 设置 税务方案
     * @param isCompoundInt 税务方案
     */
    public void setIsCompoundInt(String isCompoundInt) {
        this.isCompoundInt = isCompoundInt == null ? null : isCompoundInt.trim();
    }

    /**
     * 取得 托管活期利息计提
     * @return 托管活期利息计提
     */
    public String getIsCalc() {
        return isCalc;
    }

    /**
     * 设置 托管活期利息计提
     * @param isCalc 托管活期利息计提
     */
    public void setIsCalc(String isCalc) {
        this.isCalc = isCalc == null ? null : isCalc.trim();
    }

    /**
     * 取得 估值表模板
     * @return 估值表模板
     */
    public String getValuationTemplate() {
        return valuationTemplate;
    }

    /**
     * 设置 估值表模板
     * @param valuationTemplate 估值表模板
     */
    public void setValuationTemplate(String valuationTemplate) {
        this.valuationTemplate = valuationTemplate == null ? null : valuationTemplate.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
    
    private String belongInstitutionCode;

    public String getBelongInstitutionCode() {
        return belongInstitutionCode;
    }

    public void setBelongInstitutionCode(String belongInstitutionCode) {
        this.belongInstitutionCode = belongInstitutionCode;
    }
    
}