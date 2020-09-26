/*
 * 文件名：AmountElementDTO.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.seclendfindealbookdetail.dto;

import java.math.BigDecimal;

/**
 * 金额元素DTO（金融负债还本到期处理）
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年10月29日
 * @see AmountElement
 * @since 1.0
 */
public class AmountElementDTO {

    /**
     * 应计利息
     */
    private BigDecimal varYjlx;

    /**
     * 还本本金
     */
    private BigDecimal varHbbj;

    /**
     * 冲减公允价值变动
     */
    private BigDecimal varCjgyjzbd;

    /**
     * 冲减成本
     */
    private BigDecimal varCjcb;

    /**
     * 投资收益
     */
    private BigDecimal varTzsy;

    /**
     * 获取 varYjlx
     * 
     * @return varYjlx.
     */
    public BigDecimal getVarYjlx() {
        return varYjlx;
    }

    /**
     * 设置 varYjlx
     * 
     * @param varYjlx varYjlx
     */
    public void setVarYjlx(BigDecimal varYjlx) {
        this.varYjlx = varYjlx;
    }

    /**
     * 获取 varHbbj
     * 
     * @return varHbbj.
     */
    public BigDecimal getVarHbbj() {
        return varHbbj;
    }

    /**
     * 设置 varHbbj
     * 
     * @param varHbbj varHbbj
     */
    public void setVarHbbj(BigDecimal varHbbj) {
        this.varHbbj = varHbbj;
    }

    /**
     * 获取 varCjgyjzbd
     * 
     * @return varCjgyjzbd.
     */
    public BigDecimal getVarCjgyjzbd() {
        return varCjgyjzbd;
    }

    /**
     * 设置 varCjgyjzbd
     * 
     * @param varCjgyjzbd varCjgyjzbd
     */
    public void setVarCjgyjzbd(BigDecimal varCjgyjzbd) {
        this.varCjgyjzbd = varCjgyjzbd;
    }

    /**
     * 获取 varCjcb
     * 
     * @return varCjcb.
     */
    public BigDecimal getVarCjcb() {
        return varCjcb;
    }

    /**
     * 设置 varCjcb
     * 
     * @param varCjcb varCjcb
     */
    public void setVarCjcb(BigDecimal varCjcb) {
        this.varCjcb = varCjcb;
    }

    /**
     * 获取 varTzsy
     * 
     * @return varTzsy.
     */
    public BigDecimal getVarTzsy() {
        return varTzsy;
    }

    /**
     * 设置 varTzsy
     * 
     * @param varTzsy varTzsy
     */
    public void setVarTzsy(BigDecimal varTzsy) {
        this.varTzsy = varTzsy;
    }

}
