/*
 * 文件名：InterfCmsBftsMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icmsbfts.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * CMS信贷资产余额信息 查询数据
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月29日
 * @see InterfCmsBftsMapper
 * @since 1.0
 */
@Repository
public interface InterfCmsBftsMapper {

    /**
     * 查询CMS信贷资产余额信息
     * 
     * @param map 参数集合
     * @return CMS信贷资产余额信息
     * @see
     * @since 1.0
     */
    List<String> selectCmsBftsList(Map<String, Object> map);

}
