package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValFundMnfPayRule extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 付息频率
     */
    private String payCycle;

    /**
     * 营业日准则
     */
    private String tdayRule;

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 支付方式
     * @return 支付方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 支付方式
     * @param payType 支付方式
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 取得 付息频率
     * @return 付息频率
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 付息频率
     * @param payCycle 付息频率
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle == null ? null : payCycle.trim();
    }

    /**
     * 取得 营业日准则
     * @return 营业日准则
     */
    public String getTdayRule() {
        return tdayRule;
    }

    /**
     * 设置 营业日准则
     * @param tdayRule 营业日准则
     */
    public void setTdayRule(String tdayRule) {
        this.tdayRule = tdayRule == null ? null : tdayRule.trim();
    }
}