/*
 * 文件名：BaseDictCodeList.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2017年5月12日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.background.component.common.pack.common.base;

import com.joyintech.background.component.common.pack.common.constant.DictCodeList;

/**
 * 数据字典明细接口定义
 * 
 * @author 何阳阳
 * @version 1.0 2017年5月12日
 * @see BaseDictCodeList
 * @since 1.0
 */
public interface BaseDictCodeList extends com.joyintech.background.component.common.pack.common.base.intf.IDictCodeList {

    DictCodeList 数据状态_新建 = DictCodeList.数据状态_新建;
    
    DictCodeList 数据状态_新建中 = DictCodeList.数据状态_新建中;

    DictCodeList 数据状态_删除中 = DictCodeList.数据状态_删除中;


    DictCodeList 数据状态_删除 = DictCodeList.数据状态_删除;
    
    DictCodeList 数据状态_修改中 = DictCodeList.数据状态_修改中;
    
    DictCodeList 数据状态_修改 = DictCodeList.数据状态_修改;
    
    DictCodeList 数据状态_作废 = DictCodeList.数据状态_作废;
    
    DictCodeList 是否_是 = DictCodeList.是否_是;

    DictCodeList 是否_否 = DictCodeList.是否_否;
}
