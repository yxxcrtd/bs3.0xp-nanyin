package com.joyintech.interf.bankname.cfetsup.secrepoquote.dto;

import com.joyintech.interf.bankname.cfetsup.secrepoquote.entity.FxSecrepoQuoteDetail;

import java.math.BigDecimal;
import java.util.Date;

/**
 * FxSecrepoQuoteDetailDto
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-10
 */
public class FxSecrepoQuoteDetail001Dto extends FxSecrepoQuoteDetail {

    /**
     * 债券到期日
     */
    private Date mdate;

    /**
     * 计划行权日
     */
    private Date exerciseDate;

    /**
     * 最新净价
     */
    private BigDecimal netprice;

    /**
     * 最新净价日
     */
    private Date cdate;

    /**
     * 发行机构
     */
    private String issuser;

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public BigDecimal getNetprice() {
        return netprice;
    }

    public void setNetprice(BigDecimal netprice) {
        this.netprice = netprice;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getIssuser() {
        return issuser;
    }

    public void setIssuser(String issuser) {
        this.issuser = issuser;
    }
}
