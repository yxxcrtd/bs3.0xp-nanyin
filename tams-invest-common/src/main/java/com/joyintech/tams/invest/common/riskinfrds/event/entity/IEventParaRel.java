/*
 * 文件名：IEventParaReal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.event.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;

/**
 * 风险事件与参数的关系
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月16日
 * @see IEventParaRel
 * @since 1.0
 */
public interface IEventParaRel extends Serializable {

    /**
     * 取得 参数代码，实际存储的结构是 参数代码:参数粒度
     * 
     * @return 参数代码:参数粒度
     * @see
     * @since 1.0
     */
    String getParaCode();

    /**
     * 取得 风险事件代码
     * 
     * @return 风险事件代码
     * @see
     * @since 1.0
     */
    String getEventCode();

    /**
     * 取得 关系类型（+、-、*、/）
     * 
     * @return 关系类型（+、-、*、/）
     * @see
     * @since 1.0
     */
    String getRelType();

    /**
     * 取得 关系对象（金额、价格、数量）
     * 
     * @return 关系对象（金额、价格、数量）
     * @see
     * @since 1.0
     */
    String getRelItem();

    /**
     * 设置 关系运算的对象值
     * 
     * @param val 关系运算的对象值
     * @see
     * @since 1.0
     */
    void setRelValue(BigDecimal val);

    /**
     * 取得 关系预算的对象值
     * 
     * @return 关系预算的对象值
     * @see
     * @since 1.0
     */
    BigDecimal getRelValue();

    /**
     * 取得 自定义值
     *
     * @return 自定义值
     * @see
     * @since 1.0
     */
    BigDecimal getCustomVal();

    // add by zhangrui1 公式配置 start
    /**
     * 取得公式配置
     * 
     * @return 公式配置
     */
    String getFormulaVal();

    /**
     * 取得 公式中的参数代码
     * 
     * @return 公式中的参数代码
     */
    default String formulaParaCode() {
        String para = formula(0);
        if (StringUtil.isEmpty(para) || !StringUtil.contains(para, GlobalConstant.OPEN_MIDDLE)) {
            return para;
        }
        return StringUtil.substring(para, 0, StringUtil.indexOf(para, GlobalConstant.OPEN_MIDDLE));
    }

    /**
     * 取得 公式中的参数代码的redis识别域
     * 
     * @return 公式中的参数代码的redis识别域
     */
    default String formulaRdsField() {
        String para = formula(0);
        if (StringUtil.isEmpty(para) || !StringUtil.contains(para, GlobalConstant.OPEN_MIDDLE)) {
            return para;
        }
        return StringUtil.substring(para, StringUtil.indexOf(para, GlobalConstant.OPEN_MIDDLE) + 1,
            para.length() - 1);
    }

    /**
     * 取得 公式中指定位置的元素
     * 
     * @param index 指定位置（index ∈ (0,1,2)）
     * @return 公式中指定位置的元素
     */
    default String formula(int index) {
        String[] arr = StringUtil.split(getFormulaVal());
        if (isFormula() && null != arr && arr.length == 3 && index < 3 && index >= 0) {
            return arr[index];
        }
        return null;
    }

    /**
     * 取得 公式中的操作符
     * 
     * @return 公式中的操作符
     */
    default String formulaOprType() {
        return formula(1);
    }

    /**
     * 取得 公式中的计算对象
     * 
     * @return 公式中的计算对象
     */
    default String formulaRelItem() {
        return formula(2);
    }

    /**
     * 是否为公式配置
     * 
     * @return true : 是
     */
    default boolean isFormula() {
        return StringUtil.equals(BussinessConstantRds.FORMULA, getRelItem());
    }

    /**
     * 执行公式计算
     * 
     * @return 公式计算后结果
     */
    default BigDecimal doFormula() {
        return (isFormula() && getFormulaParaVal() != null) ? CommonUtil.math(getFormulaParaVal(), getRelValue(),
            formulaOprType()) : getRelValue();
    }

    /**
     * 执行公式计算
     * 
     * @param relVal 计算对象的值
     * @return 公式计算后结果
     */
    default BigDecimal doFormula(BigDecimal relVal) {
        return (isFormula() && getFormulaParaVal() != null) ? CommonUtil.math(getFormulaParaVal(), relVal,
            formulaOprType()) : relVal;
    }

    /**
     * 设置公式中的参数值
     * 
     * @param formulaParaVal 公式中的参数值
     */
    void setFormulaParaVal(BigDecimal formulaParaVal);

    /**
     * 取得公式中的参数值
     * 
     * @return 公式中的参数值
     */
    BigDecimal getFormulaParaVal();
    // add by zhangrui1 公式配置 end
}
