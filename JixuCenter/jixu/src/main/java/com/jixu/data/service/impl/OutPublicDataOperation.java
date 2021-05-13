package com.jixu.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.JMSObjectModel;
import com.jixu.data.service.OutPublicDataInterface;
//import com.jixu.mq.producer.ActiveMQSender;

/**
 * @ClassName 		OutPublicDataOperation
 * @author  		Pan Wei
 * @Date 			2018年2月12日 下午2:56:33
 * @Description		[]
 */

public abstract class OutPublicDataOperation implements OutPublicDataInterface {

	@Resource(name="commfuncService")
	protected CommFuncService comService;

//	@Resource(name="ActiveMQProducer")
//	private ActiveMQSender actSender;

	//发现使用多线程,需要在这里开启线程,目前不知道原因

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void sendResults(Object obj) {
		List<?> results=genResults(obj);

		if(results==null){
			return;
		}

		if(results.size()>0){
			JMSObjectModel model=new JMSObjectModel();
			model.setContent(results);
			//消息名以投资组合编号作为名字的一部分,比如现金头寸的消息, CashPosition_220101
//			actSender.sendPublicMessage(model);
		}
	}

	/**
	 * 如果发送的ID过去是负数，则为删除该记录
	 * @param obj
	 * @return
	 */
	protected abstract List<?> genResults(Object obj);

	/**
	 * 获取所有的记录
	 * @return
	 */
	protected abstract List<?> genResults();

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void sendResults(){
		List<?> results=genResults();

		if(results==null){
			return;
		}

		if(results.size()>0){
			JMSObjectModel model=new JMSObjectModel();
			model.setContent(results);
			//消息名以投资组合编号作为名字的一部分,比如现金头寸的消息, CashPosition_220101
//			actSender.sendPublicMessage(model);
		}
	}
}
