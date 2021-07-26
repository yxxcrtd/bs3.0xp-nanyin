/*
 * 文件名：RskIndExprDtlService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.dto.RskIndExprDtlDto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.service.RskSupervisionIndicator001Service;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.service.RskSupervisionIndicator002Service;
import com.joyintech.framework.constant.GlobalConstant;

/**
 * 监控值计算明细
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月28日
 * @see RskIndExprDtlService
 * @since 1.0
 */
@Service
public class RskIndExprDtlService {

    public static final String PARA_STR = "${";

    public static final String PARA_END = "}";

    @Autowired
    private RskSupervisionIndicator002Service indService;

    @Autowired
    private RskSupervisionIndicator001Service rskSupervisionIndicator001Service;

    public List<RskIndExprDtlDto> initList(String indId, String indName, String mathProcess) {
        List<RskIndExprDtlDto> paramList = getParamList(indId, indName);
        Map<String, Object> mathMap = JsonUtils.stringToMap(mathProcess);
        setParamVal(paramList, mathMap);
        return paramList;
    }

    public List<SelectOptionDto> getParamSelect() {
        List<SelectOptionDto> resList = rskSupervisionIndicator001Service.getIndicatorParamList();
        return resList == null ? new ArrayList<>() : resList;
    }

    /**
     * 设置参数值
     * 
     * @param list 参数列表
     * @param param 参数值
     */
    public void setParamVal(List<RskIndExprDtlDto> list, Map<String, Object> param) {
        if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(param)) {
            return;
        }
        list.forEach(item -> {
            Object val = param.get(item.getPara());
            if (StringUtil.isNotEmpty(val)) {
                item.setParaVal(val.toString());
            }
        });
    }

    public void setParaStr(List<RskIndExprDtlDto> paramList, List<SelectOptionDto> select) {
        if (CollectionUtils.isEmpty(paramList) || CollectionUtils.isEmpty(select)) {
            return;
        }
        Map<String, Object> param = select.stream().collect(
            Collectors.toMap(SelectOptionDto::getId, SelectOptionDto::getText));
        paramList.forEach(item -> {
            Object val = param.get(item.getPara());
            if (StringUtil.isNotEmpty(val)) {
                item.setParaStr(val.toString());
            }
        });
    }

    /**
     * 根据指标名称、指标代码取出对应的指标表达式并生成参数列表
     * 
     * @param indId 指标代码
     * @param indName 指标名称
     * @return 参数列表
     */
    public List<RskIndExprDtlDto> getParamList(String indId, String indName) {
        RskSupervisionIndicator002Dto dto = indService.getBussinessEntityByIndId(indId);
        if (null != dto) {
            return createParamList(dto.getExpr());
        }
        return new ArrayList<>();
    }

    /**
     * 根据表达式解析出参数
     * 
     * @param expr 表达式
     * @return 参数列表
     */
    protected List<RskIndExprDtlDto> createParamList(String expr) {
        List<RskIndExprDtlDto> resList = new ArrayList<>();
        if (!StringUtil.isNotEmpty(expr)) {
            return resList;
        }
        String[] arr = StringUtil.split(expr, PARA_END);
        if (arr == null || arr.length == 0) {
            return resList;
        }
        for (String str : arr) {
            RskIndExprDtlDto dto = createDto(str);
            if (dto != null) {
                resList.add(dto);
            }
        }
        return resList;
    }

    /**
     * 根据字符串解析参数代码
     * 
     * @param expr 【(${XJGM】、【${DYZCGM】、【)】
     * @return 参数代码信息
     */
    protected RskIndExprDtlDto createDto(String expr) {
        // 空字符串返回null
        if (StringUtil.isEmpty(expr)) {
            return null;
        }
        RskIndExprDtlDto dto = new RskIndExprDtlDto();
        // 根据符号分组
        String[] arr = StringUtil.split(expr, PARA_STR);
        if (arr != null && arr.length > 0) {
            // 一个元素时判断是否最后一个括号
            if (arr.length == 1 && StringUtil.equals(arr[0], GlobalConstant.CLOSE_PARENTHESIS)) {
                dto.setOper(arr[0]);
                dto.setPara("");
            }
            // 不是最后一个括号则按照前符号后参数的规则，只有一个时则为参数
            else {
                dto.setOper(arr.length == 1 ? "" : arr[0]);
                dto.setPara(arr.length == 1 ? arr[0] : arr[1]);
            }
            return dto;
        }
        return null;
    }
}
