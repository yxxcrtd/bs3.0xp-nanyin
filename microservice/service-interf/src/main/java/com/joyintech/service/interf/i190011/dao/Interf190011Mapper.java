/*
 * 文件名：Interf190011Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i190011.dao;

import com.joyintech.tams.common.base.entity.InterfBonusInfo;

/**
 * 净值分红信息查询接口（190011）-Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf190011Mapper
 * @since 1.0
 */
public interface Interf190011Mapper {

    /**
     * 删除查询条件重复的数据
     * 
     * @param info 查询条件
     * @return 删除行数
     */
    int deleteBonusInfo(InterfBonusInfo info);
}
