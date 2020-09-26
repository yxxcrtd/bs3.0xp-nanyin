/*
 * 文件名：BokStatBokPositionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：Nov 12, 2018
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.base.entity;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.BokStatBokPosition;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 Nov 12, 2018
 * @see BokStatBokPositionDto
 * @since 1.0
 */
public class BokStatBokPositionDto extends BokStatBokPosition{
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
     * 账务明细代码
     * ADD BY WUJIE ON 20190703
     */
    private String bokDetailId;
    
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

	public String getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}
    

}
