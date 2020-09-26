package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValSecVestPlan extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 行权申请开始日
     */
    private Date vdate;

    /**
     * 行权申请结束日
     */
    private Date mdate;

    /**
     * 行权方式
     */
    private String execMode;

    /**
     * 行权日期
     */
    private Date execDate;

    /**
     * 行权价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal execCprice;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

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
     * 取得 行权申请开始日
     * @return 行权申请开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 行权申请开始日
     * @param vdate 行权申请开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 行权申请结束日
     * @return 行权申请结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 行权申请结束日
     * @param mdate 行权申请结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 行权方式
     * @return 行权方式
     */
    public String getExecMode() {
        return execMode;
    }

    /**
     * 设置 行权方式
     * @param execMode 行权方式
     */
    public void setExecMode(String execMode) {
        this.execMode = execMode == null ? null : execMode.trim();
    }

    /**
     * 取得 行权日期
     * @return 行权日期
     */
    public Date getExecDate() {
        return execDate;
    }

    /**
     * 设置 行权日期
     * @param execDate 行权日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    /**
     * 取得 行权价格
     * @return 行权价格
     */
    public BigDecimal getExecCprice() {
        return execCprice;
    }

    /**
     * 设置 行权价格
     * @param execCprice 行权价格
     */
    public void setExecCprice(BigDecimal execCprice) {
        this.execCprice = execCprice;
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