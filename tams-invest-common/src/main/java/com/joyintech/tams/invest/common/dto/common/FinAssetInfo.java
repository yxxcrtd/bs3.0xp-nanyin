package com.joyintech.tams.invest.common.dto.common;

import java.math.BigDecimal;

/**
 * @program: tams-invest-parent
 * @description: 资产信息
 * @author: 强成西
 * @create: 2018-10-30 16:57
 **/
public class FinAssetInfo {

    /**
     * 一级分类
     */
    private String type6;

    /**
     * 二级分类
     */
    private String type7;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 类型
     */
    private String cashType;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 初始投资金额
     */
    private BigDecimal issueAmt;

    /**
     * 起息日
     */
    private String vDate;

    /**
     * 到期日
     */
    private String mDate;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 获取 type6
     *
     * @return type6
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 type6
     *
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    /**
     * 获取 type7
     *
     * @return type7
     */
    public String getType7() {
        return type7;
    }

    /**
     * 设置 type7
     *
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 finprodName
     *
     * @return finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     *
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 issueAmt
     *
     * @return issueAmt
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 issueAmt
     *
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 获取 vDate
     *
     * @return vDate
     */
    public String getvDate() {
        return vDate;
    }

    /**
     * 设置 vDate
     *
     * @param vDate vDate
     */
    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 cashType
     *
     * @return cashType
     */
    public String getCashType() {
        return cashType;
    }

    /**
     * 设置 cashType
     *
     * @param cashType cashType
     */
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    /**
     * 获取 mDate
     *
     * @return mDate
     */
    public String getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     *
     * @param mDate mDate
     */
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 basis
     *
     * @return basis
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }
}
