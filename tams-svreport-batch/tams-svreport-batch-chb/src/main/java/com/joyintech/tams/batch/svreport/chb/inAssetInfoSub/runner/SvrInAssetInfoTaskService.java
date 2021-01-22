package com.joyintech.tams.batch.svreport.chb.inAssetInfoSub.runner;

import java.text.SimpleDateFormat;
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
import com.joyintech.tams.batch.svreport.chb.inAssetInfoSub.service.InAssetInfoMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;

/**
 *  资产负债登记要素生成批处理
 * @author dongxiangyang
 * @version 1.0 2020年09月17日
 * @see 
 * @since 1.0
 */
@Service(value = "svrInAssetInfoTaskService")
@Scope("prototype")
public class SvrInAssetInfoTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InAssetInfoMainService mainService;
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
        logger.info("资产负债登记要素生成批处理启动:"+sdf.format(bdate));
        try {
            List<String> prodListex  = this.getProductIdList();
         
            mainService.generateDurationRegData(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate())
            		                             ,prodListex);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (BatchException e) {
            logger.error("资产负债登记要素生成批处理Error",e);
        	throw e;
        }
        catch (Exception e) {
			logger.info("批处理异常" + e.getMessage());
        	logger.error("批处理异常Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("资产负债登记要素批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
    }


}
