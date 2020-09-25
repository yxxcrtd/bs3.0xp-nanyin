/*
 * 文件名：ValBondassetXQDto.java
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
 * @see ValBondassetXQDto
 * @since 1.0
 */
public class ValBondassetXQDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4484559104805822122L;
    
    private Date applydatestr;

    private Date applydateend;
    
    private String xqmode;
    
    private Date exerciseDate;
    
    private BigDecimal xqnetprice;
    
    private String xqclause;
    
    /**
     * 获取 applydatestr
     * @return applydatestr.
     */
    public Date getApplydatestr() {
        return applydatestr;
    }
    
    /**
     * 设置 applydatestr
     * @param applydatestr applydatestr
     */
    public void setApplydatestr(Date applydatestr) {
        this.applydatestr = applydatestr;
    }
    
    /**
     * 获取 applydateend
     * @return applydateend.
     */
    public Date getApplydateend() {
        return applydateend;
    }
    
    /**
     * 设置 applydateend
     * @param applydateend applydateend
     */
    public void setApplydateend(Date applydateend) {
        this.applydateend = applydateend;
    }
    
    /**
     * 获取 xqmode
     * @return xqmode.
     */
    public String getXqmode() {
        return xqmode;
    }
    
    /**
     * 设置 xqmode
     * @param xqmode xqmode
     */
    public void setXqmode(String xqmode) {
        this.xqmode = xqmode;
    }
    
    /**
     * 获取 exerciseDate
     * @return exerciseDate.
     */
    public Date getExerciseDate() {
        return exerciseDate;
    }
    
    /**
     * 设置 exerciseDate
     * @param exerciseDate exerciseDate
     */
    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }
    
    /**
     * 获取 xqnetprice
     * @return xqnetprice.
     */
    public BigDecimal getXqnetprice() {
        return xqnetprice;
    }
    
    /**
     * 设置 xqnetprice
     * @param xqnetprice xqnetprice
     */
    public void setXqnetprice(BigDecimal xqnetprice) {
        this.xqnetprice = xqnetprice;
    }
    
    /**
     * 获取 xqclause
     * @return xqclause.
     */
    public String getXqclause() {
        return xqclause;
    }
    
    /**
     * 设置 xqclause
     * @param xqclause xqclause
     */
    public void setXqclause(String xqclause) {
        this.xqclause = xqclause;
    }
}
