package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class PtlSecReceivablesKey extends BaseEntity {
    /**
     * 日期
     */
    private Date cdate;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 证券管理账户/通道代码，无通道无证券管理账户的存999999，目前不管证券管理户维度
     */
    private String secAcctId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 投资目的
     */
    private String invAim;

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
     * 取得 证券管理账户/通道代码，无通道无证券管理账户的存999999，目前不管证券管理户维度
     * @return 证券管理账户/通道代码，无通道无证券管理账户的存999999，目前不管证券管理户维度
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 证券管理账户/通道代码，无通道无证券管理账户的存999999，目前不管证券管理户维度
     * @param secAcctId 证券管理账户/通道代码，无通道无证券管理账户的存999999，目前不管证券管理户维度
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId == null ? null : secAcctId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }
}