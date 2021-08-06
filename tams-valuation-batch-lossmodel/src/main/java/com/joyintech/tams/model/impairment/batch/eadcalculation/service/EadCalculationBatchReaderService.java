package com.joyintech.tams.model.impairment.batch.eadcalculation.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModCashFlowDto;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModValBatchParams;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModParamsService;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dao.EadCalculationBatchMapper;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadErrorParams;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadModEclResultProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EAD计算reader业务层
 * @author zhangfei
 * @date 2021年7月14日14:08:50
 */
@Service
@Scope("prototype")
public class EadCalculationBatchReaderService {

    private Logger logger = LoggerFactory.getLogger(EadCalculationBatchReaderService.class);
    @Autowired
    private EadCalculationBatchMapper eadCalculationBatchMapper;

    @Autowired
    private ModValuationCommonService modValuationCommonService;

    @Autowired
    private ModParamsService modParamsService;

    // Reader查询
    public List<EadCalculationReaderDto> execute(Map<String, Object> param){
        logger.info("---------EAD计算批处理reader开始------");
        SysBatchExecInfo batchExecuteInfo  = (SysBatchExecInfo)param.get("BATCH_INFO");
        SysBatchTask batchtask = (SysBatchTask)param.get("BATCHTASK");
        // 批处理上下文日志处理
        ExecutionContext executionContext = (ExecutionContext)param.get("executionContext");
        List<EadErrorParams> errors  = (List<EadErrorParams>)executionContext.get("ERRORS");
        if( errors == null){
            errors = new ArrayList<EadErrorParams>();
        }

        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }
        List<EadCalculationReaderDto> result = new ArrayList<EadCalculationReaderDto>();
        param.put("PS_T01", ModelDictCodeList.交易方向_买入.getValue());
        param.put("PS_T02", ModelDictCodeList.交易方向_卖出.getValue());
        param.put("PS_T03", ModelDictCodeList.交易方向_行权_回售.getValue());
        param.put("PS_T07", ModelDictCodeList.交易方向_到期.getValue());
        param.put("PS_T08", ModelDictCodeList.交易方向_还本.getValue());
        param.put("PS_T39", ModelDictCodeList.交易方向_行权_赎回.getValue());
//        param.put("batchSeqNo", batchExecuteInfo.getBatchExedetailSeqNo());
        param.put("MODEL_DEC_PROCESS_01", ModelDictCodeList.模型_减值过程_数据加载.getValue());
        param.put("MODEL_DEC_PROCESS_02", ModelDictCodeList.模型_减值过程_减值阶段判断.getValue());
        param.put("finprodIdList",finprodIdList);
        // 1.1 查询买入交易列表
        List<EadModEclResultProcess> tradeInList = eadCalculationBatchMapper.selectTradeInByDate(param);
        //1.2 查询持仓扣减交易列表
        List<EadModEclResultProcess>  tradeOutList = eadCalculationBatchMapper.selectTradeOutByDate(param);
        Map<String, Map<String, List<EadModEclResultProcess>>> tradeOutMap = new HashMap<String, Map<String, List<EadModEclResultProcess>>>();
        if(!CollectionUtils.isEmpty(tradeOutList)){
            tradeOutMap = tradeOutList.stream()
                    .collect(Collectors.groupingBy(EadModEclResultProcess::getPortfolioId, Collectors.groupingBy(EadModEclResultProcess::getAssetId)));
        }
        if(!CollectionUtils.isEmpty(tradeInList)){
            Map<String, Map<String, List<EadModEclResultProcess>>> tradeInMap = tradeInList.stream()
                    .collect(Collectors.groupingBy(EadModEclResultProcess::getPortfolioId, Collectors.groupingBy(EadModEclResultProcess::getAssetId)));
            // 2 将第1步获取的交易信息列表按照(组合代码+资产代码)为Key的方式定义List<EadCalculationReaderDto>
            for(Map.Entry<String, Map<String, List<EadModEclResultProcess>>> k: tradeInMap.entrySet()){
                for(Map.Entry<String, List<EadModEclResultProcess>> k1 : k.getValue().entrySet()){
                    EadCalculationReaderDto tempDto = new EadCalculationReaderDto();
                    tempDto.setPortfolioId(k.getKey());
                    tempDto.setAssetId(k1.getKey());
                    tempDto.setModelAssetType(k1.getValue().get(0).getModelAssetType());
                    tempDto.setMdate(k1.getValue().get(0).getMdate());
                    tempDto.setVdate(k1.getValue().get(0).getVdate());
                    tempDto.setIssuePrice(isNullBigDecimal(k1.getValue().get(0).getIssuePrice()));
                    // 如果 证券品种为债券
                    if(ModelDictCodeList.模型_证券品种_债券.getValue().equals(k1.getValue().get(0).getType8())){
                        tempDto.setCouponSpecies(k1.getValue().get(0).getCouponSpecies());
                    }else{// 如果 证券品种为其他
                        tempDto.setCouponSpecies(ModelDictCodeList.息票品种_附息.getValue());
                    }
                    tempDto.setTradeInList(k1.getValue());
                    if(!CollectionUtils.isEmpty(tradeOutMap)&& !CollectionUtils.isEmpty(tradeOutMap.get(k.getKey()))){
                        tempDto.setTradeOutList(tradeOutMap.get(k.getKey()).get(k1.getKey()));
                    }
                    result.add(tempDto);
                }
            }
        }
        // 3、设置资产其他信息
        for (EadCalculationReaderDto item : result){
            List<ModCashFlowDto> cashFlowList = modValuationCommonService.getCashFlowPlanInfoByFinprodId(item.getAssetId());
            if(cashFlowList==null || cashFlowList.size()==0){//
                logger.error("【EAD计算批处理】：资产无未来现金流");
                EadErrorParams eadErrorParams = new EadErrorParams();
                eadErrorParams.setFinprodId(item.getAssetId());
                eadErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
                eadErrorParams.setErrorMsg("【EAD计算批处理】：资产无未来现金流");
                errors.add(eadErrorParams);
                executionContext.put("ERRORS",errors);
            }else{
                item.setCashFlowList(cashFlowList);
            }
            // 2、现金流计算规则查询
            item.setCashFlowCalRuleList(modValuationCommonService.getCashFlowCalRulesByFinprodId(item.getAssetId()));
            // 3、现金流支付规则
            item.setModCashFlowPayRulesList(modValuationCommonService.getCashFlowPayRulesByFinprodId(item.getAssetId()));
        }
        // 过滤出含有未来现金流的资产DTO
        result = result.stream().filter(item-> item.getCashFlowList()!=null && item.getCashFlowList().size()>0).collect(Collectors.toList());
        logger.info("---------EAD计算批处理reader结束------");
        return result;
    }

    private BigDecimal isNullBigDecimal(Object obj){
        if(obj == null || "".equals(obj)){
            return BigDecimal.ZERO;
        }else{
            return (BigDecimal) obj;
        }
    }
}
