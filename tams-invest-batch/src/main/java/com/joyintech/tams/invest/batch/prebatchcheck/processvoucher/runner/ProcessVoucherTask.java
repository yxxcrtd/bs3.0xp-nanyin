package com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.runner;

import java.util.Date;
import java.util.HashMap;
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
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.service.ProcessVoucherService;

/**
 * 流程中手工凭证
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Service("processVoucherTask")
@Scope("prototype")
public class ProcessVoucherTask extends BatchBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    @Autowired
    private ProcessVoucherService processVoucherService;
    
    /**
     * 批任务接口
     */
	@Override
	public JobExecution execute() throws BatchException {
		//1. 设置批任务状态
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);

        //2. 获取批任务信息
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
        //3. 空值处理
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return null;
        }
        
        //4 自动跑批处理
        if(StringUtil.equals(BatchDictCodeList.批处理启动模式_自动.getValue(),batchExecuteInfo.getStartMode())){

            // 先删除 已经存在的结果 根据 taskId 及日期  自动跑批 所有机构 结果都删除。
            Map<String,Object> param = new HashMap<>();
            param.put("checkDate",batchExecuteInfo.getExecuteDate());
            param.put("taskId",batchExecuteInfo.getTaskId());
            prebatchCheckCommonService.deleteResultAndDetailList(param);

            // 查询所有机构  并生成对应的 List<SysPrebatchResultDto>
            List<SysPrebatchResultDto>  resultList = prebatchCheckCommonService.selectAllInstAndPackageDto(batchExecuteInfo);

            if(!StringUtil.isEmptyList(resultList)) {
                for (SysPrebatchResultDto dto : resultList) {
                    try {
                    	processVoucherService.execute(batchExecuteInfo,dto,dto.getInstitutionCode());
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

                }
            }

            result.setStatus(BatchStatus.COMPLETED);
            return result;
        }
        //5. 非自动跑批处理
        //5.1 获取机构代码
        String instCode = batchExecuteDetail.getOtherParam4();

        //5.2  获取SysPrebatchResultDto
        SysPrebatchResultDto paramdto = new SysPrebatchResultDto();
        paramdto.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        paramdto.setTaskId(batchExecuteInfo.getTaskId());
        SysPrebatchResultDto dto = prebatchCheckCommonService.selAndUpdPrebatchResult(paramdto);

        //5.3 空值处理
        if(dto==null || StringUtil.isEmpty(dto.getSeqNo())){
            logger.info("此次批前检查未找到该启动任务id。");
            return null;
        }

        //5.4 执行查询 并记录 错误日志  并记录最终处理时间 处理状态等等
        try {
        	processVoucherService.execute(batchExecuteInfo,dto,instCode);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_异常.getValue());
            logger.error(e.getMessage());
        }

        //5.5 更新 最终处理状态
        dto.setStartTime(null);
        dto.setEndTime(new Date());
        prebatchCheckCommonService.updateSysPrebatchResult(dto);

        return result;

    }

}
