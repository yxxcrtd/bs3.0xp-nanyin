/*
 * 文件名：TableColumn.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月27日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.service.interf.ods.entity;

import java.sql.JDBCType;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;

/**
 * 数据库表列定义
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月27日
 * @see TableColumn
 * @since 1.0
 */
public class TableColumn {

    /**
     * 所属表名
     */
    private String tableName;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 数据长度
     */
    private int dataLength;

    /**
     * 是否可为空
     */
    private String nullable;

    /**
     * 列ID
     */
    private int columnId;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    /**
     * 取得控制文件列</br>
     * 例如：COL1 VARCHA(50)
     * 
     * @return 控制文件列
     * @see
     * @since 1.0
     */
    public String getSqlldrLine() {
        StringBuilder sb = new StringBuilder();

        String type = getDataType();
        // CHAR
        if (StringUtil.equalsIgnoreCase(type, JDBCType.CHAR.name())) {
            type = JDBCType.CHAR.name();
        }
        // DATE
        else if (StringUtil.equalsIgnoreCase(type, JDBCType.DATE.name())) {
            type = JDBCType.DATE.name();
        }
        // TIMESTAMP
        else if (StringUtil.startsWithIgnoreCase(type, JDBCType.TIMESTAMP.name())) {
            type = JDBCType.TIMESTAMP.name();
        }
        else if (StringUtil.equalsIgnoreCase(type, JDBCType.VARCHAR.name())) {
            type = JDBCType.CHAR.name();
        }
        else if (StringUtil.equalsIgnoreCase(type, "VARCHAR2")) {
            type = JDBCType.CHAR.name();
        }
        else {
            type = null;
        }
        sb.append(columnName);
        if (StringUtil.isNotEmpty(type)) {
            sb.append(" ").append(type);
            if (this.dataLength > 0) {
                sb.append(GlobalConstant.OPEN_PARENTHESIS).append(this.dataLength).append(
                    GlobalConstant.CLOSE_PARENTHESIS);
            }
        }
        return sb.toString();
    }
}
