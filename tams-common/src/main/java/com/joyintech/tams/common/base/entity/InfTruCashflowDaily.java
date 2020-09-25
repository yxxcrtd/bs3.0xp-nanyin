package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InfTruCashflowDaily extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 序号
     */
    private Long numno;

    /**
     * 到账日
     */
    private Date cdate;

    /**
     * 帐号
     */
    private String account;

    /**
     * 借贷标识
     */
    private String ps;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;

    /**
     * 余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal reprinamt;

    /**
     * 对手方账户
     */
    private String seller;

    /**
     * 对手方账户名称
     */
    private String sellername;

    /**
     * 摘要
     */
    private String remark;

    /**
     * 用途
     */
    private String usage;

    /**
     * 附言
     */
    private String postScript;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 序号
     * @return 序号
     */
    public Long getNumno() {
        return numno;
    }

    /**
     * 设置 序号
     * @param numno 序号
     */
    public void setNumno(Long numno) {
        this.numno = numno;
    }

    /**
     * 取得 到账日
     * @return 到账日
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 到账日
     * @param cdate 到账日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 帐号
     * @return 帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 帐号
     * @param account 帐号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 借贷标识
     * @return 借贷标识
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 借贷标识
     * @param ps 借贷标识
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
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
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 设置 金额
     * @param prinamt 金额
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    /**
     * 取得 余额
     * @return 余额
     */
    public BigDecimal getReprinamt() {
        return reprinamt;
    }

    /**
     * 设置 余额
     * @param reprinamt 余额
     */
    public void setReprinamt(BigDecimal reprinamt) {
        this.reprinamt = reprinamt;
    }

    /**
     * 取得 对手方账户
     * @return 对手方账户
     */
    public String getSeller() {
        return seller;
    }

    /**
     * 设置 对手方账户
     * @param seller 对手方账户
     */
    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    /**
     * 取得 对手方账户名称
     * @return 对手方账户名称
     */
    public String getSellername() {
        return sellername;
    }

    /**
     * 设置 对手方账户名称
     * @param sellername 对手方账户名称
     */
    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    /**
     * 取得 摘要
     * @return 摘要
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 摘要
     * @param remark 摘要
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 用途
     * @return 用途
     */
    public String getUsage() {
        return usage;
    }

    /**
     * 设置 用途
     * @param usage 用途
     */
    public void setUsage(String usage) {
        this.usage = usage == null ? null : usage.trim();
    }

    /**
     * 取得 附言
     * @return 附言
     */
    public String getPostScript() {
        return postScript;
    }

    /**
     * 设置 附言
     * @param postScript 附言
     */
    public void setPostScript(String postScript) {
        this.postScript = postScript == null ? null : postScript.trim();
    }
}