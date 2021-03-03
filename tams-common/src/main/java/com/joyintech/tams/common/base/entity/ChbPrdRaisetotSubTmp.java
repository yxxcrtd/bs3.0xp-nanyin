package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.anno.ItemAndNameDictCode;

import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdRaisetotSubTmp extends BaseEntity {
    /**
     * 产品登记编码
     */
    private String prodRegistCode;
    /**
     * 产品ID(临时表中不存在)
     */
    private String prodId;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 个人投资者总数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal singleTotal;

    /**
     * 法人投资者总数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal legalTotal;

    /**
     * 非法人投资者总数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unlegalTotal;

    /**
     * 认购币种及金额
     */
    private String subscribeCcyAmt;

    /**
     * 产品销售区域及募集金额
     */
    private String saleAreaAmt;

    /**
     * 实际募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalAmt;

    /**
     * 是否代销 参照数据字典
     */
    @ItemAndNameDictCode
    private String isConsign;

    /**
     * 代销总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal consignAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 募集总份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseQty;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据日期
     */
    private Date rowDate;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 个人投资者总数
     * @return 个人投资者总数
     */
    public BigDecimal getSingleTotal() {
        return singleTotal;
    }

    /**
     * 设置 个人投资者总数
     * @param singleTotal 个人投资者总数
     */
    public void setSingleTotal(BigDecimal singleTotal) {
        this.singleTotal = singleTotal;
    }

    /**
     * 取得 法人投资者总数
     * @return 法人投资者总数
     */
    public BigDecimal getLegalTotal() {
        return legalTotal;
    }

    /**
     * 设置 法人投资者总数
     * @param legalTotal 法人投资者总数
     */
    public void setLegalTotal(BigDecimal legalTotal) {
        this.legalTotal = legalTotal;
    }

    /**
     * 取得 非法人投资者总数
     * @return 非法人投资者总数
     */
    public BigDecimal getUnlegalTotal() {
        return unlegalTotal;
    }

    /**
     * 设置 非法人投资者总数
     * @param unlegalTotal 非法人投资者总数
     */
    public void setUnlegalTotal(BigDecimal unlegalTotal) {
        this.unlegalTotal = unlegalTotal;
    }

    /**
     * 取得 认购币种及金额
     * @return 认购币种及金额
     */
    public String getSubscribeCcyAmt() {
        return subscribeCcyAmt;
    }

    /**
     * 设置 认购币种及金额
     * @param subscribeCcyAmt 认购币种及金额
     */
    public void setSubscribeCcyAmt(String subscribeCcyAmt) {
        this.subscribeCcyAmt = subscribeCcyAmt == null ? null : subscribeCcyAmt.trim();
    }

    /**
     * 取得 产品销售区域及募集金额
     * @return 产品销售区域及募集金额
     */
    public String getSaleAreaAmt() {
        return saleAreaAmt;
    }

    /**
     * 设置 产品销售区域及募集金额
     * @param saleAreaAmt 产品销售区域及募集金额
     */
    public void setSaleAreaAmt(String saleAreaAmt) {
        this.saleAreaAmt = saleAreaAmt == null ? null : saleAreaAmt.trim();
    }

    /**
     * 取得 实际募集金额
     * @return 实际募集金额
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * 设置 实际募集金额
     * @param totalAmt 实际募集金额
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * 取得 是否代销 参照数据字典
     * @return 是否代销 参照数据字典
     */
    public String getIsConsign() {
        return isConsign;
    }

    /**
     * 设置 是否代销 参照数据字典
     * @param isConsign 是否代销 参照数据字典
     */
    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign == null ? null : isConsign.trim();
    }

    /**
     * 取得 代销总金额
     * @return 代销总金额
     */
    public BigDecimal getConsignAmt() {
        return consignAmt;
    }

    /**
     * 设置 代销总金额
     * @param consignAmt 代销总金额
     */
    public void setConsignAmt(BigDecimal consignAmt) {
        this.consignAmt = consignAmt;
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
     * 取得 募集总份额
     * @return 募集总份额
     */
    public BigDecimal getRaiseQty() {
        return raiseQty;
    }

    /**
     * 设置 募集总份额
     * @param raiseQty 募集总份额
     */
    public void setRaiseQty(BigDecimal raiseQty) {
        this.raiseQty = raiseQty;
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
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRowDate() {
        return rowDate;
    }

    /**
     * 设置 数据日期
     * @param rowDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRowDate(Date rowDate) {
        this.rowDate = rowDate;
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

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
}