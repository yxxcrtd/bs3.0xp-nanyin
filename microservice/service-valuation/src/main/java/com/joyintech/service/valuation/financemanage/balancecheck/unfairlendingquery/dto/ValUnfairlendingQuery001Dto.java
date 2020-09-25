/*
 * 文件名：ValSubjectWaterQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.balancecheck.unfairlendingquery.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wanwgei1
 * @version 1.0 2019年01月28日
 * @see ValUnfairlendingQuery001Dto
 * @since 1.0
 */
public class ValUnfairlendingQuery001Dto extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 账套名称
	 */
	private String booksetName;
	/**
	 * 凭证编号
	 */
	private String vouchNum;
	/**
	 * 科目名称
	 */
	private String subjectName;
	/**
	 * 借贷
	 */
	private String cdFlag;
	/**
	 * 金额
	 */
	private BigDecimal happenAmt;
	/**
	 * 数量
	 */
	private BigDecimal happenNumber;
	/**
	 * 资产/负债名称
	 */
	private String busiName;
	/**
	 * 业务编号
	 */
	private String tradeId;
	/**
	 * 原始业务编号
	 */
	private String pTradeId;
	/**
	 * 凭证类型
	 */
	private String bookType;
	/**
	 * 冲销标识
	 */
	private String offsetFlag;
	/**
	 * 摘要
	 */
	private String vouchRemark;
	/**
	 * 账套代码
	 */
	private String booksetId;
	/**
	 * 账套代码
	 */
	private String[] booksetIdArr;

	/**
	 * 业务类别
	 */
	private String tableId;

	/**
	 * 资产/负债代码
	 */
	private String busiId;

	/**
	 * 记账日期
	 */
	private String bookDate;

	/**
	 * 科目号
	 */
	private String subjectNo;

	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 业务类别
	 */
	private String[] comTableIdArr;

	/**
	 * 科目号
	 */
	private String[] subjectNoArr;

	/**
	 * 记账日期开始时间
	 */
	private Date bookDateBegin;

	/**
	 * 记账日期结束时间
	 */
	private Date bookDateEnd;

	/**
	 * 本位币
	 */
	private String bCcy;

	/**
	 * 本位币发生额
	 */
	private BigDecimal bHappenAmt;

	public String getbCcy() {
		return bCcy;
	}

	public void setbCcy(String bCcy) {
		this.bCcy = bCcy;
	}

	public BigDecimal getbHappenAmt() {
		return bHappenAmt;
	}

	public void setbHappenAmt(BigDecimal bHappenAmt) {
		this.bHappenAmt = bHappenAmt;
	}

	/**
	 * 获取 comTableIdArr
	 * 
	 * @return comTableIdArr.
	 */
	public String[] getComTableIdArr() {
		return comTableIdArr;
	}

	/**
	 * 设置 comTableIdArr
	 * 
	 * @param comTableIdArr
	 *            comTableIdArr
	 */
	public void setComTableIdArr(String[] comTableIdArr) {
		this.comTableIdArr = comTableIdArr;
	}

	/**
	 * 获取 subjectNoArr
	 * 
	 * @return subjectNoArr.
	 */
	public String[] getSubjectNoArr() {
		return subjectNoArr;
	}

	/**
	 * 设置 subjectNoArr
	 * 
	 * @param subjectNoArr
	 *            subjectNoArr
	 */
	public void setSubjectNoArr(String[] subjectNoArr) {
		this.subjectNoArr = subjectNoArr;
	}

	/**
	 * 获取 booksetId
	 * 
	 * @return booksetId.
	 */
	public String getBooksetId() {
		return booksetId;
	}

	/**
	 * 设置 booksetId
	 * 
	 * @param booksetId
	 *            booksetId
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	/**
	 * 获取 busiId
	 * 
	 * @return busiId.
	 */
	public String getBusiId() {
		return busiId;
	}

	/**
	 * 设置 busiId
	 * 
	 * @param busiId
	 *            busiId
	 */
	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	/**
	 * 获取 bookDate
	 * 
	 * @return bookDate.
	 */
	public String getBookDate() {
		return bookDate;
	}

	/**
	 * 设置 bookDate
	 * 
	 * @param bookDate
	 *            bookDate
	 */
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	/**
	 * 获取 subjectNo
	 * 
	 * @return subjectNo.
	 */
	public String getSubjectNo() {
		return subjectNo;
	}

	/**
	 * 设置 subjectNo
	 * 
	 * @param subjectNo
	 *            subjectNo
	 */
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	/**
	 * 获取 币种
	 * 
	 * @return ccy.
	 */
	public String getCcy() {
		return ccy;
	}

	/**
	 * 设置 币种
	 * 
	 * @param ccy
	 *            ccy
	 */
	public void setCcy(String ccy) {
		this.ccy = ccy == null ? null : ccy.trim();
	}

	public String getBooksetName() {
		return booksetName;
	}

	public void setBooksetName(String booksetName) {
		this.booksetName = booksetName;
	}

	public String getVouchNum() {
		return vouchNum;
	}

	public void setVouchNum(String vouchNum) {
		this.vouchNum = vouchNum;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * 获取 cdFlag
	 * 
	 * @return cdFlag.
	 */
	public String getCdFlag() {
		return cdFlag;
	}

	/**
	 * 设置 cdFlag
	 * 
	 * @param cdFlag
	 *            cdFlag
	 */
	public void setCdFlag(String cdFlag) {
		this.cdFlag = cdFlag;
	}

	public BigDecimal getHappenAmt() {
		return happenAmt;
	}

	public void setHappenAmt(BigDecimal happenAmt) {
		this.happenAmt = happenAmt;
	}

	public BigDecimal getHappenNumber() {
		return happenNumber;
	}

	public void setHappenNumber(BigDecimal happenNumber) {
		this.happenNumber = happenNumber;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getpTradeId() {
		return pTradeId;
	}

	public void setpTradeId(String pTradeId) {
		this.pTradeId = pTradeId;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getOffsetFlag() {
		return offsetFlag;
	}

	public void setOffsetFlag(String offsetFlag) {
		this.offsetFlag = offsetFlag;
	}

	public String getVouchRemark() {
		return vouchRemark;
	}

	public void setVouchRemark(String vouchRemark) {
		this.vouchRemark = vouchRemark;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String[] getBooksetIdArr() {
		return booksetIdArr;
	}

	public void setBooksetIdArr(String[] booksetIdArr) {
		this.booksetIdArr = booksetIdArr;
	}

	public Date getBookDateBegin() {
		return bookDateBegin;
	}

	public void setBookDateBegin(Date bookDateBegin) {
		this.bookDateBegin = bookDateBegin;
	}

	public Date getBookDateEnd() {
		return bookDateEnd;
	}

	public void setBookDateEnd(Date bookDateEnd) {
		this.bookDateEnd = bookDateEnd;
	}

}
