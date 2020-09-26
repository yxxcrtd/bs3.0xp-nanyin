package com.joyintech.tams.batch.svreport.datacheck.run;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.datacheck.service.SvrDataCheckCBMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;

@Service(value = "svrDataCheckCBService")
@Scope("prototype")
public class SvrDataCheckCBService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private SvrDataCheckCBMainService chbTotalCheckCBMainService;
	
	@Override
	public JobExecution execute() throws BatchException {
		JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("中债总体核查批处理启动:"+sdf.format(bdate));
        chbTotalCheckCBMainService.autoCheck(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate()));
        result.setStatus(BatchStatus.COMPLETED);
    	Date edate=new Date();
        Long et=edate.getTime();
        Long time=(et-bt)/1000;
    	logger.info("中债总体核查批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");
        return result;
	}


}
