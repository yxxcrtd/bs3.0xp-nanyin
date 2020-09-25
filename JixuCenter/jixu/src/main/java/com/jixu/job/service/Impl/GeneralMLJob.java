package com.jixu.job.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.JobParam;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.GeneralCooa;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.service.GeneralJNLService;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranSum;

@Service("generalJnlMLjob")
public class GeneralMLJob extends GeneralBaseJob {

	private GeneralJNLService genjnlServiceML;


	public GeneralJNLService getGenjnlServiceML() {
		return genjnlServiceML;
	}


	public void setGenjnlServiceML(GeneralJNLService genjnlServiceML) {
		this.genjnlServiceML = genjnlServiceML;
	}


	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) {
		// TODO Auto-generated method stub
		//List<GLCOOABase> genJnlList = new ArrayList<GLCOOABase>();
		
		/*try {
			*//****001股票估值********//*
			//secVJNL(jobParam, genJnlList, portfolio);
			
			*//****002 银行存款估值 **************//*
			//super.bankBalanceVJNL(jobParam, genJnlList);
			
			*//****003 证券清算款估值 **************//*
			//super.secCashVJNL(jobParam, genJnlList);
			
		} catch (JNLException e) {
			//
			return null;
		}
		getPrintjnl().printJNL(genJnlList);
		//SimpleDataLoadService.loadTestData(genJnlList, jobParam.getDate());
		return null;*/
		return null;
	}

	
	private void secVJNL(JobParam jobParam, List<GLCOOABase> genJnlList, PortfolioHdr portfolio) throws JNLException {
		//A1*P2*R2 - A1*P1*R1 (总的损益)= (A1*P2*R2 - A1*P1*R2) (价格引起)+ (A1*P1*R2 - A1*P1*R1) (汇率变化引起)
		//公式：估值=今天的市值-昨天的市值
		//(估值=价格损益+汇率损益)
		// VALUATION_LOSS, CARRY_VAL_ADJ, VALUATION_GAIN,
		//外汇引起的(FX_VALUATION_GAIN,FX_VALUATION_LOSS),FX_RESERVE_CAP
		String chartnum = "1"; 
		int groupnum = 1;
		
		GeneralCooa generalCooa = null;
		
		List<STCostWrapObj> stcostWrapList = getGenjnlServiceML().loadSecGenerelJNLRecord(portfolio, jobParam.getDate(), "0");
		STCostWrapObj costwrapMLObj = null;
		MiniCost priorDayV = null;
		MiniCost asofdayV = null;
		
		if (stcostWrapList != null) {
			for (STCostWrapObj costWrap : stcostWrapList) {
				
				//if (costWrap.getCusip() != 174712802) {
					//continue;
				//}
				
				costwrapMLObj = costWrap;
				
				groupnum = costWrap.getGlgroupnum();
				generalCooa = new GeneralCooa();
				//generalCooa.setCusip(costWrap.getCusip());
				
				//计算前一日每个LOT的估值
				//昨天的估值要在今天冲销，所以借转贷，贷转借.
				//priorDayV = calculateGainLoss(costwrapMLObj.getPriordayCost(),
				//		costwrapMLObj.getPriordayPrice(),costwrapMLObj.getPriordayRate(),-1);
				//计算当天每个LOT的估值
				//asofdayV = calculateGainLoss(costwrapMLObj.getAsofdayCost(),
				//		costwrapMLObj.getAsofdayPrice(),costwrapMLObj.getAsofdayRate(),1);
				
				//generalCooa.setAmount(asofdayV.amount);
				
				/***saveGL(GLCOOABase glcooa, JournalBaseObj jnlobj,
				double dcash, double dfxcash, double ccash, double cfxcash, String acctnum) {**/
				if (priorDayV.p_funcloss + asofdayV.p_funcgain > 0) {
					jobCom.saveGL(generalCooa,
							RoundUtil.roundMoney2Dec(asofdayV.p_funcgain),
							RoundUtil.roundMoney2Dec(asofdayV.p_localgain),
							RoundUtil.roundMoney2Dec(priorDayV.p_funcloss),
							RoundUtil.roundMoney2Dec(priorDayV.p_localloss),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_GAIN));
				}
				if (priorDayV.p_funcgain + asofdayV.p_funcloss > 0) {
					jobCom.saveGL(generalCooa,
							RoundUtil.roundMoney2Dec(asofdayV.p_funcloss),
							RoundUtil.roundMoney2Dec(asofdayV.p_localloss),
							RoundUtil.roundMoney2Dec(priorDayV.p_funcgain),
							RoundUtil.roundMoney2Dec(priorDayV.p_localgain),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_LOSS));
				}
				
				jobCom.saveGL(generalCooa,
					RoundUtil.roundMoney2Dec(asofdayV.p_funcgain + priorDayV.p_funcgain),
					RoundUtil.roundMoney2Dec(asofdayV.p_localgain + priorDayV.p_localgain),
					RoundUtil.roundMoney2Dec(asofdayV.p_funcloss + priorDayV.p_funcloss),
					RoundUtil.roundMoney2Dec(asofdayV.p_localloss+ priorDayV.p_localloss),
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CARRY_VAL_ADJ));
				
				if (priorDayV.r_funcgain + asofdayV.r_funcloss > 0) {
					jobCom.saveGL(generalCooa,
						RoundUtil.roundMoney2Dec(asofdayV.r_funcloss),
						RoundUtil.roundMoney2Dec(0),
						RoundUtil.roundMoney2Dec(priorDayV.r_funcgain),
						RoundUtil.roundMoney2Dec(0),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_LOSS));
				}
				if (priorDayV.r_funcloss + asofdayV.r_funcgain > 0) {
					jobCom.saveGL(generalCooa,
							RoundUtil.roundMoney2Dec(asofdayV.r_funcgain),
							RoundUtil.roundMoney2Dec(0),
							RoundUtil.roundMoney2Dec(priorDayV.r_funcloss),
							RoundUtil.roundMoney2Dec(0),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_GAIN));
				}
				
				if (asofdayV.r_funcgain + priorDayV.r_funcgain + asofdayV.r_funcloss + priorDayV.r_funcloss > 0) {
					jobCom.saveGL(generalCooa,
							RoundUtil.roundMoney2Dec(priorDayV.r_funcgain + priorDayV.r_funcloss),
							RoundUtil.roundMoney2Dec(0),
							RoundUtil.roundMoney2Dec(asofdayV.r_funcgain + asofdayV.r_funcloss),
							RoundUtil.roundMoney2Dec(0),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_RESERVE_CAP));
				}
				genJnlList.add(generalCooa);
			}
		}
	}
	
	private MiniCost calculateGainLoss(List<TranSum> costList, double aprice, 
			double arate, int swith) {
		//A2为今天Amount.P2为今天价格。GCOST(当天剩余成本),R2为今天汇率.R1交易Lot交易时汇率
		//价格估值=A2*P2*R2 - GCOST*R2.   //汇率估值=GCOST*R2 - GCOST*R1
		TranSum costObj = null;
		double gap = 0.0;
		MiniCost miniCost = new MiniCost();
		for (int i = 0; i < costList.size();i++) {
			costObj = costList.get(i);
			
			gap = RoundUtil.roundMoney2Dec(costObj.getAmount()*aprice)
			- RoundUtil.roundMoney2Dec(costObj.getgCost());
			if (gap*swith > 0) {
				miniCost.p_localgain += Math.abs(gap*swith);
			} else {
				miniCost.p_localloss += Math.abs(gap*swith);
			}
			
			gap = RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(costObj.getAmount()*aprice) * arate)
			- RoundUtil.roundMoney2Dec(costObj.getgCost() * arate);
			if (gap*swith > 0) {
				miniCost.p_funcgain += Math.abs(gap*swith);
			} else {
				miniCost.p_funcloss += Math.abs(gap*swith);
			}
			
			//gap = RoundUtil.roundMoney2Dec(costObj.getGcost() * arate)
			//- RoundUtil.roundMoney2Dec(costObj.getGcost() * costObj.getFxrate());  //fxrate为买入时的汇率
			//重新构架凭证逻辑,修改结构 pan wei,2017/01/22
			
			if (gap*swith > 0) {
				miniCost.r_funcgain += Math.abs(gap*swith);
			} else {
				miniCost.r_funcloss += Math.abs(gap*swith);
			}
			
			miniCost.amount += costObj.getAmount();
		}
		
		//合并汇率的LOSS和GAIN
		if (miniCost.r_funcgain > miniCost.r_funcloss) {
			miniCost.r_funcgain = RoundUtil.roundMoney2Dec(miniCost.r_funcgain) - RoundUtil.roundMoney2Dec(miniCost.r_funcloss);
			miniCost.r_funcloss = 0.0;
		} else {
			miniCost.r_funcloss = RoundUtil.roundMoney2Dec(miniCost.r_funcloss) - RoundUtil.roundMoney2Dec(miniCost.r_funcgain);
			miniCost.r_funcgain = 0.0;
		}
		
		return miniCost;
	}
	
	class MiniCost {
		public double p_localgain = 0.0; //价格引起
		public double p_localloss = 0.0;//价格引起
		public double p_funcloss = 0.0;//价格引起
		public double p_funcgain = 0.0;//价格引起
		public double r_funcloss = 0.0; //汇率引起
		public double r_funcgain = 0.0;//汇率引起
		public double amount = 0.0;
	}
	
	public boolean isallLotGainOrLoss(MiniCost cost) {
		return false;
	}
		
}
