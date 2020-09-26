package com.joyintech.tams.batch.svreport.pbc.monthreport.runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.svreport.pbc.monthreport.service.PbcMonthReportCBMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;

@Service(value = "svrPbcReportTaskService")
@Scope("prototype")
public class SvrPbcReportTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PbcMonthReportCBMainService pbcMonthReportCBMainService;
	
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
        logger.info("人行报表批处理启动:"+sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<String>();
            List<String> prodListex = new ArrayList<String>();
            if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
                prodList = this.getProductIdList();
                for(String prod : prodList) {
            		//产品过滤“F16_”前缀
            		String prodStr = prod.replaceFirst("F16_", "");
            		prodListex.add(prodStr);
            	}
            }
            pbcMonthReportCBMainService.generateReport(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate()),
            		prodListex,false);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
			logger.info("批处理异常" + e.getMessage(),e);
            logger.error("人行报表批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("人行报表批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
        
	}

}
