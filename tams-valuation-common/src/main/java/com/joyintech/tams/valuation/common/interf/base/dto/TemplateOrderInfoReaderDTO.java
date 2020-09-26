/*
 * 文件名：TodoDeal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.math.BigDecimal;

/**
 * 计提基础信息DTO 〈功能详细描述〉
 * 
 * @author wudebao
 * @version 1.0 2018年5月23日
 * @see TemplateOrderInfoReaderDTO
 * @since 1.0
 */
public class TemplateOrderInfoReaderDTO {

    /**
     * 模板类型
     */
    private String tmpType;
    /**
     * 模版中文名称
     */
    private String tmpCName;
    /**
     * 模版英文名称
     */
    private String tmpEName;
    /**
     * 接入批次
     */
    private String order;
    /**
     * 字段中文名
     */
    private String colCName;
    /**
     * 字段英文名
     */
    private String colEName;
    /**
     * 字段类型
     */
    private String colType;
    /**
     * 非空判断
     */
    private String isNull;
    /**
     * 长度
     */
    private BigDecimal maxLength;
    /**
     * 存在性
     */
    private String isExist;
    
    /**
     * 数据字典 add by wujie on 20180926
     */
    private String dictCode;

    /**
     *  取得 模板类型
     *  @return 模板类型
     */
    public String getTmpType() {
       return tmpType;
    }

    /**
     *  设置 模板类型
     *  @param tmpType 模板类型
     */
    public void setTmpType( String tmpType)  {
       this.tmpType = tmpType;
    }

    /**
     *  取得 模版中文名称
     *  @return 模版中文名称
     */
    public String getTmpCName() {
       return tmpCName;
    }

    /**
     *  设置 模版中文名称
     *  @param tmpCName 模版中文名称
     */
    public void setTmpCName( String tmpCName)  {
       this.tmpCName = tmpCName;
    }

    /**
     *  取得 模版英文名称
     *  @return 模版英文名称
     */
    public String getTmpEName() {
       return tmpEName;
    }

    /**
     *  设置 模版英文名称
     *  @param tmpEName 模版英文名称
     */
    public void setTmpEName( String tmpEName)  {
       this.tmpEName = tmpEName;
    }


    /**
     *  取得 接入批次
     *  @return 接入批次
     */
    public String getOrder() {
       return order;
    }

    /**
     *  设置 接入批次
     *  @param order 接入批次
     */
    public void setOrder( String order)  {
       this.order = order;
    }

    /**
     *  取得 字段中文名
     *  @return 字段中文名
     */
    public String getColCName() {
       return colCName;
    }

    /**
     *  设置 字段中文名
     *  @param colCName 字段中文名
     */
    public void setColCName( String colCName)  {
       this.colCName = colCName;
    }

    /**
     *  取得 字段英文名
     *  @return 字段英文名
     */
    public String getColEName() {
       return colEName;
    }

    /**
     *  设置 字段英文名
     *  @param colEName 字段英文名
     */
    public void setColEName( String colEName)  {
       this.colEName = colEName;
    }

    /**
     *  取得 字段类型
     *  @return 字段类型
     */
    public String getColType() {
       return colType;
    }

    /**
     *  设置 字段类型
     *  @param colType 字段类型
     */
    public void setColType( String colType)  {
       this.colType = colType;
    }

    /**
     *  取得 非空判断
     *  @return 非空判断
     */
    public String getIsNull() {
       return isNull;
    }

    /**
     *  设置 非空判断
     *  @param isNull 非空判断
     */
    public void setIsNull( String isNull)  {
       this.isNull = isNull;
    }

    /**
     *  取得 长度
     *  @return 长度
     */
    public BigDecimal getMaxLength() {
       return maxLength;
    }

    /**
     *  设置 长度
     *  @param maxLength 长度
     */
    public void setMaxLength( BigDecimal maxLength)  {
       this.maxLength = maxLength;
    }

    /**
     *  取得 存在性
     *  @return 存在性
     */
    public String getIsExist() {
       return isExist;
    }

    /**
     *  设置 存在性
     *  @param isExist 存在性
     */
    public void setIsExist( String isExist)  {
       this.isExist = isExist;
    }

	/**
	 * 取得 数据字典
	 * @return 数据字典
	 */
	public String getDictCode() {
		return dictCode;
	}

	/**
	 * 设置 数据字典
	 * @param dictCode 数据字典
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

}
