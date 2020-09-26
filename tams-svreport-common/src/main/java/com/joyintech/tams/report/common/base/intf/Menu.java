/*
 * 文件名：Menu.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.common.base.intf;

import java.io.Serializable;
import java.util.Collection;

/**
 * 菜单
 * @author 何阳阳
 * @version 1.0 2018年2月5日
 * @see Menu
 * @since 1.0
 */
public interface Menu extends Serializable {

    /**
     * ID
     * @return ID
     */
    String getId();
    
    /**
     * 获取图标
     * @return 图标
     */
    String getIcon();
    
    /**
     * 获取菜单名
     * @return 菜单名
     */
    String getName();
    
    /**
     * 获取父菜单
     * @return 父菜单
     */
    Menu getParent();
    
    /**
     * 获取画面
     * @return 画面
     */
    Page getPage();
    
    /**
     * 获取排序
     * @return 排序
     */
    int getOrderNo();
    
    /**
     * 是否展开
     * @return 是否展开
     */
    boolean isSpread();
    
    /**
     * 获取子菜单
     * @return 子菜单
     */
    Collection<Menu> getChildren();
}
