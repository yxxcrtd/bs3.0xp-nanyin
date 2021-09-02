/*
 * 文件名：AssetIntdateCarryoverReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.cashflowdiscount.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dao.CashFlowDiscountMapper;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.CashFlowDiscountReaderResult;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.ModCurveConfig;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.ModCurveDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.component.batch.service.ModValuationCommonService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 现金流贴现批处理- reader相关业务处理
 *
 * @author zznn
 * @date 20210706
 */
@Service
@Scope("prototype")
public class CashFlowDiscountReaderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ModValuationCommonService modValuationCommonService;
    /**
     * reader方法的 Service
     */
    @Autowired
    private CashFlowDiscountMapper cashFlowDiscountMapper;

    @Autowired
    private ModParamsService modParamsService;

    @Autowired
    private ModLogsService modLogsService;

    /**
     * 获取数据库数据
     *
     * @param param 传递参数
     * @return List<BookDetailCompareIntoReadResult>
     * @see
     * @since 1.0
     */
    public List<CashFlowDiscountReaderResult> execute(Map<String, Object> param) {
        List<CashFlowDiscountReaderResult> result = new ArrayList<>();
        Date happenDate = (Date) param.get("HAPPEN_DATE"); // 发生日：批处理任务执行信息.处理日（发生日）
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo) param.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());

        //1.读取画面跑批选择的资产
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            finprodIdList = listBatch.stream().map(x -> x.getFinprodId()).collect(Collectors.toList());
        }
        param.put("finprodIdList", finprodIdList);

        ModAssetInfoParamsDto modAssetInfoParamsDto = new ModAssetInfoParamsDto();
        modAssetInfoParamsDto.setSearchDate(happenDate);
        modAssetInfoParamsDto.setFinprodIdList(finprodIdList);

        //2.查询贴现法配置数据
        //此处查询出来的是所有的配置
        List<ModCurveDto> allModCurve = cashFlowDiscountMapper.getAllModCurve(modAssetInfoParamsDto);
        Map<String,ModCurveDto> configIndex = allModCurve.stream().collect(Collectors.toMap(x->x.getSecTypeModel()+x.getSecDetailTypeModel(),(y)->y));

        //3.查询待处理资产：
        // 估值方法配置为模型法的
        // 债券中剔除可转债、可交换债、优先股
        // 其他债权
        List<ModAssetInfoDto> pendingModAssetInfo = modValuationCommonService.selectAssetInfo(modAssetInfoParamsDto);

        if(CollectionUtils.isEmpty(pendingModAssetInfo)){
            //待处理资产为空，无需处理
            return result;
        }else{
            //初始化日志
            modLogsService.batchInitLogs(batchExecuteInfo.getTaskId(),batchExecuteInfo.getBatchExedetailSeqNo(),pendingModAssetInfo.stream().map(x->x.getFinprodId()).collect(Collectors.toList()));

            if(CollectionUtils.isEmpty(allModCurve)){
                pendingModAssetInfo.forEach(x->{
                    modLogsService.error(x.getFinprodId(),batchExecuteInfo.getBatchExedetailSeqNo(),batchExecuteInfo.getTaskId(),"现金流贴现模型缺少配置，无法继续.");
                });
                return result;
            }else{
                //处理result
                pendingModAssetInfo.stream().forEach(item -> {
                    CashFlowDiscountReaderResult cashFlowDiscountReaderResult = new CashFlowDiscountReaderResult();
                    cashFlowDiscountReaderResult.setModAssetInfoDto(item);
                    String key = item.getSecType()+item.getSecTypeDetail();
                    if(configIndex.containsKey(key)){
                        cashFlowDiscountReaderResult.setModCurveConfig(configIndex.get(key));
                        //查找现金流计划信息
                        List<ModCashFlowDto> modCashFlows = modValuationCommonService.getCashFlowPlanInfoByFinprodId(item.getFinprodId());
                        cashFlowDiscountReaderResult.setModCashFlows(modCashFlows);
                        //查找现金流计算规则信息
                        List<ModCashFlowCalRuleDto> modCashFlowCalRules = modValuationCommonService.getCashFlowCalRulesByFinprodId(item.getFinprodId());
                        cashFlowDiscountReaderResult.setModCashFlowCalRules(modCashFlowCalRules);
                        //查找现金流支付规则
                        List<ModCashFlowPayRulesDto> modCashFlowPayRules = modValuationCommonService.getCashFlowPayRulesByFinprodId(item.getFinprodId());
                        cashFlowDiscountReaderResult.setModCashFlowPayRules(modCashFlowPayRules);

                        //查找评级信息
                        ModRateParamsDto modRateParamsDto = new ModRateParamsDto();
                        if (null != cashFlowDiscountReaderResult.getModCurveConfig()) {
                            modRateParamsDto.setModSecType(cashFlowDiscountReaderResult.getModCurveConfig().getSecTypeModel());
                            if (StringUtil.equals(modRateParamsDto.getModSecType(), DictCodeListExt1.证券品种_债券.getValue())) {
                                modRateParamsDto.setRateId(item.getFinprodMarketId());
                            } else if (StringUtil.equals(modRateParamsDto.getModSecType(), DictCodeListExt1.证券品种_其他债权类.getValue())) {
                                //TODO 其他债权评级主体待定
                                modRateParamsDto.setRateId(item.getIssueId());
                            }
                        }
                        modRateParamsDto.setSearchDate(happenDate);
                        ModRateDataDto rateInfo = modValuationCommonService.getRateInfo(modRateParamsDto);

                        //处理曲线信息
                        ModCurveConfig modCurveConfig = cashFlowDiscountReaderResult.getModCurveConfig();
                        Map<String, Object> paramCurveCode = new HashMap<>();
                        paramCurveCode.put("curveGroupCode", modCurveConfig.getCurveGroupCode());
                        if (StringUtil.equals(modCurveConfig.getCurveType(), DictCodeList.收益率曲线类型_SP即期.getValue())) {
                            paramCurveCode.put("curveType", "SP");
                        } else if (StringUtil.equals(modCurveConfig.getCurveType(), DictCodeList.收益率曲线类型_MA远期.getValue())) {
                            paramCurveCode.put("curveType", "MA");
                        } else if (StringUtil.equals(modCurveConfig.getCurveType(), DictCodeList.收益率曲线类型_FS远期的即期.getValue())) {
                            paramCurveCode.put("curveType", "FS");
                        } else if (StringUtil.equals(modCurveConfig.getCurveType(), DictCodeList.收益率曲线类型_FM远期的到期.getValue())) {
                            paramCurveCode.put("curveType", "FM");
                        }
                        if (null != rateInfo) {
                            paramCurveCode.put("graderesult", rateInfo.getGraderesult());
                        }
                        List<String> curveCode = cashFlowDiscountMapper.getCurveCode(paramCurveCode);

                        cashFlowDiscountReaderResult.setModRateData(rateInfo);
                        cashFlowDiscountReaderResult.setCurveCodes(curveCode);

                        result.add(cashFlowDiscountReaderResult);
                    }else{
                        modLogsService.error(item.getFinprodId(),batchExecuteInfo.getBatchExedetailSeqNo(),batchExecuteInfo.getTaskId(),"现金流贴现模型缺少配置，无法继续.");
                    }




                });
            }
        }




//        //贴现法曲线配置集合
//        Map<String, ModCurveConfig> modCurveConfigMap = new HashMap<>();
//        //贴现法曲线配置集合_资产
//        Map<String, ModCurveConfig> modCurveConfigAssetMap = new HashMap<>();
//        //资产五级分类集合
//        List<String> assetFives = new ArrayList<>();
//        //待查询资产代码集合
//        List<String> finprodIds = new ArrayList<>();
//        if (null != allModCurve && allModCurve.size() > 0) {
//            allModCurve.stream().forEach(item -> {
//                if (StringUtil.isNotEmpty(item.getFinprodId())) {
//                    finprodIds.add(item.getFinprodId());
//                    modCurveConfigAssetMap.put(item.getAssetFive() + item.getFinprodId(), item);
//                } else {
//                    modCurveConfigMap.put(item.getAssetFive(), item);
//                    assetFives.add(item.getAssetFive());
//                }
//            });
//        }
//
//        //调用资产基本信息查询_估值模型
//        if (assetFives.size() > 0) {
//            ModAssetInfoParamsDto modAssetInfoParamsDto = new ModAssetInfoParamsDto();
////                modAssetInfoParamsDto.setAssetFiveList(assetFives);
//            modAssetInfoParamsDto.setSearchDate(happenDate);
//            pendingModAssetInfo.addAll(modValuationCommonService.selectAssetInfo(modAssetInfoParamsDto));
//        }
//        if (finprodIds.size() > 0) {
//            ModAssetInfoParamsDto modAssetInfoParamsDto = new ModAssetInfoParamsDto();
//            modAssetInfoParamsDto.setFinprodIdList(finprodIds);
//            modAssetInfoParamsDto.setSearchDate(happenDate);
//            pendingModAssetInfo.addAll(modValuationCommonService.selectAssetInfo(modAssetInfoParamsDto));
//        }
//


        return result;
    }
}
