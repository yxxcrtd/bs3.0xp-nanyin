/*
 * 文件名：SubjectBalance.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 科目余额
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see SubjectBalance
 * @since 2.0
 */
public class SubjectBalance extends SubjectBalanceKey {

    private static final long serialVersionUID = 1L;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 上级科目号
     */
    private String fsubjectId;

    /**
     * 科目级别
     */
    private Long subjectLevel;

    /**
     * 投资目的
     */
    private String invAim;
    /**
     * 科目余额方向
     */
    private String balFlag;
    /**
     * 余额方向（发生日）
     */
    private String balFlagHappenDate;

    /**
     * 科目余额
     */
    private BigDecimal balance;

    /**
     * 数量余额
     */
    private BigDecimal num;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 四级科目号
     */
    private String detailSubjectNo;

    /**
     * 结息日
     */
    private Date mdate;

    /**
     * 借方余额
     */
    private BigDecimal dAmt;

    /**
     * 贷方余额
     */
    private BigDecimal cAmt;
    
    /**
     * 结转余额
     */
    private BigDecimal KnotsAmt;
    
    /**
     * 原币结转余额
     */
    private BigDecimal oCcyKnotsAmt;
    
    /**
     * 原币结转余额贷方
     */
    private BigDecimal oCcyKnotsAmtC;

    /**
     * 原币结转余额借方
     */
    private BigDecimal oCcyKnotsAmtD;
    /**
     * 原币当日发生额
     */
    private BigDecimal tdyOAmt;

    /**
     * 原币当日发生额贷方
     */
    private BigDecimal tdyOCAmt;

    /**
     * 原币当日发生额借方
     */
    private BigDecimal tdyODAmt;

    /**
     * 原币结转当日发生额
     */
    private BigDecimal tdyOCoAmt;

    /**
     * 原币结转当日发生额贷方
     */
    private BigDecimal tdyOCoCAmt;

    /**
     * 原币结转当日发生额借方
     */
    private BigDecimal tdyOCoDAmt;  
    
    /**
     *申购未实现平准金
     */
    private BigDecimal purOUlamt;

    /**
     * 赎回未实现平准金
     */
    private BigDecimal redOUlamt;

    /**
     * 原币申购未实现平准金借
     */
    private BigDecimal tdyPurODUlamt;

    /**
     * 原币赎回未实现平准金借
     */
    private BigDecimal tdyRedODUlamt;

    /**
     * 原币申购未实现平准金贷
     */
    private BigDecimal tdyPurOCUlamt;

    /**
     * 原币赎回未实现平准金借
     */
    private BigDecimal tdyRedOCUlamt;
    
    /**
     * 是否叶子节点
     */
    private String isleaf;
    
    /**
     * 父科目号
     */
    private String fsubjectNo;

    /**
     * 结转余额方向
     */
    private String balCoFlag;
    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 本位币余额
     */
    private BigDecimal bAmt;

    /**
     * 本位币余额贷方
     */
    private BigDecimal bCAmt;

    /**
     * 本位币结转余额
     */
    private BigDecimal bCoAmt;

    /**
     * 本位币结转余额贷方
     */
    private BigDecimal bCoCAmt;

    /**
     * 本位币结转余额借方
     */
    private BigDecimal bCoDAmt;

    /**
     * 本位币余额借方
     */
    private BigDecimal bDAmt;
    /**
     * 本位币当日发生额
     */
    private BigDecimal tdyBAmt;

    /**
     * 本位币当日发生额贷方
     */
    private BigDecimal tdyBCAmt;

    /**
     * 本位币当日发生额借方
     */
    private BigDecimal tdyBDAmt;

    /**
     * 本位币结转当日发生额
     */
    private BigDecimal tdyBCoAmt;

    /**
     * 本位币结转当日发生额贷方
     */
    private BigDecimal tdyBCoCAmt;

    /**
     * 本位币结转当日发生额借方
     */
    private BigDecimal tdyBCoDAmt;


    /**
     * 数量余额
     */
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
     * 本位币申购未实现平准金借
     */
    private BigDecimal tdyPurBDUlamt;

    /**
     * 本位币赎回未实现平准金借
     */
    private BigDecimal tdyRedBDUlamt;

    /**
     * 本位币申购未实现平准金贷
     */
    private BigDecimal tdyPurBCUlamt;

    /**
     * 本位币赎回未实现平准金贷
     */
    private BigDecimal tdyRedBCUlamt;
    
    /**
     * 余额日期
     */
    private Date balanceDate;
    
    /**
     * 获取 knotsAmt
     * @return knotsAmt.
     */
    public BigDecimal getKnotsAmt() {
        return KnotsAmt;
    }

    
    /**
     * 设置 knotsAmt
     * @param knotsAmt knotsAmt
     */
    public void setKnotsAmt(BigDecimal knotsAmt) {
        KnotsAmt = knotsAmt;
    }

    
    /**
     * 获取 tdyOAmt
     * @return tdyOAmt.
     */
    public BigDecimal getTdyOAmt() {
        return tdyOAmt;
    }

    
    /**
     * 设置 tdyOAmt
     * @param tdyOAmt tdyOAmt
     */
    public void setTdyOAmt(BigDecimal tdyOAmt) {
        this.tdyOAmt = tdyOAmt;
    }

    
    /**
     * 获取 tdyOCAmt
     * @return tdyOCAmt.
     */
    public BigDecimal getTdyOCAmt() {
        return tdyOCAmt;
    }

    
    /**
     * 设置 tdyOCAmt
     * @param tdyOCAmt tdyOCAmt
     */
    public void setTdyOCAmt(BigDecimal tdyOCAmt) {
        this.tdyOCAmt = tdyOCAmt;
    }

    
    /**
     * 获取 tdyODAmt
     * @return tdyODAmt.
     */
    public BigDecimal getTdyODAmt() {
        return tdyODAmt;
    }

    
    /**
     * 设置 tdyODAmt
     * @param tdyODAmt tdyODAmt
     */
    public void setTdyODAmt(BigDecimal tdyODAmt) {
        this.tdyODAmt = tdyODAmt;
    }

    
    /**
     * 获取 tdyOCoAmt
     * @return tdyOCoAmt.
     */
    public BigDecimal getTdyOCoAmt() {
        return tdyOCoAmt;
    }

    
    /**
     * 设置 tdyOCoAmt
     * @param tdyOCoAmt tdyOCoAmt
     */
    public void setTdyOCoAmt(BigDecimal tdyOCoAmt) {
        this.tdyOCoAmt = tdyOCoAmt;
    }

    
    /**
     * 获取 tdyOCoCAmt
     * @return tdyOCoCAmt.
     */
    public BigDecimal getTdyOCoCAmt() {
        return tdyOCoCAmt;
    }

    
    /**
     * 设置 tdyOCoCAmt
     * @param tdyOCoCAmt tdyOCoCAmt
     */
    public void setTdyOCoCAmt(BigDecimal tdyOCoCAmt) {
        this.tdyOCoCAmt = tdyOCoCAmt;
    }

    
    /**
     * 获取 tdyOCoDAmt
     * @return tdyOCoDAmt.
     */
    public BigDecimal getTdyOCoDAmt() {
        return tdyOCoDAmt;
    }

    
    /**
     * 设置 tdyOCoDAmt
     * @param tdyOCoDAmt tdyOCoDAmt
     */
    public void setTdyOCoDAmt(BigDecimal tdyOCoDAmt) {
        this.tdyOCoDAmt = tdyOCoDAmt;
    }

    
    
    /**
     * 获取 purOUlamt
     * @return purOUlamt.
     */
    public BigDecimal getPurOUlamt() {
        return purOUlamt;
    }


    
    /**
     * 设置 purOUlamt
     * @param purOUlamt purOUlamt
     */
    public void setPurOUlamt(BigDecimal purOUlamt) {
        this.purOUlamt = purOUlamt;
    }


    
    /**
     * 获取 redOUlamt
     * @return redOUlamt.
     */
    public BigDecimal getRedOUlamt() {
        return redOUlamt;
    }


    
    /**
     * 设置 redOUlamt
     * @param redOUlamt redOUlamt
     */
    public void setRedOUlamt(BigDecimal redOUlamt) {
        this.redOUlamt = redOUlamt;
    }


    /**
     * 获取 tdyPurODUlamt
     * @return tdyPurODUlamt.
     */
    public BigDecimal getTdyPurODUlamt() {
        return tdyPurODUlamt;
    }

    
    /**
     * 设置 tdyPurODUlamt
     * @param tdyPurODUlamt tdyPurODUlamt
     */
    public void setTdyPurODUlamt(BigDecimal tdyPurODUlamt) {
        this.tdyPurODUlamt = tdyPurODUlamt;
    }

    
    /**
     * 获取 tdyRedODUlamt
     * @return tdyRedODUlamt.
     */
    public BigDecimal getTdyRedODUlamt() {
        return tdyRedODUlamt;
    }

    
    /**
     * 设置 tdyRedODUlamt
     * @param tdyRedODUlamt tdyRedODUlamt
     */
    public void setTdyRedODUlamt(BigDecimal tdyRedODUlamt) {
        this.tdyRedODUlamt = tdyRedODUlamt;
    }

    
    /**
     * 获取 tdyPurOCUlamt
     * @return tdyPurOCUlamt.
     */
    public BigDecimal getTdyPurOCUlamt() {
        return tdyPurOCUlamt;
    }

    
    /**
     * 设置 tdyPurOCUlamt
     * @param tdyPurOCUlamt tdyPurOCUlamt
     */
    public void setTdyPurOCUlamt(BigDecimal tdyPurOCUlamt) {
        this.tdyPurOCUlamt = tdyPurOCUlamt;
    }

    
    /**
     * 获取 tdyRedOCUlamt
     * @return tdyRedOCUlamt.
     */
    public BigDecimal getTdyRedOCUlamt() {
        return tdyRedOCUlamt;
    }

    
    /**
     * 设置 tdyRedOCUlamt
     * @param tdyRedOCUlamt tdyRedOCUlamt
     */
    public void setTdyRedOCUlamt(BigDecimal tdyRedOCUlamt) {
        this.tdyRedOCUlamt = tdyRedOCUlamt;
    }

    
    /**
     * 获取 fsubjectNo
     * @return fsubjectNo.
     */
    public String getFsubjectNo() {
        return fsubjectNo;
    }

    
    /**
     * 设置 fsubjectNo
     * @param fsubjectNo fsubjectNo
     */
    public void setFsubjectNo(String fsubjectNo) {
        this.fsubjectNo = fsubjectNo;
    }

    
    /**
     * 获取 balCoFlag
     * @return balCoFlag.
     */
    public String getBalCoFlag() {
        return balCoFlag;
    }

    
    /**
     * 设置 balCoFlag
     * @param balCoFlag balCoFlag
     */
    public void setBalCoFlag(String balCoFlag) {
        this.balCoFlag = balCoFlag;
    }

    
    /**
     * 获取 bCcy
     * @return bCcy.
     */
    public String getbCcy() {
        return bCcy;
    }

    
    /**
     * 设置 bCcy
     * @param bCcy bCcy
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }

    
    /**
     * 获取 bAmt
     * @return bAmt.
     */
    public BigDecimal getbAmt() {
        return bAmt;
    }

    
    /**
     * 设置 bAmt
     * @param bAmt bAmt
     */
    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }

    
    /**
     * 获取 bCAmt
     * @return bCAmt.
     */
    public BigDecimal getbCAmt() {
        return bCAmt;
    }

    
    /**
     * 设置 bCAmt
     * @param bCAmt bCAmt
     */
    public void setbCAmt(BigDecimal bCAmt) {
        this.bCAmt = bCAmt;
    }

    
    /**
     * 获取 bCoAmt
     * @return bCoAmt.
     */
    public BigDecimal getbCoAmt() {
        return bCoAmt;
    }

    
    /**
     * 设置 bCoAmt
     * @param bCoAmt bCoAmt
     */
    public void setbCoAmt(BigDecimal bCoAmt) {
        this.bCoAmt = bCoAmt;
    }

    
    /**
     * 获取 bCoCAmt
     * @return bCoCAmt.
     */
    public BigDecimal getbCoCAmt() {
        return bCoCAmt;
    }

    
    /**
     * 设置 bCoCAmt
     * @param bCoCAmt bCoCAmt
     */
    public void setbCoCAmt(BigDecimal bCoCAmt) {
        this.bCoCAmt = bCoCAmt;
    }

    
    /**
     * 获取 bCoDAmt
     * @return bCoDAmt.
     */
    public BigDecimal getbCoDAmt() {
        return bCoDAmt;
    }

    
    /**
     * 设置 bCoDAmt
     * @param bCoDAmt bCoDAmt
     */
    public void setbCoDAmt(BigDecimal bCoDAmt) {
        this.bCoDAmt = bCoDAmt;
    }

    
    /**
     * 获取 bDAmt
     * @return bDAmt.
     */
    public BigDecimal getbDAmt() {
        return bDAmt;
    }

    
    /**
     * 设置 bDAmt
     * @param bDAmt bDAmt
     */
    public void setbDAmt(BigDecimal bDAmt) {
        this.bDAmt = bDAmt;
    }

    
    /**
     * 获取 tdyBAmt
     * @return tdyBAmt.
     */
    public BigDecimal getTdyBAmt() {
        return tdyBAmt;
    }

    
    /**
     * 设置 tdyBAmt
     * @param tdyBAmt tdyBAmt
     */
    public void setTdyBAmt(BigDecimal tdyBAmt) {
        this.tdyBAmt = tdyBAmt;
    }

    
    /**
     * 获取 tdyBCAmt
     * @return tdyBCAmt.
     */
    public BigDecimal getTdyBCAmt() {
        return tdyBCAmt;
    }

    
    /**
     * 设置 tdyBCAmt
     * @param tdyBCAmt tdyBCAmt
     */
    public void setTdyBCAmt(BigDecimal tdyBCAmt) {
        this.tdyBCAmt = tdyBCAmt;
    }

    
    /**
     * 获取 tdyBDAmt
     * @return tdyBDAmt.
     */
    public BigDecimal getTdyBDAmt() {
        return tdyBDAmt;
    }

    
    /**
     * 设置 tdyBDAmt
     * @param tdyBDAmt tdyBDAmt
     */
    public void setTdyBDAmt(BigDecimal tdyBDAmt) {
        this.tdyBDAmt = tdyBDAmt;
    }

    
    /**
     * 获取 tdyBCoAmt
     * @return tdyBCoAmt.
     */
    public BigDecimal getTdyBCoAmt() {
        return tdyBCoAmt;
    }

    
    /**
     * 设置 tdyBCoAmt
     * @param tdyBCoAmt tdyBCoAmt
     */
    public void setTdyBCoAmt(BigDecimal tdyBCoAmt) {
        this.tdyBCoAmt = tdyBCoAmt;
    }

    
    /**
     * 获取 tdyBCoCAmt
     * @return tdyBCoCAmt.
     */
    public BigDecimal getTdyBCoCAmt() {
        return tdyBCoCAmt;
    }

    
    /**
     * 设置 tdyBCoCAmt
     * @param tdyBCoCAmt tdyBCoCAmt
     */
    public void setTdyBCoCAmt(BigDecimal tdyBCoCAmt) {
        this.tdyBCoCAmt = tdyBCoCAmt;
    }

    
    /**
     * 获取 tdyBCoDAmt
     * @return tdyBCoDAmt.
     */
    public BigDecimal getTdyBCoDAmt() {
        return tdyBCoDAmt;
    }

    
    /**
     * 设置 tdyBCoDAmt
     * @param tdyBCoDAmt tdyBCoDAmt
     */
    public void setTdyBCoDAmt(BigDecimal tdyBCoDAmt) {
        this.tdyBCoDAmt = tdyBCoDAmt;
    }

    
    /**
     * 获取 numAmt
     * @return numAmt.
     */
    public BigDecimal getNumAmt() {
        return numAmt;
    }

    
    /**
     * 设置 numAmt
     * @param numAmt numAmt
     */
    public void setNumAmt(BigDecimal numAmt) {
        this.numAmt = numAmt;
    }

    
    /**
     * 获取 tdyAmtFlag
     * @return tdyAmtFlag.
     */
    public String getTdyAmtFlag() {
        return tdyAmtFlag;
    }

    
    /**
     * 设置 tdyAmtFlag
     * @param tdyAmtFlag tdyAmtFlag
     */
    public void setTdyAmtFlag(String tdyAmtFlag) {
        this.tdyAmtFlag = tdyAmtFlag;
    }

    
    /**
     * 获取 tdyCoFlag
     * @return tdyCoFlag.
     */
    public String getTdyCoFlag() {
        return tdyCoFlag;
    }

    
    /**
     * 设置 tdyCoFlag
     * @param tdyCoFlag tdyCoFlag
     */
    public void setTdyCoFlag(String tdyCoFlag) {
        this.tdyCoFlag = tdyCoFlag;
    }

    
    /**
     * 获取 tdyPurBDUlamt
     * @return tdyPurBDUlamt.
     */
    public BigDecimal getTdyPurBDUlamt() {
        return tdyPurBDUlamt;
    }

    
    /**
     * 设置 tdyPurBDUlamt
     * @param tdyPurBDUlamt tdyPurBDUlamt
     */
    public void setTdyPurBDUlamt(BigDecimal tdyPurBDUlamt) {
        this.tdyPurBDUlamt = tdyPurBDUlamt;
    }

    
    /**
     * 获取 tdyRedBDUlamt
     * @return tdyRedBDUlamt.
     */
    public BigDecimal getTdyRedBDUlamt() {
        return tdyRedBDUlamt;
    }

    
    /**
     * 设置 tdyRedBDUlamt
     * @param tdyRedBDUlamt tdyRedBDUlamt
     */
    public void setTdyRedBDUlamt(BigDecimal tdyRedBDUlamt) {
        this.tdyRedBDUlamt = tdyRedBDUlamt;
    }

    
    /**
     * 获取 tdyPurBCUlamt
     * @return tdyPurBCUlamt.
     */
    public BigDecimal getTdyPurBCUlamt() {
        return tdyPurBCUlamt;
    }

    
    /**
     * 设置 tdyPurBCUlamt
     * @param tdyPurBCUlamt tdyPurBCUlamt
     */
    public void setTdyPurBCUlamt(BigDecimal tdyPurBCUlamt) {
        this.tdyPurBCUlamt = tdyPurBCUlamt;
    }

    
    /**
     * 获取 tdyRedBCUlamt
     * @return tdyRedBCUlamt.
     */
    public BigDecimal getTdyRedBCUlamt() {
        return tdyRedBCUlamt;
    }

    
    /**
     * 设置 tdyRedBCUlamt
     * @param tdyRedBCUlamt tdyRedBCUlamt
     */
    public void setTdyRedBCUlamt(BigDecimal tdyRedBCUlamt) {
        this.tdyRedBCUlamt = tdyRedBCUlamt;
    }

    /**
     * 获取 结息日
     * 
     * @return 结息日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结息日
     * 
     * @param mdate 结息日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 账套日期
     * 
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * 
     * @param booksetDate 账套日期
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 入账日
     * 
     * @return 入账日
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日
     * 
     * @param bookDate 入账日
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 业务明细代码
     * 
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * 
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 取得 场景代码
     * 
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * 
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 取得 科目号
     * 
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * 
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 取得 上级科目号
     * 
     * @return 上级科目号
     */
    public String getFsubjectId() {
        return fsubjectId;
    }

    /**
     * 设置 上级科目号
     * 
     * @param fsubjectId 上级科目号
     */
    public void setFsubjectId(String fsubjectId) {
        this.fsubjectId = fsubjectId;
    }

    /**
     * 取得 科目级别
     * 
     * @return 科目级别
     */
    public Long getSubjectLevel() {
        return subjectLevel;
    }

    /**
     * 设置 科目级别
     * 
     * @param subjectLevel 科目级别
     */
    public void setSubjectLevel(Long subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    /**
     * 取得 投资目的
     * 
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * 
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    // /**
    // * 取得 余额方向
    // *
    // * @return 余额方向
    // */
    // public String getBalFlag() {
    // return balFlag;
    // }
    //
    // /**
    // * 设置 余额方向
    // *
    // * @param balFlag
    // * 余额方向
    // */
    // public void setBalFlag(String balFlag) {
    // this.balFlag = balFlag;
    // }

    
    /**
     * 获取 balFlag
     * @return balFlag.
     */
    public String getBalFlag() {
        return balFlag;
    }

    
    /**
     * 设置 balFlag
     * @param balFlag balFlag
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

    /**
     * 获取 余额方向（发生日）
     * 
     * @return 余额方向（发生日）.
     */
    public String getBalFlagHappenDate() {
        return balFlagHappenDate;
    }

    /**
     * 设置 余额方向（发生日）
     * 
     * @param balFlagHappenDate 余额方向（发生日）
     */
    public void setBalFlagHappenDate(String balFlagHappenDate) {
        this.balFlagHappenDate = balFlagHappenDate;
    }

    /**
     * 取得 科目余额
     * 
     * @return 科目余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置 科目余额
     * 
     * @param balance 科目余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 取得 数量余额
     * 
     * @return 数量余额
     */
    public BigDecimal getNum() {
        return num;
    }

    /**
     * 设置 数量余额
     * 
     * @param num 数量余额
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }

    /**
     * 获取 基础科目性质
     * 
     * @return 基础科目性质.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 基础科目性质
     * 
     * @param baseSubjectNature 基础科目性质
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }

    /**
     * 获取 四级科目号
     * 
     * @return 四级科目号.
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 设置 四级科目号
     * 
     * @param detailSubjectNo 四级科目号
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }

    /**
     * 获取 借方余额
     * 
     * @return 借方余额
     */
    public BigDecimal getdAmt() {
        return dAmt;
    }

    /**
     * 设置 借方余额
     * 
     * @param dAmt 借方余额
     */
    public void setdAmt(BigDecimal dAmt) {
        this.dAmt = dAmt;
    }

    /**
     * 获取 贷方余额
     * 
     * @return 贷方余额
     */
    public BigDecimal getcAmt() {
        return cAmt;
    }

    /**
     * 设置 贷方余额
     * 
     * @param cAmt 贷方余额
     */
    public void setcAmt(BigDecimal cAmt) {
        this.cAmt = cAmt;
    }

    /**
     * 获取 是否叶子节点
     * 
     * @return 是否叶子节点
     */
    public String getIsleaf() {
        return isleaf;
    }

    /**
     * 设置 是否叶子节点
     * 
     * @param isleaf 是否叶子节点
     */
    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    
    
    /**
     * 获取 oCcyKnotsAmt
     * @return oCcyKnotsAmt.
     */
    public BigDecimal getoCcyKnotsAmt() {
        return oCcyKnotsAmt;
    }

    
    /**
     * 设置 oCcyKnotsAmt
     * @param oCcyKnotsAmt oCcyKnotsAmt
     */
    public void setoCcyKnotsAmt(BigDecimal oCcyKnotsAmt) {
        this.oCcyKnotsAmt = oCcyKnotsAmt;
    }

    
    /**
     * 获取 oCcyKnotsAmtC
     * @return oCcyKnotsAmtC.
     */
    public BigDecimal getoCcyKnotsAmtC() {
        return oCcyKnotsAmtC;
    }

    
    /**
     * 设置 oCcyKnotsAmtC
     * @param oCcyKnotsAmtC oCcyKnotsAmtC
     */
    public void setoCcyKnotsAmtC(BigDecimal oCcyKnotsAmtC) {
        this.oCcyKnotsAmtC = oCcyKnotsAmtC;
    }

    
    /**
     * 获取 oCcyKnotsAmtD
     * @return oCcyKnotsAmtD.
     */
    public BigDecimal getoCcyKnotsAmtD() {
        return oCcyKnotsAmtD;
    }

    
    /**
     * 设置 oCcyKnotsAmtD
     * @param oCcyKnotsAmtD oCcyKnotsAmtD
     */
    public void setoCcyKnotsAmtD(BigDecimal oCcyKnotsAmtD) {
        this.oCcyKnotsAmtD = oCcyKnotsAmtD;
    }

    /**
     * 获取key
     * 
     * @return SubjectBalanceKey
     * @see
     * @since 1.0
     */
    public SubjectBalanceKey getKeys() {
        SubjectBalanceKey dto = new SubjectBalanceKey();
        dto.setBooksetId(this.getBooksetId());
        dto.setHappenDate(this.getHappenDate());
        dto.setOCcy(this.getOCcy());
        // dto.setDetailSubjectNo(this.getDetailSubjectNo());
        dto.setBokDetailId(this.getBokDetailId());
        return dto;
    }

    /**
     * 获取 balanceDate
     * @return balanceDate.
     */
    public Date getBalanceDate() {
        return balanceDate;
    }

    /**
     * 设置 balanceDate
     * @param balanceDate balanceDate
     */
    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }
}
