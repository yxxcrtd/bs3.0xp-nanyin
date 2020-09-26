package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValFundMnfPayPlan extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 现金流序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal orderNo;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 是否已支付
     */
    private String payStatus;

    /**
     * 付息日
     */
    private Date payDate;

    /**
     * 计息开始日
     */
    private Date vdate;

    /**
     * 计息结束日
     */
    private Date mdate;

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
     * 取得 是否已支付
     * @return 是否已支付
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置 是否已支付
     * @param payStatus 是否已支付
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    /**
     * 取得 付息日
     * @return 付息日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 付息日
     * @param payDate 付息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 计息开始日
     * @return 计息开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 计息开始日
     * @param vdate 计息开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 计息结束日
     * @return 计息结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 计息结束日
     * @param mdate 计息结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
}