package com.jixu.data.service.impl;


import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.JMSObjectModel;
import com.jixu.dao.intf.FxCurrencyDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.data.service.OutDataInterface;
import com.jixu.mq.producer.ActiveMQSender;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.orm.obj.PortfolioHdr;

/** 
 * @ClassName 		OutDataOperation
 * @author  		Pan Wei 
 * @Date 			2017年10月30日 下午10:10:17 	
 * @Description		[]  
 */
@Component("JixuOutDataOperation")
public abstract class OutDataOperation implements OutDataInterface {
	
	@Autowired
	protected PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	protected PortfolioCfgService portCfgService;
	
	@Resource(name="commfuncService")
	protected CommFuncService comService;
	
	@Autowired
	protected FxCurrencyDao fxCurrencyDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Resource(name="ActiveMQProducer")
	private ActiveMQSender actSender;
	//private void execut
	
	//发现使用多线程,需要在这里开启线程,目前不知道原因
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void sendResults(String portNum, Date closeDate) {
		List<?> results=genResults(portNum, closeDate);
		
		if(results==null){
			return;
		}
		
		if(results.size()>0){
			PortfolioHdr portfolio=comService.loadPortfolio(portNum);
			JMSObjectModel model=new JMSObjectModel();
			model.setPortfolioCode(portfolio.getShortCode());
			model.setContent(results);
			//消息名以投资组合编号作为名字的一部分,比如现金头寸的消息, CashPosition_220101
			actSender.sendMessage(model);
		}
	}
}
