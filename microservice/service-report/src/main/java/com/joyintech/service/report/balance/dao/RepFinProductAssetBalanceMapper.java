/*
 * 文件名：RepFinProductAssetBalanceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.balance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.report.balance.dto.RepFinProductAssetBalanceDto;

/**
 *  资产余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月5日
 * @see RepFinProductAssetBalanceMapper
 * @since 1.0
 */
@Repository
public interface RepFinProductAssetBalanceMapper {

    List<RepFinProductAssetBalanceDto> queryForPage1(Map<String, Object> pageParam);
    
    List<RepFinProductAssetBalanceDto> queryForPage2(Map<String, Object> pageParam);
    
    List<RepFinProductAssetBalanceDto> queryForPage3(Map<String, Object> pageParam);
    
    List<RepFinProductAssetBalanceDto> queryForPage4(Map<String, Object> pageParam);
    
    List<RepFinProductAssetBalanceDto> selectAllList(Map<String, Object> pageParam);
}
