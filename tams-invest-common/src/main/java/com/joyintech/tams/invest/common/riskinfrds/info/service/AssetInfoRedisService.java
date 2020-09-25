/*
 * 文件名：AssetInfoRedisService.java
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
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetInfo;

/**
 * 资产基本信息更新Redis服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月18日
 * @see AssetInfoRedisService
 * @since 1.0
 */
@Service
public class AssetInfoRedisService extends RedisBaseService<IAssetInfo> {

    public Map<String, Object> getAssetInfoAsMap(String prodId) {
        IAssetInfo assetInfo = getInfoFromRedis(RedisKeyEnum.资产基本信息.key(), prodId);
        return assetInfo == null ? null : BeanUtil.object2Map(assetInfo);
    }
}
