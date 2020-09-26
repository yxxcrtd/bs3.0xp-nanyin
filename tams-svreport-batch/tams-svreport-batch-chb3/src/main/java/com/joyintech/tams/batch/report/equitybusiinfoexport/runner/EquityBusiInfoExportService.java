package com.joyintech.tams.batch.report.equitybusiinfoexport.runner;


import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.equitybusiinfoexport.service.EquityBusiInfoExportMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * 投资者明细信息登记导出
 *
 * @author zhangmingming
 * @version 1.0 2020年01月13日
 * @see EquityBusiInfoExportService
 * @since 1.0
 */
@Service(value = "equityBusiInfoExportService")
@Scope("prototype")
public class EquityBusiInfoExportService extends BatchBaseService {


    @Autowired
    private EquityBusiInfoExportMainService mainService;
    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        mainService.getExportData();
        result.setStatus(BatchStatus.COMPLETED);
        return result;

    }
}
