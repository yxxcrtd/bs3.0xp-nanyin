/*
 * 文件名：ValSetting.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.dto;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.springframework.util.CollectionUtils;

/**
 * 固定值配置
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月22日
 * @see ValSetting
 * @since 1.0
 */
public class ValSetting extends Setting<Map<String, Object>> {

    /**
     * @param isResult
     * @param vals
     */
    public ValSetting(boolean isResult, Map<String, Object> vals) {
        super(TYPE_VAL, isResult, vals);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute(Function<Map<String, Object>, Object> func) {
        if (null != func) {
            return func.apply(getExpress());
        }
        return super.execute(exprSet -> {
            if (CollectionUtils.isEmpty(exprSet) || CollectionUtils.isEmpty(getParamSet())) {
                return null;
            }
            Entry<String, Object> en = getParamSet().entrySet().stream().findFirst().orElse(null);
            if (en != null) {
                return exprSet.get(en.getValue());
            }
            return null;
        });
    }
}
