/*
 * 文件名：ValVoucherQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto;

import java.util.Date;

/**
 * ValVoucherQuery001页面筛选条件dto 用于页面查询条件校验
 * 
 * @author weipeng
 * @version 1.0 2018年5月8日
 * @see ValManualVoucher001SearchDto
 * @since 1.0
 */
public class ValManualVoucher001SearchDto {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套类别
     */
    private String booksetType;

    /**
     * 业务类别
     */
    private String tableId;

    /**
     * 资产/负债代码
     */
    private String busiId;

    /**
     * 入账日期
     */
    private String bookDate;

    /**
     * 入账日期开始时间
     */
    private Date bookDateBegin;

    /**
     * 入账日期结束时间
     */
    private Date bookDateEnd;

    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 凭证类型
     */
    private String bookType;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 生成日期
     */
    private String happenDate;

    /**
     * 生成日期开始时间
     */
    private Date happenDateBegin;

    /**
     * 生成日期结束时间
     */
    private Date happenDateEnd;

    /**
     * 是否显示分录
     */
    private String isShowfl;
    /**
     * 上游业务编号（金融产品表原交易编号）
     */
    private String pTradeId;

    /**
     * 金融产品类型
     */
    private String finprodType;
    
    
    /**
     * 账套集合
     */
    private String[] booksetIdArr;
    
    /**
     * 制作人
     */
    private String createUser;
    

    public String[] getBooksetIdArr() {
		return booksetIdArr;
	}

	public void setBooksetIdArr(String[] booksetIdArr) {
		this.booksetIdArr = booksetIdArr;
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
     * 获取 booksetType
     * 
     * @return booksetType.
     */
    public String getBooksetType() {
        return booksetType;
    }

    /**
     * 设置 booksetType
     * 
     * @param booksetType booksetType
     */
    public void setBooksetType(String booksetType) {
        this.booksetType = booksetType;
    }

    /**
     * 取得 业务类别
     * 
     * @return 业务类别
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 业务类别
     * 
     * @param tableId 业务类别
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 取得 资产/负债代码
     * 
     * @return 资产/负债代码
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
     * 取得 入账日期
     * 
     * @return 入账日期
     */
    public String getBookDate() {
        return bookDate;
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
     * 取得 入账日期开始时间
     * 
     * @return 入账日期开始时间
     */
    public Date getBookDateBegin() {
        return bookDateBegin;
    }

    /**
     * 设置 入账日期开始时间
     * 
     * @param bookDateBegin 入账日期开始时间
     */
    public void setBookDateBegin(Date bookDateBegin) {
        this.bookDateBegin = bookDateBegin;
    }

    /**
     * 取得 入账日期结束时间
     * 
     * @return 入账日期结束时间
     */
    public Date getBookDateEnd() {
        return bookDateEnd;
    }

    /**
     * 设置 入账日期结束时间
     * 
     * @param bookDateEnd 入账日期结束时间
     */
    public void setBookDateEnd(Date bookDateEnd) {
        this.bookDateEnd = bookDateEnd;
    }

    /**
     * 取得 业务编号
     * 
     * @return 业务编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号
     * 
     * @param tradeId 业务编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 取得 凭证类型
     * 
     * @return 凭证类型
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * 设置 凭证类型
     * 
     * @param bookType 凭证类型
     */
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    /**
     * 获取 凭证编号
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
     * 取得 生成日期
     * 
     * @return 生成日期
     */
    public String getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 生成日期
     * 
     * @param happenDate 生成日期
     */
    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 生成日期开始时间
     * 
     * @return 生成日期开始时间
     */
    public Date getHappenDateBegin() {
        return happenDateBegin;
    }

    /**
     * 设置 生成日期开始时间
     * 
     * @param happenDateBegin 生成日期开始时间
     */
    public void setHappenDateBegin(Date happenDateBegin) {
        this.happenDateBegin = happenDateBegin;
    }

    /**
     * 取得 生成日期结束时间
     * 
     * @return 生成日期结束时间
     */
    public Date getHappenDateEnd() {
        return happenDateEnd;
    }

    /**
     * 设置 生成日期结束时间
     * 
     * @param happenDateEnd 生成日期结束时间
     */
    public void setHappenDateEnd(Date happenDateEnd) {
        this.happenDateEnd = happenDateEnd;
    }

    /**
     * 获取 上游业务编号
     * 
     * @return 上游业务编号.
     */
    public String getpTradeId() {
        return pTradeId;
    }

    /**
     * 设置 上游业务编号
     * 
     * @param pTradeId 上游业务编号
     */
    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getIsShowfl() {
        return isShowfl;
    }

    public void setIsShowfl(String isShowfl) {
        this.isShowfl = isShowfl;
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

}
