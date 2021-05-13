package com.jixu.job.service.TaxFeeJob;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.GlCloseDao;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.service.TaxFeeJobService;
import com.jixu.job.service.Impl.JobCommonImpl;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.GlClose;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		BankZGGlrbc
 * @author  		Pan Wei 
 * @Date 			2018年6月2日 下午2:24:16 	
 * @Description		[]  
 * @return  
 */

@Service
public class BankZGGlrbc  implements TaxFeeJobService{
	
	@Autowired
	private GlCloseDao glCloseDao;
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="jobCommonImpl")
	private JobCommonImpl jobCom;
	
	@Override
	public GLCOOABase executeJob(int userID, List<AcctDCBalance> balList, PortExpenseCfg cfg, Date asOfDate) throws JNLException {
		PortfolioHdr portfolio=commfuncService.loadPortfolio(cfg.getPortfolio().getId());
		Date lastNavDate=portfolio.getLastGLClose();
		Date startDate=null;
		if(lastNavDate!=null){
			startDate=DateUtil.getNextDay(lastNavDate);
		}else{
			startDate=portfolio.getLastTranClose();
		}
		
		String baseAcct=cfg.getBaseAcct();
		String[] baseAccts=baseAcct.split(",");
		String portnum=cfg.getPortfolio().getId();
		
		List<GlClose> glCloseList=glCloseDao.getClosedGL(portnum, portfolio.getLastGLClose());
		ConcurrentHashMap<String, GlClose> glCloseHM=new ConcurrentHashMap<String, GlClose>();
		for(GlClose gl:glCloseList){
			glCloseHM.put(gl.getAcctnum(), gl);
		}
		
		if(baseAccts.length==4){
			String tzsyAcct=baseAccts[0];
			String fzcbAcct=baseAccts[1];
			String jtfyAcct=baseAccts[2];
			String jtdfkfsyAcct=baseAccts[3];
			
			double tzsy=0;
			double fzcb=0;
			double jyfy=0;
			double jtdfkfsy=0;
			for(Map.Entry<String, GlClose> entry:glCloseHM.entrySet()){
				GlClose gl=entry.getValue();
				double diffvalue=0;
				if(tzsyAcct.startsWith("6")){
					diffvalue=RoundUtil.roundMoney2Dec(gl.getCr_func()-gl.getDr_func());
				}else{
					diffvalue=RoundUtil.roundMoney2Dec(gl.getDr_func()-gl.getCr_func());
				}
				
				if(entry.getKey().matches(Pattern.quote(tzsyAcct))){
					tzsy=tzsy+diffvalue;
				}else if(entry.getKey().matches(Pattern.quote(fzcbAcct))){
					fzcb=fzcb+diffvalue;
				}else if(entry.getKey().matches(Pattern.quote(jtfyAcct))){
					jyfy=jyfy+diffvalue;
				}else if(entry.getKey().matches(Pattern.quote(jtdfkfsyAcct))){
					jtdfkfsy=jtdfkfsy+diffvalue;
				}
			}
			
			double finalValue=RoundUtil.roundMoney2Dec(tzsy-fzcb-jyfy-jtdfkfsy);
			double expense=incomeExpenseCal.incomeCal(cfg.getIntCode(), startDate, asOfDate, cfg.getExpenseAmount(), finalValue);
			
			if(expense!=0){
				GLCOOABase expenseCooa=new GLCOOABase();
				if(cfg.isExpenseFlag()){
					jobCom.saveGL(expenseCooa, 
							DBCR.DEBIT, 
							expense,
							expense, 
							cfg.getDrAcct());
					
					jobCom.saveGL(expenseCooa, 
							DBCR.CREDIT, 
							expense,
							expense, 
							cfg.getCrAcct());
				}else{
					jobCom.saveGL(expenseCooa, 
							DBCR.CREDIT, 
							expense,
							expense, 
							cfg.getDrAcct());
					
					jobCom.saveGL(expenseCooa, 
							DBCR.DEBIT, 
							expense,
							expense, 
							cfg.getCrAcct());
				}
				
				Voucher voucher=jobCom.genVoucher(asOfDate, portfolio, JournalTypes.EXPEND, "", jobName());
				expenseCooa.setVoucher(voucher);
				
				jobCom.genVoucherDetails(expenseCooa, portfolio, finalValue, cfg.getPortFeeName());
				return expenseCooa;
			}
		}
		return null;
	}
 
	@Override
	public String jobName() {
		return "银行资管-管理人报酬";
	}
	
	@Override
	public String jobDesc() {
		return "根据管理人报酬的特殊要求，开发出新的支持类，加入投资组合税费框架中；开发时针对如下规则:规则上，“应付管理人报酬”应为每日通过“投资收益 – "
				+ "负债成本 – 计提费用 – 计提兑付客户收益”来进行计提，如果上述公式计算结果是负的，需要往下扣";
	}
}
