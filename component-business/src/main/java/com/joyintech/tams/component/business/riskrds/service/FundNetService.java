/*
 * 文件名：FundNetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2020年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.joyintech.framework.util.StringUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.redisinit.dao.RskRedisInitMapper;
import com.joyintech.tams.invest.common.redisinit.dto.ImplFundValuation;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IFundValuation;
import com.joyintech.tams.invest.common.riskinfrds.info.service.FundValuationService;

/**
 * 基金净值查询Service
 * 
 * @author Administrator
 * @version 1.0 2020年9月11日
 * @see FundNetService
 * @since 1.0
 */
@Service
public class FundNetService {

    @Autowired
    private RskRedisInitMapper initMapper;

    /**
     * 基金估值行情
     */
    @Autowired
    private FundValuationService fundValSrc;

    /**
     * 取得当日最新基金净值
     * 
     * @param assetCode 基金代码
     * @return 当日最新基金净值
     */
    public BigDecimal getValuation(String assetCode) {
        // 从Redis取出最新净值
        BigDecimal valuation = fundValSrc.getFundValuation(assetCode);
        if (null != valuation) {
            return valuation;
        }
        List<String> assetList = new ArrayList<>();
        assetList.add(assetCode);
        Map<String, BigDecimal> map = getValuation(assetList, DateUtil.platDateFromCache());
        if (CollectionUtils.isEmpty(map)) {
            return BigDecimal.ONE;
        }
        return map.getOrDefault(assetCode, BigDecimal.ONE);
    }

    public Map<String, BigDecimal> getValuationMap(Collection<String> assetList) {
        if (CollectionUtils.isEmpty(assetList)) {
            return new HashMap<>();
        }
        Map<String, IFundValuation> infoMap = fundValSrc.getMapFromRedis(RedisKeyEnum.基金估值行情信息.key(), assetList);
        infoMap = CommonUtil.mapFilter(infoMap, en -> en != null && en.getValue() != null);
        if (!CollectionUtils.isEmpty(infoMap)) {
            return infoMap.entrySet().stream().collect(
                Collectors.toMap(Entry::getKey, en -> en.getValue().getValuation()));
        }
        return getValuation(assetList.stream().collect(Collectors.toList()),
            DateUtil.platDateFromCache());
    }

    /**
     * 取得截至指定日期的最新基金净值
     * 
     * @param assetCode 基金代码
     * @param valDate 指定日期
     * @return 截至指定日期的最新基金净值
     */
    public Map<String, BigDecimal> getValuation(List<String> assetList, Date valDate) {
        if (CollectionUtils.isEmpty(assetList)) {
            return new HashMap<>();
        }
        // 从库里取指定日期的净值
        Map<String, Object> map = new HashMap<>();
        map.put("condList", assetList);
        map.put("valDate", valDate);
        List<ImplFundValuation> valLsit = initMapper.selectFundVal(map);
        if (!StringUtil.isEmptyList(valLsit)) {
            return valLsit.stream().collect(Collectors.toMap(ImplFundValuation::getAssetCode,
                ImplFundValuation::getValuation));
        }
        return null;
    }
}
