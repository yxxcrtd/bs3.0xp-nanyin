/*
 * 文件名：SqlldrColsEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;

/**
 * Sqlldr控制文件列顺序定义
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月3日
 * @see SqlldrColsEnum
 * @since 1.0
 */
public enum SqlldrColsEnum {
    /**
     * 每日托管流水
     */
    每日托管流水(
        "INTERF_ODS_ACCOUNT_CASH", "INTERF_ODS_ACCOUNT_CASH",
        "CDATE|ACCOUNT|DEALDATE|PS|CCY|PRINAMT|REPRINAMT|SELLER|EFFECTFLAG|NETNO|SEQNO|NUMNO|SELLERNAME|REMARK"
    ),
    /**
     * 开放式阶梯产品到期
     */
    ODS_448192(
        "INTERF_PROD_LADDER_DETAIL",
        "INTERF_PROD_LADDER_DETAIL", //
        "PRODID|STARTDATE|FIELD1 FILLER|ENDDATE|STARTAMOUNT|ENDAMOUNT|STARTCUSTOMERLEVEL|ENDCUSTOMERLEVEL"
                                     + "|STARTDAYNUM|ENDDAYNUM|SOURCEAMOUNTBYRATE|TOTALPROFITAMOUNT|TOTALCOUNT"
                                     + "|REFADDRATE|STICKDAYNUM|TRANDAYNUM|CDATE CONSTANT \"getSysDate\"|INTERF_ID CONSTANT \"448192-01\""
                                     + "|CREATE_TIME \"to_timestamp(to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24:mi:ss')\""
    );

    /**
     * 表名
     */
    private String table;

    /**
     * 数据文件名
     */
    private String dataFile;

    /**
     * 字段（顺序）
     */
    private String cols;

    SqlldrColsEnum(String table, String dataFile, String cols) {
        this.table = table;
        this.dataFile = dataFile;
        this.cols = cols;
    }

    /**
     * 获取 table
     * 
     * @return table.
     */
    public String getTable() {
        return table;
    }

    /**
     * 获取 cols
     * 
     * @return cols.
     */
    public String getCols() {
        return cols;
    }

    /**
     * 取得列数组
     * 
     * @return 列数组
     * @see
     * @since 1.0
     */
    public String[] getColsAsArr() {
        if (StringUtil.isEmpty(this.cols)) {
            return new String[0];
        }
        return StringUtil.split(this.cols, GlobalConstant.VERTICAL_LINE);
    }

    /**
     * 获取 dataFile
     * 
     * @return dataFile.
     */
    public String getDataFile() {
        return dataFile;
    }

    /**
     * 取得列数组
     * 
     * @param table 对象表名
     * @return 对象表所对应的列数组
     * @see
     * @since 1.0
     */
    public static String[] getColsAsArr(String table) {
        return getEnum(table, 1).getColsAsArr();
    }

    /**
     * 根据文件名取得对应表名
     * 
     * @param dataFie 数据文件名
     * @return 对应表名
     * @see
     * @since 1.0
     */
    public static String getTableByDataFile(String dataFie) {
        return getEnum(dataFie, 2).getTable();
    }

    /**
     * 根据表名取得对应数据文件名
     * 
     * @param table 表名
     * @return 对应数据文件名
     * @see
     * @since 1.0
     */
    public static String getDataFileByTable(String table) {
        return getEnum(table, 1).getTable();
    }

    /**
     * 根据条件查询枚举值
     * 
     * @param value 查询条件
     * @param index 查询对象 1:表名,2:数据文件名
     * @return 查询结果
     * @see
     * @since 1.0
     */
    private static SqlldrColsEnum getEnum(String value, int index) {
        for (SqlldrColsEnum sqlldrEnum : SqlldrColsEnum.values()) {
            if (sqlldrEnum.getIndexValue(index).equals(value)) {
                return sqlldrEnum;
            }
        }
        return 每日托管流水;
    }

    /**
     * 查询对象 1:表名,2:数据文件名
     * 
     * @param index 条件
     * @return 查询对象
     * @see
     * @since 1.0
     */
    private String getIndexValue(int index) {
        if (index == 1) {
            return this.getTable();
        }
        else if (index == 2) {
            return this.getDataFile();
        }
        else {
            return this.getCols();
        }
    }
}
