/*
 * 文件名：TradeDetail.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：qiangchengxi 修改时间：2016年12月24日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.lendbookdetail.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 拆借交易信息DTO
 * 
 * @author 吴德宝
 * @version 1.0 2018年04月26日
 * @see LendDealDetailDTO
 * @since 1.0
 */
@SuppressWarnings("serial")
public class LendDealDetailDTO  extends NeedOperateEntity implements Serializable  {

    
    // 账套代码
    private String booksetId;
 // 账套代码
    private String finprodId;
    //交易编号
    private String tradeId;
    //原交易编号
    private String originalSeqNo;
    //交易类型
    private String dealType;
    //交易日期
    private Date dealDate;
    //交易份额
    private BigDecimal dealCopy;
    //交易金额
    private BigDecimal dlAMT;
    //交易数量
    private BigDecimal dlNum;
    //应计利息
    private BigDecimal intSettAMT;
    //入账日
    private Date bookDate;
    //计息编码
    private BigDecimal intCode;
    //固定利率
    private BigDecimal intRate;
    
    
    
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
     * 获取 originalSeqNo
     * @return originalSeqNo.
     */
    public String getOriginalSeqNo() {
        return originalSeqNo;
    }
    
    /**
     * 设置 originalSeqNo
     * @param originalSeqNo originalSeqNo
     */
    public void setOriginalSeqNo(String originalSeqNo) {
        this.originalSeqNo = originalSeqNo;
    }
    
    /**
     * 获取 dealType
     * @return dealType.
     */
    public String getDealType() {
        return dealType;
    }
    
    /**
     * 设置 dealType
     * @param dealType dealType
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    
    /**
     * 获取 dealDate
     * @return dealDate.
     */
    public Date getDealDate() {
        return dealDate;
    }
    
    /**
     * 设置 dealDate
     * @param dealDate dealDate
     */
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
    
    /**
     * 获取 dealCopy
     * @return dealCopy.
     */
    public BigDecimal getDealCopy() {
        return dealCopy;
    }
    
    /**
     * 设置 dealCopy
     * @param dealCopy dealCopy
     */
    public void setDealCopy(BigDecimal dealCopy) {
        this.dealCopy = dealCopy;
    }
    
    /**
     * 获取 dlAMT
     * @return dlAMT.
     */
    public BigDecimal getDlAMT() {
        return dlAMT;
    }
    
    /**
     * 设置 dlAMT
     * @param dlAMT dlAMT
     */
    public void setDlAMT(BigDecimal dlAMT) {
        this.dlAMT = dlAMT;
    }
    
    /**
     * 获取 intSettAMT
     * @return intSettAMT.
     */
    public BigDecimal getIntSettAMT() {
        return intSettAMT;
    }
    
    /**
     * 设置 intSettAMT
     * @param intSettAMT intSettAMT
     */
    public void setIntSettAMT(BigDecimal intSettAMT) {
        this.intSettAMT = intSettAMT;
    }
    
    /**
     * 获取 bookDate
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }
    
    /**
     * 设置 bookDate
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

	public BigDecimal getDlNum() {
		return dlNum;
	}

	public void setDlNum(BigDecimal dlNum) {
		this.dlNum = dlNum;
	}

	public BigDecimal getIntCode() {
		return intCode;
	}

	public void setIntCode(BigDecimal intCode) {
		this.intCode = intCode;
	}

	public BigDecimal getIntRate() {
		return intRate;
	}

	public void setIntRate(BigDecimal intRate) {
		this.intRate = intRate;
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
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }   

}
