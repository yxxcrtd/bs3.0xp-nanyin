package com.joyintech.tams.batch.svreport.chb.assetinfocb.runner;


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
import com.joyintech.tams.batch.svreport.chb.assetinfocb.service.AssetInfoCBMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;

@Service(value = "svrAssetInfoRegTaskService")
@Scope("prototype")
public class SvrAssetInfoRegTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private AssetInfoCBMainService assetInfoCBMainService;
	
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
        logger.info("资产要素登记报送批处理启动:"+sdf.format(bdate));
        SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
        List<String> prodList = new ArrayList<String>();
        if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
            prodList = this.getProductIdList();
        }
    	assetInfoCBMainService.autoRegistAssetInfo(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate()),prodList,null);
        result.setStatus(BatchStatus.COMPLETED);
    	Date edate=new Date();
        Long et=edate.getTime();
        Long time=(et-bt)/1000;
    	logger.info("资产要素登记报送批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        return result;
	}


}
