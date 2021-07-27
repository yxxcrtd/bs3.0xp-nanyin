/*
 * 文件名：IndicatorTrendService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年8月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.indic.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicatorTrend;

/**
 * 指标趋势信息-Redis读写Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年8月11日
 * @see IndicatorTrendService
 * @since 1.0
 */
@Service
public class IndicatorTrendService extends RedisBaseService<IIndicatorTrend> {

    public Map<String, IIndicatorTrend> getIndicatorTrend(String portfolioCode,
                                                          Collection<String> indCodeList) {
        if (StringUtil.isEmpty(portfolioCode) || CollectionUtils.isEmpty(indCodeList)) {
            return new HashMap<>();
        }
        return getMapFromRedis(RedisKeyEnum.指标趋势信息.key(), //
            indCodeList.stream().map(indCode -> //
            indCode + GlobalConstant.COLON + portfolioCode).collect(Collectors.toList()));
    }
}
