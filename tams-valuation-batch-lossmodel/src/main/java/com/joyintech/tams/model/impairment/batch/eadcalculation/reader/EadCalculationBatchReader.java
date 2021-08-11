package com.joyintech.tams.model.impairment.batch.eadcalculation.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eadcalculation.service.EadCalculationBatchReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EAD计算批处理Reader
 * @author zhangfei
 * @date 2021年7月14日16:51:15
 */
@Service
@Scope("prototype")
public class EadCalculationBatchReader extends BaseReader<EadCalculationReaderDto> implements StepExecutionListener {

    private StepExecution stepExecution;

    @Autowired
    private EadCalculationBatchReaderService eadCalculationBatchReaderService;

    @Override
    public List<EadCalculationReaderDto> createList() {

        Map<String, Object> param = new HashMap<String, Object>();
        param.putAll(this.getParams());
        param.put("executionContext",this.stepExecution.getJobExecution().getExecutionContext());
        param.put("decDate", this.getBatchExecuteInfo().getExecuteDate());
        return eadCalculationBatchReaderService.execute(param);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution  = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
