package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValBotAstRepayCapInt extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 交易类型
     */
    private String dealType;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 还本是否带息
     */
    private String isInt;

    /**
     * 付息频率
     */
    private String payCycle;

    /**
     * 还本频率
     */
    private String capCycle;

    /**
     * 营业日准则
     */
    private String tdayRule;

    /**
     * 推算方式
     */
    private String calcRule;

    /**
     * 首次付息日
     */
    private Date firstPayDate;

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
     * 取得 交易类型
     * @return 交易类型
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * 设置 交易类型
     * @param dealType 交易类型
     */
    public void setDealType(String dealType) {
        this.dealType = dealType == null ? null : dealType.trim();
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
     * 取得 还本是否带息
     * @return 还本是否带息
     */
    public String getIsInt() {
        return isInt;
    }

    /**
     * 设置 还本是否带息
     * @param isInt 还本是否带息
     */
    public void setIsInt(String isInt) {
        this.isInt = isInt == null ? null : isInt.trim();
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
     * 取得 还本频率
     * @return 还本频率
     */
    public String getCapCycle() {
        return capCycle;
    }

    /**
     * 设置 还本频率
     * @param capCycle 还本频率
     */
    public void setCapCycle(String capCycle) {
        this.capCycle = capCycle == null ? null : capCycle.trim();
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

    /**
     * 取得 推算方式
     * @return 推算方式
     */
    public String getCalcRule() {
        return calcRule;
    }

    /**
     * 设置 推算方式
     * @param calcRule 推算方式
     */
    public void setCalcRule(String calcRule) {
        this.calcRule = calcRule == null ? null : calcRule.trim();
    }

    /**
     * 取得 首次付息日
     * @return 首次付息日
     */
    public Date getFirstPayDate() {
        return firstPayDate;
    }

    /**
     * 设置 首次付息日
     * @param firstPayDate 首次付息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstPayDate(Date firstPayDate) {
        this.firstPayDate = firstPayDate;
    }
}