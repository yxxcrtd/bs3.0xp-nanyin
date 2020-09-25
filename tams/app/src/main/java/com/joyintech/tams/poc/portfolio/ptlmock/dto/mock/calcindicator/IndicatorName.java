/*
 * 文件名：IndicatorName.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.calcindicator;


/**
 * 模拟试算各指标
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see IndicatorName
 * @since 1.0
 */
public enum IndicatorName {
    /**
     * 单位净值
     */
    单位净值("UNIT_VALUE"),
    /**
     * 万份收益
     */
    万份收益("P_YIELD"),
    /**
     * 剩余现金
     */
    剩余现金("CASH_POSITON"),
    /**
     * 涨跌幅
     */
    涨跌幅("VAL_CHG"),
    /**
     * 七日年化收益率
     */
    七日年化收益率("SEVEN_DAY_RATE"),
    /**
     * 总资产
     */
    总资产("ASSET_VALUE"),
    /**
     * 净资产
     */
    净资产("ASSET_NETVALUE"),
    /**
     * 杠杆率
     */
    杠杆率("LEVER_RATE"),
    /**
     * 基点价值 
     */
    基点价值("BPVALUE"),
    /**
     * 平均剩余期限
     */
    平均剩余期限("AVGSURPLUSTIME"),
    /**
     * 平均剩余存续期限
     */
    平均剩余存续期限("AVGSURPLUSTIME_P"),
    /**
     * 组合久期
     */
    组合久期("PTL_M_DURATION"),
    /**
     * 一月年化收益率
     */
    一月年化收益率("ONE_MONTH_RATE"),
    /**
     * 三月年化收益率
     */
    三月年化收益率("THREE_MONTH_RATE"),
    /**
     * 至下一季报日收益率
     */
    至下一季报日收益率("QUARTER_DAY_RATE"),
    /**
     * 一年收益率
     */
    一年收益率("ONE_YEAR_RATE"),
    /**
     * 到期收益率
     */
    到期收益率("EXPIRE_YIELD"),
    /**
     * 区间收益率
     */
    区间收益率("RANGE_RATE")
    ;
    
    /**
     * 对应于数据字典code
     */
    private String value;

    IndicatorName(String value) {
        this.value = value;
    }
    
    /**
     * 获取 value
     *
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value
     *            value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
