/*
 * 文件名：BankBalanceQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：wanggengyun
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.psbc.bankbalancequery.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

 /***
 * 银行账科目余额查询Dto
 * 
 * @author wangyang1
 * @version 1.0 2019年8月23日
 * @see BankBalanceQuery001Dto
 * @since 1.0
 */
public class BankBalanceQuery001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

     /**金融产品代码*/  
    private String finprodId ;  
     /**余额日期*/  
    private Date balanceDate;  
     /**科目号*/
    private String subjectNo ;  
    /**账套号*/
    private String booksetId ;
     /**父科目号*/  
    private String fsubjectNo;  
     /**科目级别*/  
    private Long subjectLevel;  
     /**余额方向*/  
    private String balFlag;  
     /**原币*/  
    private String oCcy;  
     /**原币余额*/  
    private BigDecimal oAmt;  
     /**本位币*/
    private String bCcy;  
  
     /**是否叶子节点*/  
    private String isLeaf;  
    
     /**数量余额*/  
    private BigDecimal numAmt;
    
     /***
     * 本位币余额
     */
    private BigDecimal bAmt;

     /***
     * 账套日期
     */
    private String balanceDateBegin;

     /***
     * 账套日期
     */
    private String balanceDateEnd;

     /***
     * 账套名称
     */
    private String bookSetName;

     /***
     * 科目名称
     */
    private String subjectName;
 
     /***
     * 场景名称
     */
    private String tableName;
    
     /***
     * 获取 vouchNumStr
     * 
     * @return vouchNumStr.
     */
    public String getBalanceDateFmt() {
        return DateUtil.format(this.getBalanceDate(), "yyyy-MM-dd");
    }

     
     
    /**
     * 获取 balanceDateBegin
     * @return balanceDateBegin.
     */
    public String getBalanceDateBegin() {
        return balanceDateBegin;
    }


    
    /**
     * 设置 balanceDateBegin
     * @param balanceDateBegin balanceDateBegin
     */
    public void setBalanceDateBegin(String balanceDateBegin) {
        this.balanceDateBegin = balanceDateBegin;
    }


    
    /**
     * 获取 balanceDateEnd
     * @return balanceDateEnd.
     */
    public String getBalanceDateEnd() {
        return balanceDateEnd;
    }


    
    /**
     * 设置 balanceDateEnd
     * @param balanceDateEnd balanceDateEnd
     */
    public void setBalanceDateEnd(String balanceDateEnd) {
        this.balanceDateEnd = balanceDateEnd;
    }


    /***
     * 获取 bookSetName
     * 
     * @return bookSetName.
     */
    public String getBookSetName() {
        return bookSetName;
    }

     /***
     * 设置 bookSetName
     * 
     * @param bookSetName bookSetName
     */
    public void setBookSetName(String bookSetName) {
        this.bookSetName = bookSetName;
    }

     /***
     * 获取 原位币数据字典
     * 
     * @return 原位币数据字典.
     */
    public String getoCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.getoCcy()))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, this.getoCcy()):this.getoCcy();
    }
 
    
     /***
     * 获取 原位币数据字典
     * 
     * @return 原位币数据字典.
     */
    public String getbCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.getbCcy()))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, this.getbCcy()):this.getbCcy();
    }
    
     /***
     * 获取 借贷方向数据字典
     * 
     * @return 借贷方向数据字典.
     */
    public String getBalFlagFmt() {
    	 return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, this.getBalFlag()))?
                 SysDataUtil.getNameFromDataDict(DictCode.借贷方向, this.getBalFlag()):this.getBalFlag();
    }

     /***
     * 获取 subjectName
     * 
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

     /***
     * 设置 subjectName
     * 
     * @param subjectName subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
   
     /***
     * 获取 本位币余额
     * @param bAmt 本位币余额
     */
    public BigDecimal getbAmt(){
        if(this.bAmt == null){
            return BigDecimal.ZERO;
        }
        return this.bAmt;
    }

     /***
     * 设置 本位币余额
     * @param bAmt 本位币余额
     */
    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }

    
     /***
     * 获取 tableName
     * @return tableName.
     */
    public String getTableName() {
        return tableName;
    }

    
     /***
     * 设置 tableName
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    
     /***
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
     /***
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
     /***
     * 获取 balanceDate
     * @return balanceDate.
     */
    public Date getBalanceDate() {
        return balanceDate;
    }

    
     /***
     * 设置 balanceDate
     * @param balanceDate balanceDate
     */
    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    
     /***
     * 获取 subjectNo
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    
     /***
     * 设置 subjectNo
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    
     /***
     * 获取 fsubjectNo
     * @return fsubjectNo.
     */
    public String getFsubjectNo() {
        return fsubjectNo;
    }

    
     /***
     * 设置 fsubjectNo
     * @param fsubjectNo fsubjectNo
     */
    public void setFsubjectNo(String fsubjectNo) {
        this.fsubjectNo = fsubjectNo;
    }

    
     /***
     * 获取 subjectLevel
     * @return subjectLevel.
     */
    public Long getSubjectLevel() {
        return subjectLevel;
    }

    
     /***
     * 设置 subjectLevel
     * @param subjectLevel subjectLevel
     */
    public void setSubjectLevel(Long subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    
     /***
     * 获取 balFlag
     * @return balFlag.
     */
    public String getBalFlag() {
        return balFlag;
    }

    
     /***
     * 设置 balFlag
     * @param balFlag balFlag
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

    
     /***
     * 获取 oCcy
     * @return oCcy.
     */
    public String getoCcy() {
        return oCcy;
    }

    
     /***
     * 设置 oCcy
     * @param oCcy oCcy
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    
     /***
     * 获取 oAmt
     * @return oAmt.
     */
    public BigDecimal getoAmt() {
        if(this.oAmt == null){
            return BigDecimal.ZERO;
        }
        return oAmt;
    }

    
     /***
     * 设置 oAmt
     * @param oAmt oAmt
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }

    
     /***
     * 获取 bCcy
     * @return bCcy.
     */
    public String getbCcy() {
        return bCcy;
    }

    
     /***
     * 设置 bCcy
     * @param bCcy bCcy
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }

    
     /***
     * 获取 isLeaf
     * @return isLeaf.
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    
     /***
     * 设置 isLeaf
     * @param isLeaf isLeaf
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    
     /***
     * 获取 numAmt
     * @return numAmt.
     */
    public BigDecimal getNumAmt() {
        if(this.numAmt == null){
            return BigDecimal.ZERO;
        }
        return numAmt;
    }

    
     /***
     * 设置 numAmt
     * @param numAmt numAmt
     */
    public void setNumAmt(BigDecimal numAmt) {
        this.numAmt = numAmt;
    }



    
    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }



    
    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    
    
}
