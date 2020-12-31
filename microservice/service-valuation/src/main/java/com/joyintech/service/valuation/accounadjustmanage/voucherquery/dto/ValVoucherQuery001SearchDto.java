/*
 * 文件名：ValVoucherQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto;

import java.util.Date;

/**
 * ValVoucherQuery001页面筛选条件dto 用于页面查询条件校验
 * 
 * @author weipeng
 * @version 1.0 2018年5月8日
 * @see ValVoucherQuery001SearchDto
 * @since 1.0
 */
public class ValVoucherQuery001SearchDto {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套类别
     */
    private String booksetType;
    
    /**
     * 账套类型-银行账
     */
    private String booksetType2;

    /**
     * 业务类别
     */
    private String[] tableIds;
    
    
    /**
     * 账套代码
     */
    private String[] booksetIdArr;

    /**
     * 资产/负债代码
     */
    private String busiId;

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
    private String busVouchnum;

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
     * 上游业务编号（金融产品表原交易编号）
     */
    private String pTradeId;

    /**
     * 凭证是否展示分录
     */
    private String isShowfl;
    
    /**
     * 制作人
     */
    private String createUser;

    /**
     * 科目号
     */
    private String subjectNo;

    public String getBooksetType2() {
		return booksetType2;
	}

	public void setBooksetType2(String booksetType2) {
		this.booksetType2 = booksetType2;
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
    public String[] getTableIds() {
        return tableIds;
    }

    /**
     * 设置 业务类别
     * 
     * @param tableId 业务类别
     */
    public void setTableIds(String[] tableIds) {
        this.tableIds = tableIds;
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
     * 取得 凭证编号
     * 
     * @return 凭证编号
     */
    public String getBusVouchnum() {
        return busVouchnum;
    }

    /**
     * 设置 凭证编号
     * 
     * @param busVouchnum 凭证编号
     */
    public void setBusVouchnum(String busVouchnum) {
        this.busVouchnum = busVouchnum;
    }

    /**
     * 获取 happenDate
     * 
     * @return happenDate.
     */
    public String getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 happenDate
     * 
     * @param happenDate happenDate
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
     * 获取 isShowfl
     * 
     * @return isShowfl.
     */
    public String getIsShowfl() {
        return isShowfl;
    }

    /**
     * 设置 isShowfl
     * 
     * @param isShowfl isShowfl
     */
    public void setIsShowfl(String isShowfl) {
        this.isShowfl = isShowfl;
    }

	public String[] getBooksetIdArr() {
		return booksetIdArr;
	}

	public void setBooksetIdArr(String[] booksetIdArr) {
		this.booksetIdArr = booksetIdArr;
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
      
	 /**
     * 用户名
     */
    private String userId;
  
    /**
     * 获取 userId
     * @return userId.
     */
    public String getUserId() {
        return userId;
    }

    
    /**
     * 设置 userId
     * @param userId userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
}
