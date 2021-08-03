/*
 * 文件名：RiskIndConditionLineService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.redisinit.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dao.RiskIndConditionLineMapper;
import com.joyintech.tams.invest.common.redisinit.dto.ImplIndicator;
import com.joyintech.tams.invest.common.redisinit.dto.RiskIndConditionLine;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndividLimit;
import com.joyintech.tams.invest.common.risktrialrds.dao.SupervisionCalcDao;
import com.joyintech.tams.invest.common.risktrialrds.dto.SystemParam;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;

/**
 * 监督指标条件阈值Redis初始化-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年8月31日
 * @see RiskIndConditionLineService
 * @since 1.0
 */
@Service
public class RiskIndConditionLineService {

    public static final String QUOTE = "'";

    @Autowired
    private RiskIndConditionLineMapper mapper;

    @Autowired
    private SupervisionCalcDao supervisionCalcDao;

    /**
     * 取得 自定义条件阈值信息<b>Map<指标代码,Map<组合（包）代码,阈值线>></b>
     * 
     * @param sysDate 平台日期
     * @param indIdList 指标代码
     * @return 自定义条件阈值信息
     */
    public Map<String, Map<String, IIndividLimit>> getRiskIndConditionLine(Date sysDate,
                                                                           List<String> indIdList) {
        Map<String, Map<String, IIndividLimit>> resMap = new HashMap<>();
        // 取得自定义条件阈值信息
        List<RiskIndConditionLine> lines = selectIndConditionLine(indIdList);
        if (!CollectionUtils.isEmpty(lines)) {
            // 取得自定义条件值Map<条件项,Map<组合（包）代码,条件值>>
            Map<String, Map<String, BigDecimal>> conditionValMap = getConditionValMap(sysDate,
                lines);
            if (CollectionUtils.isEmpty(conditionValMap)) {
                return resMap;
            }
            lines.forEach(line -> {
                Map<String, IIndividLimit> limitMap = resMap.getOrDefault(line.getIndId(), new HashMap<>());
                if (null == conditionValMap.get(line.getConditionItem())) {
                    return;
                }
                IIndividLimit limit = initLimit(line, conditionValMap.get(line.getConditionItem()).get(line.getPrtBagId()));
                if (limit != null) {
                    limitMap.put(line.getPrtBagId(), limit);
                }
                resMap.put(line.getIndId(), limitMap);
            });
        }
        return resMap;
    }

    private IIndividLimit initLimit(RiskIndConditionLine line, BigDecimal val) {
        BigDecimal minVal = line.getValueStr();
        BigDecimal maxVal = line.getValueEnd();
        minVal = minVal == null ? null : BigDecimal.valueOf(
            RiskUtil.convertUnitToValue(line.getConditionUnit(), minVal.doubleValue()));
        maxVal = maxVal == null ? null : BigDecimal.valueOf(
            RiskUtil.convertUnitToValue(line.getConditionUnit(), maxVal.doubleValue()));
        if (CommonUtil.isType(line.getControlType(), BussinessDictCodeListRds.控制类型_范围控制)) {
            if (minVal == null || maxVal == null) {
                throw new BusinessException("自定义条件阈值范围控制时，必须输入最小值最大值。指标【" + line.getIndId()
                                            + "】,组合（包）【" + line.getPrtBagId() + "】,条件项【"
                                            + line.getConditionItem() + "】");
            }
            if (CommonUtil.compareV(val, minVal, BussinessDictCodeListRds.运算符_大于等于.getValue())
                && CommonUtil.compareV(val, maxVal,
                    BussinessDictCodeListRds.运算符_小于等于.getValue())) {
                return toLimit(line);
            }
        }
        else {
            if (minVal == null && maxVal == null) {
                throw new BusinessException(
                    "自定义条件阈值，必须输入比较值。指标【" + line.getIndId()
                                            + "】,组合（包）【" + line.getPrtBagId() + "】,条件项【"
                                            + line.getConditionItem() + "】");
            }
            if (RiskUtil.compareNumberVal("NUM", line.getControlType(), minVal, maxVal, val)) {
                return toLimit(line);
            }
        }
        return null;
    }

    private IIndividLimit toLimit(RiskIndConditionLine line) {
        if (!StringUtil.isEmpty(line.getWarnCond()) || !StringUtil.isEmpty(line.getFoulCond())
            || !StringUtil.isEmpty(line.getApproveCond())) {
            ImplIndicator limit = new ImplIndicator();
            limit.setWarnCond(line.getWarnCond());
            limit.setApproveCond(line.getApproveCond());
            limit.setFoulCond(line.getFoulCond());
            return limit;
        }
        return null;
    }

    public List<RiskIndConditionLine> selectIndConditionLine(List<String> indIdList) {
        return mapper.selectIndConditionLine(indIdList);
    }

    /**
     * 取出 所有条件项的条件值
     * 
     * @param sysDate 平台日期
     * @param lines 条件列表
     * @return 所有条件项的条件值Map<条件项,Map<组合（包）代码,条件值>
     */
    protected Map<String, Map<String, BigDecimal>> getConditionValMap(Date sysDate,
                                                                    List<RiskIndConditionLine> lines) {
        // Map<条件项代码, List<组合（包）代码>>
        Map<String, List<String>> conditionMap = new HashMap<>();
        // Map<条件项代码, 条件内容>
        Map<String, String> contentMap = lines.stream().filter(line -> CommonUtil.isType(
            line.getConditionType(), BussinessDictCodeListRds.指标参数实现类型_SQL)).collect(
                Collectors.toMap(
                    RiskIndConditionLine::getConditionItem, RiskIndConditionLine::getContent,
                    (oldVal, newVal) -> newVal));
        if (CollectionUtils.isEmpty(contentMap)) {
            return new HashMap<>();
        }
        lines.forEach(line -> {
            String item = line.getConditionItem();
            List<String> ptlList = conditionMap.getOrDefault(item, new ArrayList<>());
            ptlList.add(line.getPrtBagId());
            conditionMap.put(item, ptlList);
        });
        Map<String, Map<String, BigDecimal>> resMap = new HashMap<>();
        conditionMap.forEach(
            (key, val) -> {
                Map<String, BigDecimal> valMap = getConditionVal(sysDate, contentMap.get(key),
                    val);
                if (!CollectionUtils.isEmpty(valMap)) {
                    resMap.put(key, valMap);
                }
            });
        return resMap;
    }

    /**
     * 查询 条件值 <b>Map<组合（包）代码,条件值></b>
     * 
     * @param sysDate 平台日期
     * @param content 查询SQL
     * @param ptlList 组合（包）代码
     * @return 条件值
     */
    private Map<String, BigDecimal> getConditionVal(Date sysDate, String content,
                                                   List<String> ptlList) {
        // 构造参数条件
        SystemParam sysParam = new SystemParam();
        sysParam.setValDate(DateUtil.format(sysDate));
        // 拼接组合（包）代码作为查询条件
        if (!CollectionUtils.isEmpty(ptlList)) {
            StringBuilder builder = new StringBuilder(GlobalConstant.OPEN_PARENTHESIS);
            for (int i = 0; i < ptlList.size(); i++ ) {
                String ptl = ptlList.get(i);
                builder.append(QUOTE).append(ptl).append(QUOTE);
                if (i < ptlList.size() - 1) {
                    builder.append(GlobalConstant.COMMA);
                }
            }
            builder.append(GlobalConstant.CLOSE_PARENTHESIS);
            sysParam.setPortfolioCode(builder.toString());
        }

        List<Map<String, Object>> resList = supervisionCalcDao.selectOne(
            sysParam.replaceForString(content));
        Map<String, BigDecimal> resMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(resList)) {
            resList.forEach(map -> {
                Object key = map.get(RiskUtil.redisKey);
                Object val = map.get(RiskUtil.redisVal);
                if (StringUtil.isNotEmpty(key) && StringUtil.isNotEmpty(val)) {
                    resMap.put(key.toString(), new BigDecimal(val.toString()));
                }
            });
        }
        return resMap;
    }

    /**
     * 取得 自定义条件开启的组合关联指标
     * 
     * @param ptlList 组合（包）列表
     * @return 自定义条件开启的组合关联指标
     */
    public Map<String, Set<String>> getPtlIndMap(List<String> ptlList) {
        List<Map<String, Object>> list = selectEnabledInd(ptlList);
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        Map<String, Set<String>> resMap = new HashMap<>();
        list.forEach(map -> {
            Object key = map.get(RiskUtil.redisKey);
            Object val = map.get(RiskUtil.redisVal);
            if (StringUtil.isNotEmpty(key) && StringUtil.isNotEmpty(val)) {
                Set<String> indSet = resMap.getOrDefault(key.toString(), new HashSet<>());
                indSet.add(val.toString());
                resMap.put(key.toString(), indSet);
            }
        });
        return resMap;
    }

    public List<Map<String, Object>> selectEnabledInd(List<String> ptlList) {
        return mapper.selectDisabledInd(ptlList);
    }

}
