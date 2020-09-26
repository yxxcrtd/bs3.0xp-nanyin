package com.joyintech.tams.svreport.pbc.assetdebt.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_ASSET_INCOME_DETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportAssetIncomeDetail{
    /**
     * 主键
     */
    private String detailuuid;

    /**
     * 报表类型：:XFCP 新发产品分合同期限募集金额统计报表;CXQCP 存续产品分合同期限募集余额统计模板
     */
    private String reporttype;

    /**
     * 分组类型
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 贷款收益权	
     */
    private BigDecimal loanIncomeRight;

    /**
     * 债务证券收益权
     */
    private BigDecimal debtSecuritiesIncomeRights;

    /**
     * 应收账款收益权
     */
    private BigDecimal receivableIncomeRights;

    /**
     * 其他债权收益权
     */
    private BigDecimal otherCreditorRights;

    /**
     * 股票收益权
     */
    private BigDecimal stockIncomeRight;

    /**
     * 其他股权收益权
     */
    private BigDecimal otherEquityIncomeRights;

    /**
     * 物权资产收益权
     */
    private BigDecimal propertyRights;

    /**
     * 总计
     */
    private BigDecimal total;

    /**
     * 关联主表uuid
     */
    private String reportuuid;

    /**
     * 主键
     * @return DETAILUUID 主键
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 主键
     * @param detailuuid 主键
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 报表类型：:XFCP 新发产品分合同期限募集金额统计报表;CXQCP 存续产品分合同期限募集余额统计模板
     * @return REPORTTYPE 报表类型：:XFCP 新发产品分合同期限募集金额统计报表;CXQCP 存续产品分合同期限募集余额统计模板
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型：:XFCP 新发产品分合同期限募集金额统计报表;CXQCP 存续产品分合同期限募集余额统计模板
     * @param reporttype 报表类型：:XFCP 新发产品分合同期限募集金额统计报表;CXQCP 存续产品分合同期限募集余额统计模板
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 分组类型
     * @return GROUPTYPE 分组类型
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 分组类型
     * @param grouptype 分组类型
     */
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype == null ? null : grouptype.trim();
    }

    /**
     * 属性代码
     * @return PROPERTYCODE 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 属性代码
     * @param propertycode 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 贷款收益权	
     * @return LOAN_INCOME_RIGHT 贷款收益权	
     */
    public BigDecimal getLoanIncomeRight() {
        return loanIncomeRight;
    }

    /**
     * 贷款收益权	
     * @param loanIncomeRight 贷款收益权	
     */
    public void setLoanIncomeRight(BigDecimal loanIncomeRight) {
        this.loanIncomeRight = loanIncomeRight;
    }

    /**
     * 债务证券收益权
     * @return DEBT_SECURITIES_INCOME_RIGHTS 债务证券收益权
     */
    public BigDecimal getDebtSecuritiesIncomeRights() {
        return debtSecuritiesIncomeRights;
    }

    /**
     * 债务证券收益权
     * @param debtSecuritiesIncomeRights 债务证券收益权
     */
    public void setDebtSecuritiesIncomeRights(BigDecimal debtSecuritiesIncomeRights) {
        this.debtSecuritiesIncomeRights = debtSecuritiesIncomeRights;
    }

    /**
     * 应收账款收益权
     * @return RECEIVABLE_INCOME_RIGHTS 应收账款收益权
     */
    public BigDecimal getReceivableIncomeRights() {
        return receivableIncomeRights;
    }

    /**
     * 应收账款收益权
     * @param receivableIncomeRights 应收账款收益权
     */
    public void setReceivableIncomeRights(BigDecimal receivableIncomeRights) {
        this.receivableIncomeRights = receivableIncomeRights;
    }

    /**
     * 其他债权收益权
     * @return OTHER_CREDITOR_RIGHTS 其他债权收益权
     */
    public BigDecimal getOtherCreditorRights() {
        return otherCreditorRights;
    }

    /**
     * 其他债权收益权
     * @param otherCreditorRights 其他债权收益权
     */
    public void setOtherCreditorRights(BigDecimal otherCreditorRights) {
        this.otherCreditorRights = otherCreditorRights;
    }

    /**
     * 股票收益权
     * @return STOCK_INCOME_RIGHT 股票收益权
     */
    public BigDecimal getStockIncomeRight() {
        return stockIncomeRight;
    }

    /**
     * 股票收益权
     * @param stockIncomeRight 股票收益权
     */
    public void setStockIncomeRight(BigDecimal stockIncomeRight) {
        this.stockIncomeRight = stockIncomeRight;
    }

    /**
     * 其他股权收益权
     * @return OTHER_EQUITY_INCOME_RIGHTS 其他股权收益权
     */
    public BigDecimal getOtherEquityIncomeRights() {
        return otherEquityIncomeRights;
    }

    /**
     * 其他股权收益权
     * @param otherEquityIncomeRights 其他股权收益权
     */
    public void setOtherEquityIncomeRights(BigDecimal otherEquityIncomeRights) {
        this.otherEquityIncomeRights = otherEquityIncomeRights;
    }

    /**
     * 物权资产收益权
     * @return PROPERTY_RIGHTS 物权资产收益权
     */
    public BigDecimal getPropertyRights() {
        return propertyRights;
    }

    /**
     * 物权资产收益权
     * @param propertyRights 物权资产收益权
     */
    public void setPropertyRights(BigDecimal propertyRights) {
        this.propertyRights = propertyRights;
    }

    /**
     * 总计
     * @return TOTAL 总计
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 总计
     * @param total 总计
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 关联主表uuid
     * @return REPORTUUID 关联主表uuid
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 关联主表uuid
     * @param reportuuid 关联主表uuid
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }
}