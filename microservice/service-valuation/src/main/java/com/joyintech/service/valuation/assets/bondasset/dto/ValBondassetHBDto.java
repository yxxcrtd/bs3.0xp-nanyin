/*
 * 文件名：ValBondassetHBDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.bondasset.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月30日
 * @see ValBondassetHBDto
 * @since 1.0
 */
public class ValBondassetHBDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4291872286324572183L;
    
    /**
     * 起息日
     */
    private Date vdate1; 
    
    /**
     * 计息截止日
     */
    private Date jsdatestr;
    
    /**
     * 计息截止日
     */
    private Date jsdateend;
    
    /**
     * 支付日
     */
    private Date paydate;
    
    /**
     * 还本面额/百元
     */
    private BigDecimal remoney;
    
    /**
     * 计息本金
     */
    private BigDecimal jxamt;
    


	public Date getVdate1() {
		return vdate1;
	}

	public void setVdate1(Date vdate1) {
		this.vdate1 = vdate1;
	}

	/**
     * 获取 jsdatestr
     * @return jsdatestr.
     */
    public Date getJsdatestr() {
        return jsdatestr;
    }

    /**
     * 设置 jsdatestr
     * @param jsdatestr jsdatestr
     */
    public void setJsdatestr(Date jsdatestr) {
        this.jsdatestr = jsdatestr;
    }

    /**
     * 获取 jsdateend
     * @return jsdateend.
     */
    public Date getJsdateend() {
        return jsdateend;
    }

    /**
     * 设置 jsdateend
     * @param jsdateend jsdateend
     */
    public void setJsdateend(Date jsdateend) {
        this.jsdateend = jsdateend;
    }
    
    /**
     * 获取 paydate
     * @return paydate.
     */
    public Date getPaydate() {
        return paydate;
    }
    
    /**
     * 设置 paydate
     * @param paydate paydate
     */
    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }
    
    /**
     * 获取 remoney
     * @return remoney.
     */
    public BigDecimal getRemoney() {
        return remoney;
    }
    
    /**
     * 设置 remoney
     * @param remoney remoney
     */
    public void setRemoney(BigDecimal remoney) {
        this.remoney = remoney;
    }
    
    /**
     * 获取 jxamt
     * @return jxamt.
     */
    public BigDecimal getJxamt() {
        return jxamt;
    }
    
    /**
     * 设置 jxamt
     * @param jxamt jxamt
     */
    public void setJxamt(BigDecimal jxamt) {
        this.jxamt = jxamt;
    }
}
