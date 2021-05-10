/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto;

import java.math.BigDecimal;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.validator.ValManualVoucher001Validator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 手工凭证查询dto
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher001Dto
 * @since 2.0
 */
@PageId("ValManualVoucher002")
@ValidLogic(targetClass = ValManualVoucher001Validator.class)
public class ValManualVoucher001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 凭证流水号(即交易编号（业务编号）tradeId)
     */
    @PrimaryKey(value=Field.PK_1)
    private String seqno;

    /**
     * 入账日期
     */
    private String bookDate;

    /**
     * 发生日期
     */
    private String happenDate;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 凭证编号
     */
    private String bookId;

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
     * 账套日期
     */
    private String booksetDate;
     
    /**
     * 制作人
     */
    private String createUser;
    
    /**
     * 收益类型
     * add by wujie on 20201112
     */
    private String profitType;
    

    /**
     * 获取 booksetDate
     * 
     * @return booksetDate.
     */
    public String getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 booksetDate
     * 
     * @param booksetDate booksetDate
     */
    public void setBooksetDate(String booksetDate) {
        this.booksetDate = booksetDate;
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

    /**
     * 借贷方向(余额方向)
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
     * 业务类别
     */
    private String tableId;

    /**
     * 业务类别
     */
    private String tableIdFmt;

    /**
     * 资产/负债代码
     */
    private String busiId;

    /**
     * 资产/负债名称
     */
    private String busiName;

    /**
     * 交易编号（业务编号）
     */
    private String tradeId;

    /**
     * 原始业务编号
     */
    private String pTradeId;

    /**
     * 冲销标识
     */
    private String offsetFlag;
    
    /**
     * 记账币种
     */
    private String bCcy;
    
    /**
     * 本币金额
     */
    private BigDecimal bHappenAmt;

    /**
     * 摘要（凭证说明）
     */
    private String bookRemark;

    /**
     * 获取 凭证流水号
     * 
     * @return 凭证流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 凭证流水号
     * 
     * @param seqno 凭证流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    /**
     * 取得 入账日期
     * 
     * @return 入账日期
     */
    public String getBookDate() {
        return DateUtil.formatDateAddSplit(bookDate);
    }

    /**
     * 设置 入账日期
     * 
     * @param bookDate 入账日期
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 发生日期
     * 
     * @return 发生日期
     */
    public String getHappenDate() {
        return DateUtil.formatDateAddSplit(happenDate);
    }

    /**
     * 设置 发生日期
     * 
     * @param happenDate 发生日期
     */
    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
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
     * 取得 账套名称
     * 
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * 
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
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
     * 获取 借贷方向
     * 
     * @return 借贷方向
     */
    public String getCdFlag() {
        return cdFlag;
    }

    public String getCdFlagFmt() {
        return this.cdFlag;
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
        return ccy;
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
        return null != happenNumber ? happenNumber : BigDecimal.ZERO;
    }

    /**
     * 设置 数量
     * 
     * @param happenNumber 数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }

    /**
     * 获取 交易编号
     * 
     * @return 交易编号.
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * 
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 凭证说明
     * 
     * @return 凭证说明.
     */
    public String getBookRemark() {
        return bookRemark;
    }

    /**
     * 设置 凭证说明
     * 
     * @param bookRemark 凭证说明
     */
    public void setBookRemark(String bookRemark) {
        this.bookRemark = bookRemark;
    }

    /**
     * 获取 业务类型
     * 
     * @return 业务类型.
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 业务类型
     * 
     * @param tableId 业务类型
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 获取 资产/负债代码
     * 
     * @return 资产/负债代码.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 资产/负债代码
     * 
     * @param busiId 资产/负债代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 资产/负债名称
     * 
     * @return 资产/负债名称.
     */
    public String getBusiName() {
        return busiName;
    }

    /**
     * 设置 资产/负债名称
     * 
     * @param busiName 资产/负债名称
     */
    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    /**
     * 获取 原始交易编号
     * 
     * @return 原始交易编号.
     */
    public String getpTradeId() {
        return pTradeId;
    }

    /**
     * 设置 原始交易编号
     * 
     * @param pTradeId 原始交易编号
     */
    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    /**
     * 获取 冲正标识
     * 
     * @return 冲正标识.
     */
    public String getOffsetFlag() {
        return offsetFlag;
    }

    /**
     * 设置 冲正标识
     * 
     * @param offsetFlag 冲正标识
     */
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    public void setTableIdFmt(String tableIdFmt) {
        this.tableIdFmt = tableIdFmt;
    }

    /**
     * 获取 createUser
     * @return createUser.
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置 createUser
     * @param createUser createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

}
