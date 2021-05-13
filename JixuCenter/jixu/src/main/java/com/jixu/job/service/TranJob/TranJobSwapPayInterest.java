package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jixu.common.type.TransTypes;
import com.joyintech.framework.util.StringUtil;
import org.springframework.stereotype.Service;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.types.TranCloType;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.Voucher;

/**
 * @ClassName AssetJobBasic
 * @author Pan Wei
 * @Date 2017年1月18日 下午3:43:21
 * @Description []
 */

@Service
public class TranJobSwapPayInterest extends TranJobBasic {

	@Resource(name="secPositionCenter")
	private SecPositionCenter position;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio,
			String desc, Date ledgerDate) throws JNLException {
		IssueInfo sec = tranbasic.getCusip();
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		//结转利息凭证
		double gainloss=0;
		int glgroup=0;
		String chartnum=portfolio.getCoaahdr();
		
		TranCloType clotype=new TranCloType(tranbasic.getPortfolio(), sec.getId(), tranbasic.getDivBasicDate());
		//获取基准日的头寸
		List<TranClo> cloList=position.getSecClo(clotype, false);
		
		for(CashFlowObj cashobj:cashflowObjList){
			CashCooa cashCooa = new CashCooa(); 
			Voucher voucher=new Voucher();
			
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			
			double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
			gainloss=RoundUtil.roundMoney2Dec(func_cash-cashobj.getOrigcash());
			//总账组
			glgroup=cashobj.getCusip().getCoaaGroup();
				
			TranClo clo=null;
			for(TranClo tmpclo:cloList) {
				if(cashobj.getDbcr().equals(DBCR.DEBIT) && tmpclo.getLsindicator()==LongShortType.longTerm) {
					clo=tmpclo;
					break;
				}else if(cashobj.getDbcr().equals(DBCR.CREDIT) && tmpclo.getLsindicator()==LongShortType.ShortTerm) {
					clo=tmpclo;
					break;
				}
			}
				
			int inNum=0;
			int ingcNum=0;	//轧差科目总账栏
			int outNum=0;
			int outgcNum=0;	//支轧差总账栏
			if (clo != null){
				double ysyflx=clo.getAccrued();	//获取基准头寸日常计提的利息
				double ysyfunit=clo.getAmount();	//获取基准头寸的数量
				double cjje=cashobj.getCash();
				if(tranbasic.isSetAllIntatmatFlag() && cashobj.getDbcr().equals(DBCR.DEBIT)
						|| tranbasic.isSetAllIntatmatFlag2() && cashobj.getDbcr().equals(DBCR.CREDIT)){
					//判断多空双方分别是否需要结清利息
					if(!(cashobj.getTranType() == TransTypes.SWAP_Pay_Interest)){
						cjje=ysyflx*tranbasic.getAmount()/ysyfunit;	//冲减金额
					}
				}
				double gcje=RoundUtil.roundMoney2Dec(cashobj.getCash()-cjje);
				double gcjeFunc=RoundUtil.roundMoney2Dec(gcje*cashobj.getAsofdayRate());

				if(cashobj.getPostType()==PostType.Income){
					outNum=GLACCT.INC_PAID;		//其它应付款-利息
					outgcNum=GLACCT.ACCRUED_INT_EXP_SHORT;
					inNum=GLACCT.INC_REC;		//其它应收款-利息
					ingcNum=GLACCT.INC_ACCRUAL;

					String cashglcode=bkcashacct.getCashglcode();
					checkCashAcctValiable(cashglcode, bkcashacct.getBankdesc(), BankAccountType.Cash_Account);

					if (cashobj.getDbcr().equals(DBCR.DEBIT)) { //收入
						jobCom.saveGL(cashCooa,
								DBCR.DEBIT,
								RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate()),
								cashobj.getCash(),
								cashglcode);

						jobCom.saveGL(cashCooa,
								DBCR.CREDIT,
								RoundUtil.roundMoney2Dec(cjje*cashobj.getAsofdayRate()),
								cjje,
								jobCom.loadGLAcctnum(chartnum, glgroup,inNum));
						//银行存款已实现汇兑

						//轧差金额
						jobCom.saveGL(cashCooa,
								DBCR.CREDIT,
								gcjeFunc,
								gcje,
								jobCom.loadGLAcctnum(chartnum, glgroup,ingcNum));

						if (gainloss > 0) {
							jobCom.saveGL(cashCooa,
									DBCR.CREDIT,
									RoundUtil.roundMoney2Dec(gainloss),
									0,
									jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL));
						} else if(gainloss<0){
							jobCom.saveGL(cashCooa,
									DBCR.DEBIT,
									RoundUtil.roundMoney2Dec(Math.abs(gainloss)),
									0,
									jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL));
						}
						cashCooa.setTableID("1811");

					} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出
						jobCom.saveGL(cashCooa,
								DBCR.DEBIT,
								RoundUtil.roundMoney2Dec(cjje*cashobj.getAsofdayRate()),
								cjje,
								jobCom.loadGLAcctnum(chartnum, glgroup,outNum));

						jobCom.saveGL(cashCooa,
								DBCR.CREDIT,
								RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getBkavgrate()),
								cashobj.getCash(), cashglcode);
						//轧差金额
						jobCom.saveGL(cashCooa,
								DBCR.DEBIT,
								gcjeFunc,
								gcje,
								jobCom.loadGLAcctnum(chartnum, glgroup,outgcNum));

						//清算款已实现汇兑
						if (gainloss < 0) {
							jobCom.saveGL(cashCooa,
									DBCR.CREDIT,
									RoundUtil.roundMoney2Dec(Math.abs(gainloss)),
									0,
									jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL));
						} else if(gainloss>0){
							jobCom.saveGL(cashCooa,
									DBCR.DEBIT,
									RoundUtil.roundMoney2Dec(gainloss),
									0,
									jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL));
						}
						cashCooa.setTableID("1812");
					}
					jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
					voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
					cashCooa.setVoucher(voucher);
					cooaList.add(cashCooa);
				}
			}
		}
		return cooaList;
	}

	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
	}

	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
	}

	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
