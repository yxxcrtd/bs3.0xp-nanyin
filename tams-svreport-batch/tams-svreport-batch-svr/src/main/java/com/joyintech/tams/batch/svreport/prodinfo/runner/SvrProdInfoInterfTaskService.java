package com.joyintech.tams.batch.svreport.prodinfo.runner;

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
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.svreport.prodinfo.service.SvrProdInfoImpMainService;

/**
 * 产品要素接入批处理
 * @author lingxq
 * @version 1.0 2019年2月19日
 * @see prodDealImpService
 * @since 1.0
 */
@Service(value = "svrProdInfoInterfTaskService")
@Scope("prototype")
public class SvrProdInfoInterfTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SvrProdInfoImpMainService mainService;

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
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("产品要素接入批处理启动:"+sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<String>();
            // 数据接入不再支持单产品接入
//            if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
//                prodList = this.getProductIdList();
//            }
            mainService.generateProdInfoData(batchExecuteInfo.getExecuteDate(),prodList);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
			logger.info("批处理异常" + e.getMessage());
            logger.error("产品要素接入批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("产品要素接入批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
    }


}
