package com.joyintech.tams.svreport.pbc.assetdebt.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_BALANCETERM表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportDebtTermDetail {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 人行报表主表uuid
     */
    private String reportuuid;

    /**
     * 报表类型 DEBTTERM-资管产品资产负债剩余期限统计
     */
    private String reporttype;

    /**
     * 分组类型 资产-ASSET，负债-DEBT
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 债务工具
     */
    private BigDecimal debtInstrument;

    /**
     * 股权
     */
    private BigDecimal stockRight;

    /**
     * 特定目的载体份额
     */
    private BigDecimal specialShare;

    /**
     * 实收本金
     */
    private BigDecimal actualAmt;

    /**
     * 明细统计uuid
     * @return DETAILUUID 明细统计uuid
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 明细统计uuid
     * @param detailuuid 明细统计uuid
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 人行报表主表uuid
     * @return REPORTUUID 人行报表主表uuid
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 人行报表主表uuid
     * @param reportuuid 人行报表主表uuid
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 报表类型 DEBTTERM-资管产品资产负债剩余期限统计
     * @return REPORTTYPE 报表类型 DEBTTERM-资管产品资产负债剩余期限统计
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型 DEBTTERM-资管产品资产负债剩余期限统计
     * @param reporttype 报表类型 DEBTTERM-资管产品资产负债剩余期限统计
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 分组类型 资产-ASSET，负债-DEBT
     * @return GROUPTYPE 分组类型 资产-ASSET，负债-DEBT
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 分组类型 资产-ASSET，负债-DEBT
     * @param grouptype 分组类型 资产-ASSET，负债-DEBT
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
     * 债务工具
     * @return DEBT_INSTRUMENT 债务工具
     */
    public BigDecimal getDebtInstrument() {
        return debtInstrument;
    }

    /**
     * 债务工具
     * @param debtInstrument 债务工具
     */
    public void setDebtInstrument(BigDecimal debtInstrument) {
        this.debtInstrument = debtInstrument;
    }

    /**
     * 股权
     * @return STOCK_RIGHT 股权
     */
    public BigDecimal getStockRight() {
        return stockRight;
    }

    /**
     * 股权
     * @param stockRight 股权
     */
    public void setStockRight(BigDecimal stockRight) {
        this.stockRight = stockRight;
    }

    /**
     * 特定目的载体份额
     * @return SPECIAL_SHARE 特定目的载体份额
     */
    public BigDecimal getSpecialShare() {
        return specialShare;
    }

    /**
     * 特定目的载体份额
     * @param specialShare 特定目的载体份额
     */
    public void setSpecialShare(BigDecimal specialShare) {
        this.specialShare = specialShare;
    }

    /**
     * 实收本金
     * @return ACTUAL_AMT 实收本金
     */
    public BigDecimal getActualAmt() {
        return actualAmt;
    }

    /**
     * 实收本金
     * @param actualAmt 实收本金
     */
    public void setActualAmt(BigDecimal actualAmt) {
        this.actualAmt = actualAmt;
    }
}