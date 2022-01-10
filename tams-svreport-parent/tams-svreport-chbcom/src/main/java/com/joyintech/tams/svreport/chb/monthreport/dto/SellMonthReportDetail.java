package com.joyintech.tams.svreport.chb.monthreport.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.TableName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * 描述:SellMonthReportDetail
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-24
 */
@TableName("CHB_SALE_MONTH_REPORT_DETAIL")
public class SellMonthReportDetail extends BaseEntity {
    /**
     * 明细uuid
     */
    private String detailuuid;
    /**
     * 主表uuid
     */
    private String reportuuid;

    /**
     * 报表类型：1_1 ：分投资者类型，1_2 ：分地区类型，1_3 : 分销售渠道'.
     */
    private String reporttype;

    /**
     * 产品登记编码
     */
    private String prdRegistCode;

    /**
     * 是否代销
     */
    private String isConsign;

    /**
     * 销售机构代码
     */
    private String institutionCode;

    /**
     * 地区类型
     */
    private String areaCode;

    /**
     * 投资者类型
     */
    private String holderType;

    /**
     * 投资者数量
     */
    private BigDecimal holderNum;

    /**
     * 总销售金额
     */
    private BigDecimal saleAmountTotal;

    /**
     * 净销售金额
     */
    private BigDecimal valueAmountTotal;

    /**
     * 持有余额
     */
    private BigDecimal balanceAmt;

    /**
     * 持有份额
     */
    private BigDecimal balanceShares;

    /**
     * 总销售份额
     */
    private BigDecimal saleSharesTotal;
    /**
     * 净销售份额
     */
    private BigDecimal valueSharesTotal;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建部门
     */
    private String createDept;

    public String getDetailuuid() {
        return detailuuid;
    }

    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid;
    }

    public String getReportuuid() {
        return reportuuid;
    }

    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode;
    }

    public String getIsConsign() {
        return isConsign;
    }

    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign;
    }

    public String getIsConsignStr() {return (this.getIsConsign() + " " + SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsConsign()));}

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getHolderTypeStr() {return (this.getHolderType() + " " + SysDataUtil.getNameFromDataDict(DictCode.销售月表投资者类型, this.getHolderType()));}
    public String getAreaCodeStr() {return (this.getAreaCode() + " " + SysDataUtil.getNameFromDataDict(DictCode.销售区域_中债, this.getAreaCode()));}
    public String getReporttypeStr() {return SysDataUtil.getNameFromDataDict(DictCode.销售结算报表类型, this.getReporttype());}

    public BigDecimal getHolderNum() {
        return holderNum;
    }

    public String getHolderNumStr() {
        return holderNum.stripTrailingZeros().toPlainString();
    }

    public void setHolderNum(BigDecimal holderNum) {
        this.holderNum = holderNum;
    }

    public BigDecimal getSaleAmountTotal() {
        return saleAmountTotal;
    }

    public void setSaleAmountTotal(BigDecimal saleAmountTotal) {
        this.saleAmountTotal = saleAmountTotal;
    }

    public BigDecimal getValueAmountTotal() {
        return valueAmountTotal;
    }

    public void setValueAmountTotal(BigDecimal valueAmountTotal) {
        this.valueAmountTotal = valueAmountTotal;
    }

    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    public BigDecimal getBalanceShares() {
        return balanceShares;
    }

    public void setBalanceShares(BigDecimal balanceShares) {
        this.balanceShares = balanceShares;
    }

    public BigDecimal getSaleSharesTotal() {
        return saleSharesTotal;
    }

    public void setSaleSharesTotal(BigDecimal saleSharesTotal) {
        this.saleSharesTotal = saleSharesTotal;
    }

    public BigDecimal getValueSharesTotal() {
        return valueSharesTotal;
    }

    public void setValueSharesTotal(BigDecimal valueSharesTotal) {
        this.valueSharesTotal = valueSharesTotal;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public SellMonthReportDetail() {
        detailuuid =IDGenerator.generate(36);
        reporttype="";
        prdRegistCode="";
        isConsign="";
        institutionCode="";
        areaCode="";
        holderType="";
        holderNum=BigDecimal.ZERO;
        saleAmountTotal=BigDecimal.ZERO;
        valueAmountTotal=BigDecimal.ZERO;
        balanceAmt=BigDecimal.ZERO;
        balanceShares=BigDecimal.ZERO;
        saleSharesTotal=BigDecimal.ZERO;
        valueSharesTotal=BigDecimal.ZERO;
    }
}