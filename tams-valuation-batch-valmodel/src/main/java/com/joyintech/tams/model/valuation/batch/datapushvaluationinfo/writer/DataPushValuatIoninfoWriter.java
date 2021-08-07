/*
 * 文件名：ResetBaseRuleInfoWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.valuation.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynResultDto;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.service.DataPushValuatIoninfoProcessService;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:数据推送批处理Writer
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
@Scope("prototype")
public class DataPushValuatIoninfoWriter extends BaseWriter<ValuationBatchSynResultDto> implements StepExecutionListener {

    private final String BATCH_LOG_RESULTS = "logResults";
    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution=stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    private StepExecution stepExecution;


    /**
     *  数据推送批处理Service
     */
    @Autowired
    private DataPushValuatIoninfoProcessService dataPushValuatIoninfoProcessService;

    @Autowired
    private ModLogsService modLogsService;

    private final String BATCH_PARAMS_KEY = "params";
    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends ValuationBatchSynResultDto> items)
        throws Exception {
        /*@SuppressWarnings("unchecked")*/

        Map<String,Object> mapParam = this.getParams();
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)mapParam.get("BATCH_INFO");
        List<ValuationBatchSynResultDto> resultList = (List<ValuationBatchSynResultDto>)items;
        List<ModValBatchLogResult> bBatchLogResultList = new ArrayList<>();
        String taskId =(String)mapParam.get("TASK_ID");
        try {
            dataPushValuatIoninfoProcessService.sendValuationInfoResult(resultList);
            //=====记录成功日志=====start=====
            for(ValuationBatchSynResultDto resultDto: resultList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(resultDto.getFinprodId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_正常结束.getValue());//成功
                batchParam.setMsg("【估值模型数据推送批处理】执行批处理【" + resultDto.getProdId()+ "】成功结束！");
                bBatchLogResultList.add(batchParam);
            }
            dataPushValuatIoninfoProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录成功日志=====end=====
            System.out.println("====================test========writer========");
        } catch (Exception e) {
            //=====记录计算错误日志=====start=====
            for(ValuationBatchSynResultDto processDto:resultList){
                ModValBatchLogResult batchParam = new ModValBatchLogResult();
                batchParam.setFinprodId(processDto.getFinprodId());
                batchParam.setTaskId(taskId);
                batchParam.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                batchParam.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());//EXP 失败
                batchParam.setMsg("【估值模型数据推送批处理】：计算错误");
                bBatchLogResultList.add(batchParam);
            }
            dataPushValuatIoninfoProcessService.sendlogResults(bBatchLogResultList,stepExecution);
            //=====记录计算错误日志=====end=====
        }

    }


}