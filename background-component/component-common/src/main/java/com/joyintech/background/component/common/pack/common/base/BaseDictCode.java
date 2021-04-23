/*
 * 文件名：BaseDictCode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年2月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.base;

import com.joyintech.background.component.common.pack.common.base.intf.IDictCode;
import com.joyintech.background.component.common.pack.common.constant.DictCode;

/**
 * 数据字典
 * 
 * @author 何阳阳
 * @version 1.0 2018年2月3日
 * @see BaseDictCode
 * @since 1.0
 */
public interface BaseDictCode extends IDictCode {

    /**
     * 数据状态
     */
    DictCode 数据状态 = DictCode.数据状态;

    /**
     * 流程状态
     */
    DictCode 流程状态 = DictCode.流程状态;

    /**
     * 是否
     */
    DictCode 是否 = DictCode.是否;
}
