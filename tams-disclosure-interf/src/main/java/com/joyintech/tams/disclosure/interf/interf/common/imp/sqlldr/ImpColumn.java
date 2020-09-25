/*
 * 文件名：ImpColumn.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月22日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.interf.interf.common.imp.sqlldr;

import java.sql.JDBCType;

/**
 * 数据表对应的列
 * 
 * @author 何阳阳
 * @version 1.0 2017年11月22日
 * @see ImpColumn
 * @since 1.0
 */
public class ImpColumn {

    /**
     * 列名
     */
    private String name;

    /**
     * 类型
     */
    private JDBCType type = JDBCType.VARCHAR;

    /**
     * 长度
     */
    private int length;

    /**
     * 序列
     */
    private String sequence;

    /**
     * 固定值
     */
    private String constant;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 如果javabean和数据库表中的字段名不一直，该属性指定应该使用javabean中的哪个属性值
     */
    private String valueField;

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
     * 获取 type
     * 
     * @return type.
     */
    public JDBCType getType() {
        return type;
    }

    /**
     * 设置 type
     * 
     * @param type type
     */
    public void setType(JDBCType type) {
        this.type = type;
    }

    /**
     * 获取 length
     * 
     * @return length.
     */
    public int getLength() {
        return length;
    }

    /**
     * 设置 length
     * 
     * @param length length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * 获取 sequence
     * 
     * @return sequence.
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * 设置 sequence
     * 
     * @param sequence sequence
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    /**
     * @param name
     */
    public ImpColumn(String name) {
        super();
        this.name = name;
    }

    /**
     * @param name
     * @param type
     */
    public ImpColumn(String name, JDBCType type) {
        super();
        this.name = name;
        this.type = type;
    }

    /**
     * @param name
     * @param type
     * @param length
     */
    public ImpColumn(String name, JDBCType type, int length) {
        super();
        this.name = name;
        this.type = type;
        this.length = length;
    }

    /**
     * @param name
     * @param type
     * @param sequence
     */
    public ImpColumn(String name, String sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    /**
     * 是否为一个有效的数据列
     * 
     * @return boolean
     */
    public boolean isValid() {
        return /* null == this.sequence || */null == this.constant;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" ");
        if (this.sequence != null) {
            sb.append("\"").append(this.sequence).append(".NEXTVAL\"");
        }
        else if (null != this.type) {
            if (!JDBCType.VARCHAR.equals(this.type)) {
                sb.append(this.type.getName());
            }
            if (this.length > 0) {
                sb.append("(" + this.length + ")");
            }
            if (this.constant != null) {
                // 系统时间
                if (this.constant.equalsIgnoreCase("sysdate")
                    || this.constant.equalsIgnoreCase("systimestamp")) {
                    sb.append(" " + this.constant);
                }
                else {
                    sb.append(String.format(" constant \"%s\"", this.constant));
                }
            }
            else if (JDBCType.DATE.equals(this.type)) {
                sb.append(" \"yyyy-mm-dd\"");
            }
            else if (JDBCType.TIMESTAMP.equals(this.type)) {
                sb.append(" \"yyyy-mm-dd hh24:mi:ss\"");
            }
        }
        return sb.toString();
    }

    /**
     * 获取 constant
     * 
     * @return constant.
     */
    public String getConstant() {
        return constant;
    }

    /**
     * 设置 constant
     * 
     * @param constant constant
     */
    public void setConstant(String constant) {
        this.constant = constant;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

}
