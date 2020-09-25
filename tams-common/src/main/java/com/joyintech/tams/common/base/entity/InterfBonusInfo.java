package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfBonusInfo extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 母产品代码
     */
    private String mProdId;

    /**
     * 产品期次
     */
    private String prodBatchNo;

    /**
     * 销售商代码
     */
    private String vendorCode;

    /**
     * 查询起始日期
     */
    private String queryStartDate;

    /**
     * 查询结束日期
     */
    private String queryEndDate;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 权益登记日
     */
    private String regDate;

    /**
     * 分红日
     */
    private String bonusDate;

    /**
     * 单位分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitBonusVlu;

    /**
     * 单位分红份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitBonusVol;

    /**
     * 录入日期
     */
    private String insertDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 系统平台日期
     */
    private String cdate;

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
     * 取得 母产品代码
     * @return 母产品代码
     */
    public String getmProdId() {
        return mProdId;
    }

    /**
     * 设置 母产品代码
     * @param mProdId 母产品代码
     */
    public void setmProdId(String mProdId) {
        this.mProdId = mProdId == null ? null : mProdId.trim();
    }

    /**
     * 取得 产品期次
     * @return 产品期次
     */
    public String getProdBatchNo() {
        return prodBatchNo;
    }

    /**
     * 设置 产品期次
     * @param prodBatchNo 产品期次
     */
    public void setProdBatchNo(String prodBatchNo) {
        this.prodBatchNo = prodBatchNo == null ? null : prodBatchNo.trim();
    }

    /**
     * 取得 销售商代码
     * @return 销售商代码
     */
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     * 设置 销售商代码
     * @param vendorCode 销售商代码
     */
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode == null ? null : vendorCode.trim();
    }

    /**
     * 取得 查询起始日期
     * @return 查询起始日期
     */
    public String getQueryStartDate() {
        return queryStartDate;
    }

    /**
     * 设置 查询起始日期
     * @param queryStartDate 查询起始日期
     */
    public void setQueryStartDate(String queryStartDate) {
        this.queryStartDate = queryStartDate == null ? null : queryStartDate.trim();
    }

    /**
     * 取得 查询结束日期
     * @return 查询结束日期
     */
    public String getQueryEndDate() {
        return queryEndDate;
    }

    /**
     * 设置 查询结束日期
     * @param queryEndDate 查询结束日期
     */
    public void setQueryEndDate(String queryEndDate) {
        this.queryEndDate = queryEndDate == null ? null : queryEndDate.trim();
    }

    /**
     * 取得 页码
     * @return 页码
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设置 页码
     * @param pageNo 页码
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 取得 页大小
     * @return 页大小
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置 页大小
     * @param pageSize 页大小
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 取得 权益登记日
     * @return 权益登记日
     */
    public String getRegDate() {
        return regDate;
    }

    /**
     * 设置 权益登记日
     * @param regDate 权益登记日
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate == null ? null : regDate.trim();
    }

    /**
     * 取得 分红日
     * @return 分红日
     */
    public String getBonusDate() {
        return bonusDate;
    }

    /**
     * 设置 分红日
     * @param bonusDate 分红日
     */
    public void setBonusDate(String bonusDate) {
        this.bonusDate = bonusDate == null ? null : bonusDate.trim();
    }

    /**
     * 取得 单位分红金额
     * @return 单位分红金额
     */
    public BigDecimal getUnitBonusVlu() {
        return unitBonusVlu;
    }

    /**
     * 设置 单位分红金额
     * @param unitBonusVlu 单位分红金额
     */
    public void setUnitBonusVlu(BigDecimal unitBonusVlu) {
        this.unitBonusVlu = unitBonusVlu;
    }

    /**
     * 取得 单位分红份额
     * @return 单位分红份额
     */
    public BigDecimal getUnitBonusVol() {
        return unitBonusVol;
    }

    /**
     * 设置 单位分红份额
     * @param unitBonusVol 单位分红份额
     */
    public void setUnitBonusVol(BigDecimal unitBonusVol) {
        this.unitBonusVol = unitBonusVol;
    }

    /**
     * 取得 录入日期
     * @return 录入日期
     */
    public String getInsertDate() {
        return insertDate;
    }

    /**
     * 设置 录入日期
     * @param insertDate 录入日期
     */
    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate == null ? null : insertDate.trim();
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
     * 取得 系统平台日期
     * @return 系统平台日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 系统平台日期
     * @param cdate 系统平台日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }
}