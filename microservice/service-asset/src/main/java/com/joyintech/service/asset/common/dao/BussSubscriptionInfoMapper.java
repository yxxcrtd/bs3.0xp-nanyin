/*
 * 文件名：BussSubscriptionInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;

/**
 *  认购信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see BussSubscriptionInfoMapper
 * @since 1.0
 */
@Repository
public interface BussSubscriptionInfoMapper {

    List<FinSubscriptionInfo> getBussSubscriptionInfoByProductId(@Param("finprodId")String finprodId);
    
    int deleteByProductId(@Param("finprodId")String finprodId);
}
