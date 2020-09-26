package com.joyintech.tams.svreport.interf.dis.bokbalanceinterf.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class BokBalance extends BokBalanceKey {
    /**
     * 父科目号
     */
    private String fsubjectNo;

    /**
     * 科目级别
     */
    private Long subjectLevel;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 结转余额方向
     */
    private String balCoFlag;

    /**
     * 原币
     */
    private String oCcy;

    /**
     * 原币余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oAmt;

    /**
     * 原币余额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oCAmt;

    /**
     * 原币余额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oDAmt;

    /**
     * 原币结转余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oCoAmt;

    /**
     * 原币结转余额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oCoCAmt;

    /**
     * 原币结转余额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oCoDAmt;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 本位币余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAmt;

    /**
     * 本位币余额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCAmt;

    /**
     * 本位币结转余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCoAmt;

    /**
     * 本位币结转余额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCoCAmt;

    /**
     * 本位币结转余额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCoDAmt;

    /**
     * 本位币余额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bDAmt;

    /**
     * 原币当日发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyOAmt;

    /**
     * 原币当日发生额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyOCAmt;

    /**
     * 原币当日发生额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyODAmt;

    /**
     * 原币结转当日发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyOCoAmt;

    /**
     * 原币结转当日发生额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyOCoCAmt;

    /**
     * 原币结转当日发生额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyOCoDAmt;

    /**
     * 本位币当日发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBAmt;

    /**
     * 本位币当日发生额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBCAmt;

    /**
     * 本位币当日发生额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBDAmt;

    /**
     * 本位币结转当日发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBCoAmt;

    /**
     * 本位币结转当日发生额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBCoCAmt;

    /**
     * 本位币结转当日发生额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBCoDAmt;

    /**
     * 是否叶子节点
     */
    private String isLeaf;

    /**
     * 数量余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal numAmt;

    /**
     * 当日发生额方向
     */
    private String tdyAmtFlag;

    /**
     * 结转当日发生额方向
     */
    private String tdyCoFlag;

    /**
     * 原币申购未实现平准金借
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPurODUlamt;

    /**
     * 原币赎回未实现平准金借
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyRedODUlamt;

    /**
     * 原币申购未实现平准金贷
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPurOCUlamt;

    /**
     * 原币赎回未实现平准金贷
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyRedOCUlamt;

    /**
     * 本位币申购未实现平准金借
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPurBDUlamt;

    /**
     * 本位币赎回未实现平准金借
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyRedBDUlamt;

    /**
     * 本位币申购未实现平准金贷
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPurBCUlamt;

    /**
     * 本位币赎回未实现平准金贷
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyRedBCUlamt;

    /**
     * null
     */
    private String inputType;

    /**
     * null
     */
    private String inputUser;

    /**
     * null
     */
    private String adapterId;

    /**
     * 取得 父科目号
     * @return 父科目号
     */
    public String getFsubjectNo() {
        return fsubjectNo;
    }

    /**
     * 设置 父科目号
     * @param fsubjectNo 父科目号
     */
    public void setFsubjectNo(String fsubjectNo) {
        this.fsubjectNo = fsubjectNo == null ? null : fsubjectNo.trim();
    }

    /**
     * 取得 科目级别
     * @return 科目级别
     */
    public Long getSubjectLevel() {
        return subjectLevel;
    }

    /**
     * 设置 科目级别
     * @param subjectLevel 科目级别
     */
    public void setSubjectLevel(Long subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    /**
     * 取得 余额方向
     * @return 余额方向
     */
    public String getBalFlag() {
        return balFlag;
    }

    /**
     * 设置 余额方向
     * @param balFlag 余额方向
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag == null ? null : balFlag.trim();
    }

    /**
     * 取得 结转余额方向
     * @return 结转余额方向
     */
    public String getBalCoFlag() {
        return balCoFlag;
    }

    /**
     * 设置 结转余额方向
     * @param balCoFlag 结转余额方向
     */
    public void setBalCoFlag(String balCoFlag) {
        this.balCoFlag = balCoFlag == null ? null : balCoFlag.trim();
    }

    /**
     * 取得 原币
     * @return 原币
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 设置 原币
     * @param oCcy 原币
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy == null ? null : oCcy.trim();
    }

    /**
     * 取得 原币余额
     * @return 原币余额
     */
    public BigDecimal getoAmt() {
        return oAmt;
    }

    /**
     * 设置 原币余额
     * @param oAmt 原币余额
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }

    /**
     * 取得 原币余额贷方
     * @return 原币余额贷方
     */
    public BigDecimal getoCAmt() {
        return oCAmt;
    }

    /**
     * 设置 原币余额贷方
     * @param oCAmt 原币余额贷方
     */
    public void setoCAmt(BigDecimal oCAmt) {
        this.oCAmt = oCAmt;
    }

    /**
     * 取得 原币余额借方
     * @return 原币余额借方
     */
    public BigDecimal getoDAmt() {
        return oDAmt;
    }

    /**
     * 设置 原币余额借方
     * @param oDAmt 原币余额借方
     */
    public void setoDAmt(BigDecimal oDAmt) {
        this.oDAmt = oDAmt;
    }

    /**
     * 取得 原币结转余额
     * @return 原币结转余额
     */
    public BigDecimal getoCoAmt() {
        return oCoAmt;
    }

    /**
     * 设置 原币结转余额
     * @param oCoAmt 原币结转余额
     */
    public void setoCoAmt(BigDecimal oCoAmt) {
        this.oCoAmt = oCoAmt;
    }

    /**
     * 取得 原币结转余额贷方
     * @return 原币结转余额贷方
     */
    public BigDecimal getoCoCAmt() {
        return oCoCAmt;
    }

    /**
     * 设置 原币结转余额贷方
     * @param oCoCAmt 原币结转余额贷方
     */
    public void setoCoCAmt(BigDecimal oCoCAmt) {
        this.oCoCAmt = oCoCAmt;
    }

    /**
     * 取得 原币结转余额借方
     * @return 原币结转余额借方
     */
    public BigDecimal getoCoDAmt() {
        return oCoDAmt;
    }

    /**
     * 设置 原币结转余额借方
     * @param oCoDAmt 原币结转余额借方
     */
    public void setoCoDAmt(BigDecimal oCoDAmt) {
        this.oCoDAmt = oCoDAmt;
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
     * 取得 本位币余额
     * @return 本位币余额
     */
    public BigDecimal getbAmt() {
        return bAmt;
    }

    /**
     * 设置 本位币余额
     * @param bAmt 本位币余额
     */
    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }

    /**
     * 取得 本位币余额贷方
     * @return 本位币余额贷方
     */
    public BigDecimal getbCAmt() {
        return bCAmt;
    }

    /**
     * 设置 本位币余额贷方
     * @param bCAmt 本位币余额贷方
     */
    public void setbCAmt(BigDecimal bCAmt) {
        this.bCAmt = bCAmt;
    }

    /**
     * 取得 本位币结转余额
     * @return 本位币结转余额
     */
    public BigDecimal getbCoAmt() {
        return bCoAmt;
    }

    /**
     * 设置 本位币结转余额
     * @param bCoAmt 本位币结转余额
     */
    public void setbCoAmt(BigDecimal bCoAmt) {
        this.bCoAmt = bCoAmt;
    }

    /**
     * 取得 本位币结转余额贷方
     * @return 本位币结转余额贷方
     */
    public BigDecimal getbCoCAmt() {
        return bCoCAmt;
    }

    /**
     * 设置 本位币结转余额贷方
     * @param bCoCAmt 本位币结转余额贷方
     */
    public void setbCoCAmt(BigDecimal bCoCAmt) {
        this.bCoCAmt = bCoCAmt;
    }

    /**
     * 取得 本位币结转余额借方
     * @return 本位币结转余额借方
     */
    public BigDecimal getbCoDAmt() {
        return bCoDAmt;
    }

    /**
     * 设置 本位币结转余额借方
     * @param bCoDAmt 本位币结转余额借方
     */
    public void setbCoDAmt(BigDecimal bCoDAmt) {
        this.bCoDAmt = bCoDAmt;
    }

    /**
     * 取得 本位币余额借方
     * @return 本位币余额借方
     */
    public BigDecimal getbDAmt() {
        return bDAmt;
    }

    /**
     * 设置 本位币余额借方
     * @param bDAmt 本位币余额借方
     */
    public void setbDAmt(BigDecimal bDAmt) {
        this.bDAmt = bDAmt;
    }

    /**
     * 取得 原币当日发生额
     * @return 原币当日发生额
     */
    public BigDecimal getTdyOAmt() {
        return tdyOAmt;
    }

    /**
     * 设置 原币当日发生额
     * @param tdyOAmt 原币当日发生额
     */
    public void setTdyOAmt(BigDecimal tdyOAmt) {
        this.tdyOAmt = tdyOAmt;
    }

    /**
     * 取得 原币当日发生额贷方
     * @return 原币当日发生额贷方
     */
    public BigDecimal getTdyOCAmt() {
        return tdyOCAmt;
    }

    /**
     * 设置 原币当日发生额贷方
     * @param tdyOCAmt 原币当日发生额贷方
     */
    public void setTdyOCAmt(BigDecimal tdyOCAmt) {
        this.tdyOCAmt = tdyOCAmt;
    }

    /**
     * 取得 原币当日发生额借方
     * @return 原币当日发生额借方
     */
    public BigDecimal getTdyODAmt() {
        return tdyODAmt;
    }

    /**
     * 设置 原币当日发生额借方
     * @param tdyODAmt 原币当日发生额借方
     */
    public void setTdyODAmt(BigDecimal tdyODAmt) {
        this.tdyODAmt = tdyODAmt;
    }

    /**
     * 取得 原币结转当日发生额
     * @return 原币结转当日发生额
     */
    public BigDecimal getTdyOCoAmt() {
        return tdyOCoAmt;
    }

    /**
     * 设置 原币结转当日发生额
     * @param tdyOCoAmt 原币结转当日发生额
     */
    public void setTdyOCoAmt(BigDecimal tdyOCoAmt) {
        this.tdyOCoAmt = tdyOCoAmt;
    }

    /**
     * 取得 原币结转当日发生额贷方
     * @return 原币结转当日发生额贷方
     */
    public BigDecimal getTdyOCoCAmt() {
        return tdyOCoCAmt;
    }

    /**
     * 设置 原币结转当日发生额贷方
     * @param tdyOCoCAmt 原币结转当日发生额贷方
     */
    public void setTdyOCoCAmt(BigDecimal tdyOCoCAmt) {
        this.tdyOCoCAmt = tdyOCoCAmt;
    }

    /**
     * 取得 原币结转当日发生额借方
     * @return 原币结转当日发生额借方
     */
    public BigDecimal getTdyOCoDAmt() {
        return tdyOCoDAmt;
    }

    /**
     * 设置 原币结转当日发生额借方
     * @param tdyOCoDAmt 原币结转当日发生额借方
     */
    public void setTdyOCoDAmt(BigDecimal tdyOCoDAmt) {
        this.tdyOCoDAmt = tdyOCoDAmt;
    }

    /**
     * 取得 本位币当日发生额
     * @return 本位币当日发生额
     */
    public BigDecimal getTdyBAmt() {
        return tdyBAmt;
    }

    /**
     * 设置 本位币当日发生额
     * @param tdyBAmt 本位币当日发生额
     */
    public void setTdyBAmt(BigDecimal tdyBAmt) {
        this.tdyBAmt = tdyBAmt;
    }

    /**
     * 取得 本位币当日发生额贷方
     * @return 本位币当日发生额贷方
     */
    public BigDecimal getTdyBCAmt() {
        return tdyBCAmt;
    }

    /**
     * 设置 本位币当日发生额贷方
     * @param tdyBCAmt 本位币当日发生额贷方
     */
    public void setTdyBCAmt(BigDecimal tdyBCAmt) {
        this.tdyBCAmt = tdyBCAmt;
    }

    /**
     * 取得 本位币当日发生额借方
     * @return 本位币当日发生额借方
     */
    public BigDecimal getTdyBDAmt() {
        return tdyBDAmt;
    }

    /**
     * 设置 本位币当日发生额借方
     * @param tdyBDAmt 本位币当日发生额借方
     */
    public void setTdyBDAmt(BigDecimal tdyBDAmt) {
        this.tdyBDAmt = tdyBDAmt;
    }

    /**
     * 取得 本位币结转当日发生额
     * @return 本位币结转当日发生额
     */
    public BigDecimal getTdyBCoAmt() {
        return tdyBCoAmt;
    }

    /**
     * 设置 本位币结转当日发生额
     * @param tdyBCoAmt 本位币结转当日发生额
     */
    public void setTdyBCoAmt(BigDecimal tdyBCoAmt) {
        this.tdyBCoAmt = tdyBCoAmt;
    }

    /**
     * 取得 本位币结转当日发生额贷方
     * @return 本位币结转当日发生额贷方
     */
    public BigDecimal getTdyBCoCAmt() {
        return tdyBCoCAmt;
    }

    /**
     * 设置 本位币结转当日发生额贷方
     * @param tdyBCoCAmt 本位币结转当日发生额贷方
     */
    public void setTdyBCoCAmt(BigDecimal tdyBCoCAmt) {
        this.tdyBCoCAmt = tdyBCoCAmt;
    }

    /**
     * 取得 本位币结转当日发生额借方
     * @return 本位币结转当日发生额借方
     */
    public BigDecimal getTdyBCoDAmt() {
        return tdyBCoDAmt;
    }

    /**
     * 设置 本位币结转当日发生额借方
     * @param tdyBCoDAmt 本位币结转当日发生额借方
     */
    public void setTdyBCoDAmt(BigDecimal tdyBCoDAmt) {
        this.tdyBCoDAmt = tdyBCoDAmt;
    }

    /**
     * 取得 是否叶子节点
     * @return 是否叶子节点
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    /**
     * 设置 是否叶子节点
     * @param isLeaf 是否叶子节点
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }

    /**
     * 取得 数量余额
     * @return 数量余额
     */
    public BigDecimal getNumAmt() {
        return numAmt;
    }

    /**
     * 设置 数量余额
     * @param numAmt 数量余额
     */
    public void setNumAmt(BigDecimal numAmt) {
        this.numAmt = numAmt;
    }

    /**
     * 取得 当日发生额方向
     * @return 当日发生额方向
     */
    public String getTdyAmtFlag() {
        return tdyAmtFlag;
    }

    /**
     * 设置 当日发生额方向
     * @param tdyAmtFlag 当日发生额方向
     */
    public void setTdyAmtFlag(String tdyAmtFlag) {
        this.tdyAmtFlag = tdyAmtFlag == null ? null : tdyAmtFlag.trim();
    }

    /**
     * 取得 结转当日发生额方向
     * @return 结转当日发生额方向
     */
    public String getTdyCoFlag() {
        return tdyCoFlag;
    }

    /**
     * 设置 结转当日发生额方向
     * @param tdyCoFlag 结转当日发生额方向
     */
    public void setTdyCoFlag(String tdyCoFlag) {
        this.tdyCoFlag = tdyCoFlag == null ? null : tdyCoFlag.trim();
    }

    /**
     * 取得 原币申购未实现平准金借
     * @return 原币申购未实现平准金借
     */
    public BigDecimal getTdyPurODUlamt() {
        return tdyPurODUlamt;
    }

    /**
     * 设置 原币申购未实现平准金借
     * @param tdyPurODUlamt 原币申购未实现平准金借
     */
    public void setTdyPurODUlamt(BigDecimal tdyPurODUlamt) {
        this.tdyPurODUlamt = tdyPurODUlamt;
    }

    /**
     * 取得 原币赎回未实现平准金借
     * @return 原币赎回未实现平准金借
     */
    public BigDecimal getTdyRedODUlamt() {
        return tdyRedODUlamt;
    }

    /**
     * 设置 原币赎回未实现平准金借
     * @param tdyRedODUlamt 原币赎回未实现平准金借
     */
    public void setTdyRedODUlamt(BigDecimal tdyRedODUlamt) {
        this.tdyRedODUlamt = tdyRedODUlamt;
    }

    /**
     * 取得 原币申购未实现平准金贷
     * @return 原币申购未实现平准金贷
     */
    public BigDecimal getTdyPurOCUlamt() {
        return tdyPurOCUlamt;
    }

    /**
     * 设置 原币申购未实现平准金贷
     * @param tdyPurOCUlamt 原币申购未实现平准金贷
     */
    public void setTdyPurOCUlamt(BigDecimal tdyPurOCUlamt) {
        this.tdyPurOCUlamt = tdyPurOCUlamt;
    }

    /**
     * 取得 原币赎回未实现平准金贷
     * @return 原币赎回未实现平准金贷
     */
    public BigDecimal getTdyRedOCUlamt() {
        return tdyRedOCUlamt;
    }

    /**
     * 设置 原币赎回未实现平准金贷
     * @param tdyRedOCUlamt 原币赎回未实现平准金贷
     */
    public void setTdyRedOCUlamt(BigDecimal tdyRedOCUlamt) {
        this.tdyRedOCUlamt = tdyRedOCUlamt;
    }

    /**
     * 取得 本位币申购未实现平准金借
     * @return 本位币申购未实现平准金借
     */
    public BigDecimal getTdyPurBDUlamt() {
        return tdyPurBDUlamt;
    }

    /**
     * 设置 本位币申购未实现平准金借
     * @param tdyPurBDUlamt 本位币申购未实现平准金借
     */
    public void setTdyPurBDUlamt(BigDecimal tdyPurBDUlamt) {
        this.tdyPurBDUlamt = tdyPurBDUlamt;
    }

    /**
     * 取得 本位币赎回未实现平准金借
     * @return 本位币赎回未实现平准金借
     */
    public BigDecimal getTdyRedBDUlamt() {
        return tdyRedBDUlamt;
    }

    /**
     * 设置 本位币赎回未实现平准金借
     * @param tdyRedBDUlamt 本位币赎回未实现平准金借
     */
    public void setTdyRedBDUlamt(BigDecimal tdyRedBDUlamt) {
        this.tdyRedBDUlamt = tdyRedBDUlamt;
    }

    /**
     * 取得 本位币申购未实现平准金贷
     * @return 本位币申购未实现平准金贷
     */
    public BigDecimal getTdyPurBCUlamt() {
        return tdyPurBCUlamt;
    }

    /**
     * 设置 本位币申购未实现平准金贷
     * @param tdyPurBCUlamt 本位币申购未实现平准金贷
     */
    public void setTdyPurBCUlamt(BigDecimal tdyPurBCUlamt) {
        this.tdyPurBCUlamt = tdyPurBCUlamt;
    }

    /**
     * 取得 本位币赎回未实现平准金贷
     * @return 本位币赎回未实现平准金贷
     */
    public BigDecimal getTdyRedBCUlamt() {
        return tdyRedBCUlamt;
    }

    /**
     * 设置 本位币赎回未实现平准金贷
     * @param tdyRedBCUlamt 本位币赎回未实现平准金贷
     */
    public void setTdyRedBCUlamt(BigDecimal tdyRedBCUlamt) {
        this.tdyRedBCUlamt = tdyRedBCUlamt;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 null
     * @param inputType null
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 null
     * @param inputUser null
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 null
     * @param adapterId null
     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }
}