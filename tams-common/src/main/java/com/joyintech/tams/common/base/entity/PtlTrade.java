package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class PtlTrade extends PtlTradeKey {
    /**
     * 业务明细代码，金融产品代码等
     */
    private String busiId;

    /**
     * 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    private String busiType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 证券托管户代码
     */
    private String secManageAcctId;

    /**
     * 实际发生日
     */
    private Date settleDate;

    /**
     * 取得 业务明细代码，金融产品代码等
     * @return 业务明细代码，金融产品代码等
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码，金融产品代码等
     * @param busiId 业务明细代码，金融产品代码等
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 业务类型，债券交易、债券存续交易、基金交易、收划款等
     * @return 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型，债券交易、债券存续交易、基金交易、收划款等
     * @param busiType 业务类型，债券交易、债券存续交易、基金交易、收划款等
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
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

    /**
     * 取得 证券托管户代码
     * @return 证券托管户代码
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 证券托管户代码
     * @param secManageAcctId 证券托管户代码
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId == null ? null : secManageAcctId.trim();
    }

    /**
     * 取得 实际发生日
     * @return 实际发生日
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 实际发生日
     * @param settleDate 实际发生日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
}