/*
 * 文件名：DataDict.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.base.intf;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 数据字典统一接口
 * 
 * @author 何阳阳
 * @version 1.0 2018年2月6日
 * @see DataDict
 * @since 1.0
 */
public interface DataDict extends Serializable {

    /**
     * 获取 dictCode
     * 
     * @return dictCode.
     */
    String getDictCode();

    /**
     * 获取数据字典中文名
     * 
     * @return 数据字典中文名.
     */
    String getDictCnname();

    /**
     * 获取数据字典英文名
     * 
     * @return 数据字典英文名.
     */
    String getDictEnname();

    /**
     * 获取可更新标识
     * 
     * @return 更新标识.
     */
    String getUpdateFlg();

    /**
     * 获取明细
     * 
     * @return 数据字典明细.
     */
    List<? extends DataDictItem> getItems();

    /**
     * 获取数据字典对应的画面
     * @return 画面ID.
     */
    Set<String> getPageIds();

}
