package com.joyintech.tams.batch.svreport.salecustsvrreport.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.svreport.salecustsvrreport.service.SaleCustSvrrepportImpMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "SvrSaleCustSvrreportInterfTastService")
@Scope("prototype")
public class SvrSaleCustSvrreportInterfTastService extends BatchBaseService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SaleCustSvrrepportImpMainService mainService;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecInfo = this.getBatchExecuteInfo();
        if (batchExecInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate = new Date();
        Long bt = bdate.getTime();
        logger.info("监管客户组别区域报送数据导入批处理启动:"+sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<>();
            mainService.generateSaleCustSvrreportData(batchExecInfo.getExecuteDate(),prodList,false);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            Date edate = new Date();
            Long et = edate.getTime();
            Long time = (et-bt)/1000;
            logger.info("监管客户组别区域报送数据导入批处理结束:" + sdf.format(edate) + "耗时" + time + "秒");
        }
        return result;
    }
}
