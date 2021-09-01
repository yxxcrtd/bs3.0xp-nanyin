package com.joyintech.tams.valuation.model.common.rateconfig.service;

import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import com.joyintech.tams.common.base.entity.ModAssetGradeSpecialConfig;
import com.joyintech.tams.valuation.model.common.rateconfig.dao.RateConfigCommonMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 模型用的评级配置查询共通
 */
@Service
public class RateConfigCommonService {

    @Autowired
    RateConfigCommonMapper rateConfigCommonMapper;

    /**
     * 查询所有的资产大类评级配置数据
     * 返回信息：证券品种+证券细分类为key
     * @return
     */
    public Map<String, ModAssetGradeConfig> listAssetGradeConfigs() {
        List<ModAssetGradeConfig> list = rateConfigCommonMapper.listAssetGradeConfigs();
        Map<String, ModAssetGradeConfig> result = new HashedMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            result = list.stream().collect(Collectors.toMap(x->x.getAssetType()+x.getAssetDetailType(), y -> y));
        }
        return result;
    }

    /**
     * 查询所有的特殊资产评级配置数据
     * 返回信息：资产代码为key
     * @return
     */
    public Map<String, ModAssetGradeSpecialConfig> listAssetSpecialGradeConfigs() {
        List<ModAssetGradeSpecialConfig> list = rateConfigCommonMapper.listAssetSpecialGradeConfigs();
        Map<String, ModAssetGradeSpecialConfig> result = new HashedMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            result = list.stream().collect(Collectors.toMap(ModAssetGradeSpecialConfig::getFinprodId, y -> y));
        }
        return result;
    }

}
