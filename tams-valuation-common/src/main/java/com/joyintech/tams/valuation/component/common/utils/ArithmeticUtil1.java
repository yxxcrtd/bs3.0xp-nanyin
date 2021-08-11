package com.joyintech.tams.valuation.component.common.utils;

import java.math.BigDecimal;

import static com.joyintech.framework.util.ArithmeticUtil.subtract;

/**
 * @Auther: yuzj
 * @Date: 2021/8/5 10:34
 * @Description:
 */
public class ArithmeticUtil1 {
    /**
     * 供精确的减法运算，参数为空的作为0处理 连减
     * 第一个参数为被减数
     *
     * @param datas 数据（数值）
     * @return 计算结果
     * @see
     * @since 1.0
     */
    public static BigDecimal doSub(BigDecimal... datas) {
        BigDecimal result = BigDecimal.ZERO;
        int dataIndex = 0;
        for (BigDecimal data : datas) {
            if (dataIndex == 0) {
                result = data;
            }
            else {
                result = subtract(result, data);
            }

            dataIndex++;
        }

        return result;
    }

}
