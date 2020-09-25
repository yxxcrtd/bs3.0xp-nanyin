package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdfeeBasisData extends BaseEntity {
    /**
     * 主键uuid
     */
    private String posuuid;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 下一付费日
     */
    private Date paydate;

    /**
     * 当日计费本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPosition;

    /**
     * 当日新增费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAdd;

    /**
     * 累计应付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnpaidIntincexp;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 主键uuid
     * @return 主键uuid
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * 设置 主键uuid
     * @param posuuid 主键uuid
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 父产品代码
     * @return 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 取得 费用类型
     * @return 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 下一付费日
     * @return 下一付费日
     */
    public Date getPaydate() {
        return paydate;
    }

    /**
     * 设置 下一付费日
     * @param paydate 下一付费日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    /**
     * 取得 当日计费本金
     * @return 当日计费本金
     */
    public BigDecimal getTdyPosition() {
        return tdyPosition;
    }

    /**
     * 设置 当日计费本金
     * @param tdyPosition 当日计费本金
     */
    public void setTdyPosition(BigDecimal tdyPosition) {
        this.tdyPosition = tdyPosition;
    }

    /**
     * 取得 当日新增费用
     * @return 当日新增费用
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 设置 当日新增费用
     * @param tdyIntincexpAdd 当日新增费用
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
    }

    /**
     * 取得 累计应付费用
     * @return 累计应付费用
     */
    public BigDecimal getTdyUnpaidIntincexp() {
        return tdyUnpaidIntincexp;
    }

    /**
     * 设置 累计应付费用
     * @param tdyUnpaidIntincexp 累计应付费用
     */
    public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
        this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
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
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
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
}