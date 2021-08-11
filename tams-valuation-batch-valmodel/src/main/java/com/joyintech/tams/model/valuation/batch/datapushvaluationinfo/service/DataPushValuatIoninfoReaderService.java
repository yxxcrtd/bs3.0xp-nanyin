package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.service;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dao.DataPushValuatIoninfoMapper;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:数据推送批处理Service
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class DataPushValuatIoninfoReaderService {
    @Autowired
    private DataPushValuatIoninfoMapper dataPushValuatIoninfoMapper;
    @Autowired
    private ModParamsService modParamsService;
    @Autowired
    private DataPushValuatIoninfoProcessService dataPushValuatIoninfoProcessService;
    /**
     * @Description:获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/5
     * @param:
     * @return:
     */
    public List<ValuationBatchSynDto> execute(Map<String, Object> mapParam, StepExecution stepExecution) {
        List<ValuationBatchSynDto> valuationBatchSynList = new ArrayList<>();
        Date plateDate = (Date)mapParam.get("PLATE_DATE");//平台日
        String taskId =(String)mapParam.get("TASK_ID");
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)mapParam.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        List<ModValBatchLogResult> bBatchLogResultList = new ArrayList<>();
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }
        valuationBatchSynList =dataPushValuatIoninfoMapper.selectValuationInfoList(DateUtil.format(plateDate),finprodIdList);
        if(CollectionUtils.isEmpty(valuationBatchSynList)){
           //=====记录计算错误日志=====start=====
            for(String finprodId:finprodIdList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(finprodId);
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());//EXP 失败
                batchParam.setMsg("【数据推送批处理】：计算错误");
                bBatchLogResultList.add(batchParam);
            }
            dataPushValuatIoninfoProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            return null;
            //=====记录计算错误日志=====end=====
        }
        return valuationBatchSynList;
    }




}
