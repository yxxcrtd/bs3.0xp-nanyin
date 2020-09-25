package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbInvestholdInfoSub extends BaseEntity {
    /**
     * 登记序号
     */
    private String registSeqno;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 产品登记编码
     */
    private String prdRegistCode;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 持有日期
     */
    private Date holdDate;

    /**
     * 持有金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 持有份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 折算成人民币金额(元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cnyAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报送截止日
     */
    private Date submitDeadline;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 检查序列号
     */
    private String checkSeqno;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 取得 登记序号
     * @return 登记序号
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 登记序号
     * @param registSeqno 登记序号
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 登记银行代码
     * @return 登记银行代码
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 登记银行代码
     * @param registBankCode 登记银行代码
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 取得 中债报送截止日
     * @return 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 设置 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prdRegistCode 产品登记编码
     */
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode == null ? null : prdRegistCode.trim();
    }

    /**
     * 取得 识别标识
     * @return 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 识别标识
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
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
     * 取得 持有日期
     * @return 持有日期
     */
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * 设置 持有日期
     * @param holdDate 持有日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    /**
     * 取得 持有金额
     * @return 持有金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 持有金额
     * @param amount 持有金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 持有份额
     * @return 持有份额
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 持有份额
     * @param qty 持有份额
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 取得 折算成人民币金额(元)
     * @return 折算成人民币金额(元)
     */
    public BigDecimal getCnyAmt() {
        return cnyAmt;
    }

    /**
     * 设置 折算成人民币金额(元)
     * @param cnyAmt 折算成人民币金额(元)
     */
    public void setCnyAmt(BigDecimal cnyAmt) {
        this.cnyAmt = cnyAmt;
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
     * 取得 报送截止日
     * @return 报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 报送截止日
     * @param submitDeadline 报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送日期
     * @param submitTime 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 报送流水号
     * @return 报送流水号
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 报送流水号
     * @param sumitSeq 报送流水号
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
    }

    /**
     * 取得 检查序列号
     * @return 检查序列号
     */
    public String getCheckSeqno() {
        return checkSeqno;
    }

    /**
     * 设置 检查序列号
     * @param checkSeqno 检查序列号
     */
    public void setCheckSeqno(String checkSeqno) {
        this.checkSeqno = checkSeqno == null ? null : checkSeqno.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }
}