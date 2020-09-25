/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valuationstatementquery.dto;


import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
/**
 * 记账凭证查询dto
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValValuationStatementQuery001Dto
 * @since 2.0
 */
public class ValValuationStatementQuery001Dto extends BaseEntity{
	
	 private static final long serialVersionUID = 1L;

	  /**
     * 账套代码
     */
    private String booksetId;
	  /**
     * 账套名称
     */
    private String booksetName;
    /**
     * 日期
     */
    private String sdate;

    /**
     * 文件类型，估值表、余额表
     */
    private String fileType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;
    
    /**
     * 产品代码
     */
    private String finprodId;
    
    /**
     * 产品名称
     */
    private String finprodName;
    
    /**
     * 估值日期
     */
    private String valDate;

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
     * 取得 文件类型，估值表、余额表
     * @return 文件类型，估值表、余额表
     */
    public String getFileType() {
        return this.fileType;
    }


    /**
     * 设置 文件类型，估值表、余额表
     * @param fileType 文件类型，估值表、余额表
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * 取得 文件名
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 文件路径
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

	/**
	 * 获取 sdate
	 * @return sdate.
	 */
	public String getSdate() {
		return sdate;
	}

	/**
	 * 设置 sdate
	 * @param sdate sdate
	 */
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	/**
	 * 获取 booksetName
	 * @return booksetName.
	 */
	public String getBooksetName() {
		return booksetName;
	}

	/**
	 * 设置 booksetName
	 * @param booksetName booksetName
	 */
	public void setBooksetName(String booksetName) {
		this.booksetName = booksetName;
	}

    
    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getValDate() {
        return valDate;
    }

    
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    
    public String getFinprodName() {
        return finprodName;
    }

    
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
    
}
