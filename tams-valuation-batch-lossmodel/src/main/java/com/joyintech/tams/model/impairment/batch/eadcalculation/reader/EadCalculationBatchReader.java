package com.joyintech.tams.model.impairment.batch.eadcalculation.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalculationReaderDto;
import com.joyintech.tams.model.impairment.batch.eadcalculation.service.EadCalculationBatchReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        // 普华方案只查询买入交易的数据
        List<EadCalculationReaderDto> listResult = new ArrayList<>();
        // 减值方案系统参数
        String lossType = CacheUtil.getSysParam(DictCode.减值方案类型.getValue());
        // 只有普华处理EAD计算，毕马威依赖于日终账面，模型中不计算EAD
        if (DictCodeListExt3.减值方案类型_普华.getValue().equals(lossType)) {
            listResult = eadCalculationBatchReaderService.execute(param);
        }
        return listResult;
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
