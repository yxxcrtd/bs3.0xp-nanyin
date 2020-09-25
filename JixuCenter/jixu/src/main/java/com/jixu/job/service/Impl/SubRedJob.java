package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.NavDao;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.JNLJob;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SubRedWrapObj;
import com.jixu.journal.service.SubRedJNLService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.Nav;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranSubRed;
import com.orm.obj.Voucher;

@Service("subredJNLJob")
public class SubRedJob extends JNLJob{
	
	@Resource(name="subredJNLService")
	private SubRedJNLService subredJNLService;
	
	@Resource(name="genTranFee")
	private GenTranFee genTranFee;
	
	@Autowired
	private NavDao navDao;
	
	//private static Logger logger = Logger.getLogger(SubRedJob.class);
	
	public SubRedJob() {
		jobType=JobsType.SUBRED;
		journalType=JournalTypes.SUBRED;
	}
	
	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException{
		List<GLCOOABase> subredJnlList = new ArrayList<GLCOOABase>();
		List<SubRedWrapObj> subRedWrapList = null;
		
		subRedWrapList = subredJNLService.loadSubReadTran(jobParam.getDate(),jobParam.getPortfolio());
		processSubRed(jobParam, subRedWrapList, subredJnlList, getPortfolio(jobParam.getPortfolio()));
		
		return subredJnlList;
	}
	
	private void processSubRed(JobParam jobParam, List<SubRedWrapObj> subRedWrapList, List<GLCOOABase> subredJnlList, PortfolioHdr portfolio)  throws JNLException{
		String chartnum=portfolio.getCoaahdr();
		int groupnum=portfolio.getCoaaGroup().getGroupNum();
		double capital=0;			//资本
		double capitalRG=0;			//已实现收益
		double caoitalUnReal=0;		//未实现收益
		double income=0;			//收入
		double expenses=0;			//开支
		
		
		Nav nav=navDao.loadLatestNAV(jobParam.getPortfolio(), jobParam.getDate());
		//净资产=资本+已实现收益+未实现收益+收入-开支
		
		if(nav != null){
			//第一天做账，没有nav信息
			capital=nav.getCapital();			//资本
			capitalRG=nav.getCapitalRG();		//已实现收益
			caoitalUnReal=nav.getCapitalUnreal();//未实现收益
			income=nav.getIncome();				//收入
			expenses=nav.getExpenses();			//开支
		}
		
		double netAsset=capital+capitalRG-caoitalUnReal+income-expenses;	//净资产
		//double shares=nav.getSharesOutStanding();							//份额
		for(SubRedWrapObj subredwrap:subRedWrapList) {
			TranSubRed subRedTran=subredwrap.getTranSubRed();
			GLCOOABase subredCooa=new GLCOOABase();
			String desc=SecurityCoreFactory.getAllTranTypeHM().get(subRedTran.getTranType());
			
			if(subRedTran.getTranType()==TransTypes.SUB || subRedTran.getTranType()==TransTypes.Share_SwitchIn || subRedTran.getTranType()==TransTypes.Share_Div2Share){
				double subAsset=subRedTran.getgCost();	//申购款
				double subAssetFunc=RoundUtil.roundMoney2Dec(subAsset*subRedTran.getFxrate());
				//double subCash=subRedTran.getCash();
				double subShares=subRedTran.getAmount();//实收基金
				
				List<TranBasicFee> tranFeeList=subredwrap.getTranFeeList();
				genTranFee.genTrannFeeVoucherDetails(subredCooa, tranFeeList, chartnum, groupnum);
				
				double ch_unreal_equal_sub=0;
				if(netAsset!=0){
					ch_unreal_equal_sub=RoundUtil.roundMoney2Dec(subAssetFunc*(-1*caoitalUnReal/netAsset));	//损益平准金未实现
				}
				double ch_real_equal_sub=RoundUtil.roundMoney2Dec(subAssetFunc-subShares-ch_unreal_equal_sub);						//损益平准金已实现
				
				int glacct=GLACCT.RECEIV_FUND_SHARES_SOLD;
				if(subRedTran.getTranType()==TransTypes.Share_SwitchIn){
					glacct=GLACCT.share_RECEIV_FUND_SHARES_switch_in;
				}else if(subRedTran.getTranType()==TransTypes.Share_Div2Share){
					glacct=GLACCT.share_Div2Share;
				}
				
				jobCom.saveGL(subredCooa, 
						DBCR.DEBIT, 
						subAssetFunc,
						subAsset, 
						jobCom.loadGLAcctnum(chartnum, groupnum, glacct));
				
				jobCom.saveGL(subredCooa,
						DBCR.CREDIT, 
						subShares, 
						subShares, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_STOCK_PAR));
				
				jobCom.saveGL(subredCooa,
						DBCR.CREDIT, 
						ch_real_equal_sub, 
						ch_real_equal_sub, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.share_real_equal_sub));
				
				jobCom.saveGL(subredCooa,
						DBCR.CREDIT, 
						ch_unreal_equal_sub, 
						ch_unreal_equal_sub, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.share_unreal_equal_sub));
			
				Voucher voucher=jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.SUBRED, subRedTran.getTanum().getId(), desc);
				subredCooa.setVoucher(voucher);
				
				jobCom.genSubRedVoucherDetails(subredCooa,subRedTran);
				subredJnlList.add(subredCooa);
			}else{
				double redAsset=subRedTran.getgCost();	//赎回款
				double redAssetFunc=RoundUtil.roundMoney2Dec(redAsset*subRedTran.getFxrate());
				double redShares=subRedTran.getAmount();//实收基金
				double ch_real_equal_red=0;				//损益平准金已实现--赎回
				double ch_unreal_equal_red=0;			//损益平准金未实现--赎回
				
				List<TranBasicFee> tranFeeList=subredwrap.getTranFeeList();
				
				genTranFee.genTrannFeeVoucherDetails(subredCooa, tranFeeList, chartnum, groupnum);
				
				int glacct=GLACCT.PAY_FUND_SHARES_REDEEM;
				if(subRedTran.getTranType()==TransTypes.Share_SwitchOut){
					glacct=GLACCT.share_PAY_FUND_SHARES_switch_out;
				}
				
				jobCom.saveGL(subredCooa,
						DBCR.CREDIT, 
						redAssetFunc, 
						redAsset, 
						jobCom.loadGLAcctnum(chartnum, groupnum, glacct));
				
				ch_unreal_equal_red=RoundUtil.roundMoney2Dec(redAssetFunc*(-1*caoitalUnReal/netAsset));
				ch_real_equal_red=RoundUtil.roundMoney2Dec(redAssetFunc-redShares-ch_unreal_equal_red);
				
				jobCom.saveGL(subredCooa, 
						DBCR.DEBIT, 
						redShares, 
						redShares, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_STOCK_PAR));
			
				jobCom.saveGL(subredCooa,
						DBCR.DEBIT, 
						ch_real_equal_red, 
						ch_real_equal_red, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.share_real_equal_red));
				
				jobCom.saveGL(subredCooa,
						DBCR.DEBIT, 
						ch_unreal_equal_red, 
						ch_unreal_equal_red, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.share_unreal_equal_red));
			
				Voucher voucher=jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.SUBRED, subRedTran.getTanum().getId(), desc);
				subredCooa.setVoucher(voucher);
				
				jobCom.genSubRedVoucherDetails(subredCooa, subRedTran);
				subredJnlList.add(subredCooa);
			}
		}
	}
}
