/*
 * 文件名：SpcSetting.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2020年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;

/**
 * 处理字母组合、交易编号等
 * 
 * @author Administrator
 * @version 1.0 2020年9月22日
 * @see MtdSetting
 * @since 1.0
 */
public class MtdSetting extends Setting<Method> {

    /**
     * 是否组合处理
     */
    public boolean isPortfolio;

    /**
     * Bean
     */
    public Object bean;

    /**
     * parameter setting
     */
    public Set<String> params;

    /**
     * @param isResult
     * @param method
     */
    public MtdSetting(boolean isResult, String methodName, Set<String> params) {
        super(TYPE_MTD, isResult, null);
        if (StringUtil.isEmpty(methodName) || CollectionUtils.isEmpty(params)) {
            return;
        }
        this.params = params;
        try {
            int lastIndex = StringUtil.lastIndexOf(methodName, GlobalConstant.POINT);
            int paraStr = StringUtil.indexOf(methodName, GlobalConstant.OPEN_PARENTHESIS);
            int paraEnd = StringUtil.indexOf(methodName, GlobalConstant.CLOSE_PARENTHESIS);
            // 类名
            String clzName = StringUtil.substring(methodName, 0, lastIndex);
            // 函数名
            String mtdName = StringUtil.substring(methodName, lastIndex + 1, paraStr);
            // 参数列表
            String param = StringUtil.substring(methodName, paraStr + 1, paraEnd);
            // 类
            Class<?> clz = Class.forName(clzName);
            // 实例化
            bean = SpringContextUtil.getApplicationContext().getBean(clz);
            String[] arr = StringUtil.split(param, GlobalConstant.COMMA);
            Class<?>[] paraClz = new Class<?>[arr.length];
            setExpress(clz.getMethod(mtdName, paraClz));
        }
        catch (BeansException | ClassNotFoundException | NoSuchMethodException
            | SecurityException e) {
            // nothing to do
        }
    }

    /**
     * 获取 isPortfolio
     * 
     * @return isPortfolio.
     */
    public boolean isPortfolio() {
        return isPortfolio;
    }

    /**
     * 设置 isPortfolio
     * 
     * @param isPortfolio isPortfolio
     */
    public void setPortfolio(boolean isPortfolio) {
        this.isPortfolio = isPortfolio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute(Function<Method, Object> func) {
        if (isPortfolio && !CollectionUtils.isEmpty(getParamSet())) {
            // 字母组合处理
            return AllTradeCommonUtil.exchangePortfolio(
                getParamSet().get("portfolioId").toString());
        }
        // 自定义处理
        if (null != func) {
            return func.apply(getExpress());
        }
        // 函数执行
        return super.execute(mtd -> {
            try {
                if (mtd == null) {
                    return null;
                }
                mtd.setAccessible(true);
                // 执行函数
                return mtd.invoke(bean,
                    CollectionUtils.isEmpty(
                        getParamSet()) ? new ArrayList<>() : 
                    getParamSet().entrySet().stream().filter(
                        en -> params.contains(en.getKey())).map(
                        Entry::getValue).collect(Collectors.toList()));
            }
            catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
                return null;
            }
        });
    }
}
