package com.jixu.adapter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.export.ExpAssembleOutput;
import com.jixu.adapter.service.impl.DataServiceCenter;
import com.jixu.adapter.service.impl.ExecuteReturnJobTask;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.ResultContainer;

@Service
public class ExecuateChildTask {
	
	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	@Autowired
	private ExpAssembleOutput expAssembleOutput;
	
	@Autowired
	private DataServiceCenter dataServiceCenter;
	
	
	@Resource(name="taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor; 
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ExecuateChildTask.class);
	

	/**
	 * 根据父ID的依赖关系，执行子节点的job
	 * 每个适配器都可以配置父适配器，在父适配器执行结束后，自动被调用
	 * @param sourceID
	 * @param userID
	 */
	public boolean execuateChildTask(int sourceID, int userID){
		List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
		
		List<InterfaceSource> interfaceSource=interfaceSourceDao.loadChildInterfaces(sourceID);
		for(InterfaceSource source:interfaceSource){
			if(sourceID!=source.getSourceID()){
				ExecuteReturnJobTask task=new ExecuteReturnJobTask(dataServiceCenter,expAssembleOutput, userID, source, new Date());
				Future<ResultContainer> future=taskExecutor.submit(task);
				resultList.add(future);
			}
		}
		
		boolean returnFlag=true;
		try{
			for(Future<ResultContainer> future:resultList){
				ResultContainer container=future.get();
				if(!container.isCorrectFlag()){
					returnFlag=false;
				}
			}
		}catch(InterruptedException ie){
			returnFlag=false;
			logger.error("Error", ie);
		}catch(Exception e){
			returnFlag=false;
			logger.error("Error", e);
		}

		return returnFlag;
	}
}
