package com.jixu.job.service.AssetJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.util.DateUtil;
import com.jixu.job.service.AssetJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.msg.dto.SwapFXMaturityDto;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/**
 * @ClassName AssetJobSwapFX
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description [外汇掉期]
 */

@Service
public class AssetJobSwapFX extends AssetJobBasic {

	@Autowired
	private AssetJobService assetJobForwardCurrency;


	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj stcostWrap, Date ledgerDate)
			throws JNLException {
		/*
		 * GLCOOABase generalCooa = new GLCOOABase(); generalCooa =
		 * exeIncomeSingleGeneralJob(generalCooa, portfolio, stcostWrap, ledgerDate);
		 * return generalCooa;
		 */
		return assetJobForwardCurrency.exeGeneralJob(portfolio, stcostWrap, ledgerDate);
	}

	@Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData) throws JNLException {
		return null;
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo lastClo,
			List<Tran> periodTrans, List<Tran> settleTranList) {
		clo=setCloInterest(portfolio, sec, clo, lastClo, periodTrans, settleTranList);
		if(clo.getLsindicator()==LongShortType.longTerm) 
			if(clo.getAmount()!=0) {
				clo.setgMkValue(clo.getPrice());
			}else {
				clo.setgMkValue(0);
			}
		else
			clo.setgMkValue(0);
		return clo;
	}
	
	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		//到期当天自动结转，因为当天要把头寸降为0，因此需要一笔交易来降头寸
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		Date lastGlCloseDate = portfolio.getLastGLClose();
		List<TranClo> cloList = tranCloDao.loadTranCloWithAssetTypeNoAccrued(portnum, lastGlCloseDate, AssetTypes.SWAP_FX);
		HashMap<String, List<TranClo>> cloHM=new HashMap<String, List<TranClo>>();
		//按照证券ID分组，一个证券ID是一对收入和支出。证券是临时创建的
		for(TranClo clo:cloList) {
			if(clo.getAmount()==0)
				continue;
			if(cloHM.containsKey(clo.getCusip())) {
				cloHM.get(clo.getCusip()).add(clo);
			}else {
				List<TranClo> tmpCloList=new ArrayList<TranClo>();
				tmpCloList.add(clo);
				cloHM.put(clo.getCusip(), tmpCloList);
			}
		}
		
		for (Entry<String, List<TranClo>> entry:cloHM.entrySet()) {
			String cusip=entry.getKey();
			IssueInfo sec = issueInfoDao.loadIssueByCusip(cusip);
			
			if(!ledgerDate.before(sec.getExpireDate())){
				// 如果当天是非工作日,不结转
				TranBasic tran=tranBasicDao.getSwapFxTranBasic(portnum, cusip);
				
				//收入端金额
				double long_principal=tran.getPrincipal();
				double long_cash=tran.getCash();
				String long_cur=tran.getFxcurrency();
				int long_cashacct=tran.getBkCashacctNo().getId();
				double fxrate=commfuncService.loadFxrate(portnum, portfolio.getCurID(), long_cur, tran.getTradedate());
				
				//支出端金额
				double short_principal=tran.getPrincipal2();
				double short_cash=tran.getCash2();
				String short_cur=tran.getFxcurrency2();
				int short_cashacct=tran.getBkCashacctNo2().getId(); 
				double short_fxrate=commfuncService.loadFxrate(portnum, portfolio.getCurID(), short_cur, tran.getTradedate());
				
				SwapFXMaturityDto dto=new SwapFXMaturityDto();
				dto.setHeader("jxSwapFXMaturityDtoMsg");
				dto.setOperation("ADD");
				dto.setPort(portnum);
				dto.setAssetType(sec.getIssueType());
				dto.setSecid(sec.getSecurityid());
				dto.setTradeType(String.valueOf(TransTypes.SWAP_FX_MATURITY));
				dto.setTradeDate(DateUtil.convertD2YYYY_MM_DD(ledgerDate));
				dto.setSettleDate(dto.getTradeDate());
				dto.setDivBasicDate(DateUtil.convertD2YYYY_MM_DD(DateUtil.getLastDay(ledgerDate)));
				
				dto.setPrincipal(String.valueOf(long_principal));
				dto.setTradeCash(String.valueOf(long_cash));
				dto.setIncurrency(long_cur);
				dto.setBankAcct(String.valueOf(long_cashacct));
				dto.setTradeFX(String.valueOf(fxrate));
				
				dto.setPrincipal2(String.valueOf(short_principal));
				dto.setTradeCash2(String.valueOf(short_cash));
				dto.setIncurrency2(short_cur);
				dto.setBankAcct2(String.valueOf(short_cashacct));
				dto.setTradeFX2(String.valueOf(short_fxrate));
				
				ResultContainer resultcontainer=tradeService.operateTranAuto(dto);
				if(!resultcontainer.isCorrectFlag())
					return resultcontainer;
			}
		}
		return null;
	}
}
