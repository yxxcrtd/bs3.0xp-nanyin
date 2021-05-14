package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokVouchDetail extends BaseEntity {
    /**
     * 凭证子表编号
     */
    private String vouchSubnum;

    /**
     * 凭证编号
     */
    private String vouchNum;

    /**
     * 业务凭证子表编号
     */
    private String busVouchsubnum;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenAmt;

    /**
     * 发生数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenNumber;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 四级科目号
     */
    private String detailSubjectNo;

    /**
     * 凭证类型
     */
    private String bookType;

    /**
     * 本位币发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bHappenAmt;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 四级科目名称
     */
    private String detailSubjectName;

    /**
     * 取得 凭证子表编号
     * @return 凭证子表编号
     */
    public String getVouchSubnum() {
        return vouchSubnum;
    }

    /**
     * 设置 凭证子表编号
     * @param vouchSubnum 凭证子表编号
     */
    public void setVouchSubnum(String vouchSubnum) {
        this.vouchSubnum = vouchSubnum == null ? null : vouchSubnum.trim();
    }

    /**
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getVouchNum() {
        return vouchNum;
    }

    /**
     * 设置 凭证编号
     * @param vouchNum 凭证编号
     */
    public void setVouchNum(String vouchNum) {
        this.vouchNum = vouchNum == null ? null : vouchNum.trim();
    }

    /**
     * 取得 业务凭证子表编号
     * @return 业务凭证子表编号
     */
    public String getBusVouchsubnum() {
        return busVouchsubnum;
    }

    /**
     * 设置 业务凭证子表编号
     * @param busVouchsubnum 业务凭证子表编号
     */
    public void setBusVouchsubnum(String busVouchsubnum) {
        this.busVouchsubnum = busVouchsubnum == null ? null : busVouchsubnum.trim();
    }

    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
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
     * 取得 发生额
     * @return 发生额
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 发生额
     * @param happenAmt 发生额
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 取得 发生数量
     * @return 发生数量
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 发生数量
     * @param happenNumber 发生数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
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
     * 取得 四级科目号
     * @return 四级科目号
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 设置 四级科目号
     * @param detailSubjectNo 四级科目号
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo == null ? null : detailSubjectNo.trim();
    }

    /**
     * 取得 凭证类型
     * @return 凭证类型
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * 设置 凭证类型
     * @param bookType 凭证类型
     */
    public void setBookType(String bookType) {
        this.bookType = bookType == null ? null : bookType.trim();
    }

    /**
     * 取得 本位币发生额
     * @return 本位币发生额
     */
    public BigDecimal getbHappenAmt() {
        return bHappenAmt;
    }

    /**
     * 设置 本位币发生额
     * @param bHappenAmt 本位币发生额
     */
    public void setbHappenAmt(BigDecimal bHappenAmt) {
        this.bHappenAmt = bHappenAmt;
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getbCcy() {
        return bCcy;
    }

    /**
     * 设置 本位币
     * @param bCcy 本位币
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy == null ? null : bCcy.trim();
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
     * 取得 四级科目名称
     * @return 四级科目名称
     */
    public String getDetailSubjectName() {
        return detailSubjectName;
    }

    /**
     * 设置 四级科目名称
     * @param detailSubjectName 四级科目名称
     */
    public void setDetailSubjectName(String detailSubjectName) {
        this.detailSubjectName = detailSubjectName == null ? null : detailSubjectName.trim();
    }
}