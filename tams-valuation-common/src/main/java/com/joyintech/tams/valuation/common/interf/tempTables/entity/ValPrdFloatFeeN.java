package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValPrdFloatFeeN extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 费用ID
     */
    private String feeId;

    /**
     * 计提类型
     */
    private String drawType;

    /**
     * 计提频率
     */
    private String accrCycle;

    /**
     * 计提频率数值
     */
    private Long accrCycleUnit;

    /**
     * 计提频率类型
     */
    private String accrDayType;

    /**
     * 计提频率值
     */
    private String accrDayValue;

    /**
     * 营业日准则
     */
    private String tdayRule;

    /**
     * 首次计提日
     */
    private Date fAccrDate;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

    /**
     * 计提结束日
     */
    private Date eAccrDate;

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
     * 取得 费用ID
     * @return 费用ID
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用ID
     * @param feeId 费用ID
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 取得 计提类型
     * @return 计提类型
     */
    public String getDrawType() {
        return drawType;
    }

    /**
     * 设置 计提类型
     * @param drawType 计提类型
     */
    public void setDrawType(String drawType) {
        this.drawType = drawType == null ? null : drawType.trim();
    }

    /**
     * 取得 计提频率
     * @return 计提频率
     */
    public String getAccrCycle() {
        return accrCycle;
    }

    /**
     * 设置 计提频率
     * @param accrCycle 计提频率
     */
    public void setAccrCycle(String accrCycle) {
        this.accrCycle = accrCycle == null ? null : accrCycle.trim();
    }

    /**
     * 取得 计提频率数值
     * @return 计提频率数值
     */
    public Long getAccrCycleUnit() {
        return accrCycleUnit;
    }

    /**
     * 设置 计提频率数值
     * @param accrCycleUnit 计提频率数值
     */
    public void setAccrCycleUnit(Long accrCycleUnit) {
        this.accrCycleUnit = accrCycleUnit;
    }

    /**
     * 取得 计提频率类型
     * @return 计提频率类型
     */
    public String getAccrDayType() {
        return accrDayType;
    }

    /**
     * 设置 计提频率类型
     * @param accrDayType 计提频率类型
     */
    public void setAccrDayType(String accrDayType) {
        this.accrDayType = accrDayType == null ? null : accrDayType.trim();
    }

    /**
     * 取得 计提频率值
     * @return 计提频率值
     */
    public String getAccrDayValue() {
        return accrDayValue;
    }

    /**
     * 设置 计提频率值
     * @param accrDayValue 计提频率值
     */
    public void setAccrDayValue(String accrDayValue) {
        this.accrDayValue = accrDayValue == null ? null : accrDayValue.trim();
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
     * 取得 首次计提日
     * @return 首次计提日
     */
    public Date getfAccrDate() {
        return fAccrDate;
    }

    /**
     * 设置 首次计提日
     * @param fAccrDate 首次计提日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setfAccrDate(Date fAccrDate) {
        this.fAccrDate = fAccrDate;
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

    /**
     * 取得 计提结束日
     * @return 计提结束日
     */
    public Date geteAccrDate() {
        return eAccrDate;
    }

    /**
     * 设置 计提结束日
     * @param eAccrDate 计提结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void seteAccrDate(Date eAccrDate) {
        this.eAccrDate = eAccrDate;
    }
}