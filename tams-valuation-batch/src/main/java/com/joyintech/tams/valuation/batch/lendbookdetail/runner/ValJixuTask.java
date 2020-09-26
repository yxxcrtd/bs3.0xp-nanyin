package com.joyintech.tams.valuation.batch.lendbookdetail.runner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jixu.common.type.JobsType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.JobsService;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.baseclass.BatchBuilder;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;

@Service("valJixuTask")
@Scope("prototype")
public class ValJixuTask extends BatchBaseService {
	@Autowired
	private BatchBuilder<BaseReader<NeedOperateEntity>, BaseProcessor<NeedOperateEntity, NeedToWriteEntity>, BaseWriter<NeedToWriteEntity>> batchBuilder;

	private static final Logger logger = LoggerFactory.getLogger(ValJixuTask.class);

	@Resource(name = "configService")
	private ConfigService configService;

	@Resource(name = "jixuJobsService")
	private JobsService jobsService;
	
	@Autowired
    private BookCommonLogic bookCommonLogic;

	@Override
	public JobExecution execute() throws BatchException {
		Map<String, Object> serviceParamMap = new HashMap<String, Object>();
		SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
		Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        serviceParamMap.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        serviceParamMap.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        serviceParamMap.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        serviceParamMap.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        serviceParamMap.put("taskId", this.getBatchExecuteInfo().getTaskId());
//		List<String> ports = this.getProductIdList();
        // 上述查询方法有问题，应该去待处理任务中查询对应账套代码
        List<String> ports = bookCommonLogic.getBatchExcuteProdList(serviceParamMap);
		Date ledgerDate = this.getBatchExecuteInfo().getExecuteDate();

		if (batchExecuteInfo == null) {
			logger.info("无启动参数，批处理退出。");
			return null;
		}
		JobExecution result = new JobExecution(1l);
		
		//首先根据做账日期撤销相应的步骤，主要撤销关账，自动化job

		int[] jobsSteps= {WorkFlowSteps.autojob,WorkFlowSteps.dailyClose,WorkFlowSteps.daily,WorkFlowSteps.generalClose};
		int[] dailyJobs= {JobsType.PURSALEJNL, JobsType.GENERAL, JobsType.INCOME, JobsType.CASH};
		
		//执行指定的jobs
		ResultContainer resultcontainer=jobsService.executeAppointedJobs(ports, ledgerDate, jobsSteps, dailyJobs, false);
		if(resultcontainer.isCorrectFlag())
			result.setStatus(BatchStatus.COMPLETED);
		else
			result.setStatus(BatchStatus.FAILED);
		
		
		//if(!resultcontainer.isCorrectFlag()) {
		List<ResultContainer> containerList=(List<ResultContainer>)resultcontainer.getEntityObj();
		for(ResultContainer container:containerList){
			if(!container.isCorrectFlag()) {
				logger.error("Error", container.getErrorMsg());
				NeedOperateEntity noe = new NeedOperateEntity();
				noe.setProdId(container.getEntityID());
				Exception exp = new Exception(container.getErrorMsg());
				batchBuilder.init(this).processorListener.onProcessError(noe, exp);
			}
		}
		return result;
	}
}
