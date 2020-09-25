/*
 * 文件名：RiskTrialDayTimeProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.risktrialrds.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.risktrialrds.dto.RiskTrialDayTimeReaderResult;
import com.joyintech.tams.invest.batch.risktrialrds.dto.RiskTrialDayTimeResult;
import com.joyintech.tams.invest.common.risktrialrds.RiskTrialComm;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrialrds.util.Const;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 日间批处理生成生成processor相关业务处理
 *
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see RiskTrialDayTimeProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class RiskTrialDayTimeProcessorService extends BaseService {

	 private Logger logger = LoggerFactory.getLogger(getClass());

	 
    @Autowired
    private RiskTrialComm riskTrialComm;
    
    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * process 数据处理
     * @param param1 传递参数
     * @return BookGenerateResult
     * @throws InterruptedException 
     * @see
     * @since 1.0
     */
    public List<RiskTrialDayTimeResult> execute(List<RiskTrialDayTimeReaderResult> readerList,Map<String, Object> param1) throws InterruptedException  {
        // 获取reader返回值
    	List<RiskTrialDayTimeResult> resultList=new ArrayList<>();
    	 final CountDownLatch countDown = new CountDownLatch(readerList.size());
		 for(final RiskTrialDayTimeReaderResult readerResult:readerList){
			// 若reader传递的参数为空，不进行后续处理
			if (readerResult == null) {
				countDown.countDown();
				continue;
			}

			threadPoolTaskExecutor.execute(()->{
				try{
					Map<String,Object> mapOther=new HashMap<>();
					mapOther=readerResult.getMapOther();
					RiskTrialRetDto retEntry=riskTrialComm.afterRiskTrialDeal(mapOther,readerResult.getTaskQueue(), param1.get(Const.SUP_FREQUENCY).toString());
					if(StringUtil.isNotEmpty(retEntry)&&!StringUtil.isEmpty(retEntry.getIndId())&&!StringUtil.isEmpty(retEntry.getPortfolioId())&&!StringUtil.isEmpty(retEntry.getSupStat())){
						RiskTrialDayTimeResult resultDto = new RiskTrialDayTimeResult();
						resultDto.setRiskTrialRetDto(retEntry);
						resultList.add(resultDto);
					}

				}
				catch(Exception e){
					logger.error(e.getMessage(),e);
				}
				finally{
					countDown.countDown();
				}
			});

		 }
		try {
			// 等待线程结束
			countDown.await();
		}
		catch (InterruptedException e) {
			logger.error("线程池中断异常", e);
			Thread.currentThread().interrupt();
		}
        return resultList;
    }




}
