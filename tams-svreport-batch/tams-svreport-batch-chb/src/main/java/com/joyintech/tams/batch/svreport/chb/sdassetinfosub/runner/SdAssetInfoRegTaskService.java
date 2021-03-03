package com.joyintech.tams.batch.svreport.chb.sdassetinfosub.runner;


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
import com.joyintech.tams.batch.svreport.chb.sdassetinfosub.service.SdAssetInfoCBMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;

/**
 * 功能:资产要素信息登记直连报送批处理调度入口
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @创建时间：2020年09月27日
 * @修改记录：
 */
@Service(value = "sdAssetInfoRegTaskService")
@Scope("prototype")
public class SdAssetInfoRegTaskService extends BatchBaseService {
	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SdAssetInfoCBMainService mainService;
	
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
        logger.info("【资产要素信息登记】直连报送批处理启动:"+sdf.format(bdate));
        try {
            mainService.sdAssetInfoRegData(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate()));
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (BatchException e) {
            logger.error("【资产要素信息登记】直连报送批处理Error",e);
        	throw e;
        }
        catch (Exception e) {
			logger.info("批处理异常" + e.getMessage());
        	logger.error("批处理异常Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("【资产要素信息登记】直连报送批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
	}


}
