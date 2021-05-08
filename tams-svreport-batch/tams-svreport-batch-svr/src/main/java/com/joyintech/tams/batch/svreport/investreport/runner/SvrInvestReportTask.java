package com.joyintech.tams.batch.svreport.investreport.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.svreport.investreport.service.SvrInvestReportMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 中债报告生成批处理任务
 */
@Service(value = "svrInvestReportTask")
@Scope("prototype")
public class SvrInvestReportTask extends BatchBaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SvrInvestReportMainService svrInvestReportMainService;

    @Override
    public JobExecution execute() {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate = new Date();
        Long bt = bdate.getTime();
        logger.info("中债报告生成批处理启动:" + sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            //根据跑批日期调用
            svrInvestReportMainService.generateInvestReport(batchExecuteInfo.getExecuteDate());
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.info("中债报告生成批处理异常" + e.getMessage());
            logger.error("中债报告生成批处理Error",e);
        }
        finally{
            Date edate = new Date();
            Long et = edate.getTime();
            Long time=(et - bt)/1000;
            logger.info("中债报告生成批处理结束:" + sdf.format(edate) + " 耗时" + time + "秒");
        }
        return result;
    }
}
