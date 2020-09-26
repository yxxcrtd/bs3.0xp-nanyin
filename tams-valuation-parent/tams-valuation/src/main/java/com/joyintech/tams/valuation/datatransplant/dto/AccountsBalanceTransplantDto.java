/*
 * 文件名：SecSubjectBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：daiyue
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.datatransplant.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class AccountsBalanceTransplantDto {

    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     */
    private String busiId;
    
    /**
     * 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    private String busiDetailType;
    

    /**
     * 余额日期
     */
    private Date balanceDate;
    

    /**
     * 投资目的
     */
    private String invAim;
    
    /**
     * 场景代码
     */
    private String tableId;

    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 在途日期
     */
    private Date onwayDate;
    

    /**
     * 科目号
     */
    private String subjectNo;
    
    /**
     * 分支序号
     */
    private Long branch;
    
    /**
     * 原币
     */
    private String oCcy;

    /**
     * 借贷方向
     */
    private String cdFlag;

    /**
     * 原币发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oHappenAmt;
    
    /**
     * 发生数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenNumber;
    
    /**
     * 结息日
     */
    private Date mdate;
    
    /**
     * 余额方向（发生日）
     */
    private String balFlagHappenDate;

    /**
     * 原币余额（发生日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oAmtHappenDate;

    /**
     * 数量余额（发生日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal numAmtHappenDate;
    
    /**
     * 是否是在途债券
     */
    private boolean isNotOnwayDate;




	/**
	 * 获取 isNotOnwayDate
	 * @return isNotOnwayDate.
	 */
	public boolean isNotOnwayDate() {
		return isNotOnwayDate;
	}

	/**
	 * 设置 isNotOnwayDate
	 * @param isNotOnwayDate isNotOnwayDate
	 */
	public void setNotOnwayDate(boolean isNotOnwayDate) {
		this.isNotOnwayDate = isNotOnwayDate;
	}

	/**
     * 取得 余额方向（发生日）
     * @return 余额方向（发生日）
     */
    public String getBalFlagHappenDate() {
        return balFlagHappenDate;
    }

    /**
     * 设置 余额方向（发生日）
     * @param balFlagHappenDate 余额方向（发生日）
     */
    public void setBalFlagHappenDate(String balFlagHappenDate) {
        this.balFlagHappenDate = balFlagHappenDate == null ? null : balFlagHappenDate.trim();
    }

    /**
     * 取得 原币余额（发生日）
     * @return 原币余额（发生日）
     */
    public BigDecimal getoAmtHappenDate() {
        return oAmtHappenDate;
    }

    /**
     * 设置 原币余额（发生日）
     * @param oAmtHappenDate 原币余额（发生日）
     */
    public void setoAmtHappenDate(BigDecimal oAmtHappenDate) {
        this.oAmtHappenDate = oAmtHappenDate;
    }
    
    /**
     * 取得 数量余额（发生日）
     * @return 数量余额（发生日）
     */
    public BigDecimal getNumAmtHappenDate() {
        return numAmtHappenDate;
    }

    /**
     * 设置 数量余额（发生日）
     * @param numAmtHappenDate 数量余额（发生日）
     */
    public void setNumAmtHappenDate(BigDecimal numAmtHappenDate) {
        this.numAmtHappenDate = numAmtHappenDate;
    }
    
    /**
     * 取得 结息日
     * @return 结息日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结息日
     * @param mdate 结息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
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
     * 取得 原币发生额
     * @return 原币发生额
     */
    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }

    /**
     * 设置 原币发生额
     * @param oHappenAmt 原币发生额
     */
    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
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
     * 取得 在途日期
     * @return 在途日期
     */
    public Date getOnwayDate() {
        return onwayDate;
    }

    /**
     * 设置 在途日期
     * @param onwayDate 在途日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOnwayDate(Date onwayDate) {
        this.onwayDate = onwayDate;
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
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }
    
    /**
     * 取得 余额日期
     * @return 余额日期
     */
    public Date getBalanceDate() {
        return balanceDate;
    }

    /**
     * 设置 余额日期
     * @param balanceDate 余额日期
     */
    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }
    

    /**
     * 取得 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     * @return 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     * @param busiDetailType 业务明细类型，存金融产品类型、现金流类型或者资金账户类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType == null ? null : busiDetailType.trim();
    }
    
    
    /**
     * 取得 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     * @return 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     * @param busiId 业务明细代码，存金融产品代码、现金流代码（费用）或者资金账户
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
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
}
