/*
 * 文件名：ImplEventParaRel.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.tams.common.base.entity.RskEventParaRel;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEventParaRel;

import java.math.BigDecimal;

/**
 * 风险事件与参数关联
 * 
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see ImplEventParaRel
 * @since 1.0
 */
public class ImplEventParaRel extends RskEventParaRel implements IEventParaRel {

    private BigDecimal rekValue;

    @Override
    public void setRelValue(BigDecimal val) {
        rekValue=val;
    }

    @Override
    public BigDecimal getRelValue() {
        return rekValue;
    }

    // add by zhangrui1 公式配置 start
    /**
     * 公式中的参数值
     */
    private BigDecimal formulaParaVal;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFormulaParaVal(BigDecimal formulaParaVal) {
        this.formulaParaVal = formulaParaVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getFormulaParaVal() {
        return formulaParaVal;
    }
    // add by zhangrui1 公式配置 end
}
