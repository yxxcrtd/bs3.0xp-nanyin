package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

/**
 * 描述:CHB_ASSETS_BASIS_DATA_CHILD表的实体类
 * @version
 * @author:  zhangzhao
 * @创建时间: 2019-11-19
 */
public class ChbAssetsBasisDataChild extends BaseEntity{
    /**
     * 主键
     */
    private String valId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 母资产代码
     */
    private String pAssetId;

    /**
     * 日期
     */
    private Date valueDate;

    /**
     * 资产到期日
     */
    private Date mdate;

    /**
     * 当前持仓
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posAmt;

    /**
     * 当日成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyCostAmt;

    /**
     * 当日公允价值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngpl;

    /**
     * 当日累计应计利息余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    /**
     * 待清算款
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal delayPayAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 业务主键md5
     */
    private String md5primarykey;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getValId() {
        return valId;
    }

    /**
     * 设置 主键
     * @param valId 主键
     */
    public void setValId(String valId) {
        this.valId = valId == null ? null : valId.trim();
    }

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
     * 取得 母资产代码
     * @return 母资产代码
     */
    public String getpAssetId() {
        return pAssetId;
    }

    /**
     * 设置 母资产代码
     * @param pAssetId 母资产代码
     */
    public void setpAssetId(String pAssetId) {
        this.pAssetId = pAssetId == null ? null : pAssetId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 日期
     * @param valueDate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 资产到期日
     * @return 资产到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 资产到期日
     * @param mdate 资产到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 当前持仓
     * @return 当前持仓
     */
    public BigDecimal getPosAmt() {
        return posAmt;
    }

    /**
     * 设置 当前持仓
     * @param posAmt 当前持仓
     */
    public void setPosAmt(BigDecimal posAmt) {
        this.posAmt = posAmt;
    }

    /**
     * 取得 当日成本
     * @return 当日成本
     */
    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }

    /**
     * 设置 当日成本
     * @param tdyCostAmt 当日成本
     */
    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }

    /**
     * 取得 当日公允价值变动
     * @return 当日公允价值变动
     */
    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    /**
     * 设置 当日公允价值变动
     * @param tdyFloatIngpl 当日公允价值变动
     */
    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

    /**
     * 取得 当日累计应计利息余额
     * @return 当日累计应计利息余额
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 设置 当日累计应计利息余额
     * @param tdyIntincexp 当日累计应计利息余额
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 取得 待清算款
     * @return 待清算款
     */
    public BigDecimal getDelayPayAmt() {
        return delayPayAmt;
    }

    /**
     * 设置 待清算款
     * @param delayPayAmt 待清算款
     */
    public void setDelayPayAmt(BigDecimal delayPayAmt) {
        this.delayPayAmt = delayPayAmt;
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
     * 取得 部门代码
     * @return 部门代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 部门代码
     * @param orgCode 部门代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 取得 业务主键md5
     * @return 业务主键md5
     */
    public String getMd5primarykey() {
        return md5primarykey;
    }

    /**
     * 设置 业务主键md5
     * @param md5primarykey 业务主键md5
     */
    public void setMd5primarykey(String md5primarykey) {
        this.md5primarykey = md5primarykey == null ? null : md5primarykey.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }}