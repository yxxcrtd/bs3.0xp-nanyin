/*
 * 文件名：DataIndexSequenceType.java 版权：Copyright by www.joyintech.com 描述： 修改人：Administrator
 * 修改时间：2016年10月22日 修改内容：
 */

package com.joyintech.tams.valuation.common.constants;

/**
 * 採番类型枚举类
 *
 * @author 强成西
 * @version 2016年10月22日
 * @see DataIndexSequenceType
 * @since
 */
public enum DataIndexSequenceType {
    /**
     * 系统管理用序列
     */
    系统管理("sys_sequence", "A0"),
    /**
     * 日志用序列
     */
    系统日志("log_sequence", "B0"),
    /**
     * 工作流用序列
     */
    工作流("wfl_sequence", "C0"),
    /**
     * 交易编码
     */
    交易编号("trade_id_sequence", "PD"),
    /**
     * 账务明细
     */
    记账类型("book_sequence", "BK"),
    /**
     * 账务明细
     */
    汇兑损益("book_sequence", "HLZS"),
    /**
     * 处理序列
     */
    处理序列("bookorder_sequence", ""),
    /**
     * 处理序列
     */
    账务汇总表_处理序列("bok_summary_bookorder_sequence", ""),
    /**
     * 业务凭证编号
     */
    业务凭证编号("bus_vouch_sequence", ""),
    ;
    /**
     * 序列名
     */
    private String sequenceName;

    /**
     * 用于加在採番后结果的前缀，以保证各个sequence的採番结果不会出现重复的情况
     */
    private String shortName;

    /**
     * @param sequenceName 序列名
     */
    DataIndexSequenceType(String sequenceName, String shortName) {
        this.sequenceName = sequenceName;
        this.shortName = shortName;
    }

    public String getSequenceName() {
        return this.sequenceName;
    }

    /**
     * 获取 shortName
     * @return shortName.
     */
    public String getShortName() {
        return shortName;
    }
}
