package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.BkCashCloDao;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.dao.intf.BkInteSetHisDao;
import com.jixu.dao.intf.CloseHisDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranSubRedDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.dao.intf.ValuationReportDao;
import com.jixu.dao.intf.WorkFlowFactoryDao;
import com.jixu.job.service.CloseControlService;
import com.jixu.job.service.ContractualSetService;
import com.jixu.post.service.PostIssueService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.tams.valuation.common.interf.common.ResultObject;
import com.joyintech.tams.valuation.common.interf.howmany.service.HowManyService;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;
import com.orm.obj.TranSumRecord;

@Component
public class CleanAndPostDataJob {

	@Autowired
	private TranCloDao tranCloDao;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private TranSubRedDao tranSubRedDao;
	
	@Autowired
	private BkCashRcpyDao bkCashRcpyDao;
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Resource(name="postIssues")
	private PostIssueService postIssues;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private WorkFlowFactoryDao workFlowFactoryDao;
	
	@Autowired
	private BkInteSetHisDao bkInteSetHisDao;
	
	@Autowired
	private CloseHisDao closeHisDao;
	
	@Autowired
	private BkCashCloDao bkCashCloDao;
	
	@Autowired
	private ValuationReportDao valuationReportDao;
	
	@Autowired
	private NavDao navDao;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Autowired(required=false)
	private HowManyService howManyService;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="contractualSetService")
	private ContractualSetService setCashService;
	
	@Resource(name="closeService")
	private CloseControlService closeControlService;

	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer executeJob(String portnum, Date ledgerDate){
		ResultContainer container=deleteAfterLedgerData(portnum, ledgerDate);
		if(container.isCorrectFlag()) {
			//复制当日头寸信息到tsum表，支持重新计算
			copyTSumRecord2TSum(portnum, ledgerDate);
		}
		return container;
	}
	
	private ResultContainer deleteAfterLedgerData(String portnum, Date ledgerDate) {
		ResultContainer container=new ResultContainer();
		
		//1. 删除实收实付现金流
		container=setCashService.deleteCashFlow(portnum, ledgerDate);
		if(!container.isCorrectFlag())
			return container;
		//bkCashFlowDao.deleteCashFlowAfterLedgerDate(portnum, ledgerDate);
		//2. 删除>=出账日的rcpy
		bkCashRcpyDao.deleteRcpyAfterLedgerDate(portnum, ledgerDate);
		//3. 删除closedate>=出账日的头寸
		tranCloDao.deleteTranCloAfterLedgerDate(portnum, ledgerDate);
		//4. 删除tsum表相关数据
		tranSumDao.deleteAllTSumDataByPortnum(portnum);
		//5. 删除>=ledgerdate的tran记录
		tranDao.deleteTranAfterLedgerDate(portnum, ledgerDate);
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			tranSubRedDao.deleteTranSubRedAfterLedgerDate(portnum, ledgerDate);
		}
		
		//6.更新>=ledgerdate的tranbasic的post状态
		List<TranBasic> tranbasicList=tranBasicDao.loadTranBasicAfterLedgerDate(portnum, ledgerDate);
		if(JixuUserUtil.isJoyinTablesFlag()){
			//判断如果存在债转股股票买入交易  则不删除
			container=closeControlService.revokeDealAccount(tranbasicList);
			if(!container.isCorrectFlag()){
				return container;
			}
		}
		
		for (TranBasic tranBasic : tranbasicList) {
			if(tranBasic.getImportType()==JixuDictCode.IMP_TRADE_TYPE_AUTO) {
				tranBasic.setPostStatus(PostStatusType.delete);
				if(!JixuUserUtil.isJoyinTablesFlag()) {
					//银行自动结息的记录，删除bkintesethis表
					bkInteSetHisDao.deleteBkInteSetHisByTanum(tranBasic.getId());
				}
			}else
				tranBasic.setPostStatus(PostStatusType.unpost);
			tranBasicDao.updateEntity(tranBasic);
		}
		
		//tranBasicDao.updateTranBasicPostStatusLedgerDate(portnum, ledgerDate);
		//7.删除当日之后自动生成的交易
		//tranBasicDao.deleteAutoTranBasic(portnum, ledgerDate);
		//8.更新投资组合关账日期
		Date lastDate=DateUtil.getLastDay(ledgerDate);
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		portfolio.setLastTranClose(lastDate);
		portfolio.setLastGLClose(lastDate);
		portfolio.setNextTranClose(ledgerDate);
		portfolio.setNextGLClose(ledgerDate);
		portfolioHdrDao.updatePortflio(portfolio);
		
		//8.更新工作流状态
		if(JixuUserUtil.isJoyinTablesFlag()) {
			workFlowFactoryDao.updateWorkFlowStatusToOpen(portnum, ledgerDate);
		}else {
			bkCashCloDao.deleteBkCashClo(portnum, ledgerDate);
			navDao.deleteNav(portnum, ledgerDate);
			valuationReportDao.deleteValuationReport(portnum, DateUtil.ConvertUtil2Sql(ledgerDate));
			closeHisDao.deleteCloseHisRecords(portnum, ledgerDate);
		}
		
		
		return container;
	}

	/**
	 * 把transumrecord的记录备份到transum表
	 * @param portnum
	 * @param ledgerDate
	 */
	private void copyTSumRecord2TSum(String portnum, Date ledgerDate) {
		List<TranSumRecord> tsrList=tranSumDao.loadTranSumRecordByPortDate(portnum, DateUtil.getLastDay(ledgerDate));
		if(tsrList.size()>0) {
			//TranSumRecord有值就复制
			for(TranSumRecord tsr:tsrList) {
				TranSum sum=new TranSum();
				BeanUtils.copyProperties(tsr, sum);
				tranSumDao.saveEntity(sum);
			}
		}else {
			//没有值就用库里的tran记录重新计算并存入transum表
			//主要用于系统切换，之前没有transum记录
			List<Tran> transList=tranDao.loadReopenRecalPostionTrans(portnum, ledgerDate);
			//按照cusip,lot,trannum正向排序
			HashMap<String, List<Tran>> tranMap=new HashMap<String, List<Tran>>();
			for(Tran tran:transList) {
				String key=tran.getCusip().getId()+"___"+tran.getLotnum();
				if(tranMap.containsKey(key)) {
					tranMap.get(key).add(tran);
				}else {
					List<Tran> innerList=new ArrayList<Tran>();
					innerList.add(tran);
					tranMap.put(key, innerList);
				}
			}
			
			for(Map.Entry<String, List<Tran>> entry : tranMap.entrySet()) {
				List<Tran> cusipTranList=entry.getValue();
				TranSum tsum=new TranSum();
				for(Tran tran:cusipTranList) {
					tsum=postIssues.calPosition(tran, tsum);
				}
				tranSumDao.saveEntity(tsum); 
			}
		}
	}
	
	/**
	 * 提交当天做账的交易
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public ResultContainer postTodayTrans(String portnum, Date ledgerDate) {
		ResultContainer container=new ResultContainer();
		//获取当天尚未提交的交易
		List<TranBasic> tranList=tranBasicDao.loadUnpostedTranBasic(portnum, ledgerDate);
		//头寸增加交易
		List<TranBasic> buyList=new ArrayList<TranBasic>();
		//头寸减少交易
		List<TranBasic> saleList=new ArrayList<TranBasic>();
		
		for(TranBasic tran:tranList) {
			if(tran.getTrantype()==TransTypes.ValMannulPositionUpdate)
				continue;
			SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
			if(secBasic.buySaleFlag())
				buyList.add(tran);
			else
				saleList.add(tran);
		}
		
		//先执行买入交易
		for(TranBasic tran:buyList) {
			postIssues.postIssue(tran, container);
			if(!container.isCorrectFlag())
				break;
		}
		//再执行卖出交易
		//先执行买入交易
		for(TranBasic tran:saleList) {
			postIssues.postIssue(tran, container);
			if(!container.isCorrectFlag())
				break;
		}
		
		return container;
	}
}
