/*
 * 文件名：GridRowDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年11月30日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.common.common.dto;

/**
 * 表格的行数据
 * 
 * @author 何阳阳
 * @version 1.0 2016年11月30日
 * @see GridRowDto
 * @since 1.0
 */
public interface GridRowDto {

    /**
     * 获取 行号
     * 
     * @return 行号.
     */
    String getRowId();

    /**
     * 设置 行号
     * 
     * @param rowId 行号
     */
    void setRowId(String rowId);
    
    /**
     * 获取排序
     * @return 排序
     */
    int getOrder();
    
    /**
     * 设置排序
     * @param order 排序
     */
    void setOrder(int order);
}
