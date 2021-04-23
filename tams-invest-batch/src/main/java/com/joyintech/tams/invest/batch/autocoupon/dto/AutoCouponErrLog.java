package com.joyintech.tams.invest.batch.autocoupon.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class AutoCouponErrLog extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 资产代码
     */
    private String finprodId;


    /**
     * 执行时间
     */
    private Date execTime;

    /**
     * 异常信息
     */
    private String errMessage;


    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
