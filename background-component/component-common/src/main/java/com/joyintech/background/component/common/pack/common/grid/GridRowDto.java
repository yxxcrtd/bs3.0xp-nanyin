/*
 * 文件名：GridRowDto.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月5日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.grid;

import com.joyintech.background.component.common.pack.common.export.paginator.domain.ITableRow;

/**
 * 表格的行数据
 * 
 * @author 何阳阳
 * @version 1.0 2018年1月16日
 * @see GridRowDto
 * @since 1.0
 */
public interface GridRowDto extends ITableRow {

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
     * 
     * @return 排序
     */
    int getOrder();

    /**
     * 设置排序
     * 
     * @param order 排序
     */
    void setOrder(int order);

    /**
     * 获取 id
     * 
     * @return id.
     */
    String getId();

    /**
     * 设置 id
     * 
     * @param id id
     */
    void setId(String id);

    /**
     * 获取 parentId
     * 
     * @return parentId.
     */
    String getParentId();

    /**
     * 设置 parentId
     * 
     * @param parentId parentId
     */
    void setParentId(String parentId);

    /**
     * 获取 name
     * 
     * @return name.
     */
    String getName();

    /**
     * 设置 name
     * 
     * @param name name
     */
    void setName(String name);

    /**
     * 获取 leaf
     * 
     * @return leaf.
     */
    boolean isLeaf();

    /**
     * 设置 leaf
     * 
     * @param leaf leaf
     */
    void setLeaf(boolean leaf);
}
