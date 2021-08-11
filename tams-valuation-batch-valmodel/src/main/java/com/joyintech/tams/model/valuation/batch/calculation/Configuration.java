package com.joyintech.tams.model.valuation.batch.calculation;

public interface Configuration {

    /**
     * 获取未来现金流中剩余期限计算结果的有效小位数
     * @return 有效小数位数
     */
    int getForwadCashTermScale();

    /**
     * 获取引擎计算中全精度的指定精度
     * @return 全精度
     */
    int getAllPrecision();

    /**
     * 默认小数位
     * @return 小数位
     */
    int defalutScale();

    /**
     * 二分法--精度
     */
    double dicPrecision();

    /**
     * 二分法--最大循环次数
     */
    int dicTotalTimes();

    /**
     * 二分法--起始最低收益率
     */
    double dicStartRateLow();

    /**
     * 二分法--起始最高收益率
     */
    double dicStartRateHigh();

    /**
     * 零息贴现债券特殊处理算法中的方案，支持A和B
     */
    String disDebtScheme();

    /**
     * 二分法--起始最低金额
     */
    double dicStartAmountLow();

    /**
     * 二分法--起始最高金额
     */
    double dicStartAmountHigh();
}
