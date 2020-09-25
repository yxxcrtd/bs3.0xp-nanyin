/*
 * 文件名：FinProductAnapleroticPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年4月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dto;

import java.util.Date;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

/**
 * 回补计划列表Dto
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月22日
 * @see FinProductAnapleroticPlanDto
 * @since 1.0
 */
public class FinProductAnapleroticPlanDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3757382605998018489L;
    
    /**
     * 产品代码
     */
    private String finprodId; 

    /**
     * 回补周期开始日
     */
    private Date anapleroticCycleStartDate;
    
    /**
     * 回补周期结束日
     */
    private Date anapleroticCycleEndDate;
    
    /**
     * 回补日
     */
    private Date anapleroticDate;
    /**
     * 调整前结束日
     */
    private Date valDate;
    /**
     * 现金流代码
     */
    private String cashId;
    
    public String getCashId() {
		return cashId;
	}


	public void setCashId(String cashId) {
		this.cashId = cashId;
	}


	public Date getValDate() {
		return valDate;
	}


	public void setValDate(Date valDate) {
		this.valDate = valDate;
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
     * 获取 anapleroticCycleStartDate
     * @return anapleroticCycleStartDate.
     */
    public Date getAnapleroticCycleStartDate() {
        return anapleroticCycleStartDate;
    }

    public String getAnapleroticCycleStartDateStr(){
       return DateUtil.format(this.getAnapleroticCycleStartDate());
    }
    
    /**
     * 设置 anapleroticCycleStartDate
     * @param anapleroticCycleStartDate anapleroticCycleStartDate
     */
    public void setAnapleroticCycleStartDate(Date anapleroticCycleStartDate) {
        this.anapleroticCycleStartDate = anapleroticCycleStartDate;
    }

    
    /**
     * 获取 anapleroticCycleEndDate
     * @return anapleroticCycleEndDate.
     */
    public Date getAnapleroticCycleEndDate() {
        return anapleroticCycleEndDate;
    }
    public String getAnapleroticCycleEndDateStr(){
        return DateUtil.format(this.getAnapleroticCycleEndDate());
     }
     
    
    /**
     * 设置 anapleroticCycleEndDate
     * @param anapleroticCycleEndDate anapleroticCycleEndDate
     */
    public void setAnapleroticCycleEndDate(Date anapleroticCycleEndDate) {
        this.anapleroticCycleEndDate = anapleroticCycleEndDate;
    }

    
    /**
     * 获取 anapleroticDate
     * @return anapleroticDate.
     */
    public Date getAnapleroticDate() {
        return anapleroticDate;
    }
    public String getAnapleroticDateStr() {
        return DateUtil.format(this.getAnapleroticDate());
    }

    
    /**
     * 设置 anapleroticDate
     * @param anapleroticDate anapleroticDate
     */
    public void setAnapleroticDate(Date anapleroticDate) {
        this.anapleroticDate = anapleroticDate;
    }

    
}
