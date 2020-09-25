package com.jixu.post.service.imp;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.post.service.PostIssueService;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("postIssues")
public class PostIssuesServiceImpl implements PostIssueService{

	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void postIssue(TranBasic tranAdvice, ResultContainer container) {
		if(tranAdvice.getPostStatus().equals(PostStatusType.post)){
			return;
		}
		
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tranAdvice.getTrantype());
		PostService postService = secBasic.createPostService();
		//判断该股成交之后的日期,是否有交易
		if(postService!=null && tranAdvice.getCusip()!=null){
			String cusip=tranAdvice.getCusip().getId();
			String portnum=tranAdvice.getPortfolio();
			Date tradeDate=tranAdvice.getTradedate();
			int countnum=tranDao.getTranNumByCusipDate(portnum, cusip, tradeDate,tranAdvice.getId());
			if(countnum>0){
				IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
				container.setErrorCode(ReturnResult.trade_after_TranDate);
				container.setErrorMsg(false, ReturnResult.getResultHM().get(ReturnResult.trade_after_TranDate)+sec.getSecurityid());
				return;
			}
		}
		
		Date ledger=tranAdvice.getLedgerdate();
		PortfolioHdr portfolio=commService.loadPortfolio(tranAdvice.getPortfolio());
		Date close=portfolio.getLastTranClose();
		//Date initDate=portfolio.getInceptionDate();
		//Date nextDate=portfolio.getNextTranClose();

		
		//这个判断在审核逻辑会有问题， 审核之前先关了当天账，审核必定失败。把Ledgerdate移到tran表?
		// || (ledger.compareTo(close)==0 && ledger.compareTo(initDate)==0 && ledger.compareTo(nextDate)==0)
		if(ledger.after(close) || tranAdvice.getTrantype()==TransTypes.ValMannulPositionUpdate){
			if(postService!=null){	
				postService.post(tranAdvice,container);
				if(container.isCorrectFlag()){
					PostToRCPYService postrcpy=secBasic.createPostToRCPYService();
					if(postrcpy!=null){
						postrcpy.postRcpy(tranAdvice, container);
					}
				}
			}else{
				secBasic.createPostToRCPYService().postRcpy(tranAdvice, container);
			}
			
			if(container.isCorrectFlag()){
				tranAdvice.setPostStatus(PostStatusType.post);
				tranBasicDao.updateEntity(tranAdvice);
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.ledger_before_close_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.ledger_before_close_failed));
			return;
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void postIssue(String tanum, ResultContainer container) {
		TranBasic tranAdvice = (TranBasic)tranBasicDao.loadEntity(TranBasic.class, tanum);
		postIssue(tranAdvice, container);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void reverse(String tanum, ResultContainer container) {
		TranBasic tranAdvice = (TranBasic)tranBasicDao.loadEntity(TranBasic.class, tanum);
		if(tranAdvice==null){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.id_not_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.id_not_exist) + ": "+tanum);
			return;
		}
		Date ledger=tranAdvice.getLedgerdate();
		
		PortfolioHdr portfolio=commService.loadPortfolio(tranAdvice.getPortfolio());
		Date close=portfolio.getLastTranClose();
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tranAdvice.getTrantype());
		
		if(ledger.after(close)){
			//删除有利息收入结转的交易
			//autoJobRecordDao.deleteAccruedRepord(tanum);
			
			PostToRCPYService postrcpy=secBasic.createPostToRCPYService();
			if(postrcpy!=null){
				postrcpy.deleteRcpy(tranAdvice, container);
			}
			PostService postService = secBasic.createPostService();
			if(container.isCorrectFlag() && postService!=null){
				tranAdvice.setPostStatus(PostStatusType.unpost);
				postService.reverse(tranAdvice,container);
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.ledger_before_close_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.ledger_before_close_failed));
			return;
		}
	}
	
	public TranSum calPosition(Tran tran, TranSum tSum){
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
		PostService postService = secBasic.createPostService();
		return postService.CaltSum(tran, tSum);
	}
	
}
