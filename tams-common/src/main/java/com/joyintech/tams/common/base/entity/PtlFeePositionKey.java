package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class PtlFeePositionKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 组合费用类型
     */
    private String ptlFeeType;

    /**
     * 组合费用场所，结算手续费时分登记托管机构存储，其他的存999999
     */
    private String ptlFeePlace;

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
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
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 组合费用类型
     * @return 组合费用类型
     */
    public String getPtlFeeType() {
        return ptlFeeType;
    }

    /**
     * 设置 组合费用类型
     * @param ptlFeeType 组合费用类型
     */
    public void setPtlFeeType(String ptlFeeType) {
        this.ptlFeeType = ptlFeeType == null ? null : ptlFeeType.trim();
    }

    /**
     * 取得 组合费用场所，结算手续费时分登记托管机构存储，其他的存999999
     * @return 组合费用场所，结算手续费时分登记托管机构存储，其他的存999999
     */
    public String getPtlFeePlace() {
        return ptlFeePlace;
    }

    /**
     * 设置 组合费用场所，结算手续费时分登记托管机构存储，其他的存999999
     * @param ptlFeePlace 组合费用场所，结算手续费时分登记托管机构存储，其他的存999999
     */
    public void setPtlFeePlace(String ptlFeePlace) {
        this.ptlFeePlace = ptlFeePlace == null ? null : ptlFeePlace.trim();
    }
}