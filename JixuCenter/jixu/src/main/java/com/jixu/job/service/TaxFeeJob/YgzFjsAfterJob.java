package com.jixu.job.service.TaxFeeJob;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;
import com.orm.obj.YGZTaiZhang;

/** 
 * @ClassName 		ZzsAfterJob
 * @author  		Pan Wei 
 * @Date 			2018年5月2日 下午2:25:10 	
 * @Description		[]  
 */

@Service
public class YgzFjsAfterJob extends AfterJobAbstract{
	
	@Override
	public String jobName() {
		return "营改增-附加税";
	}
	
	@Override
	public GLCOOABase exeAfterJob(int userID, PortExpenseCfg cfg, List<AcctDCBalance> balList, Date asOfDate) throws JNLException {
		//首先删除相关记录
		deleteJobResult(cfg, asOfDate);
		PortfolioHdr portfolio=commfuncService.loadPortfolio(cfg.getPortfolio().getId());
		//获取当天台账
		List<YGZTaiZhang> todayTaiZhangList=ygzTaiZhangDao.loadYGZTaiZhang(cfg.getPortfolio().getId(), DateUtil.ConvertUtil2Sql(asOfDate));
		
		double gainloss=0;
		for(YGZTaiZhang tz:todayTaiZhangList){
			if(tz.getCusip().equals("") && tz.getIssueType().equals("0") && tz.getRpSecType()==0){
				gainloss=gainloss+tz.getDrsffse();
			}
		}
		
		double tax=RoundUtil.roundMoney2Dec(gainloss*cfg.getExpenseAmount());
		
		if(tax==0){
			return null;
		}
		
		double syrtaxye=0;
		double drtaxye=0;
		for(AcctDCBalance bacl:balList){
			if(bacl.getSecid()==null || bacl.getSecid().equals("")){
				if(bacl.getAcctnum().equals(cfg.getDrAcct())){
					syrtaxye=bacl.getDrFuncAmt()-bacl.getCrFuncAmt();
				}
			}
		}
		
		drtaxye=RoundUtil.roundMoney2Dec(tax+syrtaxye);
		
		if(drtaxye<0){
			tax=-1*syrtaxye;
		}
		
		GLCOOABase expenseCooa=new GLCOOABase();
		jobCom.saveGL(expenseCooa, 
				DBCR.DEBIT, 
				tax,
				tax, 
				cfg.getDrAcct());
		
		jobCom.saveGL(expenseCooa, 
				DBCR.CREDIT, 
				tax,
				tax, 
				cfg.getCrAcct());
		Voucher voucher=jobCom.genVoucher(asOfDate, portfolio, JournalTypes.EXPEND, "", jobName());
		expenseCooa.setVoucher(voucher);
		
		jobCom.genVoucherDetails(expenseCooa, portfolio, RoundUtil.roundMoney2Dec(gainloss), cfg.getPortFeeName());
		
		return expenseCooa;
	}
	
	@Override
	public String jobDesc() {
		String desc="计算公式: 当日基准科目发生额*税率  \r\n"
				+ "如果当日税费+上一日税费余额<0，则当日税费发生额=-1*上一日税费余额";
		return desc;
	}
}
