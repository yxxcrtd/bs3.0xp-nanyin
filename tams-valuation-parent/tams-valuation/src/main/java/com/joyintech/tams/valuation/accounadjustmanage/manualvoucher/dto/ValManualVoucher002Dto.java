/*
 * 文件名：ValVoucherQuery002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.controller.ValVoucherQuery001Controller;

import java.math.BigDecimal;

/**
 * 手工凭证详情查看dto
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery001Controller
 * @since 2.0
 */
public class ValManualVoucher002Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 分录类型
     */
    private String recordType;

    /**
     * 冲销标识
     */
    private String offsetFlag;

    /**
     * 记账日期
     */
    private String bookDate;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 原交易编号
     */
    private String pTradeId;

    /**
     * 记账顺序
     */
    private String accountOrder;

    /**
     * 摘要（凭证说明）
     */
    private String bookRemark;

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
    private String detailSubjectNo;

    /**
     * 科目尾号名称
     */
    private String detailSubjectName;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    private BigDecimal happenAmt;

    /**
     * 数量
     */
    private BigDecimal happenNumber;

    /**
     * 记账币种
     */
    private String bCcy;
    
    /**
     * 本币金额
     */
    private BigDecimal bHappenAmt;    
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
     * 取得 科目名称
     * 
     * @return 科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 科目名称
     * 
     * @param subjectName 科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 取得 借贷方向
     * 
     * @return 借贷方向
     */
    public String getCdFlag() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag))?
            SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag):cdFlag;
//        return SysDataUtil.getNameFromDataDict(DictCode.借贷方向, cdFlag);
    }

    /**
     * 设置 借贷方向
     * 
     * @param cdFlag 借贷方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    /**
     * 取得 币种
     * 
     * @return 币种
     */
    public String getCcy() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, ccy))?
            SysDataUtil.getNameFromDataDict(DictCode.币种, ccy):ccy;
//        return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 取得 金额
     * 
     * @return 金额
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 金额
     * 
     * @param happenAmt 金额
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 取得 数量
     * 
     * @return 数量
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    /**
     * 设置 数量
     * 
     * @param happenNumber 数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }
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
     * 取得 凭证编号
     *
     * @return 凭证编号
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置 凭证编号
     *
     * @param bookId 凭证编号
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * 取得 账套代码
     *
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     *
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 取得 分录类型
     *
     * @return 分录类型
     */
    public String getRecordType() {
        return  StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.分录类型, recordType))?
                SysDataUtil.getNameFromDataDict(DictCode.分录类型, recordType):recordType;
    }

    /**
     * 设置 分录类型
     *
     * @param recordType 分录类型
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    /**
     * 取得 冲销标识
     *
     * @return 冲销标识
     */
    public String getOffsetFlag() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.冲正标识, offsetFlag))?
                SysDataUtil.getNameFromDataDict(DictCode.冲正标识, offsetFlag):offsetFlag;
    }

    /**
     * 设置 冲销标识
     *
     * @param offsetFlag 冲销标识
     */
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    /**
     * 取得 记账日
     *
     * @return 记账日
     */
    public String getBookDate() {
        return DateUtil.formatDateAddSplit(bookDate);
    }

    /**
     * 设置 记账日
     *
     * @param bookDate 记账日
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 业务类型
     *
     * @return 业务类型
     */
    public String getBusiType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.业务类型, busiType))?
                SysDataUtil.getNameFromDataDict(DictCode.业务类型, busiType):busiType;
    }

    /**
     * 设置 业务类型
     *
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 取得 资产代码
     *
     * @return 资产代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 资产代码
     *
     * @param busiId 资产代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 取得 自定义现金流编号
     *
     * @return 自定义现金流编号
     */
    public String getpTradeId() {
        return pTradeId;
    }

    /**
     * 设置 自定义现金流编号
     *
     * @param pTradeId 自定义现金流编号
     */
    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    /**
     * 取得 记账顺序
     *
     * @return 记账顺序
     */
    public String getAccountOrder() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.记账顺序, accountOrder))?
                SysDataUtil.getNameFromDataDict(DictCode.记账顺序, accountOrder):accountOrder;
    }

    /**
     * 设置 记账顺序
     *
     * @param accountOrder 记账顺序
     */
    public void setAccountOrder(String accountOrder) {
        this.accountOrder = accountOrder;
    }

    /**
     * 取得 摘要
     *
     * @return 摘要
     */
    public String getBookRemark() {
        return bookRemark;
    }

    /**
     * 设置 摘要
     *
     * @param bookRemark 摘要
     */
    public void setBookRemark(String bookRemark) {
        this.bookRemark = bookRemark;
    }

    /**
     * 获取 detailSubjectNo
     *
     * @return detailSubjectNo.
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 设置 detailSubjectNo
     *
     * @param detailSubjectNo detailSubjectNo
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }

    /**
     * 获取 detailSubjectName
     *
     * @return detailSubjectName.
     */
    public String getDetailSubjectName() {
        return detailSubjectName;
    }

    /**
     * 设置 detailSubjectName
     *
     * @param detailSubjectName detailSubjectName
     */
    public void setDetailSubjectName(String detailSubjectName) {
        this.detailSubjectName = detailSubjectName;
    }
}
