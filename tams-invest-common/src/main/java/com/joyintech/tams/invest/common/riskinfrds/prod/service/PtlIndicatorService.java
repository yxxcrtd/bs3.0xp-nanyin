/*
 * 文件名：PortfolioIndicatorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.prod.service;

import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.prod.entity.IPtlIndicator;

/**
 * 指标关联组合信息读写服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月15日
 * @see PtlIndicatorService
 * @since 1.0
 */
@Service
public class PtlIndicatorService extends RedisBaseService<IPtlIndicator> {

    /**
     * 根据组合代码取得组合关联指标关系
     * 
     * @param ptlCode 组合代码
     * @return 组合关联指标关系
     * @see
     * @since 1.0
     */
    public IPtlIndicator getPtlIndicator(String ptlCode) {
        return getInfoFromRedis(RedisKeyEnum.组合关联指标配置.key(), ptlCode);
    }
}
