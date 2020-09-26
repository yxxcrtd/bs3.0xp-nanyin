package com.joyintech.tams.batch.svreport.chb.prodterminationregist.runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.common.db.SvrDBUtil;
import com.joyintech.tams.batch.svreport.chb.prodterminationregist.service.ProdTerminationCBMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;

@Service(value = "svrProdEndRegTaskService")
@Scope("prototype")
public class SvrProdEndRegTaskService extends BatchBaseService {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
	ProdTerminationCBMainService mainService;
    @Autowired
	private SvrDBUtil dbUtil;
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
        logger.info("产品终止登记报送批处理启动:"+sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<String>();
            List<String> prodListex = new ArrayList<String>();
            if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
            	Map<String,String> prodMap=dbUtil.getAllProdMapWithNoSameId();
            	prodListex = this.getProductIdList();
                if(!prodListex.isEmpty()) {
                	for(String prodString : prodListex) {
                		//净值型产品过滤“F16_”前缀
                		prodString = prodString.replaceFirst("F16_", "");
                		if(prodMap.containsKey(prodString)){
                			prodList.add(prodMap.get(prodString));
                		}else{
                			prodList.add(prodString);
                		}
                	}
                }
            }
            mainService.generateRegistData(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate()),prodList);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (BatchException e) {
            logger.error("产品终止登记报送批处理Error",e);
        	throw e;
        }
        catch (Exception e) {
            logger.error("产品终止登记报送批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("产品终止登记报送批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
	}
}
