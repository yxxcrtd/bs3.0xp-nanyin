package com.joyintech.tams.model.valuation.batch.calculation;

import com.joyintech.tams.model.valuation.batch.calculation.util.BasicUtil;

import java.util.Collection;

/**
 * 计算输入参数
 */
public interface InputParameter {
    default void checkNull(Object value, String message) {
        if(BasicUtil.isNull(value)) {
            throw new CalculationException("[" + message + "]必须输入！");
        }
        else if(value instanceof Collection) {
            @SuppressWarnings("unchecked")
            Collection<InputParameter> valueList = (Collection<InputParameter>)value;
            if(valueList.isEmpty()) {
                throw new CalculationException("[" + message + "]必须输入！");
            }
        }
    }

    /**
     * 输入参数验证
     */
    void check();
}
