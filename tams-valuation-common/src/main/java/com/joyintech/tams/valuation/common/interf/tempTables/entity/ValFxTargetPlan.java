package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValFxTargetPlan extends BaseEntity {
    /**
     * 逾期资产代码
     */
    private String assetCode;

    /**
     * 逾期资产类型
     */
    private String finprodType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 开始日
     */
    private Date vdate;

    /**
     * 截止日
     */
    private Date mdate;

    /**
     * 支付日
     */
    private Date payDay;

    /**
     * 计息本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinCapi;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 取得 逾期资产代码
     * @return 逾期资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 逾期资产代码
     * @param assetCode 逾期资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 逾期资产类型
     * @return 逾期资产类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 逾期资产类型
     * @param finprodType 逾期资产类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日
     * @param vdate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 截止日
     * @return 截止日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 截止日
     * @param mdate 截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
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
}