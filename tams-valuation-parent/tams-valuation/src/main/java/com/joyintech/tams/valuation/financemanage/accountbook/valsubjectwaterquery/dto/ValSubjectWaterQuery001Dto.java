/*
 * 文件名：ValSubjectWaterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 *科目流水dto
 * @author Administrator
 * @version 1.0 2018年7月12日
 * @see ValSubjectWaterQuery001Dto
 * @since 1.0
 */
public class ValSubjectWaterQuery001Dto extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    /**
     * 记账日期
     */
    private String bookDate;
    /**
     * 记账流水号
     */
    private String vouchNum;
    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 账套名称
     */
    private String booksetName;
    /**
     * 资产/负债代码
     */
    private String busiId;
    /**
     * 资产/负债名称
     */
    private String finprodAbbr;
    /**
     * 科目号
     */
    private String subjectNo;
    /**
     * 科目名称
     */
    private String subjectName;
    /**
     * 借贷方向
     */
    private String cdFlag;
    /**
     * 余额方向
     */
    private String balFlag;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 发生额
     */
    private BigDecimal happenAmt;
    
    /**
     * 余额金额
     */
    private BigDecimal balance;
    /**
     * 发生数量
     */
    private BigDecimal happenNumber;
    /**
     * 业务编码
     */
    private String tradeId;
    /**
     * 业务类别
     */
    private String comTableId;
    
    /**
     * 凭证编号
     */
    private String busVouchnum;   
    /**
     * 本位币
     */
    private String bCcy;
    /**
     * 本位币发生额
     */
    private BigDecimal bHappenAmt;
    /**
     * 获取 bookDate
     * @return bookDate.
     */
    public String getBookDate() {
        return DateUtil.formatDateAddSplit(bookDate);
    }
    
    /**
     * 设置 bookDate
     * @param bookDate bookDate
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
    
    /**
     * 获取 vouchNum
     * @return vouchNum.
     */
    public String getVouchNum() {
        return vouchNum;
    }
    
    /**
     * 设置 vouchNum
     * @param vouchNum vouchNum
     */
    public void setVouchNum(String vouchNum) {
        this.vouchNum = vouchNum;
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
    
    /**
     * 获取 账套名称
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }
    
    /**
     * 设置 账套名称
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName == null ? null : booksetName.trim();
    }
    
    /**
     * 获取 busiId
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }
    
    /**
     * 设置 busiId
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }
    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }
    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    
    /**
     * 获取 subjectNo
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }
    
    /**
     * 设置 subjectNo
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    
    /**
     * 获取 subjectName
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }
    
    /**
     * 设置 subjectName
     * @param subjectName subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }
    
    /**
     * 获取 cdFlag
     * @return cdFlag.
     */
    public String getCdFlag() {
        return cdFlag;
    }
    
    /**
     * 获取 cdFlag
     * @return cdFlag.
     */
    public String getCdFlagFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, this.cdFlag))?
                SysDataUtil.getNameFromDataDict(DictCode.借贷方向, this.cdFlag):this.cdFlag;
    }
    
    /**
     * 设置 cdFlag
     * @param cdFlag cdFlag
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }
    /**
     *   获取 balFlag 
     * 
     * @return balFlag
     */
    public String getBalFlag() {
        return balFlag;
    }
    /**
     *   获取 balFlag 
     * 
     * @return balFlag
     */
    public String getBalFlagFmt() {
    	 return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.余额方向, this.balFlag))?
                 SysDataUtil.getNameFromDataDict(DictCode.余额方向, this.cdFlag):this.balFlag;
    }

   /**
    * 
    *  设置  balFlag 
    * @param balFlag balFlag
    */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }
    
    /**
     * 获取 币种
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }
    
    /**
     * 获取 币种
     * @return ccy.
     */
    public String getCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccy))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, this.ccy):this.ccy;
    }
    
    /**
     * 设置 币种
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
    
    /**
     * 获取 happenAmt
     * @return happenAmt.
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }
    
    /**
     * 设置 happenAmt
     * @param happenAmt happenAmt
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }
    
    /**
     * 获取 happenNumber
     * @return happenNumber.
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }
    
    
    /**
     * 获取 balance
     * @return balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置 balance
     * @param balance balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 设置 happenNumber
     * @param happenNumber happenNumber
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }
    
    /**
     * 获取 tradeId
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }
    
    /**
     * 设置 tradeId
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
    
    /**
     * 获取 业务类型
     * @return comTableId.
     */
    public String getComTableId() {
        return comTableId;
    }
    /**
     * 获取 业务类型
     * @return comTableId.
     */
    public String getComTableIdFmt() {
    	 return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.会计场景代码, this.comTableId))?
                 SysDataUtil.getNameFromDataDict(DictCode.会计场景代码, this.comTableId):this.comTableId;
    }
    
    /**
     * 设置  业务类型
     * @param comTableId comTableId
     */
    public void setComTableId(String comTableId) {
        this.comTableId = comTableId == null ? null : comTableId.trim();
    }

    public String getBusVouchnum() {
        return busVouchnum;
    }

    public void setBusVouchnum(String busVouchnum) {
        this.busVouchnum = busVouchnum;
    }

	public String getbCcy() {
		return bCcy;
	}	
    /**
     * 获取 币种
     * @return bCcy.
     */
    public String getBccyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.bCcy))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, this.bCcy):this.bCcy;
    }
 

	public void setbCcy(String bCcy) {
		 this.bCcy = bCcy == null ? null : bCcy.trim();
	}

	public BigDecimal getbHappenAmt() {
		return bHappenAmt;
	}

	public void setbHappenAmt(BigDecimal bHappenAmt) {
		this.bHappenAmt = bHappenAmt;
	}


}
