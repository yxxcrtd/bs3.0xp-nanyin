/*
 * 文件名：ITableRow.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.export.paginator.domain;


/**
 * 表格行数据格式定义
 *
 * @author 何阳阳
 * @version 1.0 2017年9月14日
 * @see ITableRow
 * @since 1.0
 */
public interface ITableRow{

    /**
     * 获取创建人
     * @return 创建人
     */
    String getCreateUser();

    /**
     * 设置创建人
     * @param createUser 创建人
     */
    void setCreateUser(String createUser);

    /**
     * 获取 createDept
     *
     * @return createDept.
     */
    String getCreateDept();

    /**
     * 设置 createDept
     *
     * @param createDept createDept
     */
    void setCreateDept(String createDept);
}
