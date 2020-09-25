package com.jixu.job.service.TaxFeeJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.mvc.control.service.TradeService;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		ZzsAfterJob
 * @author  		Pan Wei 
 * @Date 			2018年5月2日 下午2:25:10 	
 * @Description		[]  
 */

@Service
public class Hbjj1NavAfterJob extends AfterJobAbstract{
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Override
	public String jobName() {
		return "货币基金净值回归面值";
	}
	
	@Override
	public GLCOOABase exeAfterJob(int userID, PortExpenseCfg cfg, List<AcctDCBalance> balList, Date asOfDate) throws JNLException {
		PortfolioHdr portfolio=cfg.getPortfolio();
		if(!portfolio.isJzgyFlag()){
			return null;
		}
		
		String portnum=portfolio.getId();
		
		double asset=0;
		double lib=0;
		for(AcctDCBalance bal:balList){
			double value=bal.getDrFuncAmt()-bal.getCrFuncAmt();
			if(bal.getAcctnum().startsWith("1") || bal.getAcctnum().startsWith("3")){
				asset=asset+value;
			}else if(bal.getAcctnum().startsWith("2")){
				lib=lib+value;
			}
		}
		
		//计算出临时净资产
		double netAsset=RoundUtil.roundMoney2Dec(asset+lib);
		double sharesOutStanding=commfuncService.calFundShares(portnum, asOfDate);
		
		//得出需要红利转份额的金额
		double diff=RoundUtil.roundMoney2Dec(netAsset-sharesOutStanding);
		
		if(diff!=0){
			GLCOOABase expenseCooa=new GLCOOABase();
			jobCom.saveGL(expenseCooa, 
					DBCR.DEBIT, 
					diff,
					diff, 
					cfg.getDrAcct());
			
			jobCom.saveGL(expenseCooa, 
					DBCR.CREDIT, 
					diff,
					diff, 
					cfg.getCrAcct());
			Voucher voucher=jobCom.genVoucher(asOfDate, portfolio, JournalTypes.PURSALEJNL, "", jobName());
			expenseCooa.setVoucher(voucher);
			
			jobCom.genVoucherDetails(expenseCooa, portfolio, diff, cfg.getPortFeeName());
			
			
			return expenseCooa;
		}else{
			return null;
		}
	}
	
	@Override
	public String jobDesc() {
		String desc="货币基金:每天货币基金单位净值归一。产生一笔凭证，把营收调整到红利再投科目";
		return desc;
	}
}
