/*
 * 文件名：YieldMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.yield.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.yield.entity.YiledEntity;

/**
 * 查询净值信息
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see YieldMapper
 * @since 1.0
 */
@Repository
public interface YieldMapper {

    /**
     * 查询期限天数
     * 
     * @param finProdId 金融产品代码
     * @return 期限天数
     * @see
     * @since 1.0
     */
    int selectTermDay(@Param("finProdId") String finProdId);

    /**
     * 获取前一个日期的累计净值的净值信息
     * 
     * @param map 金融产品代码,净值日
     * @return 前一个日期的累计净值的净值信息
     * @see
     * @since 1.0
     */
    YiledEntity getYileEntity(Map<String, Object> map);
}
