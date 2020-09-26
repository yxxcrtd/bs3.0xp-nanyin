package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValPrdOpenPlanN extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 开放周期起始日
     */
    private Date openDateStr;

    /**
     * 开放周期结束日
     */
    private Date openDateEnd;

    /**
     * 开放周期起息日
     */
    private Date openVdate;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
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
     * 取得 开放周期起始日
     * @return 开放周期起始日
     */
    public Date getOpenDateStr() {
        return openDateStr;
    }

    /**
     * 设置 开放周期起始日
     * @param openDateStr 开放周期起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenDateStr(Date openDateStr) {
        this.openDateStr = openDateStr;
    }

    /**
     * 取得 开放周期结束日
     * @return 开放周期结束日
     */
    public Date getOpenDateEnd() {
        return openDateEnd;
    }

    /**
     * 设置 开放周期结束日
     * @param openDateEnd 开放周期结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenDateEnd(Date openDateEnd) {
        this.openDateEnd = openDateEnd;
    }

    /**
     * 取得 开放周期起息日
     * @return 开放周期起息日
     */
    public Date getOpenVdate() {
        return openVdate;
    }

    /**
     * 设置 开放周期起息日
     * @param openVdate 开放周期起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenVdate(Date openVdate) {
        this.openVdate = openVdate;
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 产品起息日
     * @return 产品起息日
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 产品起息日
     * @param prodVdate 产品起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }
}