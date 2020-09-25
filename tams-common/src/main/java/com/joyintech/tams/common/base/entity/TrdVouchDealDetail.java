package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TrdVouchDealDetail extends BaseEntity {
    /**
     * 流水
     */
    private String seqNo;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目尾号
     */
    private String detailSubjectNoTail;

    /**
     * 尾号名称
     */
    private String detailSubnameTail;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oHappenAmt;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenNumber;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 业务明细类型
     */
    private String busiDetailType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 证券托管户代码
     */
    private String secManageAcctId;


    /**
     * 本币种
     */
    private String bCcy;

    /**
     * 本币金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bHappenAmt;



    /**
     * 取得 流水
     * @return 流水
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水
     * @param seqNo 流水
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 科目名称
     * @return 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 取得 科目尾号
     * @return 科目尾号
     */
    public String getDetailSubjectNoTail() {
        return detailSubjectNoTail;
    }

    /**
     * 设置 科目尾号
     * @param detailSubjectNoTail 科目尾号
     */
    public void setDetailSubjectNoTail(String detailSubjectNoTail) {
        this.detailSubjectNoTail = detailSubjectNoTail == null ? null : detailSubjectNoTail.trim();
    }

    /**
     * 取得 尾号名称
     * @return 尾号名称
     */
    public String getDetailSubnameTail() {
        return detailSubnameTail;
    }

    /**
     * 设置 尾号名称
     * @param detailSubnameTail 尾号名称
     */
    public void setDetailSubnameTail(String detailSubnameTail) {
        this.detailSubnameTail = detailSubnameTail == null ? null : detailSubnameTail.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }

    /**
     * 设置 金额
     * @param oHappenAmt 金额
     */
    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }

    /**
     * 取得 借贷方向
     * @return 借贷方向
     */
    public String getCdFlag() {
        return cdFlag;
    }

    /**
     * 设置 借贷方向
     * @param cdFlag 借贷方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag == null ? null : cdFlag.trim();
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
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 数量
     * @param happenNumber 数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    /**
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }

    /**
     * 取得 业务明细代码
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 业务明细类型
     * @return 业务明细类型
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型
     * @param busiDetailType 业务明细类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType == null ? null : busiDetailType.trim();
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
     * 取得 本币种
     * @return 本币种
     */
    public String getBCcy() {
        return bCcy;
    }
    /**
     * 设置 本币种
     * @param bCcy 本币种
     */
    public void setBCcy(String bCcy) {
        this.bCcy = bCcy;
    }

    /**
     * 取得 本币金额
     * @return 本币金额
     */
    public BigDecimal getbHappenAmt() {
        return bHappenAmt;
    }
    /**
     * 设置 本币金额
     * @param bHappenAmt 本币金额
     */
    public void setbHappenAmt(BigDecimal bHappenAmt) {
        this.bHappenAmt = bHappenAmt;
    }

}