/*
 * 文件名：ValBondassetFXDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.bondasset.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月30日
 * @see ValBondassetFXDto
 * @since 1.0
 */
public class ValBondassetFXDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 2417938339048088255L;
    
    /**
     * 理论开始日
     */
    private Date theorydatestr;
    
    /**
     * 理论结束日
     */
    private Date theorydateend;
    
    /**
     * 实际计息开始日
     */
    private Date actdatestr;
    
    /**
     * 实际计息结束日
     */
    private Date actdateend;
    
    /**
     * 计划支付日
     */
    private Date plandate;
    
    /**
     * 计划支付利息
     */
    private BigDecimal planintamt;

    
    /**
     * 获取 theorydatestr
     * @return theorydatestr.
     */
    public Date getTheorydatestr() {
        return theorydatestr;
    }

    
    /**
     * 设置 theorydatestr
     * @param theorydatestr theorydatestr
     */
    public void setTheorydatestr(Date theorydatestr) {
        this.theorydatestr = theorydatestr;
    }

    
    /**
     * 获取 theorydateend
     * @return theorydateend.
     */
    public Date getTheorydateend() {
        return theorydateend;
    }

    
    /**
     * 设置 theorydateend
     * @param theorydateend theorydateend
     */
    public void setTheorydateend(Date theorydateend) {
        this.theorydateend = theorydateend;
    }

    
    /**
     * 获取 actdatestr
     * @return actdatestr.
     */
    public Date getActdatestr() {
        return actdatestr;
    }

    
    /**
     * 设置 actdatestr
     * @param actdatestr actdatestr
     */
    public void setActdatestr(Date actdatestr) {
        this.actdatestr = actdatestr;
    }

    
    /**
     * 获取 actdateend
     * @return actdateend.
     */
    public Date getActdateend() {
        return actdateend;
    }

    
    /**
     * 设置 actdateend
     * @param actdateend actdateend
     */
    public void setActdateend(Date actdateend) {
        this.actdateend = actdateend;
    }

    
    /**
     * 获取 plandate
     * @return plandate.
     */
    public Date getPlandate() {
        return plandate;
    }

    
    /**
     * 设置 plandate
     * @param plandate plandate
     */
    public void setPlandate(Date plandate) {
        this.plandate = plandate;
    }


    
    /**
     * 获取 planintamt
     * @return planintamt.
     */
    public BigDecimal getPlanintamt() {
        return planintamt;
    }


    
    /**
     * 设置 planintamt
     * @param planintamt planintamt
     */
    public void setPlanintamt(BigDecimal planintamt) {
        this.planintamt = planintamt;
    }
}
