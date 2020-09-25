/*
 * 文件名：RegistDetailDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月12日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.common.common.dto;

/**
 * 登记明细
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月12日
 * @see RegistDetailDto
 * @since 1.0
 */
public class RegistDetailDto {

    /**
     * 字段代码
     */
    private String data;

    /**
     * 字段汉字名
     */
    private String title;

    /**
     * 数据类型
     */
    private String type;

    /**
     * 小数位（type为number时可用）
     */
    private int fixed;

    /**
     * 是否存在比较值
     */
    private boolean hasOldValue;

    /**
     * 当前值
     */
    private String currentValue;

    /**
     * 修改前值
     */
    private String oldValue;

    /**
     * 获取 data
     * 
     * @return data.
     */
    public String getData() {
        return data;
    }

    /**
     * 设置 data
     * 
     * @param data data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 获取 title
     * 
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 title
     * 
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 currentValue
     * 
     * @return currentValue.
     */
    public String getCurrentValue() {
        return currentValue;
    }

    /**
     * 设置 currentValue
     * 
     * @param currentValue currentValue
     */
    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * 获取 oldValue
     * 
     * @return oldValue.
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * 设置 oldValue
     * 
     * @param oldValue oldValue
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * 获取 type
     * 
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 type
     * 
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 fixed
     * 
     * @return fixed.
     */
    public int getFixed() {
        return fixed;
    }

    /**
     * 设置 fixed
     * 
     * @param fixed fixed
     */
    public void setFixed(int fixed) {
        this.fixed = fixed;
    }

    /**
     * 获取 hasOldValue
     * 
     * @return hasOldValue.
     */
    public boolean isHasOldValue() {
        return hasOldValue;
    }

    /**
     * 设置 hasOldValue
     * 
     * @param hasOldValue hasOldValue
     */
    public void setHasOldValue(boolean hasOldValue) {
        this.hasOldValue = hasOldValue;
    }

}
