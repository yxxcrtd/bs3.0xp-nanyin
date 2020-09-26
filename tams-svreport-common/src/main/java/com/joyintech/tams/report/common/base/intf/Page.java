/*
 * 文件名：Page.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.common.base.intf;

import java.io.Serializable;
import java.util.Collection;


/**
 * 画面
 * @author 何阳阳
 * @version 1.0 2018年2月3日
 * @see Page
 * @since 1.0
 */
public interface Page extends Serializable {
    /**
     * 获取画面ID
     * @return 画面ID
     */
    String getId();
    
    /**
     * 获取画面名称
     * @return 画面名称
     */
    String getName();
    
    /**
     * 获取画面路径
     * @return 画面路径
     */
    String getPath();
    
    /**
     * 获取菜单ID
     * @return 菜单ID
     */
    String getMenuId();
    
    /**
     * 获取画面文件
     * @return 画面文件
     */
    String getFile();
    
    /**
     * 获取画面元素
     * @return 画面元素
     */
    Collection<PageElement> getElements();
}
