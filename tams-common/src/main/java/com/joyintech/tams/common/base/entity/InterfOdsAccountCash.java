package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfOdsAccountCash extends BaseEntity {
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
    private String cdate;

    /**
     * 帐号
     */
    private String account;

    /**
     * 交易时间
     */
    private String dealdate;

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
     * 归属机构
     */
    private String netno;

    /**
     * 0--正常 1--被冲正 2--冲正 3--被抹账 4--抹账
     */
    private String effectflag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用途
     */
    private String usage;

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
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 到账日
     * @param cdate 到账日
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
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
     * 取得 交易时间
     * @return 交易时间
     */
    public String getDealdate() {
        return dealdate;
    }

    /**
     * 设置 交易时间
     * @param dealdate 交易时间
     */
    public void setDealdate(String dealdate) {
        this.dealdate = dealdate == null ? null : dealdate.trim();
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
     * 取得 归属机构
     * @return 归属机构
     */
    public String getNetno() {
        return netno;
    }

    /**
     * 设置 归属机构
     * @param netno 归属机构
     */
    public void setNetno(String netno) {
        this.netno = netno == null ? null : netno.trim();
    }

    /**
     * 取得 0--正常 1--被冲正 2--冲正 3--被抹账 4--抹账
     * @return 0--正常 1--被冲正 2--冲正 3--被抹账 4--抹账
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 0--正常 1--被冲正 2--冲正 3--被抹账 4--抹账
     * @param effectflag 0--正常 1--被冲正 2--冲正 3--被抹账 4--抹账
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
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
}