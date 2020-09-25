/*
 * 文件名：DataDictItem.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.common.common.base.intf;

import java.io.Serializable;
import java.util.Set;

/**
 * 数据字典明细统一接口
 * 
 * @author 何阳阳
 * @version 1.0 2018年2月6日
 * @see DataDictItem
 * @since 1.0
 */
public interface DataDictItem extends Serializable {

    /**
     * 获取 dictCode
     * 
     * @return dictCode.
     */
    String getDictCode();

    /**
     * 获取 itemCode
     * 
     * @return itemCode.
     */
    String getItemCode();

    /**
     * 获取 itemValue
     * 
     * @return itemValue.
     */
    String getItemValue();

    /**
     * 获取 orderNo
     * 
     * @return orderNo.
     */
    String getOrderNo();

    /**
     * 获取 defaultValue
     * 
     * @return defaultValue.
     */
    String getDefaultValue();

    /**
     * 获取 parentId
     * 
     * @return parentId.
     */
    String getParentId();

    /**
     * 获取 pageIds
     * 
     * @return pageIds.
     */
    Set<String> getPageIds();

}
