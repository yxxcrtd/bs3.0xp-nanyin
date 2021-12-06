package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class StlSecpReconKey extends BaseEntity {
    /**
     * 资金账户
     */
    private String acctNo;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 币种
     */
    private String jylb;

    /**
     * 市场代码
     */
    private String marketId;

    /**
     * 取得 资金账户
     * @return 资金账户
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 资金账户
     * @param acctNo 资金账户
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
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
     * 取得 币种
     * @return 币种
     */
    public String getJylb() {
        return jylb;
    }

    /**
     * 设置 币种
     * @param jylb 币种
     */
    public void setJylb(String jylb) {
        this.jylb = jylb == null ? null : jylb.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * 设置 市场代码
     * @param marketId 市场代码
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }
}