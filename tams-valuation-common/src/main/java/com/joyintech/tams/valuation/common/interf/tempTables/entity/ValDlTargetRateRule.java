package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValDlTargetRateRule extends BaseEntity {
    /**
     * 拆借标的代码
     */
    private String dlId;

    /**
     * 新增、修改、删除
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
     * 付息频率
     */
    private String payCycle;

    /**
     * 营业日准则
     */
    private String tdayRule;

    /**
     * 推送方式
     */
    private String calcRule;

    /**
     * 首次付息日
     */
    private Date firstPayDate;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 取得 拆借标的代码
     * @return 拆借标的代码
     */
    public String getDlId() {
        return dlId;
    }

    /**
     * 设置 拆借标的代码
     * @param dlId 拆借标的代码
     */
    public void setDlId(String dlId) {
        this.dlId = dlId == null ? null : dlId.trim();
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

    /**
     * 取得 推送方式
     * @return 推送方式
     */
    public String getCalcRule() {
        return calcRule;
    }

    /**
     * 设置 推送方式
     * @param calcRule 推送方式
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

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }
}