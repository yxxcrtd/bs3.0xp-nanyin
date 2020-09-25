package com.jixu.job.service.AssetJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.SwapSetPrincipalType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.GeneralCooa;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.msg.dto.ForwardCurrencyMaurityDto;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/**
 * @ClassName AssetJobForwardCurrency
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description [外汇远期]
 */

@Service("assetJobForwardCurrency")
public class AssetJobForwardCurrency extends AssetJobBasic {

	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj costWrap, Date ledgerDate)
			throws JNLException {
		// 公式：估值=今天的市值-昨天的市值
		String chartnum = portfolio.getCoaahdr();
		GeneralCooa generalCooa = null;

		TranClo priorlotCost = null;
		TranClo asofdayCost = null;

		priorlotCost = costWrap.getPriordayCost().get(0);
		asofdayCost = costWrap.getAsofdayCost().get(0);
		int groupnum = costWrap.getGlgroupnum();

		// 估值=今天的市值-昨天的市值
		double f_diff = asofdayCost.getgMkValue() - priorlotCost.getgMkValue();

		generalCooa = new GeneralCooa();

		if (f_diff > 0) { // 赚了。
			jobCom.saveGL(generalCooa, DBCR.DEBIT, RoundUtil.roundMoney2Dec(f_diff * asofdayCost.getPrinFxRate()),
					RoundUtil.roundMoney2Dec(f_diff), jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CARRY_VAL_ADJ));

			jobCom.saveGL(generalCooa, DBCR.CREDIT, RoundUtil.roundMoney2Dec(f_diff * asofdayCost.getPrinFxRate()),
					RoundUtil.roundMoney2Dec(f_diff), jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_GAIN));
		} else if (f_diff < 0) { // 亏了或平了。
			jobCom.saveGL(generalCooa, DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(f_diff * asofdayCost.getPrinFxRate())),
					RoundUtil.roundMoney2Dec(Math.abs(f_diff)),
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_LOSS));

			jobCom.saveGL(generalCooa, DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(f_diff * asofdayCost.getPrinFxRate())),
					RoundUtil.roundMoney2Dec(Math.abs(f_diff)),
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CARRY_VAL_ADJ));
		}

		if (f_diff != 0) {
			jobCom.genVoucherDetails(generalCooa, costWrap.getTranClo(), asofdayCost.getPrice(),
					asofdayCost.getPrinFxRate());
		}
		
		if(asofdayCost.getAmount()==0)
			generalCooa.setOffVouch(JixuDictCode.JX_VOUCHER_OFF_GL);
		else
			generalCooa.setOffVouch(JixuDictCode.JX_VOUCHER_GL);
		
		return generalCooa;
	}

	@Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData) throws JNLException {
		return null;
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo lastClo,
			List<Tran> periodTrans, List<Tran> settleTranList) {
		// 估增=数量*估值汇率-数量*当日估值多方即期汇率/当日估值空方即期汇率
		// double gmkValue=RoundUtil.roundMoney2Dec(clo.getAmount()*clo.getPrice())-RoundUtil.roundMoney2Dec(clo.getAmount()*clo.getIncFxRate()/clo.getPrinFxRate());
		if (clo.getAmount() != 0) {
			clo.setgMkValue(clo.getPrice());
		} else {
			clo.setgMkValue(0);
		}
		return clo;
	}

	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		// 到期当天自动结转，因为当天要把头寸降为0，因此需要一笔交易来降头寸
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		Date lastGlCloseDate = portfolio.getLastGLClose();
		List<TranClo> cloList = tranCloDao.loadTranCloWithAssetTypeNoAccrued(portnum, lastGlCloseDate,AssetTypes.FORWARD_CURRENCY);
		HashMap<String, List<TranClo>> cloHM = new HashMap<String, List<TranClo>>();
		// 按照证券ID分组，一个证券ID是一对收入和支出。证券是临时创建的
		for (TranClo clo : cloList) {
			if (clo.getAmount() == 0)
				continue;
			if (cloHM.containsKey(clo.getCusip())) {
				cloHM.get(clo.getCusip()).add(clo);
			} else {
				List<TranClo> tmpCloList = new ArrayList<TranClo>();
				tmpCloList.add(clo);
				cloHM.put(clo.getCusip(), tmpCloList);
			}
		}
		
		//获取当天该组合下  手动做的外汇但是没有post的交易   如果手动做了则不自动到期
		List<TranBasic> tbList=tranBasicDao.loadForwrdUnpostedTranBasic(portnum,ledgerDate,AssetTypes.FORWARD_CURRENCY);
		for (TranBasic tb : tbList) {
			String cusip=tb.getCusip().getId();
			if(cloHM.containsKey(cusip)){
				cloHM.remove(cusip);
			}
		}
		

		for (Entry<String, List<TranClo>> entry : cloHM.entrySet()) {
			String cusip = entry.getKey();
			IssueInfo sec = issueInfoDao.loadIssueByCusip(cusip);
			
			if (!ledgerDate.before(sec.getExpireDate()) && sec.getOptionType()==SwapSetPrincipalType.NCA_SETTLE) {
				// 如果当天是非工作日,不结转
				TranBasic tran = tranBasicDao.getSwapFxTranBasic(portnum, cusip);

				ForwardCurrencyMaurityDto dto = new ForwardCurrencyMaurityDto();
				dto.setHeader("jxForwardCurrencyMaurityDtoMsg");
				dto.setOperation("ADD");
				dto.setTradeType(String.valueOf(TransTypes.FORWORD_CURRENCY_Maturity));
				dto.setPort(portnum);
				dto.setTradeDate(DateUtil.convertD2YYYY_MM_DD(ledgerDate));
				dto.setManager(String.valueOf(tran.getManager()));
				dto.setTradeCash(String.valueOf(tran.getAmount()));
				dto.setLongCurrency(tran.getFxcurrency());
				dto.setShortCurrency(tran.getFxcurrency2());
				//自动到期是获取到期的当天汇率
				double longFxrate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tran.getFxcurrency(), ledgerDate);
				double shortFxrate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tran.getFxcurrency2(), ledgerDate);
				dto.setTradeFX(String.valueOf(longFxrate));
				dto.setSettleFX(String.valueOf(shortFxrate));
				dto.setTradeCash2(String.valueOf(tran.getAmount2()));
				dto.setAssetType(sec.getIssueType());
				dto.setSecid(sec.getSecurityid());
				dto.setBankAcct(String.valueOf(tran.getBkCashacctNo().getId()));
				dto.setBankAcct2(String.valueOf(tran.getBkCashacctNo2().getId()));
				dto.setSettleDate(DateUtil.convertD2YYYY_MM_DD(ledgerDate));
				
				ResultContainer resultcontainer = tradeService.operateTranAuto(dto);
				if (!resultcontainer.isCorrectFlag())
					return resultcontainer;
			}
		}
		return null;
	}
}
