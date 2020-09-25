/*
 * 文件名：DailyInvestYieldDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年9月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.evaluationentrance.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【区间每日收益率】实体类
 * 
 * @author zhangJin
 * @version 1.0 2019年9月30日
 * @see DailyYieldDto
 * @since 1.0
 */
public class DailyYieldDto {

    /**
     * 数据日期
     */
    private Date cdate;

    /**
     * 日收益率
     */
    private BigDecimal yield;


    public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	/**
     * 获取 yield
     * 
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     * 
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

}
