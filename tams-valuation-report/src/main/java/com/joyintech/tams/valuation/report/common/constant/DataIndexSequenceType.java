/*
 * 文件名：DataIndexSequenceType.java 版权：Copyright by www.joyintech.com 描述： 修改人：Administrator
 * 修改时间：2016年10月22日 修改内容：
 */

package com.joyintech.tams.valuation.report.common.constant;

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
     * 基础数据用序列
     */
    基础数据("mst_sequence", "D0"),
    /**
     * 接口应用
     */
    接口应用("interf_sequence", "E0"),
    /**
     * 中债登记流水号
     */
    中债登记管理("chb_sequence","F0"),
    /**
     * 批处理用序列
     */
    批处理用("batch_sequence", "G0"),
    /**
     * 检查序列号
     */
    检查序列号("CHECK_SEQNO",""),
    /**
     * 上游文件读取处理监控序列号
     */
    上游文件读取处理监控序列号("CHB_FILE_READ_MONITOR_SEQNO", "")
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
     *
     * @return shortName.
     */
    public String getShortName() {
        return shortName;
    }
}
