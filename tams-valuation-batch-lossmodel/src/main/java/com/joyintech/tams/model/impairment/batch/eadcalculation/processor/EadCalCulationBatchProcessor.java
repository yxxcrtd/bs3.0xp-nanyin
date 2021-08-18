package com.joyintech.tams.model.impairment.batch.eadcalculation.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalCulationResultDto;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eadcalculation.service.EadCalCulationBatchProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

/**
 * EAD计算Processor
 * @author zhangfei
 * @date 2021年7月16日15:51:53
 */
@Service
@Scope("prototype")
public class EadCalCulationBatchProcessor extends BaseProcessor<NeedOperateEntity, EadCalCulationResultDto> implements StepExecutionListener {
    /**
     * processor方法的 Service
     */
    @Autowired
    private EadCalCulationBatchProcessorService eadCalCulationBatchProcessorService;

    private StepExecution stepExecution;

    @Override
    public EadCalCulationResultDto process(NeedOperateEntity item) throws Exception {

//        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
//        List<ModValBatchParams> modValBatchParamsList = (List<ModValBatchParams>)executionContext.get("params");

        EadCalculationReaderDto readerDto = (EadCalculationReaderDto)item;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, readerDto);
        thisParams.put(GlobalConstant.HAPPEN_DATE,  this.getBatchExecuteInfo().getExecuteDate());
        thisParams.put("executionContext",stepExecution.getJobExecution().getExecutionContext());
        EadCalCulationResultDto result = eadCalCulationBatchProcessorService.execute(thisParams);

        //当为null时候，给予初始化
        if(result == null){
            result = new EadCalCulationResultDto();
        }

        return result;
    }


    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
