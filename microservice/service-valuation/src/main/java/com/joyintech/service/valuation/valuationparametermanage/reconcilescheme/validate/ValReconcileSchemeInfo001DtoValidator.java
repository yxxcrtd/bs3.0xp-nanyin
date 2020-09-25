/*
 * 文件名：ReconcileSchemeInfo001DtoValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeAssetConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo001Dto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeStructConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeSubjectConfigDto;


/**
 * 方案明细保存校验
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo002DtoValidator
 * @since 1.0
 */
@Component(value="serviceValReconcileSchemeInfo001DtoValidator")
public class ValReconcileSchemeInfo001DtoValidator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        // 单一科目允许误差，输入值需要绝对值处理，输入值>=0
        ValReconcileSchemeInfo001Dto infoDto = (ValReconcileSchemeInfo001Dto)value;
        if (infoDto.getErrorNumber() != null && infoDto.getErrorNumber().signum() < 0) {
            errorList.add(new ValidMessage("errorNumber", "单一科目允许误差不能小于0"));
        }
        if (infoDto.getMyStartRowNo() != null && infoDto.getMyStartRowNo() <= 0) {
            errorList.add(new ValidMessage("myStartRowNo", "本方数据起始行不能小于等于0"));
        }
        if (infoDto.getOthersideStartRowNo() != null && infoDto.getOthersideStartRowNo() <= 0) {
            errorList.add(new ValidMessage("othersideStartRowNo", "外部系统数据起始行不能小于等于0"));
        }
        if (StringUtil.isEmptyList(infoDto.getStructConfigList())) {
            errorList.add(new ValidMessage("结构映射不能为空"));
        }
        if (StringUtil.isEmptyList(infoDto.getSubjectConfigList())) {
            errorList.add(new ValidMessage("科目映射不能为空"));
        }
        // if (StringUtil.isEmptyList(infoDto.getAssetConfigList())) {
        // errorList.add(new ValidMessage("资产映射不能为空"));
        // }

        if (this.checkStructConfigList(infoDto.getStructConfigList())) {
            errorList.add(new ValidMessage("结构映射中存在一对多或多对一的映射关系，请确认"));
        }
        if (this.checkSubjectConfigList(infoDto.getSubjectConfigList())) {
            errorList.add(new ValidMessage("科目映射中存在多对多的映射关系，请确认"));
        }
        if (this.checkAssetConfigList(infoDto.getAssetConfigList())) {
            errorList.add(new ValidMessage("资产映射中存在一对多或多对一的映射关系，请确认"));
        }

        return errorList;
    }

    /**
     * 去重后查找一对多，多对一的情况
     * 
     * @param structList 结构映射
     * @return 存在一对多，多对一的情况返回true,否则false
     * @since 1.0
     */
    private boolean checkStructConfigList(List<ValReconcileSchemeStructConfigDto> structList) {
        if (StringUtil.isEmptyList(structList)) {
            return false;
        }
        structList = structList.stream().distinct().collect(Collectors.toList());
        // 区块类型+本方列号为key做成map
        Map<String, List<ValReconcileSchemeStructConfigDto>> myStructMap = structList.stream().collect(
            Collectors.groupingBy(subject -> subject.getBlockType() + "_" + subject.getMyColId()));
        for (Map.Entry<String, List<ValReconcileSchemeStructConfigDto>> entry : myStructMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                return true;
            }
        }

        // 区块类型+外部系统列号为key做成map
        Map<String, List<ValReconcileSchemeStructConfigDto>> othStructMap = structList.stream().collect(
            Collectors.groupingBy(subject -> subject.getBlockType() + "_" + subject.getOthersideColId()));

        for (Map.Entry<String, List<ValReconcileSchemeStructConfigDto>> entry : othStructMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验科目映射是否存在多对多的映射关系
     * 
     * @param subjectList 科目映射
     * @return 存在返回true，否则false
     * @since 1.0
     */
    private boolean checkSubjectConfigList(List<ValReconcileSchemeSubjectConfigDto> subjectList) {
        if (StringUtil.isEmptyList(subjectList)) {
            return false;
        }
        subjectList = subjectList.stream().distinct().collect(Collectors.toList());
        // 本方科目代码为key做成map
        Map<String, List<ValReconcileSchemeSubjectConfigDto>> mySubjectMap = subjectList.stream().collect(
            Collectors.groupingBy(subject -> subject.getMySubjectId()));
        // 外部系统科目代码为key做成map
        Map<String, List<ValReconcileSchemeSubjectConfigDto>> otherSubjectMap = subjectList.stream().collect(
            Collectors.groupingBy(subject -> subject.getOthersideSubjectId()));

        // 循环本方科目代码的map，若某科目代码对应的value的size大于1，
        // 用该value的对方科目代码从外部系统科目代码Map中取value,若size也大于1，则为多对多的情况
        for (Map.Entry<String, List<ValReconcileSchemeSubjectConfigDto>> entry : mySubjectMap.entrySet()) {
            List<ValReconcileSchemeSubjectConfigDto> tempSubjectList = entry.getValue();
            if (tempSubjectList.size() == 1) {
                continue;
            }
            for (ValReconcileSchemeSubjectConfigDto subject : tempSubjectList) {
                if (otherSubjectMap.get(subject.getOthersideSubjectId()).size() > 1) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * 去重后查找一对多，多对一的情况
     * 
     * @param assetList 资产映射
     * @return 存在一对多，多对一的情况返回true，否则false
     * @since 1.0
     */
    private boolean checkAssetConfigList(List<ValReconcileSchemeAssetConfigDto> assetList) {
        if (StringUtil.isEmptyList(assetList)) {
            return false;
        }

        assetList = assetList.stream().distinct().collect(Collectors.toList());
        // 本方资产代码为key做成map
        Map<String, List<ValReconcileSchemeAssetConfigDto>> myAssetMap = assetList.stream().collect(
            Collectors.groupingBy(subject -> subject.getMyAssetId()));
        for (Map.Entry<String, List<ValReconcileSchemeAssetConfigDto>> entry : myAssetMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                return true;
            }
        }

        // 外部系统资产代码为key做成map
        Map<String, List<ValReconcileSchemeAssetConfigDto>> othAssetMap = assetList.stream().collect(
            Collectors.groupingBy(subject -> subject.getOthersideAssetId()));
        for (Map.Entry<String, List<ValReconcileSchemeAssetConfigDto>> entry : othAssetMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                return true;
            }
        }

        List<ValReconcileSchemeAssetConfigDto> tempList = new ArrayList<ValReconcileSchemeAssetConfigDto>();
        for (ValReconcileSchemeAssetConfigDto asset : assetList) {
            if (tempList.contains(asset)) {
                return true;
            }
            else {
                tempList.add(asset);
            }
        }
        return false;
    }

}
