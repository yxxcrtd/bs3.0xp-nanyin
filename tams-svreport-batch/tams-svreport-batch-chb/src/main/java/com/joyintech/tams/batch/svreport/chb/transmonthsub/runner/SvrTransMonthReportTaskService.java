package com.joyintech.tams.batch.svreport.chb.transmonthsub.runner;


import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.common.db.SvrDBUtil;
import com.joyintech.tams.batch.svreport.chb.transmonthsub.service.TransMonthReportMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 中债理财产品转型情况月度统计表 批处理启动类
 * @author yuluan
 *
 */
@Service(value = "svrTransMonthReportTaskService")
@Scope("prototype")
public class SvrTransMonthReportTaskService extends BatchBaseService {
	
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TransMonthReportMainService mainService;

    @Autowired
    private SvrDBUtil dbUtil;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.error("无启动参数，批处理退出。");
            return result;
        }
        SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
        List<String> prodList = new ArrayList<String>();
        if (StringUtil.isNotEmpty(batchExecuteDetail.getPrdGroupSeqNo())) {
      /*     update by xr for  TISHKBS-671
            理财产品转型情况月度统计表跑批-净值型产品的信息未统计 start */

            Map<String,String> prodMap=dbUtil.getAllProdMapWithNoSameId();
            List<String>  prodListex = this.getProductIdList();
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
                 /*     update by xr for  TISHKBS-671
            理财产品转型情况月度统计表跑批-净值型产品的信息未统计 end */
            }
        }



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("理财产品转型情况月度统计表报送批处理启动:"+sdf.format(bdate));
        try {
        	mainService.genReport(batchExecuteInfo.getExecuteDate(),prodList,false);
        	result.setStatus(BatchStatus.COMPLETED);
        }
        catch (BatchException e) {
            logger.error("理财产品转型情况月度统计表报送批处理Error:",e);
			throw e;
        }
        catch (Exception e) {
        	//update by leipeng 错误日志打印修改
            logger.error("理财产品转型情况月度统计表报送批处理Error:",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("理财产品转型情况月度统计表报送批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
    }

}
