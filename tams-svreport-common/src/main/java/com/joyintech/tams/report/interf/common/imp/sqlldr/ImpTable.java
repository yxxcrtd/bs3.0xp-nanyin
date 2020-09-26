/*
 * 文件名：CtlFile.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月22日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.interf.common.imp.sqlldr;

import java.util.List;

import com.joyintech.framework.constant.GlobalConstant;


/**
 * 数据表
 * 
 * @author 何阳阳
 * @version 1.0 2017年11月22日
 * @see ImpTable
 * @since 1.0
 */
public class ImpTable {

    /**
     * 表名
     */
    private String name;

    /**
     * 序列
     */
    private List<TableSequence> sequences;

    /**
     * 列定义
     */
    private List<ImpColumn> columns;

    /**
     * 获取 name
     * 
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 name
     * 
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 columns
     * 
     * @return columns.
     */
    public List<ImpColumn> getColumns() {
        return columns;
    }

    /**
     * 设置 columns
     * 
     * @param columns columns
     */
    public void setColumns(List<ImpColumn> columns) {
        this.columns = columns;
    }

    public List<TableSequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<TableSequence> sequences) {
        this.sequences = sequences;
        this.rebuildSequence();
    }

    /**
     * @param name
     */
    public ImpTable(String name) {
        super();
        this.name = name;
    }

    /**
     * 获取列定义
     * 
     * @return 列定义
     */
    public String getColumnsDef() {
        StringBuilder sb = new StringBuilder();
        if (this.columns != null) {
            sb.append(GlobalConstant.OPEN_PARENTHESIS);
            for (int i = 0; i < this.columns.size(); i++ ) {
                if (i > 0) {
                    sb.append(GlobalConstant.COMMA).append(GlobalConstant.LINE_SEPARATOR);
                }
                sb.append(columns.get(i).toString());
            }
            sb.append(GlobalConstant.CLOSE_PARENTHESIS);
        }
        return sb.toString();
    }

    /**
     * @see
     * @since 1.0
     */
    public void rebuildSequence() {
        if (this.columns != null && this.sequences != null) {
            for (int i = 0; i < this.columns.size(); i++ ) {
                ImpColumn column = this.columns.get(i);
                for (int j = 0; j < this.sequences.size(); j++ ) {
                    TableSequence seq = this.sequences.get(j);
                    if (seq.getColumnName().equalsIgnoreCase(column.getName())) {
                        column.setSequence(seq.getSequenceName());
                    }
                }
            }
        }
    }
}
