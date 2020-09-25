/*
 * 文件名：TableSequence.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月27日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr;

/**
 * @author 何阳阳
 * @version 1.0 2017年12月27日
 * @see TableSequence
 * @since 1.0
 */
public class TableSequence {

    private String columnName;

    private String sequenceName;

    public TableSequence(String columnName, String sequenceName) {
        super();
        this.columnName = columnName;
        this.sequenceName = sequenceName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

}
