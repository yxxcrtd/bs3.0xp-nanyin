package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValDlTargetRatePlan extends BaseEntity {
    /**
     * 拆借标的代码
     */
    private String dlId;

    /**
     * 现金流序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal orderNo;

    /**
     * 理论开始日
     */
    private Date theVdate;

    /**
     * 理论截止日
     */
    private Date theMdate;

    /**
     * 实际开始日
     */
    private Date actVdate;

    /**
     * 实际截止日
     */
    private Date actMdate;

    /**
     * 是否支付利息
     */
    private String isPayInt;

    /**
     * 支付日
     */
    private Date payDay;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 计息本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinCapi;

    /**
     * 还本金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal printAmt;

    /**
     * 利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 现金流总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashAmt;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 交易类型
     */
    private String dealType;

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
     * 取得 现金流序号
     * @return 现金流序号
     */
    public BigDecimal getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 现金流序号
     * @param orderNo 现金流序号
     */
    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 取得 理论开始日
     * @return 理论开始日
     */
    public Date getTheVdate() {
        return theVdate;
    }

    /**
     * 设置 理论开始日
     * @param theVdate 理论开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTheVdate(Date theVdate) {
        this.theVdate = theVdate;
    }

    /**
     * 取得 理论截止日
     * @return 理论截止日
     */
    public Date getTheMdate() {
        return theMdate;
    }

    /**
     * 设置 理论截止日
     * @param theMdate 理论截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTheMdate(Date theMdate) {
        this.theMdate = theMdate;
    }

    /**
     * 取得 实际开始日
     * @return 实际开始日
     */
    public Date getActVdate() {
        return actVdate;
    }

    /**
     * 设置 实际开始日
     * @param actVdate 实际开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActVdate(Date actVdate) {
        this.actVdate = actVdate;
    }

    /**
     * 取得 实际截止日
     * @return 实际截止日
     */
    public Date getActMdate() {
        return actMdate;
    }

    /**
     * 设置 实际截止日
     * @param actMdate 实际截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
    }

    /**
     * 取得 是否支付利息
     * @return 是否支付利息
     */
    public String getIsPayInt() {
        return isPayInt;
    }

    /**
     * 设置 是否支付利息
     * @param isPayInt 是否支付利息
     */
    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt == null ? null : isPayInt.trim();
    }

    /**
     * 取得 支付日
     * @return 支付日
     */
    public Date getPayDay() {
        return payDay;
    }

    /**
     * 设置 支付日
     * @param payDay 支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    /**
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 利率
     * @param yield 利率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 计息本金
     * @return 计息本金
     */
    public BigDecimal getPrinCapi() {
        return prinCapi;
    }

    /**
     * 设置 计息本金
     * @param prinCapi 计息本金
     */
    public void setPrinCapi(BigDecimal prinCapi) {
        this.prinCapi = prinCapi;
    }

    /**
     * 取得 还本金额
     * @return 还本金额
     */
    public BigDecimal getPrintAmt() {
        return printAmt;
    }

    /**
     * 设置 还本金额
     * @param printAmt 还本金额
     */
    public void setPrintAmt(BigDecimal printAmt) {
        this.printAmt = printAmt;
    }

    /**
     * 取得 利息金额
     * @return 利息金额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 利息金额
     * @param intAmt 利息金额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 现金流总额
     * @return 现金流总额
     */
    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    /**
     * 设置 现金流总额
     * @param cashAmt 现金流总额
     */
    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
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
}