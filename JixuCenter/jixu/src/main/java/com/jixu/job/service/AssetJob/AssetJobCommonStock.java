package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.GeneralCooa;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
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

@Service("assetJobCommonStock")
public class AssetJobCommonStock extends AssetJobBasic {
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;

	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj costWrap, Date ledgerDate) throws JNLException {
		// A1*P2*R2 - A1*P1*R1 (总的损益)= (A1*P2*R2 - A1*P1*R2) (价格引起)+ (A1*P1*R2 -
		// A1*P1*R1) (汇率变化引起)
		// 公式：估值=今天的市值-昨天的市值
		// (估值=价格损益+汇率损益)
		String chartnum = portfolio.getCoaahdr();
		//IssueInfo sec=jobDAO.loadIssueByCusip(costWrap.getCusip());
		GeneralCooa generalCooa = null;

		double l_diff = 0.0, f_diff = 0.0; // 估值变量。l_diff为local。f_diff为functional.
		double l_priordayValuation = 0.0; // 前一天原币估增
		double l_asofdayValuation = 0.0; // 当天原币估增
		double f_priordayValuation = 0.0; // 前一天本币估增
		double f_asofdayValuation = 0.0; // 当天本币估增
		// double l_p_valuation = 0.0; // 因价格引起的原币(估值=价格损益+汇率损益)
		// double f_p_valuation = 0.0; // 因价格引起的本币(估值=价格损益+汇率损益)

		TranClo priorlotCost = null;
		TranClo asofdayCost = null;
		// VALUATION_LOSS, CARRY_VAL_ADJ, VALUATION_GAIN,
		// 外汇引起的(FX_VALUATION_GAIN,FX_VALUATION_LOSS),FX_RESERVE_CAP

		priorlotCost = costWrap.getPriordayCost().get(0);
		asofdayCost = costWrap.getAsofdayCost().get(0);
		int groupnum = costWrap.getGlgroupnum();

		l_priordayValuation = RoundUtil.roundMoney2Dec(priorlotCost.getgMkValue() - priorlotCost.getgCost());
		f_priordayValuation = RoundUtil.roundMoney2Dec(
				RoundUtil.roundMoney2Dec(priorlotCost.getgMkValue()*priorlotCost.getPrinFxRate())
				- priorlotCost.getgCostFunc());

		l_asofdayValuation = RoundUtil.roundMoney2Dec(asofdayCost.getgMkValue() - asofdayCost.getgCost());
		f_asofdayValuation = RoundUtil.roundMoney2Dec(
				RoundUtil.roundMoney2Dec(asofdayCost.getgMkValue() * asofdayCost.getPrinFxRate())
				- asofdayCost.getgCostFunc());
		/****** start 计算因价格引起的估值 *****/
		/*
		 * l_p_valuation = RoundUtil.roundMoney2Dec(asofdayCost.getAmount()
		 * costWrap.getAsofdayPrice()) -
		 * RoundUtil.roundMoney2Dec(asofdayCost.getAmount()
		 * costWrap.getPriordayPrice());
		 * 
		 * f_p_valuation =
		 * RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(asofdayCost
		 * .getAmount() costWrap.getAsofdayPrice())*costWrap.getPriordayRate())
		 * -
		 * RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(asofdayCost.getAmount
		 * () costWrap.getPriordayPrice())*costWrap.getPriordayRate());
		 */
		/****** end 计算因价格引起的估值 *****/

		l_diff = l_asofdayValuation - l_priordayValuation;

		f_diff = f_asofdayValuation - f_priordayValuation;

		// l_diff - l_p_valuation; //外汇损益=估值-价格损益
		// f_diff - f_p_valuation; //外汇损益=估值-价格损益

		generalCooa = new GeneralCooa();
		
		if(asofdayCost.getLsindicator()==LongShortType.longTerm){
			if (f_diff > 0) { // 赚了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.CARRY_VAL_ADJ));
	
				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.VALUATION_GAIN));
	
			} else if(f_diff<0){ // 亏了或平了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.VALUATION_LOSS));
	
				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.CARRY_VAL_ADJ));
			}
		}else{
			if (f_diff < 0) { // 赚了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.BOOK_VALUE_ADJ_HEDGE));
	
				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.FOREX_UNREAL_GL_SHORT));
	
			} else if(f_diff>0){ // 亏了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.FOREX_UNREAL_GL_SHORT));
	
				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.BOOK_VALUE_ADJ_HEDGE));
			}
		}
		
		IssueInfo sec=costWrap.getSec();
		if(sec.getIssueType().equals(AssetTypes.COMMON_STOCK)) {
			double lastCloseAmount=priorlotCost.getAmount();
			if(priorlotCost!=null) {
				double last_l_v_gainloss=RoundUtil.roundMoney2Dec(priorlotCost.getgMkValue()-priorlotCost.getgCost());
				double last_f_v_gainloss=RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(priorlotCost.getgMkValue()*priorlotCost.getPrinFxRate())-priorlotCost.getgCostFunc());
				List<Tran> tranList=costWrap.getTranList();
				for(Tran tran:tranList) {
					if(tran.getTrantype()==TransTypes.SALE || tran.getTrantype()==TransTypes.TENDER_OFFER) {
						//卖出带出的估增=卖出数量/当前总数*前一天的估增
						double off_l_diff=RoundUtil.roundMoney2Dec(last_l_v_gainloss*tran.getAmount()/lastCloseAmount);
						double off_f_diff=RoundUtil.roundMoney2Dec(last_f_v_gainloss*tran.getAmount()/lastCloseAmount);
						//获得剩余估增
						last_l_v_gainloss=RoundUtil.roundMoney2Dec(last_l_v_gainloss-off_l_diff);
						last_f_v_gainloss=RoundUtil.roundMoney2Dec(last_f_v_gainloss-off_f_diff);
						
					
						if (off_f_diff < 0) { // 赚了。
							jobCom.saveGL(generalCooa, DBCR.CREDIT,
									Math.abs(off_f_diff), 
									Math.abs(off_l_diff), 
									jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CARRY_VAL_ADJ));
				
							jobCom.saveGL(generalCooa, DBCR.DEBIT,
									Math.abs(off_f_diff), 
									Math.abs(off_l_diff), 
									jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_GAIN));
				
						} else if(off_f_diff>0){ // 亏了或平了。
							jobCom.saveGL(generalCooa, DBCR.CREDIT,
									off_f_diff, 
									off_l_diff, 
									jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_LOSS));
				
							jobCom.saveGL(generalCooa, DBCR.DEBIT,
									off_f_diff, 
									off_l_diff, 
									jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CARRY_VAL_ADJ));
						}
					}
					
					SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
					boolean buysalefalg=secBasic.buySaleFlag();
					//获得剩余数量
					if(buysalefalg) {
						lastCloseAmount=lastCloseAmount+tran.getAmount();
					}else {
						lastCloseAmount=lastCloseAmount-tran.getAmount();
					}
				}
			}
		}
		
		if(f_diff!=0){
			jobCom.genVoucherDetails(generalCooa, costWrap.getTranClo(),
					asofdayCost.getPrice(), asofdayCost.getPrinFxRate());
		}
		
		return generalCooa;
	}

	@Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData)
			throws JNLException {
		return null;
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec,TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		// TODO Auto-generated method stub
		return clo;
	}

	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		/*List<SmfDiv> divList=jobDAO.loadSmfDivList(ledgerDate);
		for(SmfDiv div:divList){
			//获取记录日那天,该股的持仓
			TranCloType tranCloType=new TranCloType(portnum, div.getCusip(), div.getRecordDate());
			List<TranClo> cloList=secPositionCenter.getSecClo(tranCloType, false);
			if(cloList.size()>0){
				ResultContainer container=new ResultContainer();
				if(div.getCaType()==CorpActionTypes.CASHPAYMENTSONLY){
					IssueInfo sec=jobDAO.loadIssueByCusip(div.getCusip());
					HashMap<String, String> msgHM=msgPackage.stockCashDiv("ADD","",String.valueOf(portnum),sec.getSecurityid(),DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()), 
							DateUtil.convertD2YYYY_MM_DD(div.getExDate()), DateUtil.convertD2YYYY_MM_DD(div.getPayDate()), String.valueOf(div.getRate()));
					MsgContainer msgContainer=new MsgContainer();
					msgContainer.setFieldsHM(msgHM);
					msgContainer.setUserID(userID);
					container=tradeService.operateTran(msgContainer);
				}else if(div.getCaType()==CorpActionTypes.STOCKDIVIDEND){
					IssueInfo sec=jobDAO.loadIssueByCusip(div.getCusip());
					HashMap<String, String> msgHM=msgPackage.stockDividend("ADD","",String.valueOf(portnum),sec.getSecurityid(), DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()), 
							DateUtil.convertD2YYYY_MM_DD(div.getExDate()),String.valueOf(div.getRate()));
					MsgContainer msgContainer=new MsgContainer();
					msgContainer.setFieldsHM(msgHM);
					msgContainer.setUserID(userID);
					container=tradeService.operateTran(msgContainer);
				}else{
					container.setCorrectFlag(false);
				}
				
				if(container.isCorrectFlag()){
					int tanum=container.getObjID();
					commfuncService.autoRecord(ledgerDate,portnum,tanum);
				}
			}
		}*/
		return null;
	}
}
