package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.TranBaseMsgDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

public abstract class MsgTranBaseExecuteService<T extends TranBaseMsgDto> extends MsgExecuteService<T>{
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	protected TranSumDao tranSumDao;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Override
	protected void executeMsg(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException{
		//消息转成dto,校验输入是否正确
		execute(msgcontainer);
		if(msgcontainer.isCorrectFlag()) {
			//判断正确后，存入到数据库
			T dto=(T)msgcontainer.getEntityObj();
			//判断交易是否能够被更新或删除
//			validateDelUpdate(dto.getOperation(),dto.getId(),msgcontainer);
//			if(!dto.getOperation().equals(delete))
//				validateTranBasicDate(msgcontainer);
		
			if(msgcontainer.isCorrectFlag()){
				msgcontainer=completeEntity(dto, msgcontainer);
			}
		}
	}
	
	/**
	 * joyin流程的执行消息
	 * @param msgcontainer
	 * @throws NumberFormatException
	 * @throws MsgFieldLackException
	 */
	@Override
	protected void executeNewProcessMsg(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException{
		execute(msgcontainer);
		if(msgcontainer.isCorrectFlag()) {
			T dto=(T)msgcontainer.getEntityObj();
			//判断交易是否能够被更新或删除
//			validateDelUpdate(dto.getOperation(),dto.getId(),msgcontainer);
//			if(!dto.getOperation().equals(delete)) {
//				validateTranBasicDate(msgcontainer);
//			
//				//调用校验逻辑
//				//走工作流，需要两次重复校验
//				//1.输入交易的时候校验
//				//2.存入数据库并post提交交易的时候校验
//				//输入交易和最终post是两个时间点，可能会发生中间通过消息导入交易的情况，因此需要双重校验
//				if(msgcontainer.isCorrectFlag()) {
//					TranBasic tran=dto2TranBasic(dto, msgcontainer);
//					if(msgcontainer.isCorrectFlag()) {
//						//根据交易类型找到post校验方法
//						SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
//						PostService postService = secBasic.createPostService();
//						if(postService!=null){
//							postService.preValidateTranBasic(tran, msgcontainer);
//						}
//					}
//				}
//			}
			
			if(msgcontainer.isCorrectFlag())
				exeJoyinWFL(dto, msgcontainer);
		}
	}
	
	public abstract TranBasic dto2TranBasic(T dto, MsgContainer msgcontainer);
	
	@Override
	protected MsgContainer completeEntity(T dto, MsgContainer msgcontainer) {
		//获取操作类型，新建、更新、删除
		String operation=dto.getOperation();
		String tanum=dto.getId();
				
		TranBasic oldTran=null;
		if(operation.equalsIgnoreCase(delete) || operation.equalsIgnoreCase(update)) {
			oldTran=reverseTran(tanum, dto.getOperation(), msgcontainer);
		}
			
		if(!msgcontainer.isCorrectFlag() && operation.equalsIgnoreCase(update)){
			if (oldTran != null){
				oldTran.setPostStatus(PostStatusType.post);
			}
			tranBasicDao.updateEntity(oldTran);
			return msgcontainer;
		}
		
		if(operation.equalsIgnoreCase(delete))
			return msgcontainer;
		
		//把dto转成新的tran
		TranBasic tran=dto2TranBasic(dto, msgcontainer);
		
		msgcontainer=completeTranEntity(dto, tran, oldTran, msgcontainer);
		//所有的交易都在运行job时post，和joyin逻辑统一
//		if(!JixuUserUtil.isJoyinTablesFlag()) {
//			if(msgcontainer.isCorrectFlag()) {
//				//存入数据库后，获取交易主键tanum,提交交易,生成tran,transum表记录
//				if(dto.getOperation().equalsIgnoreCase(add) || dto.getOperation().equalsIgnoreCase(update)){
//					postIssue.postIssue(msgcontainer.getEntityID(), msgcontainer);
//				}
//			}
//		}
		return msgcontainer;
	}
	
	
	/**
	 * 检查老的交易是否存在
	 * @param operation
	 * @param tanum
	 * @param msgcontainer
	 * @return
	 * @throws NumberFormatException
	 */
	protected MsgContainer checkOldTran(String operation, String tanum, MsgContainer msgcontainer) throws NumberFormatException{
		if(operation.equalsIgnoreCase(delete) || operation.equalsIgnoreCase(update)){
			//更新、删除交易,tanum必须存在
			if(tanum==null || tanum.equals(""))
				msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error14,"TANUM"));
			else{
				//校验是否交易已存在
				TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,tanum);
				if(oldTran==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,msgcontainer.getHeader(),"TANUM:"+tanum));
				}
				//joyin不不需要判断逻辑，可以撤账重新跑账，目前只有在跑job的时候才会post
//				else {
//					//joyin不需要判断
//					//不允许删除关账之前的交易
//					PortfolioHdr portfolio=commService.loadPortfolio(oldTran.getPortfolio());
//					if(!oldTran.getLedgerdate().after(portfolio.getLastTranClose())) {
//						msgcontainer.setErrorMsg(false, ReturnResult.getResultHM().get(ReturnResult.ledger_before_close_failed));
//					}
//				}
				
			}
		}else if(operation.equalsIgnoreCase(add)){
			msgcontainer.setEntityID(tanum);
			if(tanum!=null && !tanum.equals("")){
				TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,tanum);
				if(oldTran != null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error7,"TANUM:"+tanum));
				}
			}
		}else{
			msgcontainer.setCorrectFlag(false);
		}
		return msgcontainer;
	}
	
	/**
	 * 检查交易是否能够被更新或删除
	 * @param operation
	 * @param tanum
	 * @param msgcontainer
	 * @return
	 */
	private MsgContainer validateDelUpdate(String operation,String tanum, MsgContainer msgcontainer) {
		if(operation.equalsIgnoreCase(delete) || operation.equalsIgnoreCase(update)){
			
			TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,tanum);
			String portfolio = oldTran.getPortfolio();
			String cusip = oldTran.getCusip().getId();
			
			List<Tran> trans=tranDao.loadTransByTanum(tanum);
			
			for(Tran tran:trans){
				int lotnum=tran.getLotnum();
				TranSum tSum = tranSumDao.loadTSumByLot(portfolio, cusip, lotnum);
				//第一笔交易，tsum不存在
				if(tSum!=null) {
					if(tran.getTrannum()<tSum.getLstTrannum()){
						IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorCode(ReturnResult.trade_after_TranDate);
						msgcontainer.setErrorMsg(false, ReturnResult.getResultHM().get(ReturnResult.trade_after_TranDate)+sec.getSecurityid());
					}
				}
			}
		}
		return msgcontainer;
	}
	
	protected MsgContainer completeTranEntity(T dto, TranBasic tran, TranBasic oldTran, MsgContainer msgcontainer) {
		//获取操作类型，新建、更新、删除
		String operation=dto.getOperation();
		String tanum=dto.getId();
		if(operation.equalsIgnoreCase(add)){
			if(tanum!=null && !tanum.equals("")){
				tran.setId(tanum);
			}
			tran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(tran);
			msgcontainer.setEntityID(tran.getId());
			msgcontainer.setEntityObj(tran);
		}else if(operation.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(tran, oldTran,new String[]{"id","createDate","createUser"});
			tranBasicDao.updateEntity(oldTran);
			msgcontainer.setEntityID(oldTran.getId());
			msgcontainer.setEntityObj(oldTran);
		}
		
		msgcontainer.setPortid(dto.getPort());
		return msgcontainer;
	}
	
	/**
	 * 输入交易后，公共的校验逻辑
	 * @param container
	 */
	protected void validateTranBasicDate(MsgContainer container) {
		if(container.isCorrectFlag()) {
			T dto=(T)container.getEntityObj();
			TranBasic tranBasic=dto2TranBasic(dto, container);
			if(tranBasic.getCusip()!=null){
				String cusip=tranBasic.getCusip().getId();
				String portnum=tranBasic.getPortfolio();
				Date tradeDate=tranBasic.getTradedate();
				//确认该证券在交易日之后没有交易
				int countnum=tranDao.getTranNumByCusipDate(portnum, cusip, tradeDate,dto.getId());
				if(countnum>0){
					IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.trade_after_TranDate);
					container.setErrorMsg(false, ReturnResult.getResultHM().get(ReturnResult.trade_after_TranDate)+sec.getSecurityid());
					return;
				}
			}
		}
	}
	
	
	//交易基本信息验证
	protected void validDtoDataFunc(T dto, MsgContainer msgcontainer) throws MsgFieldLackException {
		// 获取operation
		String operation = dto.getOperation();

		// 根据operation，检查以前的交易是否存在
		msgcontainer = checkOldTran(operation, dto.getId(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}

		if (operation.equalsIgnoreCase(delete)) {
			msgcontainer.setEntityObj(dto);
			return;
		}

		//验证资产类型下是否存在该交易类型
		String assetType = dto.getAssetType();
		int tranType = Integer.valueOf(dto.getTradeType());
		if (!SecurityCoreFactory.getAssetTypeHM().get(assetType).containsKey(tranType)) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(
			MsgError.genErrorMsg(MsgError.error13, msgHeader(), assetType + ":" + dto.getTradeType()));
			return;
		}

		// 验证证券输入
		getSecurity(dto.getSecid(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		// 交易日期
		checkDateFormat(msgcontainer, msgHeader(), dto.getTradeDate());
		if (!msgcontainer.isCorrectFlag())
			return;

		// 产品/投资组合
		getPortfolio(dto.getPort(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
	}
	
	/**
	 * 判断证券主文件是否被交易post
	 * @param secid
	 * @param msgcontainer
	 * @return
	 */
	protected void checkIssueInfoPost(MsgContainer container,String portnum,String secidArr){
		String[] secids=secidArr.split("\\|");
		String secid="";
		if(secids.length==3){
			secid=secids[1];
		}else{
			secid=secids[0];
		}
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		//1.判断证券主文件是否存在  如果不存在就新建没有问题     2.如果存在,查看是否已经被交易post,如果没有则更新证券主文件,如果有返回错误信息 
		if(sec!=null){
			List<TranBasic> list=tranBasicDao.loadTranBasicCusipPost(portnum, sec.getId());
			if(list.size()>0){
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.smf_already_post);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.smf_already_post));
				return;
			}
		}
	}
}
