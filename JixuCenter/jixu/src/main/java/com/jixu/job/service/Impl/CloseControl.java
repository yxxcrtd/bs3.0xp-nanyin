package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import com.joyintech.tams.valuation.common.interf.common.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WkndValuationCloseType;
import com.jixu.common.types.AcctLocalFunc;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashCloDao;
import com.jixu.dao.intf.BkCashFlowDao;
import com.jixu.dao.intf.CloseHisDao;
import com.jixu.dao.intf.GlCloseDao;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranFeeFollowCloDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.job.service.CloseControlService;
import com.jixu.mvc.control.service.SecCashService;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.howmany.dto.TrdProductDealDto;
import com.joyintech.tams.valuation.common.interf.howmany.service.HowManyService;
import com.orm.obj.BkCashClo;
import com.orm.obj.BkCashFlow;
import com.orm.obj.CloseHis;
import com.orm.obj.GlClose;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;
import com.orm.obj.TranSumRecord;

@Service("closeService")
public class CloseControl implements CloseControlService{
	
	private static final Logger logger = LoggerFactory.getLogger(CloseControl.class);
	
	@Autowired
	private JobsHisDao jobsHisDao;
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Autowired
	private GlCloseDao glCloseDao;
	
	@Autowired
	private CloseHisDao closeHisDao;
	
	@Autowired
	private TranCloDao tranCloDao;
	
	@Autowired
	private VoucherDetailsDao voucherDetailsDao;
	
	@Autowired
	private TranFeeFollowCloDao tranFeeFollowCloDao;
	
	@Autowired
	private NavDao navDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private BkCashFlowDao bkCashFlowDao;
	
	@Autowired
	private BkCashCloDao bkCashCloDao;
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="SecCashService")
	private SecCashService secCashService;
	
	@Resource(name="secCoreFactory")
	protected SecurityCoreFactory secCoreFactory;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired(required=false)
	private HowManyService howManyService;
	
	@Autowired
	private SecPositionCenter secPositionCenter;
	
	private int closeTranJobtype=JobsType.closeTran;
	private int closeGLJobtype=JobsType.clostGL;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer closeTran(int userID, String portnum, Date closeDate) {
		logger.info(portnum + ": 交易关账开始...");
		ResultContainer container=new ResultContainer();
		container.setEntityID(portnum);
		//几许交易入兆尹账初始
		if (JixuUserUtil.isJoyinTablesFlag()) {
			List<TranBasic> tranbasicList = tranBasicDao.loadTranBasic(portnum, closeDate);
			
			container=revokeDealAccount(tranbasicList);
			if(!container.isCorrectFlag()){
				return container;
			}
			
			for (TranBasic tranbasic : tranbasicList) {
				if(tranbasic.getTrantype()==TransTypes.JoyinBond2Stock){
					continue;
				}
				TrdProductDealDto td = new TrdProductDealDto();
				IssueInfo ii = tranbasic.getCusip();
				td.setTradeId(tranbasic.getId());// 交易编号
				td.setFinprodId(ii.getSecurityid());// 资产代码
				td.setFinprodType(ii.getIssueType());// 资产类型
				td.setTradeDate(tranbasic.getLedgerdate());// 记账日
				td.setBusiType(AssetTypes.getBusiType().get(ii.getIssueType()));// 业务类型
				td.setDetailSubjectNoTail(ii.getSecurityid());// 四级科目尾号(资产代码)
				td.setDetailSubnameTail(ii.getSecurityname());// 四级科目尾号名称(资产名称)
				td.setBusiTable(DictCodeList.业务表_衍生品交易表.getValue()); // 业务表
				ResultObject result = howManyService.dealAccount(portnum, td);
				if (!result.getResultCode().equals(JixuDictCode.JOYIN_SUCCESS_STATUS)) {
					container.setCorrectFlag(false);
					container.setErrorMsg(result.getResultMsg());
					return container;
				}
			}
		}
		//如果交易入账初始成功   几许交易关账
		if(container.isCorrectFlag()){
			PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
			printCloseDateLog(closeDate, portfolio);
			Date nextCloseDate=null;
			if(portfolio.getWkndValuationClose()==WkndValuationCloseType.UserDefinedWeekends){
				List<Date> holidayLSList=holidayLSDao.loadHolidayList(portfolio.getHolidaySD());
				nextCloseDate=DateUtil.getNextBusinessDay(closeDate, holidayLSList);
			}else if(portfolio.getWkndValuationClose()==WkndValuationCloseType.None){
				nextCloseDate=DateUtil.getNextDay(closeDate);
			}else{
				nextCloseDate=DateUtil.getNextDay(closeDate);
			}
			container=closeTran(userID, portfolio, closeDate, nextCloseDate);
			//如果jixu关账失败   删除入兆尹表的交易
			if(!container.isCorrectFlag() && JixuUserUtil.isJoyinTablesFlag()){
				List<TranBasic> tranbasicList=tranBasicDao.loadTranBasic(portnum, closeDate);
				for (TranBasic tranBasic : tranbasicList) {
					ResultObject result=howManyService.revokeDealAccount(tranBasic.getId());
					if(!result.getResultCode().equals(JixuDictCode.JOYIN_SUCCESS_STATUS)){
						container.setCorrectFlag(false);
						container.setErrorMsg(result.getResultMsg());
						break;
					}
				}
			}
		}
		logger.info(portnum + ": 交易关账结束...");
		return container;
	}
	
	private ResultContainer closeTran(int userID, PortfolioHdr portfolio, Date closeDate, Date nextCloseDate) {
		ResultContainer container=new ResultContainer();
		container.setEntityObj(portfolio);
		
		Date inceptionDate=portfolio.getInceptionDate();
		Date lastTranclose=portfolio.getLastTranClose();
		
		container.setHeader(JobsType.jobsTypeHM.get(closeTranJobtype));
		if(closeDate.compareTo(portfolio.getLastTranClose())<=0){
			container.setCorrectFlag(true);
			container.setErrorCode(ReturnResult.ledgerBeforeClose_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.ledgerBeforeClose_failed));		
		}else if(portfolio.getLastTranClose().compareTo(portfolio.getLastGLClose()) != 0 && portfolio.getLastGLClose().compareTo(portfolio.getInceptionDate())!=0){
			//第二个判断，初始化成立时，交易关账成功，但凭证失败。重新做账的时候，由于glclose还是初始化日期，因此得排除这个判断
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.close_tran_gl_unclose_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.close_tran_gl_unclose_failed));
		}else if(portfolio.getNextTranClose().compareTo(closeDate)==0 && nextCloseDate.after(closeDate) || inceptionDate.compareTo(lastTranclose)==0){
			//暂时先删除clo表,再去生成. 以后应该阻止clo表的重复生成,先撤帐
			//jobDAO.deleteTranClose(portfolio.getId(), closeDate);
			int recordsnum=tranCloDao.getTranCloseTotalRecords(portfolio.getId(), closeDate);
			if(recordsnum==0){
				copyTsum2Tclo(portfolio, closeDate);
			}else{
				container.setCorrectFlag(true);
				container.setErrorMsg("关帐头寸已经存在，不重复生成关帐头寸。如需重新生成，请重开后再执行关帐操作");
			}
			//生成每日现金头寸
			if(!JixuUserUtil.isJoyinTablesFlag())
				createBkCashPosition(portfolio, closeDate);
			
			//tsum表备份到tsumrecord
			tranSumDao.deleteTranSumRecord(portfolio.getId(), closeDate);
			List<TranSum> tsumList=tranSumDao.loadOpenedTranSum(portfolio.getId());
			for(TranSum tsum:tsumList) {
				TranSumRecord record=new TranSumRecord();
				BeanUtils.copyProperties(tsum, record);
				record.setLedgerDate(closeDate);
				tranSumDao.saveEntity(record);
			}
			
			Date lastclose=portfolio.getLastTranClose();
			portfolio.setLastTranClose(closeDate);
			portfolio.setNextTranClose(nextCloseDate);
			portfolioHdrDao.updatePortfolio(portfolio);
			
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				CloseHis closeHis=new CloseHis();
				closeHis.setCloseDate(closeDate);
				closeHis.setCloseTime(new Date());
				closeHis.setPortfolio(portfolio);
				closeHis.setPreClose(lastclose);
				
				closeHisDao.saveEntity(closeHis);
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.close_tran_date_unmatch_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.close_tran_date_unmatch_failed));
		}
		
		commfuncService.updateJobsHis(userID, portfolio, closeDate, closeTranJobtype, container);
		return container;
	}
	
	
	private void copyTsum2Tclo(PortfolioHdr portfolio, Date closeDate){
		List<TranClo> tcloList=secPositionCenter.getSecClo(portfolio.getId(), closeDate, false);
		for(TranClo clo:tcloList){
			tranCloDao.saveEntity(clo);
		}
	}
	
	private void createBkCashPosition(PortfolioHdr portfolio, Date closeDate){
		//查询上一个总账关账日的BkCashClo
		List<BkCashClo> oldList=commfuncService.loadBkCashClo(portfolio.getId(), portfolio.getLastGLClose());
		//查询当日现金流    并将有同一个银行账户的多个现金流存入list中
		HashMap<Integer, List<BkCashFlow>> bkCashFlowMap=new HashMap<Integer, List<BkCashFlow>>();
		List<BkCashFlow> bkList=bkCashFlowDao.loadCashFlowList(portfolio.getId(), closeDate);
		for (BkCashFlow bkCashFlow : bkList) {
			if(bkCashFlowMap.containsKey(bkCashFlow.getBkCashAcct().getId())){
				bkCashFlowMap.get(bkCashFlow.getBkCashAcct().getId()).add(bkCashFlow);
			}else{
				List<BkCashFlow> newBkCashFlowList=new ArrayList<BkCashFlow>();
				newBkCashFlowList.add(bkCashFlow);
				bkCashFlowMap.put(bkCashFlow.getBkCashAcct().getId(), newBkCashFlowList);
			}
		}
		
		HashMap<Integer, BkCashClo> bkCashCloMap=new HashMap<Integer, BkCashClo>();
		if(oldList.size()>0){
			//处理oldBkCashClo     
			for(BkCashClo oldBkCashClo : oldList) {
				BkCashClo bcc=new BkCashClo();
				bcc.setBkCashAcctID(oldBkCashClo.getBkCashAcctID());
				bcc.setCloseDate(closeDate);
				bcc.setCurID(oldBkCashClo.getCurID());
				//当天交易利率
				double newFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(),oldBkCashClo.getCurID(), closeDate);
				bcc.setFxRate(newFxRate);
				bcc.setPortnum(oldBkCashClo.getPortnum());
				//判断当天有没有现金流
				List<BkCashFlow> bcflist=bkCashFlowMap.get(oldBkCashClo.getBkCashAcctID());
				//如果当天有现金流  则对当天现金流进行累加  
				double balanceAmt=0;
				if(bcflist!=null&&bcflist.size()>0){
					for (BkCashFlow bcf : bcflist) {
						if(bcf.getDbcr().equals(DBCR.CREDIT)){
							balanceAmt=balanceAmt-(bcf.getFxorigbal()-bcf.getFxremainbal());
						}else{
							balanceAmt=balanceAmt+(bcf.getFxorigbal()-bcf.getFxremainbal());
						}
					}
				}
				//当天余额发生额
				bcc.setTodayBalanceAmt(RoundUtil.roundMoney2Dec(balanceAmt));
				//截至到今天的余额=前一天的累计余额+当天余额发生额
				bcc.setBalanceAmt(RoundUtil.roundMoney2Dec(oldBkCashClo.getBalanceAmt()+bcc.getTodayBalanceAmt()));
				//计算当天应收利息
				double todayIncome=secPositionCenter.incomeCal(bcc.getBkCashAcctID(), closeDate, closeDate,bcc.getBalanceAmt());
				bcc.setTodayIncome(todayIncome);
				//截至到今天的总利息=截至到昨天的总利息+今天应收利息
				bcc.setInterestIncome(RoundUtil.roundMoney2Dec(oldBkCashClo.getInterestIncome()+todayIncome));
				bkCashCloDao.saveEntity(bcc);
				bkCashCloMap.put(oldBkCashClo.getBkCashAcctID(), oldBkCashClo);
			}
		}
		//处理当天现金流所产生的新头寸
		for (int bkCashAcctID : bkCashFlowMap.keySet()) {
			//因为是新头寸   所以当oldBkCashClo不存在才保存
			BkCashClo oldBkCashClo=bkCashCloMap.get(bkCashAcctID);
			if(oldBkCashClo==null){
				//取出是同一个银行账户的现金流并进行累加
				List<BkCashFlow> bkFlowList=bkCashFlowMap.get(bkCashAcctID);
				BkCashClo bcc=new BkCashClo();
				bcc.setBkCashAcctID(bkCashAcctID);
				bcc.setCloseDate(closeDate);
				bcc.setInterestIncome(0);
				bcc.setCurID(bkFlowList.get(0).getFxcurrency());
				bcc.setFxRate(bkFlowList.get(0).getFxrate());
				bcc.setPortnum(bkFlowList.get(0).getPortfolio().getId());
				//因为是新头寸  所以刚开始的现金余额是0
				double balanceAmt=0;
				for (BkCashFlow bcf : bkFlowList) {
					if(bcf.getDbcr().equals(DBCR.CREDIT)){
						balanceAmt=balanceAmt-(bcf.getFxorigbal()-bcf.getFxremainbal());
					}else{
						balanceAmt=balanceAmt+(bcf.getFxorigbal()-bcf.getFxremainbal());
					}
				}
				bcc.setBalanceAmt(RoundUtil.roundMoney2Dec(balanceAmt));
				bcc.setTodayBalanceAmt(RoundUtil.roundMoney2Dec(balanceAmt));
				double todayIncome=secPositionCenter.incomeCal(bcc.getBkCashAcctID(), closeDate, closeDate,bcc.getBalanceAmt());
				bcc.setTodayIncome(todayIncome);
				bcc.setInterestIncome(todayIncome);
				bkCashCloDao.saveEntity(bcc);
			}
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer reopenTran(int userID, String[] portnums, Date closeDate){
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for(String portnum:portnums){
			ResultContainer container=reopenTran(userID, portnum, closeDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer reopenTran(int userID, String[] portnums){
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for(String portnum:portnums){
			ResultContainer container=reopenTran(userID, portnum);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer reopenTran(int userID, String portnum, Date closeDate) {
		logger.info(portnum + ": 重开交易开始...");
		//撤帐先删除兆尹表中交易
		ResultContainer container=new ResultContainer();
		if(JixuUserUtil.isJoyinTablesFlag()){
			List<TranBasic> tranbasicList=tranBasicDao.loadTranBasic(portnum, closeDate);
			//判断如果存在债转股股票买入交易  则不删除
			container=revokeDealAccount(tranbasicList);
		}
		//兆尹删除交易成功  几许撤帐
		if(container.isCorrectFlag() ){
			PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
			printCloseDateLog(closeDate, portfolio);
			container=reopenTran(portfolio, closeDate);
		}
		logger.info(portnum + ": 重开交易结束...");
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer reopenTran(int userID, String portnum) {
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		return reopenTran(portfolio, portfolio.getNextTranClose());
	}

	private ResultContainer reopenTran(PortfolioHdr portfolio, Date closeDate) {
		String portnum=portfolio.getId();
		ResultContainer container=new ResultContainer();
		container.setEntityObj(portfolio);
		container.setHeader(JobsType.jobsTypeHM.get(closeTranJobtype));
		if(!portfolio.getLastGLClose().before(closeDate)){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.reopen_tran_glclose_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.reopen_tran_glclose_failed));
		}else if(portfolio.getLastTranClose().compareTo(closeDate) !=0){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.reopen_tran_unmatch_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.reopen_tran_unmatch_failed));
		}else{
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				CloseHis closeHis=closeHisDao.loadCloseHisByCloseDate(portnum, closeDate);
				if(closeHis==null){
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.reopen_tran_unmatch_failed);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.reopen_tran_unmatch_failed));
				}else{
					portfolio.setLastTranClose(closeHis.getPreClose());
					portfolio.setNextTranClose(closeHis.getCloseDate());
					portfolioHdrDao.updateEntity(portfolio);
					
					closeHisDao.deleteEntity(closeHis);
					jobsHisDao.deleteJobsHist(portnum, closeDate, closeTranJobtype);
					tranFeeFollowCloDao.deleteTranFeeFollowClo(portnum, closeDate);
				}
			}else {
				Date lastTranClose=portfolio.getLastTranClose();
				portfolio.setLastTranClose(DateUtil.getLastDay(lastTranClose));
				portfolio.setNextTranClose(lastTranClose);
				portfolioHdrDao.updateEntity(portfolio);
			}
		}
		
		if(container.isCorrectFlag()) {
			tranCloDao.deleteTranClose(portnum, closeDate);
			if(!JixuUserUtil.isJoyinTablesFlag())
				bkCashCloDao.deleteBkCashClo(portnum, closeDate);
		}
		
		return container;
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer closeGL(int userID, String[] portnums, Date closeDate){
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for(String portnum:portnums){
			ResultContainer container=closeGL(userID, portnum, closeDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer closeGL(int userID, String portnum, Date closeDate) {
		logger.info(portnum + ": 总账关账开始...");
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		printCloseDateLog(closeDate, portfolio);
		
		container.setEntityObj(portfolio);
		container.setHeader(JobsType.jobsTypeHM.get(closeGLJobtype));
		if(JixuUserUtil.jixuUserSystemFlag() && !navDao.checkNavExist(portnum, closeDate)){
			//查看nav是否已生成
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.close_gl_no_nav_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.close_gl_no_nav_failed));
			//return ReturnResult.close_gl_no_nav_failed;
		}else if(portfolio.getNextGLClose().compareTo(closeDate)!=0 && portfolio.getNextGLClose().compareTo(portfolio.getInceptionDate())>1){
			//查看期间是否已关帐,并且不是基金成立日
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.close_gl_date_unmatch_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.close_gl_date_unmatch_failed));
		}else if(portfolio.getLastGLClose().compareTo(closeDate)>=0){
			//总账已关帐，无需重复关帐
			container.setCorrectFlag(true);
			container.setErrorCode(ReturnResult.closegl_already_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.closegl_already_failed));
		}else{
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				//计算科目余额
				//关总帐日期和期间关帐相同
				ConcurrentHashMap<String, GlClose> resultHM=new ConcurrentHashMap<String, GlClose>();
				List<GlClose> glCloseList=glCloseDao.getClosedGL(portnum, portfolio.getLastGLClose());
				for(GlClose gl:glCloseList){
					resultHM.put(gl.getAcctnum(), gl);
				}
				List<AcctLocalFunc> resultList=voucherDetailsDao.getCurDateGlResult(portnum, closeDate);
				for(AcctLocalFunc lfResult:resultList){
					String acctnum=lfResult.getAcctnum();
					GlClose lastGL=resultHM.get(acctnum);
					double cr_func=lfResult.getCr_func()+(lastGL==null?0:lastGL.getCr_func());
					double dr_func=lfResult.getDr_func()+(lastGL==null?0:lastGL.getDr_func());
					double cr_local=lfResult.getCr_local()+(lastGL==null?0:lastGL.getCr_local());
					double dr_local=lfResult.getDr_local()+(lastGL==null?0:lastGL.getDr_local());
					
					GlClose newGlClose=new GlClose();
					newGlClose.setAcctnum(acctnum);
					newGlClose.setClassid(portfolio.getClassid());
					newGlClose.setCloseDate(closeDate);
					newGlClose.setCr_func(cr_func);
					newGlClose.setCr_local(cr_local);
					newGlClose.setCurrencycode("");
					newGlClose.setDr_func(dr_func);
					newGlClose.setDr_local(dr_local);
					newGlClose.setPortfolio(portfolio);
					glCloseDao.saveEntity(newGlClose);
				}
			}else {
				//joyin手工凭证改变头寸
				List<TranBasic> tranBasicList=tranBasicDao.loadManualValTran(portnum, portfolio.getLastGLClose(), portfolio.getNextGLClose());
				//创建tsum,复制tranclo
				for(TranBasic tranbasic:tranBasicList) {
					secPositionCenter.genCloByManualVoucher(tranbasic, container);
					if(!container.isCorrectFlag())
						break;
				}
			}
			
			if(container.isCorrectFlag()) {
				portfolio.setLastGLClose(closeDate);
				portfolio.setNextGLClose(portfolio.getNextTranClose());
				portfolioHdrDao.updatePortfolio(portfolio);
				container.setCorrectFlag(true);
			}
		}
		
		commfuncService.updateJobsHis(userID, portfolio, closeDate, closeGLJobtype, container);
		logger.info(portnum + ": 总账关账结束...");
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer reopenGL(int userID, String[] portnums, Date closeDate) {
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for(String portnum:portnums){
			ResultContainer container=reopenGL(userID, portnum,closeDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer reopenGL(int userID, String portnum, Date closeDate) {
		logger.info(portnum + ": 总账开账开始...");
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		printCloseDateLog(closeDate, portfolio);
		ResultContainer container=new ResultContainer();
		container.setEntityObj(portfolio);
		container.setHeader(ReturnResult.getDicHM().get(ReturnResult.delete_chinese)+JobsType.jobsTypeHM.get(closeGLJobtype));
		if(portfolio.getLastGLClose().compareTo(closeDate) != 0){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.reopen_gl_unmatch_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.reopen_gl_unmatch_failed));
		}else{
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				CloseHis closeHis=closeHisDao.loadCloseHisByCloseDate(portnum, closeDate);
				if(closeHis==null){
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.reopen_tran_unmatch_failed);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.reopen_tran_unmatch_failed));
				}else{
					portfolio.setLastGLClose(closeHis.getPreClose());
					portfolio.setNextGLClose(closeHis.getCloseDate());
					portfolioHdrDao.updateEntity(portfolio);
					
					glCloseDao.deleteGlGlose(portnum, closeDate);
					jobsHisDao.deleteJobsHist(portnum, closeDate, closeGLJobtype);
				}
			}else {
				portfolio.setLastGLClose(DateUtil.getLastDay(portfolio.getLastGLClose()));
				portfolio.setNextGLClose(portfolio.getLastGLClose());
				portfolioHdrDao.updateEntity(portfolio);
			}
		}
		logger.info(portnum + ": 总账开账结束...");
		return container;
	}
	
	private void printCloseDateLog(Date closeDate, PortfolioHdr portfolio) {
		String closeDateStr=DateUtil.convertD2YYYYMMDD(closeDate);
		String inceptionDate=DateUtil.convertD2YYYYMMDD(portfolio.getInceptionDate());
		String lastTranCloseDate=DateUtil.convertD2YYYYMMDD(portfolio.getLastTranClose());
		String lastGlCloseDate=DateUtil.convertD2YYYYMMDD(portfolio.getLastGLClose());
		String nextTranCloseDate=DateUtil.convertD2YYYYMMDD(portfolio.getNextTranClose());
		String nextGlCloseDate=DateUtil.convertD2YYYYMMDD(portfolio.getNextGLClose());
		
		
		logger.info(portfolio.getId() + ": " +closeDateStr +"/"+inceptionDate+"/"+lastTranCloseDate+"/"+lastGlCloseDate+"/"+nextTranCloseDate+"/"+nextGlCloseDate);
	}
	
	@Override
	public ResultContainer revokeDealAccount(List<TranBasic> tranbasicList){
		ResultContainer container=new ResultContainer();
		for (TranBasic tranBasic : tranbasicList) {
			if(tranBasic.getTrantype()==TransTypes.JoyinBond2Stock){
				continue;
			}
			ResultObject result=howManyService.revokeDealAccount(tranBasic.getId());
			if(!result.getResultCode().equals(JixuDictCode.JOYIN_SUCCESS_STATUS)){
				container.setCorrectFlag(false);
				container.setErrorMsg(result.getResultMsg());
				break;
			}
		}
		return container;
	}
}
