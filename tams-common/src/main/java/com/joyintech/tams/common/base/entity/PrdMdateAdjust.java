package com.joyintech.tams.common.base.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

public class PrdMdateAdjust extends PrdMdateAdjustKey {
    /**
     * 调整前到期日
     */
    private Date mdateUnadjust;

    /**
     * 调整后到期日
     */
    private Date mdate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 调整后清盘日
     */
    private Date ldate;

    /**
     * 调整类型
     */
    private String adjustType;

    /**
     * 调整前清盘日
     */
    private Date ldateUnadjust;

    /**
     * 费用名称
     */
    private String feeName;
    /**
     * 计提结束日
     * @return
     */
    private Date eAccrDate;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 关联影响
     */
    private List<PrdMdateAdjust> selectedItem;

    public List<PrdMdateAdjust> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<PrdMdateAdjust> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 取得 调整前到期日
     * @return 调整前到期日
     */
    public Date getMdateUnadjust() {
        return mdateUnadjust;
    }

    /**
     * 设置 调整前到期日
     * @param mdateUnadjust 调整前到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdateUnadjust(Date mdateUnadjust) {
        this.mdateUnadjust = mdateUnadjust;
    }

    /**
     * 取得 调整后到期日
     * @return 调整后到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 调整后到期日
     * @param mdate 调整后到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 ldate
     * @return ldate.
     */
    public Date getLdate() {
        return ldate;
    }

    /**
     * 设置 ldate
     * @param ldate ldate
     */
    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    /**
     * 获取 adjustType
     * @return adjustType.
     */
    public String getAdjustType() {
        return adjustType;
    }

    /**
     * 设置 adjustType
     * @param adjustType adjustType
     */
    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    /**
     * 获取 ldateUnadjust
     * @return ldateUnadjust.
     */
    public Date getLdateUnadjust() {
        return ldateUnadjust;
    }

    /**
     * 设置 ldateUnadjust
     * @param ldateUnadjust ldateUnadjust
     */
    public void setLdateUnadjust(Date ldateUnadjust) {
        this.ldateUnadjust = ldateUnadjust;
    }

    /**
     * 获取 feeName
     * @return feeName.
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 设置 feeName
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * 获取 eAccrDate
     * @return eAccrDate.
     */
    public Date geteAccrDate() {
        return eAccrDate;
    }

    /**
     * 设置 eAccrDate
     * @param eAccrDate eAccrDate
     */
    public void seteAccrDate(Date eAccrDate) {
        this.eAccrDate = eAccrDate;
    }

    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 feeType
     * @return feeType.
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 feeType
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

}