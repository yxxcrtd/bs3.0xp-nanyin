package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.ResultContainer;
import com.jixu.job.service.AssetJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/**
 * @ClassName AssetJobCommonStock
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description []
 */

@Service
public class AssetJobShareBankZG extends AssetJobBasic {

	@Resource(name = "incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Autowired
	private AssetJobService assetJobTermRepo;

	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio,
			STCostWrapObj stcostWrap, Date ledgerDate) throws JNLException {
		return null;
	}

	// @Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData)
			throws JNLException {
		return assetJobTermRepo.exeIncomeJob(sourceData);
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec,
			TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		return assetJobTermRepo.closeTsum2TcloJob(portfolio, sec, clo, lastClo, periodTrans, settleTranList);
	}

	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate)
			throws JNLException {
		return null;
		/*ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);

		List<TranClo> cloList=jobDAO.loadPreMaturityTClo(portnum, portfolio.getLastGLClose(), ledgerDate, AssetTypes.BankZiGuan);
		
		for(TranClo clo:cloList){
			IssueInfo sec=jobDAO.loadIssueByCusip(clo.getCusip());
			int cashFlowID=sec.getCashFlowCfgID();
			CashFlowCfg cfc=jobDAO.loadCashFlowCfg(cashFlowID);
			if(!cfc.isAutoSetFlag() && !cfc.isAutoInterestFlag())
				continue;
			
			TranBasic tb=jobDAO.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
					
			Date setDate=incomeExpenseCal.getCpnSetDate(cfc, ledgerDate);
			double amount=0;
			//本金是否自动结转
			if(cfc.isAutoSetFlag())
				amount=clo.getAmount();

			double intatmat=0;
			//利息是否自动结转
			if(cfc.isAutoInterestFlag())
				intatmat=clo.getAccrued();

			if(amount==0 && intatmat==0)
				continue;
			
			double principal=amount;
			double cash=RoundUtil.roundMoney2Dec(principal+intatmat);
			String secacct=clo.getSecacct();
			int bankAcct=tb.getBkCashacctNo().getId();
			
			String secID=sec.getSecurityid();
			Date tradeDate=ledgerDate;
			double fxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec.getPriCur(), tradeDate);
			int setCurCode=sec.getPriCur();
			double setfxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec.getPriCur(), tradeDate);
			double setCash=RoundUtil.roundMoney2Dec(cash*setfxRate);
			
			String header="tranBankZiGuanBorrowRedMsg";
			int trantype=TransTypes.ShareBankZGMaturity;
			
			HashMap<String, String> msgHM=msgPackage.ziguanMaturity(header, "ADD", secID, trantype, portnum, ledgerDate, 
					setDate, secacct, bankAcct, tb.getManager().getId(), amount, principal, intatmat,cash, setCurCode,
					fxRate, setCurCode, setfxRate,setCash, clo.getLotnum());
			MsgContainer msgContainer=new MsgContainer();
			msgContainer.setFieldsHM(msgHM);
			msgContainer.setUserID(userID);
			
			container=tradeService.operateTran(msgContainer);
			int tanum=container.getObjID();

			if(container.isCorrectFlag()){
				commfuncService.autoRecord(ledgerDate,portnum,tanum);
			}else{
				break;
			}
		}
		
		commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);
		return container;*/
	}
}
