package com.joyintech.tams.batch.svreport.chb.inPrdRaisetotSub.runner;

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
import com.joyintech.tams.batch.svreport.chb.inPrdRaisetotSub.service.InProdRaisetotInfoMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;
import com.joyintech.tams.svreport.chbhz.inPrdRaisetotSub.dao.InprodRaisetotInfoSvrMapper;

/**
 * 功能:产品募集总量信息生成批处理
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value = "svrInProdRaisetotInfoTaskService")
@Scope("prototype")
public class SvrInProdRaisetotInfoTaskService extends BatchBaseService {
	@Autowired
	private InprodRaisetotInfoSvrMapper inprodRaisetotInfoSvrMapper;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InProdRaisetotInfoMainService mainService;
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
        logger.info("产品募集总量登记信息生成批处理启动:"+sdf.format(bdate));
        try {
            List<String> prodIdList  = new   ArrayList<String>();
            
            /*List<String> prodCodeList = inprodRaisetotInfoSvrMapper.selectRegistCodeByid(prodIdList);*/
         
            mainService.generateDurationRegData(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate()) ,prodIdList);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (BatchException e) {
            logger.error("产品募集总量登记信息生成批处理Error",e);
        	throw e;
        }
        catch (Exception e) {
			logger.info("批处理异常" + e.getMessage());
        	logger.error("批处理异常Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("产品募集总量登记信息报送批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
    }


}
