package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.dao.intf.TranFeeFollowCloDao;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranFeeDef;
import com.orm.obj.TranFeeFollowClo;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		GenFeeFollowFee
 * @author  		Pan Wei 
 * @Date 			2018年6月5日 下午11:35:19 	
 * @Description		[费用模块配置，证券根据头寸每天计提的费用/收益。今天TranFeeFollowClo的值减去昨天TranFeeFollowClo的值]  
 */

@Service
public class GenFeeFollowFee {
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="jobCommonImpl")
	private JobCommonImpl jobCom;
	
	@Autowired
	private TranFeeDefDao tranFeeDefDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private TranFeeFollowCloDao tranFeeFollowCloDao;
	
	@Autowired
	private IncomeExpenseCal incomeExpenseCal;
	
	public List<GLCOOABase> genTrannFeeVoucherDetails(String portnum, Date ledgerDate) throws JNLException{
		List<GLCOOABase> voucherList=new ArrayList<GLCOOABase>();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		List<TranFeeFollowClo> todayfeeList=tranFeeFollowCloDao.getFollowFeeList(portnum, ledgerDate);
		if(todayfeeList.size()>0){
			List<TranFeeFollowClo> oldfeeList=tranFeeFollowCloDao.getFollowFeeList(portnum, portfolio.getLastGLClose());
			String chartnum=portfolio.getCoaahdr();
			for(TranFeeFollowClo bean:todayfeeList){
				IssueInfo sec=issueInfoDao.loadIssueByCusip(bean.getCusip());
				double fee=bean.getFeeamount();
				double fxrate=commfuncService.loadFxrate(portnum, sec.getPriCur(), portfolio.getCurID(), ledgerDate);
				double lastfxrate=commfuncService.loadFxrate(portnum, sec.getPriCur(), portfolio.getCurID(), portfolio.getLastGLClose());
	
				double funcfee=RoundUtil.roundMoney2Dec(fee*fxrate);
				for(TranFeeFollowClo oldfee:oldfeeList){
					if(bean.getCusip().equals(oldfee.getCusip()) && bean.getLotnum()==oldfee.getLotnum() && bean.getTranFeeDefID()==oldfee.getTranFeeDefID()){
						fee=RoundUtil.roundMoney2Dec(fee-oldfee.getFeeamount());
						funcfee=RoundUtil.roundMoney2Dec(funcfee-RoundUtil.roundMoney2Dec(oldfee.getFeeamount()*lastfxrate));
						break;
					}
				}
				
				TranFeeDef feedef=tranFeeDefDao.loadTranFeeDef(bean.getTranFeeDefID());
				
				if(fee!=0){
					GLCOOABase feeCooa=new GLCOOABase();
					jobCom.saveGL(feeCooa, 
							DBCR.DEBIT,
							funcfee, 
							fee, 
							jobCom.loadGLAcctnum(chartnum, sec.getCoaaGroup(), feedef.getFeeGlCol()));
					
					jobCom.saveGL(feeCooa,
							DBCR.CREDIT,
							funcfee, 
							fee, 
							jobCom.loadGLAcctnum(chartnum, sec.getCoaaGroup(), feedef.getRcpyGlCol()));
					
					Voucher voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.EXPEND, "", feedef.getFeename()+"-"+sec.getSecurityid()+"-"+bean.getLotnum());
					feeCooa.setVoucher(voucher);
					
					jobCom.genVoucherDetails(feeCooa, portfolio, bean.getAmount(), sec.getSecurityid());
					voucherList.add(feeCooa);
				}
			}
		}
		
		return voucherList;
	}
}
