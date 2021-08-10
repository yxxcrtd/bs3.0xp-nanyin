package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dao.DataPushValuatIoninfoMapper;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynDto;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynParam;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynResultDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:数据推送批处理Service
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class DataPushValuatIoninfoProcessService extends BaseService {
    private final String BATCH_LOG_RESULTS = "logResults";
    @Autowired
    private DataPushValuatIoninfoMapper dataPushValuatIoninfoMapper;

    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public ValuationBatchSynResultDto execute(ValuationBatchSynDto item, Map<String, Object> mapParam) {
        //数据推送(调用投管微服务【估值模型结果同步】)
        ValuationBatchSynResultDto valuationBatchSynResultDto = new ValuationBatchSynResultDto();
        BeanUtils.copyProperties(item,valuationBatchSynResultDto);

        return valuationBatchSynResultDto;
    }

    /**
     * @Description:数据推送
     * @auther: yuzj
     * @date: 2021/7/12
     * @param:
     * @return:
     */
    public Integer  sendValuationInfoResult(List<ValuationBatchSynResultDto> items){
        ValuationBatchSynParam param = new ValuationBatchSynParam();
        List<ValuationBatchSynDto> valuationBatchSynList = new ArrayList<>();
        for(ValuationBatchSynResultDto synResultDto :items){
            ValuationBatchSynDto synDto = new ValuationBatchSynDto();
            BeanUtils.copyProperties(synResultDto,synDto);
            valuationBatchSynList.add(synDto);
        }
        param.setSynList(valuationBatchSynList);
        Integer result = restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/ModelCallOnInvest/valModelResultSyn", param,
                Integer.class);
        return result;
    }


    /**
     * @Description:保存上下文日志信息
     * @auther: yuzj
     * @date: 2021/7/27
     * @param:
     * @return:
     */
    public  void  sendlogResults(List<ModValBatchLogResult> bBatchLogResultList, StepExecution stepExecution){
        if(CollectionUtils.isNotEmpty(bBatchLogResultList)){
            if( stepExecution.getJobExecution().getExecutionContext().get("BATCH_LOG_RESULTS") !=null){
                List<ModValBatchLogResult> oldLogResultList = (List<ModValBatchLogResult>)stepExecution.getJobExecution().getExecutionContext().get("BATCH_LOG_RESULTS");
                oldLogResultList.addAll(bBatchLogResultList);
                stepExecution.getJobExecution().getExecutionContext().put(BATCH_LOG_RESULTS,oldLogResultList);
            }else{
                stepExecution.getJobExecution().getExecutionContext().put(BATCH_LOG_RESULTS,bBatchLogResultList);
            }
        }
    }



}
