package com.joyintech.tams.svreport.chbreport.hxprofit.dto;


import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.HxProfit;

import java.math.BigDecimal;

/**
 * 华夏利润表导出Dto
 *
 * @author WanShiwei
 * @version 2.1.6 2021年4月20日
 * @see HXProfitQuery001Dto
 * @since 2.1.6
 */
public class HXProfitQuery001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 本期数据
     */
    private HxProfit bq;

    /**
     * 上月数据
     */
    private HxProfit sy;

    /**
     * 本年数据
     */
    private HxProfit bn;


    /**
     * 营业收入-本期
     */
    private BigDecimal busiIncBQ;

    /**
     * 营业收入-上月
     */
    private BigDecimal busiIncSY;

    /**
     * 营业收入-本年
     */
    private BigDecimal busiIncBN;

    /**
     * 营业支出-本期
     */
    private BigDecimal busiPayBQ;

    /**
     * 营业支出-上月
     */
    private BigDecimal busiPaySY;

    /**
     * 营业支出-本年
     */
    private BigDecimal busiPayBN;

    public HxProfit getBq() {
        return bq;
    }

    public void setBq(HxProfit bq) {
        this.bq = bq;
    }

    public HxProfit getSy() {
        return sy;
    }

    public void setSy(HxProfit sy) {
        this.sy = sy;
    }

    public HxProfit getBn() {
        return bn;
    }

    public void setBn(HxProfit bn) {
        this.bn = bn;
    }

    public BigDecimal getBusiIncBQ() {
        return busiIncBQ;
    }

    public void setBusiIncBQ(BigDecimal busiIncBQ) {
        this.busiIncBQ = busiIncBQ;
    }

    public BigDecimal getBusiIncSY() {
        return busiIncSY;
    }

    public void setBusiIncSY(BigDecimal busiIncSY) {
        this.busiIncSY = busiIncSY;
    }

    public BigDecimal getBusiIncBN() {
        return busiIncBN;
    }

    public void setBusiIncBN(BigDecimal busiIncBN) {
        this.busiIncBN = busiIncBN;
    }

    public BigDecimal getBusiPayBQ() {
        return busiPayBQ;
    }

    public void setBusiPayBQ(BigDecimal busiPayBQ) {
        this.busiPayBQ = busiPayBQ;
    }

    public BigDecimal getBusiPaySY() {
        return busiPaySY;
    }

    public void setBusiPaySY(BigDecimal busiPaySY) {
        this.busiPaySY = busiPaySY;
    }

    public BigDecimal getBusiPayBN() {
        return busiPayBN;
    }

    public void setBusiPayBN(BigDecimal busiPayBN) {
        this.busiPayBN = busiPayBN;
    }
}
