package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokBankBalance extends BaseEntity {
    /**
     * 流水号
     */
    private String sqeNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 入账日
     */
    private Date vouchNum;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAmtBookDate;

    /**
     * 余额方向
     */
    private String balFlagBookDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSqeNo() {
        return sqeNo;
    }

    /**
     * 设置 流水号
     * @param sqeNo 流水号
     */
    public void setSqeNo(String sqeNo) {
        this.sqeNo = sqeNo == null ? null : sqeNo.trim();
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
     * 取得 入账日
     * @return 入账日
     */
    public Date getVouchNum() {
        return vouchNum;
    }

    /**
     * 设置 入账日
     * @param vouchNum 入账日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVouchNum(Date vouchNum) {
        this.vouchNum = vouchNum;
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
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
     * 取得 场景代码
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
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
     * 取得 科目余额
     * @return 科目余额
     */
    public BigDecimal getbAmtBookDate() {
        return bAmtBookDate;
    }

    /**
     * 设置 科目余额
     * @param bAmtBookDate 科目余额
     */
    public void setbAmtBookDate(BigDecimal bAmtBookDate) {
        this.bAmtBookDate = bAmtBookDate;
    }

    /**
     * 取得 余额方向
     * @return 余额方向
     */
    public String getBalFlagBookDate() {
        return balFlagBookDate;
    }

    /**
     * 设置 余额方向
     * @param balFlagBookDate 余额方向
     */
    public void setBalFlagBookDate(String balFlagBookDate) {
        this.balFlagBookDate = balFlagBookDate == null ? null : balFlagBookDate.trim();
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
}