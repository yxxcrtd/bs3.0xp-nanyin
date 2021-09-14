package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_INVESTHOLD_INFO_SUB_SUM表的实体类
 * @version
 * @author:  Hai
 * @创建时间: 2021-05-19
 */
public class ChbInvestholdInfoSubSum extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 产品登记编码
     */
    private String prdRegistCode;

    /**
     * 持有日期
     */
    private Date holdDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 持有金额
     */
    private BigDecimal amount;

    /**
     * 持有份额
     */
    private BigDecimal qty;

    /**
     * 折算成人民币金额(元)
     */
    private BigDecimal cnyAmt;

    /**
     * 个人投资者总数
     */
    private Long singleTotal;

    /**
     * 法人投资者总数
     */
    private Long legalTotal;

    /**
     * 非法人投资者总数
     */
    private Long unlegalTotal;

    /**
     * 备注
     */
    private String remark;

    /**
     * 产品代码
     * @return PRODID 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 产品登记编码
     * @return PRD_REGIST_CODE 产品登记编码
     */
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 产品登记编码
     * @param prdRegistCode 产品登记编码
     */
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode == null ? null : prdRegistCode.trim();
    }

    /**
     * 持有日期
     * @return HOLD_DATE 持有日期
     */
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * 持有日期
     * @param holdDate 持有日期
     */
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    /**
     * 币种
     * @return CCY 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 持有金额
     * @return AMOUNT 持有金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 持有金额
     * @param amount 持有金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 持有份额
     * @return QTY 持有份额
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 持有份额
     * @param qty 持有份额
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 折算成人民币金额(元)
     * @return CNY_AMT 折算成人民币金额(元)
     */
    public BigDecimal getCnyAmt() {
        return cnyAmt;
    }

    /**
     * 折算成人民币金额(元)
     * @param cnyAmt 折算成人民币金额(元)
     */
    public void setCnyAmt(BigDecimal cnyAmt) {
        this.cnyAmt = cnyAmt;
    }

    /**
     * 个人投资者总数
     * @return SINGLE_TOTAL 个人投资者总数
     */
    public Long getSingleTotal() {
        return singleTotal;
    }

    /**
     * 个人投资者总数
     * @param singleTotal 个人投资者总数
     */
    public void setSingleTotal(Long singleTotal) {
        this.singleTotal = singleTotal;
    }

    /**
     * 法人投资者总数
     * @return LEGAL_TOTAL 法人投资者总数
     */
    public Long getLegalTotal() {
        return legalTotal;
    }

    /**
     * 法人投资者总数
     * @param legalTotal 法人投资者总数
     */
    public void setLegalTotal(Long legalTotal) {
        this.legalTotal = legalTotal;
    }

    /**
     * 非法人投资者总数
     * @return UNLEGAL_TOTAL 非法人投资者总数
     */
    public Long getUnlegalTotal() {
        return unlegalTotal;
    }

    /**
     * 非法人投资者总数
     * @param unlegalTotal 非法人投资者总数
     */
    public void setUnlegalTotal(Long unlegalTotal) {
        this.unlegalTotal = unlegalTotal;
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}