package com.joyintech.tams.batch.report.investorsholdinfoexport.runner;


import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.investorsholdinfoexport.service.InvestorsHoldInfoExportMainService;


/**
 * 投资者持有信息登记导出批处理
 *
 * @author shaohuchen
 * @version 1.0 2020年05月08日
 * @see InvestorsHoldInfoExportService
 * @since 1.0
 */
@Service(value = "investorsHoldInfoExportService")
@Scope("prototype")
public class InvestorsHoldInfoExportService extends BatchBaseService {


    @Autowired
    private InvestorsHoldInfoExportMainService mainService;
    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        mainService.getExportData();
        result.setStatus(BatchStatus.COMPLETED);
        return result;

    }
}
