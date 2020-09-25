/*
 * 文件名：IndicatorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.indic.service;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicator;

/**
 * 指标信息Redis读写服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月15日
 * @see IndicatorService
 * @since 1.0
 */
@Service
public class IndicatorService extends RedisBaseService<IIndicator> {

    public Map<String, IIndicator> getIndicatorMap(Set<String> indIdList) {
        return getMapFromRedis(RedisKeyEnum.指标信息配置.key(), indIdList);
    }
}
