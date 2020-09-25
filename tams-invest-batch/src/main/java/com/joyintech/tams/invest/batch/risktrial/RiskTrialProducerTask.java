/*
 * 文件名：RiskTrialProducerTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月04日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.batch.risktrial;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
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
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dao.RiskTrialCommMapper;
import com.joyintech.tams.invest.common.risktrial.dict.SupervisionFrequency;
import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.SupervisionTargetDto;
import com.joyintech.tams.invest.common.risktrial.dto.Task;
import com.joyintech.tams.invest.common.risktrial.service.TaskQueue;
import com.joyintech.tams.invest.common.risktrial.util.Const;
import com.joyintech.tams.invest.common.risktrial.util.RiskUtil;

/**
 * 监督指标事后监控生产任务
 * @author 裴家俊
 * @version 1.0 2018年12月04日
 * @see RiskTrialProducerTask
 * @since 1.0
 */
@Service("riskTrialProducerTask")
@Scope("prototype")
public class RiskTrialProducerTask extends BatchBaseService {

	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	private RiskTrialCommMapper riskTrialCommMapper;

	@Autowired(required = false)
	private TaskQueue taskQueue;
	
	private Date supDate;
	private long supMillis;
	
	/*@Value("${producer.date.flag}")
	private String produceDateFlag;
	
	@Value("${producer.date.value}")
	private String produceDateValue;*/
	
    @Override
    public JobExecution execute()
        throws BatchException {
    	JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        try {
        	if(null!=batchExecuteInfo&&null!=batchExecuteInfo.getExecuteDate()){
        		supDate=batchExecuteInfo.getExecuteDate();
        	}
        	else{
        		supDate = new Date();
        	}
			supMillis=supDate.getTime();
			//-----add by jjpei 20190717------添加组合条件----
        	List<String> batchPortList=this.getProductIdList();
			//-----add by jjpei 20190717------添加组合条件----
			// 获取指标任务集合
			InvestTradeTrialInput investTradeTrialInput = new InvestTradeTrialInput();
			investTradeTrialInput.setIsEnabled(DictCodeListExt1.使用状态_启用.getValue());
			investTradeTrialInput.setTimePoint(DictCodeListExt1.监督时分_事后监控.getValue());
			if(batchPortList.size()>0&&batchPortList.size()!=riskTrialCommMapper.selectAllBooksetsWithRisk()){
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("productIdList", batchPortList);
				List<String> newPortList=riskTrialCommMapper.getPortfolioIdInFinProduct(paramMap);
				for(String portCodeSg:newPortList) {
					investTradeTrialInput.setPortfolioCode(portCodeSg);
					dealBasicProducer(investTradeTrialInput);
				}
			}
			else{
				dealBasicProducer(investTradeTrialInput);
			}
			result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
		return result;
		
	}

	/**
	 * 获取组合关联指标加入任务队列
	 * @param investTradeTrialInput
	 */
	private void dealBasicProducer(InvestTradeTrialInput investTradeTrialInput){
		List<SupervisionTargetDto> taskList = riskTrialCommMapper.getPortfIndTask(investTradeTrialInput);
		for(SupervisionTargetDto portfolioPoint : taskList) {
			String[] portCount=portfolioPoint.getPortfolioLevel().equals(DictCodeListExt1.组合层次_组合包.getValue())?portfolioPoint.getPortfConstId().split(","):portfolioPoint.getPortfolioId().split(",");
			Map<String, Object> param = new HashMap<String, Object>();
			//---modify by 裴家俊 2019/03/22------start---
			List<List<String>> portList=RiskUtil.splitList(portCount, 1000);
			//param.put("portfolioId", portCount);
			param.put("portfolioId", portList);
			//---modify by 裴家俊 2019/03/22------end---
			int reCount=riskTrialCommMapper.getCountWithBasicPortf(param);
			if(portCount.length!=reCount){
				logger.warn("组合包中的组合在组合表出现不存在的情况，可能是该组合已被删除或到期了");
				continue;
			}
			Task task = new Task();
			task.setDate(supDate);
			task.setPortfolioId(portfolioPoint.getPortfolioId());
			task.setPoint(portfolioPoint);
			//判断生效日期
			if(null==portfolioPoint.getEndDate()){
				if(!(supMillis-portfolioPoint.getStartDate().getTime()>=0)){
					continue;
				}
			}
			else{
				if(!(supMillis-portfolioPoint.getStartDate().getTime()>=0&&supMillis-portfolioPoint.getEndDate().getTime()<=0)){
					continue;
				}
			}
			//匹配监督频率
			if(doesMatchFrequency(task)) {
				taskQueue.push(task);
			}
		}
	}
	private boolean doesMatchFrequency(Task task) {
		SupervisionFrequency f = SupervisionFrequency.valueOf(task.getPoint().getSupFrequency());
		//----add by jjpei 20190810--对频率为天忽视----
		if(f.getDays()==1){
			return  true;
		}
		//----add by jjpei 20190810--对频率为天忽视----
		Date maxSupDate = riskTrialCommMapper.getMaxSupDate(task.getPortfolioId(), task.getPoint().getIndId());
		if(maxSupDate==null){
			return true;
		}
		long intervalDays = (supMillis - maxSupDate.getTime()) / Const.MillisOfDay;

		return (intervalDays >= f.getDays());
	}
	
	
}
