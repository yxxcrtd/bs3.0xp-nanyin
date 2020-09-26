/*
 * 文件名：ValCashPlan.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.assets.fund.dto;

import java.util.Date;

/**
 * 付息计划
 * @author zhangwei
 * @version 1.0 2018年7月26日
 * @see ValCashPlan
 * @since 1.0
 */
public class ValCashPlanDto {
    
    /** 计息开始日 */
    private Date vdate;
    
    /** 计息结束日 */
    private Date mdate;
    
    /** 付息日 */
    private Date payDate;

    
    public Date getVdate() {
        return vdate;
    }

    
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    
    public Date getMdate() {
        return mdate;
    }

    
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    
    public Date getPayDate() {
        return payDate;
    }

    
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    
    
    
    
    

}
