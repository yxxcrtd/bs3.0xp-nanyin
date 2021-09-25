package com.joyintech.tams.model.valuation.batch.incomemethodmodel.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.common.base.entity.ModBatchLogs;
import com.joyintech.tams.common.base.entity.ModStockdivitrateConfig;
import com.joyintech.tams.common.base.entity.ModValBatchParams;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dao.IncomeMethodMapper;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ProductAssetsIncomeMethodReaderDto;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.component.batch.service.ModValuationCommonService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  收益法估值批处理Reader
 * @author  jinmaodong
 * @version 1.0
 * @since 20210830
 */
@Service
public class ProductAssetsIncomeMethodReaderService {

    @Resource
    private IncomeMethodMapper incomeMethodMapper;

    @Resource
    private ModValuationCommonService modValuationCommonService;

    @Resource
    private ModParamsService modParamsService;

    @Resource
    private ModLogsService modLogsService;

    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public List<ProductAssetsIncomeMethodReaderDto> createList(Map<String, Object> params, StepExecution stepExecution){
        Date plateDate = (Date)params.get(ValComponentConstant.EXECUTE_DATE);//平台日
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get(ValComponentConstant.BATCH_EXECUTEINFO);
        String taskId = batchExecuteInfo.getTaskId();
        List<ProductAssetsIncomeMethodReaderDto> productAssetsIncomeMethodReaderDtoList = new ArrayList<>();

        //1.初始化批处理参数
        List<ModValBatchParams> batchParamsList = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        List<String> finprodIdList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(batchParamsList)) {
            finprodIdList = batchParamsList.stream().map(x->x.getFinprodId()).collect(Collectors.toList());
        }
        ModAssetInfoParamsDto assetInfoParamsDto = new ModAssetInfoParamsDto();
        assetInfoParamsDto.setSearchDate(plateDate);    //查询日 = 平台日
        assetInfoParamsDto.setFinprodIdList(finprodIdList);

        //2.查询待处理资产及对应信息
        //2.1优先股、永续不含权债
        List<ModAssetInfoDto> preReaderDtoList = selectIncomeMethodDtoList(assetInfoParamsDto);

        List<String> assetIds = new ArrayList<>();

        //3.初始化日志
        if (CollectionUtils.isNotEmpty(preReaderDtoList)){
            assetIds = preReaderDtoList.stream().map((x)->x.getFinprodId()).collect(Collectors.toList());
//            modLogsService.batchInitLogs(taskId,batchExecuteInfo.getBatchExedetailSeqNo(), assetIds);
        }else{
            return productAssetsIncomeMethodReaderDtoList;
        }

        List<ModStockdivitrateConfig> modStockdivitrateConfigs = incomeMethodMapper.selectStockIntrateByFinprodIds(assetIds);
        Map<String,ModStockdivitrateConfig> modStockdivitrateConfigMap = new HashedMap<>();
        if(CollectionUtils.isNotEmpty(modStockdivitrateConfigs)){
            modStockdivitrateConfigMap = modStockdivitrateConfigs.stream().collect(Collectors.toMap(ModStockdivitrateConfig::getFinprodId, x->x));
        }

        //3.设定资产其他相关信息-数据组装
        for (ModAssetInfoDto methodReaderDto : preReaderDtoList){
            ProductAssetsIncomeMethodReaderDto productAssetsIncomeMethodReaderDto = new ProductAssetsIncomeMethodReaderDto();
            productAssetsIncomeMethodReaderDto.setModAssetInfoDto(methodReaderDto);

            //3.1 查询预期股息增长率配置

            if(modStockdivitrateConfigMap!=null&&modStockdivitrateConfigMap.containsKey(methodReaderDto.getFinprodId())){
                productAssetsIncomeMethodReaderDto.setModStockdivitrateConfig(modStockdivitrateConfigMap.get(methodReaderDto.getFinprodId()));
            }else{
                ModStockdivitrateConfig config = new ModStockdivitrateConfig();
                config.setFinprodId(methodReaderDto.getFinprodId());
                config.setStockIntrate(BigDecimal.ZERO);
                productAssetsIncomeMethodReaderDto.setModStockdivitrateConfig(config);
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),methodReaderDto.getFinprodId(), "【收益法模型批处理】：资产未配置预期股息增长率，默认使用0作为增长率计算.");
            }

            //3.2组装-资产现金流计划集合
            List<ModCashFlowDto> modCashFlowDtoList = modValuationCommonService.getCashFlowPlanInfoByFinprodId(methodReaderDto.getFinprodId());
            productAssetsIncomeMethodReaderDto.setFinCashPlanDtoList(modCashFlowDtoList);

            //3.3组装-现金流计算规则集合
            List<ModCashFlowCalRuleDto> modCashFlowCalRuleDtoList = modValuationCommonService.getCashFlowCalRulesByFinprodId(methodReaderDto.getFinprodId());
            productAssetsIncomeMethodReaderDto.setFinCashCalcRuleDtoList(modCashFlowCalRuleDtoList);

            //3.4组装-现金流支付规则集合
            List<ModCashFlowPayRulesDto> modCashFlowPayRulesList = modValuationCommonService.getCashFlowPayRulesByFinprodId(methodReaderDto.getFinprodId());
            productAssetsIncomeMethodReaderDto.setModCashFlowPayRulesList(modCashFlowPayRulesList);
            productAssetsIncomeMethodReaderDtoList.add(productAssetsIncomeMethodReaderDto);
        }

        return productAssetsIncomeMethodReaderDtoList;
    }

    /**
     * @Description:调用【资产基本信息查询-估值模型】方法查询有持仓的资产
     * @auther: yuzj
     * @date: 2021/7/7
     * @param:
     * @return:
     */
    public List<ModAssetInfoDto>  selectIncomeMethodDtoList(ModAssetInfoParamsDto dto){
        List<ModAssetInfoDto> modAssetInfoDtoList = modValuationCommonService.selectIncomeMethodAssetInfo(dto);
        return modAssetInfoDtoList;
    }

}
