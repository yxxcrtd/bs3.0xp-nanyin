package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:TRD_ACCOUNTMF_CONFIGDETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2020-07-15
 */
public class TrdAccountmfConfigdetailDto extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 主表流水号
     */
    private String pSeqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 每季费用金额
     */
    private BigDecimal feeAmtDetail;

    /**
     * 流水号
     * @return SEQ_NO 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 主表流水号
     * @return P_SEQ_NO 主表流水号
     */
    public String getpSeqNo() {
        return pSeqNo;
    }

    /**
     * 主表流水号
     * @param pSeqNo 主表流水号
     */
    public void setpSeqNo(String pSeqNo) {
        this.pSeqNo = pSeqNo == null ? null : pSeqNo.trim();
    }

    /**
     * 账套代码
     * @return BOOKSET_ID 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 每季费用金额
     * @return FEE_AMT_DETAIL 每季费用金额
     */
    public BigDecimal getFeeAmtDetail() {
        return feeAmtDetail;
    }

    /**
     * 每季费用金额
     * @param feeAmtDetail 每季费用金额
     */
    public void setFeeAmtDetail(BigDecimal feeAmtDetail) {
        this.feeAmtDetail = feeAmtDetail;
    }

}