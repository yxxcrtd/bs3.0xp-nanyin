package com.joyintech.tams.invest.batch.prebatchcheck.processasset.runner;

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
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.batch.prebatchcheck.processasset.service.ProcessAssetService;

/**
 * 批前检查-流程中的资产定义
 * @author Administrator
 *
 */
@Service("processAssetTask")
@Scope("prototype")
public class ProcessAssetTask extends BatchBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    @Autowired
    private ProcessAssetService processAssetService;
    
	
	@Override
	public JobExecution execute() throws BatchException {
		
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);


        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();

        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }

        String instCode = batchExecuteDetail.getOtherParam4();
        
        SysPrebatchResultDto paramdto = new SysPrebatchResultDto();
        paramdto.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        paramdto.setTaskId(batchExecuteInfo.getTaskId());

        SysPrebatchResultDto dto = prebatchCheckCommonService.selAndUpdPrebatchResult(paramdto);

        if(dto==null || StringUtil.isEmpty(dto.getSeqNo())){
            logger.info("此次批前检查未找到该启动任务id。");
            return null;
        }
        
        // 执行查询 并记录 错误日志  并记录最终处理时间 处理状态等等
        try {
        	processAssetService.execute(batchExecuteInfo,dto,instCode);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_异常.getValue());
            logger.error(e.getMessage());
        }

        // 更新 最终处理状态
        dto.setStartTime(null);
        dto.setEndTime(new Date());
        prebatchCheckCommonService.updateSysPrebatchResult(dto);

        return result;
	}

}
