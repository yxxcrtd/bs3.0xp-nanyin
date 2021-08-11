package com.joyintech.tams.model.valuation.batch.calculation;

/**
 *计算统一接口
 * @param <T>
 */
public interface Calculator<T extends InputParameter>  {
    OutputParameter calculate(T input);
}
