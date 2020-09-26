/*
 * 文件名：NetBokStatBokPositionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.BokStatBokPosition;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetBokStatBokPositionDto
 * @since 1.0
 */
public class NetBokStatBokPositionDto extends BokStatBokPosition {

	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 查询开始日
     */
    private Date sdateStart;
    /**
     * 查询结束日
     */
    private Date sdateEnd;
    /**
     * 查询结束日
     */
    private String finprodId;
    /**
     * 查询日列表
     */
    private  List<Date> queryDates;
    /**
     * 分层代码
     */
    private String layeringId;
    /**
     * 是否子产品
     */
    private String isSubPrd;
    
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 查询结束日
     */
    private Date bookDate;
    
    /**
     * 获取 sdateStart
     * @return sdateStart.
     */
    public Date getSdateStart() {
        return sdateStart;
    }
    
    /**
     * 设置 sdateStart
     * @param sdateStart sdateStart
     */
    public void setSdateStart(Date sdateStart) {
        this.sdateStart = sdateStart;
    }
    
    /**
     * 获取 sdateEnd
     * @return sdateEnd.
     */
    public Date getSdateEnd() {
        return sdateEnd;
    }
    
    /**
     * 设置 sdateEnd
     * @param sdateEnd sdateEnd
     */
    public void setSdateEnd(Date sdateEnd) {
        this.sdateEnd = sdateEnd;
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
     * 获取 queryDates
     * @return queryDates.
     */
    public List<Date> getQueryDates() {
        return queryDates;
    }
    
    /**
     * 设置 queryDates
     * @param queryDates queryDates
     */
    public void setQueryDates(List<Date> queryDates) {
        this.queryDates = queryDates;
    }
    
    /**
     * 获取 layeringId
     * @return layeringId.
     */
    public String getLayeringId() {
        return layeringId;
    }
    
    /**
     * 设置 layeringId
     * @param layeringId layeringId
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
    }
    
    /**
     * 获取 isSubPrd
     * @return isSubPrd.
     */
    public String getIsSubPrd() {
        return isSubPrd;
    }
    
    /**
     * 设置 isSubPrd
     * @param isSubPrd isSubPrd
     */
    public void setIsSubPrd(String isSubPrd) {
        this.isSubPrd = isSubPrd;
    }

	/**
	 * 获取 booksetId
	 * @return booksetId.
	 */
	public String getBooksetId() {
		return booksetId;
	}

	/**
	 * 设置 booksetId
	 * @param booksetId booksetId
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
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
}
