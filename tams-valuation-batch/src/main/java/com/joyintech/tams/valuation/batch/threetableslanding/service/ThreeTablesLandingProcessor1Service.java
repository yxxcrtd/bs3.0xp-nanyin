/*
 * 文件名：ExpReturnTaTradeBookProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.batch.threetableslanding.common.AssetLiabilityStatementEnum;
import com.joyintech.tams.valuation.batch.threetableslanding.common.AssetLiabilityStatementUtil;
import com.joyintech.tams.valuation.batch.threetableslanding.dao.ThreeTableLandingMapper;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.AssetLiabilityStatementDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingResultDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValTaskTodoDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 三表落地reader
 * 
 * @author weipeng
 * @version 1.0 2020年5月8日
 * @see ThreeTablesLandingProcessor1Service
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ThreeTablesLandingProcessor1Service {

    @Autowired
    private ThreeTableLandingMapper threeTableLandingMapper;

    /**
     * process 数据处理
     * 
     * @param param 传递参数
     * @return BookGenerateResultWithBokposition
     * @see
     * @since 1.0
     */
    public ThreeTableLandingResultDto execute(Map<String, Object> param) {
        // 获取reader返回值
        ValTaskTodoDto acctEntity = (ValTaskTodoDto)param.get(GlobalConstant.READER_RESULT);
        // 进行批处理需要将结果集封装到result
        ThreeTableLandingResultDto result = new ThreeTableLandingResultDto();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("booksetId", acctEntity.getBooksetId());
        params.put("balanceDate", acctEntity.getHappenDate());
        List<String> subjectNoList = new ArrayList<String>();
        // ======================= 资产类start =================================
        subjectNoList = AssetLiabilityStatementEnum.valuesfromDictCode(
            DictCodeList.科目类型_资产类);
        params.put("subjectNoList", subjectNoList);
        /** 增加根据财务四表模板类型查询配置 **/
        // 当天余额列表
        List<AssetLiabilityStatementDto> listDto = threeTableLandingMapper.selectBokBalance(
            params);

        /** 增加根据财务四表模板类型查询配置 start **/
        // 获得资产表样
        List<AssetLiabilityStatementDto> dtoList = AssetLiabilityStatementUtil.getAssetList();
        // 组装查询结果
        assembleDtoList(dtoList, listDto, true, acctEntity.getBooksetId(),
            acctEntity.getHappenDate());
        /** 增加根据财务四表模板类型查询配置 end **/
        // 处理后的结果添加到返回结果中
        result.setListAssetLiability(dtoList);
        // ======================= 资产类end =================================

        // ======================= 负债和所有者权益类start =================================
        subjectNoList = AssetLiabilityStatementEnum.valuesfromDictCode(
            DictCodeList.科目类型_负债类, DictCodeList.科目类型_所有者权益类);
        params.put("subjectNoList", subjectNoList);
        /** 增加根据财务四表模板类型查询配置 end **/
        // 当天余额列表
        listDto = threeTableLandingMapper.selectBokBalance(params);

        /** 增加根据财务四表模板类型查询配置 start **/
        // 获得负债和所有者权益报表表样
        dtoList = AssetLiabilityStatementUtil.getLiabilityInterestList();
        // 组装查询结果
        assembleDtoList(dtoList, listDto, false, acctEntity.getBooksetId(),
            acctEntity.getHappenDate());
        /** 增加根据财务四表模板类型查询配置 end **/
        // 处理后的结果添加到返回结果中
        result.getListAssetLiability().addAll(dtoList);
        // ======================= 负债和所有者权益类end =================================

        return result;
    }

    /**
     * 组装 DTOList
     * 
     * @param dtoList 表样
     * @param list 结果集
     * @param balanceType 操作余额类型
     * @param assetFlag
     * @return
     */
    private void assembleDtoList(List<AssetLiabilityStatementDto> dtoList,
                                 List<AssetLiabilityStatementDto> list, boolean assetFlag, String booksetId, Date happenDate) {
        for (AssetLiabilityStatementDto dto : dtoList) {
            dto.setBooksetId(booksetId);
            dto.setBalanceDate(happenDate);
            List<AssetLiabilityStatementEnum> subjectTypeList = dto.getSubjectTypeList();
            for (AssetLiabilityStatementDto item : list) {
                /** modify by yanya on 20190929 增加根据财务四表模板类型查询配置 start **/
                List<AssetLiabilityStatementEnum> enumList = AssetLiabilityStatementEnum.enumsfromValue(
                    item.getSubjectNo());
                /** modify by yanya on 20190929 增加根据财务四表模板类型查询配置 end **/
                enumList.retainAll(subjectTypeList);
                if (enumList.size() > 0) {
                    setInitOrEnd(dto, item, assetFlag);
                    dto.setSubjectNo(item.getSubjectNo());
                }
            }
        }
    }

    /**
     * 设置余额
     * 
     * @param dto 容器
     * @param item 结果项
     * @param balanceType 操作余额类型
     * @param assetFlag
     * @param dto.balFlag 期待方向
     * @param amtType 使用金额类型
     */
    private void setInitOrEnd(AssetLiabilityStatementDto dto, AssetLiabilityStatementDto item,
                              boolean assetFlag) {
        String amtType = dto.getUseAmtType();
        boolean specialFlag = false; // 特殊处理 表示
        if (AssetLiabilityStatementUtil.B_AMT.equals(amtType)) {
            // 应付账款: 设计变更，特殊处理。（单个项目下包含多子科目时，存在一个使用金额与其他不同）
            if (AssetLiabilityStatementEnum.资产类_其他资产_应付账款.getValue().equals(item.getSubjectNo())
                || AssetLiabilityStatementEnum.负债类_其他负债_应付账款.getValue().equals(
                    item.getSubjectNo())) {
                specialFlag = true;
                if (DictCodeList.余额方向_借方.getValue().equals(dto.getBalFlag())) {
                    item.setBalance(item.getbDAmt());
                }
                if (DictCodeList.余额方向_贷方.getValue().equals(dto.getBalFlag())) {
                    item.setBalance(item.getbCAmt());
                }
            }
            else {
                item.setBalance(item.getbAmt());
            }
        }
        // 资产、负债特殊项处理
        if (assetFlag) {
            if (inSpecialSubject(item.getSubjectNo())) {
                if (DictCodeList.余额方向_借方.getValue().equals(item.getBalFlag())) {
                    item.setBalance(item.getbAmt());
                }
                else {
                    item.setBalance(BigDecimal.ZERO);
                }
            }
        }
        else {
            if (inSpecialSubject(item.getSubjectNo())) {
                if (DictCodeList.余额方向_贷方.getValue().equals(item.getBalFlag())) {
                    item.setBalance(item.getbAmt());
                }
                else {
                    item.setBalance(BigDecimal.ZERO);
                }
            }
        }
        if (!StringUtils.equals(item.getBalFlag(), dto.getBalFlag())
            && AssetLiabilityStatementUtil.B_AMT.equals(amtType) && !specialFlag
            && !inSpecialSubject(item.getSubjectNo())) {// 实际余额方向与期望不同时 *-1
                                                        // (仅当取值为原币余额时)
            dto.setBalance(dto.getBalance().add(item.getBalance().negate()));
        }
        else {
            dto.setBalance(dto.getBalance().add(item.getBalance()));
        }
    }

    private boolean inSpecialSubject(String subjectNo) {
        return AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇买卖.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_衍生金融负债_外汇结售汇.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_衍生金融负债_金融掉期.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_衍生金融负债_远期投资.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_衍生金融负债_其他衍生工具.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_衍生金融负债_套期工具.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_衍生金融负债_被套期项目.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_应付证券清算款_证券清算款.getValue().equals(subjectNo)
               || AssetLiabilityStatementEnum.负债类_其他负债_应付账款.getValue().equals(subjectNo);
    }
}
