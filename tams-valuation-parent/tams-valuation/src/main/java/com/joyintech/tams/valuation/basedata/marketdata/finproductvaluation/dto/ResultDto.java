/*
 * 文件名：ResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.finproductvaluation.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2018年7月20日
 * @see ResultDto
 * @since 1.0
 */
public class ResultDto extends BaseEntity{
    
    /**
     * 公布日期
     */
    private String publishDate;

    /**
     * 估值，净值、万份收益等
     */
    private BigDecimal valuation;

//    /**
//     * 估值类型，市场估值、外部估值等
//     */
//    private String valType;
    
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 估值日期
     */
    private String valDate;

    /**
     * 基金类型
     */
    private String type1;
    
    /**
     * 产品名称
     */
    private String finprodAbbr;
    
    
    private BigDecimal sevenDayRate;
    
    
    
    
    public BigDecimal getSevenDayRate() {
		return sevenDayRate;
	}


	public void setSevenDayRate(BigDecimal sevenDayRate) {
		this.sevenDayRate = sevenDayRate;
	}


	public String getFinprodAbbr() {
		return finprodAbbr;
	}


	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}


	

    
//    /**
//     * 获取 valType
//     * @return valType.
//     */
//    public String getValType() {
//        return valType;
//    }
//
//    
//    /**
//     * 设置 valType
//     * @param valType valType
//     */
//    public void setValType(String valType) {
//        this.valType = valType;
//    }

    
    public BigDecimal getValuation() {
		return valuation;
	}


	public void setValuation(BigDecimal valuation) {
		this.valuation = valuation;
	}


	/**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }
    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodTypeFmt() {
//        return  SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType))?
    			SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType):finprodType;
    }
    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }


	public String getPublishDate() {
		return DateUtil.formatDateAddSplit(publishDate);
	}


	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}


	public String getValDate() {
		return DateUtil.formatDateAddSplit(valDate);
	}


	public void setValDate(String valDate) {
		this.valDate = valDate;
	}


	/**
     * 获取 type1
     * @return type1.
     */
    public String getType1() {
        return  type1;
    }

    /**
     * 获取 type1
     * @return type1.
     */
    public String getType1Fmt() {
//        return  SysDataUtil.getNameFromDataDict(DictCode.基金类型, type1);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.基金类型, type1))?
    			SysDataUtil.getNameFromDataDict(DictCode.基金类型, type1):type1;
    }
    
    /**
     * 设置 type1
     * @param type1 type1
     */
    public void setType1(String type1) {
        this.type1 = type1;
    }
    
    

}
