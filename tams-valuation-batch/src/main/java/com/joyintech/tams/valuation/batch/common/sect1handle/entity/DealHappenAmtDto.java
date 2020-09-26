/*
 * 文件名：DealTypesFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.sect1handle.entity;

import java.math.BigDecimal;

/**
 * 
 * 交易金额计算参数
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see DealHappenAmtDto
 * @since 1.0
 */
public class DealHappenAmtDto {
    
    /**
     * 发生公允价值变动
     */
    private BigDecimal happenGujzbd;

    /**
     * 发生利息调整
     */
    private BigDecimal happenLxtz;

    /**
     * 发生应计利息
     */
    private BigDecimal happenYjlx;

    /**
     * 发生成本
     */
    private BigDecimal happenCb;

    /**
     * 发生数量
     */
    private BigDecimal happenNum;

    
    /**
     * 获取 happenGujzbd
     * @return happenGujzbd.
     */
    public BigDecimal getHappenGujzbd() {
        return happenGujzbd;
    }

    
    /**
     * 设置 happenGujzbd
     * @param happenGujzbd happenGujzbd
     */
    public void setHappenGujzbd(BigDecimal happenGujzbd) {
        this.happenGujzbd = happenGujzbd;
    }

    
    /**
     * 获取 happenLxtz
     * @return happenLxtz.
     */
    public BigDecimal getHappenLxtz() {
        return happenLxtz;
    }

    
    /**
     * 设置 happenLxtz
     * @param happenLxtz happenLxtz
     */
    public void setHappenLxtz(BigDecimal happenLxtz) {
        this.happenLxtz = happenLxtz;
    }

    
    /**
     * 获取 happenYjlx
     * @return happenYjlx.
     */
    public BigDecimal getHappenYjlx() {
        return happenYjlx;
    }

    
    /**
     * 设置 happenYjlx
     * @param happenYjlx happenYjlx
     */
    public void setHappenYjlx(BigDecimal happenYjlx) {
        this.happenYjlx = happenYjlx;
    }

    
    /**
     * 获取 happenCb
     * @return happenCb.
     */
    public BigDecimal getHappenCb() {
        return happenCb;
    }

    
    /**
     * 设置 happenCb
     * @param happenCb happenCb
     */
    public void setHappenCb(BigDecimal happenCb) {
        this.happenCb = happenCb;
    }

    
    /**
     * 获取 happenNum
     * @return happenNum.
     */
    public BigDecimal getHappenNum() {
        return happenNum;
    }

    
    /**
     * 设置 happenNum
     * @param happenNum happenNum
     */
    public void setHappenNum(BigDecimal happenNum) {
        this.happenNum = happenNum;
    }

    
}
