/*
 * 文件名：RolerDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳 修改时间：2016年11月21日
 * 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.common.common.dto;

/**
 * 
 * @author 何阳阳
 * @version 1.0 2016年11月21日
 * @see RolerDto
 * @since 1.0
 */
public class RolerDto {

    /**
     * 值
     */
    private String value;

    /**
     * 文本
     */
    private String text;

    /**
     * 标签
     */
    private String label;

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
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取 text
     * 
     * @return text.
     */
    public String getText() {
        return text;
    }

    /**
     * 设置 text
     * 
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 获取 label
     * 
     * @return label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置 label
     * 
     * @param label label
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
