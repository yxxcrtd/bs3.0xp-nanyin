/*
 * 文件名：CalculatorFactory.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资管系统计算引擎
 * 修改人：何阳阳
 * 修改时间：2016年12月28日
 * 修改内容：新建
 */

package com.joyintech.tams.model.valuation.batch.calculation;

import com.joyintech.tams.model.valuation.batch.calculation.shell.calculator.*;
import com.joyintech.tams.model.valuation.batch.calculation.util.EngineContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 计算器工厂类
 *
 * @author 何阳阳
 * @version 1.0 2016年12月28日
 * @see CalculatorFactory
 * @since 1.0
 */
public final class CalculatorFactory {

    /**
     * 计算器实例cache
     */
    @SuppressWarnings("rawtypes")
    private static final Map<String, Calculator> instances = new HashMap<>();

    /**
     * 构造方法私有化
     */
    private CalculatorFactory() {
        super();
    }


    /**
     * 剩余期限
     * @return
     */
    public static final RemainingTermCalculator createRemainingTermCalculator() {
        if(!instances.containsKey(RemainingTermCalculator.class.getName())) {
            instances.put(RemainingTermCalculator.class.getName(), new RemainingTermCalculator());
        }
        return (RemainingTermCalculator)instances.get(RemainingTermCalculator.class.getName());
    }

    /**
     * 插值计算
     * @return
     */
    public static final TargetRateCalculator createTargetRateCalculator() {
        if(!instances.containsKey(TargetRateCalculator.class.getName())) {
            instances.put(TargetRateCalculator.class.getName(), new TargetRateCalculator());
        }
        return (TargetRateCalculator)instances.get(TargetRateCalculator.class.getName());
    }



    /**
     * 二分法到期点差计算
     * @return
     */
    public static final DichotomySpreadExpireCalculator createSpreadExpireCalculator() {
        if(!instances.containsKey(DichotomySpreadExpireCalculator.class.getName())) {
            instances.put(DichotomySpreadExpireCalculator.class.getName(), new DichotomySpreadExpireCalculator());
        }
        return (DichotomySpreadExpireCalculator)instances.get(DichotomySpreadExpireCalculator.class.getName());
    }

    /**
     * 创建贴现模型-现金流折现法点差计算器
     * ADD BY WUJIE ON 20210702
     */
    public static final DisCountSpreadCalculator createDisCountSpreadCalculator() {
        if(!instances.containsKey(DisCountSpreadCalculator.class.getName())) {
            instances.put(DisCountSpreadCalculator.class.getName(), new DisCountSpreadCalculator());
        }
        return (DisCountSpreadCalculator)instances.get(DisCountSpreadCalculator.class.getName());
    }
    
    /**
     * 创建贴现模型-现金流折现法全价计算器
     * ADD BY WUJIE ON 20210702
     */
    public static final DisCountFullPriceCalculator createDisCountFullPriceCalculator() {
        if(!instances.containsKey(DisCountFullPriceCalculator.class.getName())) {
            instances.put(DisCountFullPriceCalculator.class.getName(), new DisCountFullPriceCalculator());
        }
        return (DisCountFullPriceCalculator)instances.get(DisCountFullPriceCalculator.class.getName());
    }

    /**
     * 创建贴现模型-现金流信息对应的曲线收益率
     * ADD BY WUJIE ON 20210703
     */
    public static final CurveRateSpRCalculator createCurveRateSpRCalculator() {
        if(!instances.containsKey(CurveRateSpRCalculator.class.getName())) {
            instances.put(CurveRateSpRCalculator.class.getName(), new CurveRateSpRCalculator());
        }
        return (CurveRateSpRCalculator)instances.get(CurveRateSpRCalculator.class.getName());
    }

    /**
     * 创建贴现模型-点差二分算法即期计算器
     * ADD BY WUJIE ON 20210702
     */
    public static final DichotomySpreadSpCalculator createDichotomySpreadSpCalculator() {
        if(!instances.containsKey(DichotomySpreadSpCalculator.class.getName())) {
            instances.put(DichotomySpreadSpCalculator.class.getName(), new DichotomySpreadSpCalculator());
        }
        return (DichotomySpreadSpCalculator)instances.get(DichotomySpreadSpCalculator.class.getName());
    }


    /**
     * 创建贴现模型-点差二分算法到期计算器
     */
    public static final DichotomySpreadExpireCalculator createDichotomySpreadExpireCalculator() {
        if(!instances.containsKey(DichotomySpreadExpireCalculator.class.getName())) {
            instances.put(DichotomySpreadExpireCalculator.class.getName(), new DichotomySpreadExpireCalculator());
        }
        return (DichotomySpreadExpireCalculator)instances.get(DichotomySpreadExpireCalculator.class.getName());
    }


    /**
     * 获取 配置参数
     *
     * @return 配置参数.
     */
    public Configuration getConfig() {
        return EngineContext.getInstance().getConfig();
    }

    /**
     * 设置配置参数
     *
     * @param config 配置参数
     */
    public void setConfig(Configuration config) {
        EngineContext.getInstance().setConfig(config);
    }

}
