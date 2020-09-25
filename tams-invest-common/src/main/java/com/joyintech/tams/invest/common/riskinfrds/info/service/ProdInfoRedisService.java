/*
 * 文件名：ProdInfoRedisService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.info.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdInfo;

/**
 * 产品基本信息更新Redis服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月18日
 * @see ProdInfoRedisService
 * @since 1.0
 */
@Service
public class ProdInfoRedisService extends RedisBaseService<IProdInfo> {

    public Map<String, Object> getProdInfoAsMap(String prodId) {
        IProdInfo prodInfo = getInfoFromRedis(RedisKeyEnum.产品基本信息.key(), prodId);
        return prodInfo == null ? null : BeanUtil.object2Map(prodInfo);
    }

    public IProdInfo getProdInfo(String prodId) {
        return getInfoFromRedis(RedisKeyEnum.产品基本信息.key(), prodId);
    }
}
