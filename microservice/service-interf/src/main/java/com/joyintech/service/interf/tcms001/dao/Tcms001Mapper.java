/*
 * 文件名：Tcms001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms001.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * tcms001删除接口表信息mapper
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月9日
 * @see Tcms001Mapper
 * @since 1.0
 */
@Repository
public interface Tcms001Mapper {

    /**
     * 根据产品代码删除接口表数据
     * 
     * @param prodId 产品代码
     * @return 条数
     * @see
     * @since 1.0
     */
    int deleteByPrdid(@Param("prodId") String prodId);
}
