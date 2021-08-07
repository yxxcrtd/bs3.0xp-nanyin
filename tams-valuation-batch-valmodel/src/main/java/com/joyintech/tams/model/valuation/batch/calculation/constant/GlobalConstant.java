package com.joyintech.tams.model.valuation.batch.calculation.constant;

import java.math.BigDecimal;

/**
 * @author zznn
 * @date 2021/7/1
 */
public interface GlobalConstant {
    /**
     * 数字100
     */
    BigDecimal NUMBER_100 = new BigDecimal("100");

    /**
     * 数字360
     */
    BigDecimal NUMBER_360 = new BigDecimal("360");

    /**
     * 数字365
     */
    BigDecimal NUMBER_365 = new BigDecimal("365");

    /**
     * 零息贴现债券处理方案：A
     */
    String DIS_DEBT_SCHEME_A = "A";

    /**
     * 零息贴现债券处理方案：B
     */
    String DIS_DEBT_SCHEME_B = "B";

    String YES ="Y";
    String NO = "N";
}

