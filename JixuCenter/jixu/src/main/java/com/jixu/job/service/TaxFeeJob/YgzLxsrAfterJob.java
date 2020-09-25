package com.jixu.job.service.TaxFeeJob;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.obj.YGZCalObj;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.IssueInfo;
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
public class YgzLxsrAfterJob extends AfterJobAbstract{
	
	@Override
	public String jobName() {
		return "营改增-利息收入";
	}
	
	@Override
	public GLCOOABase exeAfterJob(int userID, PortExpenseCfg cfg, List<AcctDCBalance> balList, Date asOfDate) throws JNLException {
		//首先删除相关记录
		deleteJobResult(cfg, asOfDate);
		
		String[] accts=cfg.getBaseAcct().split(",");
		PortfolioHdr portfolio=portfolioHdrDao.loadPortfolio(cfg.getPortfolio().getId());
		Date lastCloseDate=portfolio.getLastGLClose();
		//获取上一天的台账
		HashMap<String, YGZTaiZhang> lasttMap=getYGZTaiZhang(portfolio.getId(), DateUtil.ConvertUtil2Sql(lastCloseDate));
		//获取应税的编号组
		List<String> yinshuiList=getYingShuiItemList(cfg);
		
		//汇总
		YGZCalObj t_ygz_ys=new YGZCalObj();
		YGZCalObj t_ygz_ms=new YGZCalObj();
		
		//issuetype的汇总
		HashMap<String, YGZCalObj> t_type_ygzHM=new HashMap<String, YGZCalObj>();
		
		for(AcctDCBalance bacl:balList){
			if(bacl.getSecid()==null || bacl.getSecid().equals(""))
				continue;
			
			for(String acct:accts){
				if(bacl.getAcctnum().matches(acct)){
					IssueInfo sec=issueInfoDao.loadSecurity(bacl.getSecid());
					//判断是否应税
					boolean ysflag=false;
					if(yinshuiList.contains(String.valueOf(sec.getRpSecType().getId()))){
						ysflag=true;
					}
					
					//获取前一天的台账
					YGZTaiZhang lastYGZTz=new YGZTaiZhang();
					String tmpkey=getKey(cfg.getPortFeeID(), sec.getIssueType(), sec.getId(), ysflag);
					if(lasttMap.containsKey(tmpkey)){
						lastYGZTz=lasttMap.get(tmpkey);
					}
					
					//获取上一日的相关信息，用上一日的当天信息做迭代
					double lastBalance=lastYGZTz.getDrsfjzye();
					//获取开始日到当天的科目余额,
					double gainlossToday=RoundUtil.roundMoney2Dec(bacl.getDrFuncAmtUnSetToday()-bacl.getCrFuncAmtUnSetToday());
					//历史基准余额,如果上线当天,历史基准余额是负数的话,把这部分负值累计到当日的基准余额中。抵扣
					double lsjzye=RoundUtil.roundMoney2Dec(bacl.getDrFuncAmtUnSetLastDay()-bacl.getCrFuncAmtUnSetLastDay());
					if(bacl.getAcctnum().startsWith("6")){
						gainlossToday=gainlossToday*-1;
						lsjzye=lsjzye*-1;
					}
					//用昨天的余额+今天的发生额
					double balance=RoundUtil.roundMoney2Dec(lastBalance+gainlossToday);
					
					if(lsjzye<0 && asOfDate.compareTo(cfg.getStartDate())==0){
						balance=RoundUtil.roundMoney2Dec(balance+lsjzye);
					}
					
					YGZCalObj ygzobj=new YGZCalObj();
					ygzobj.setDrsfjzye(balance);
					ygzobj.setDrsfjzfse(gainlossToday);
					
					YGZTaiZhang tz=genYGZTaizhang(cfg, asOfDate, ysflag, ygzobj, lastYGZTz, sec, sec.getIssueType());
					if(tz==null)
						continue;
			
					//按照issuetype累加
					if(t_type_ygzHM.containsKey(sec.getIssueType())){
						YGZCalObj t_type_ygz=t_type_ygzHM.get(sec.getIssueType());
						t_type_ygz.setDrsfjzye(t_type_ygz.getDrsfjzye()+tz.getDrsfjzye());
						t_type_ygz.setDrsfjzfse(t_type_ygz.getDrsfjzfse()+tz.getDrsfjzfse());
					}else{
						YGZCalObj t_type_ygz=new YGZCalObj(tz.getDrsfjzye(),tz.getDrsfjzfse()); 
						t_type_ygzHM.put(sec.getIssueType(), t_type_ygz);
					}
					
					//汇总，以issuetype为类型汇总
					if(tz.isYsfs()){
						t_ygz_ys.setDrsfjzye(t_ygz_ys.getDrsfjzye()+tz.getDrsfjzye());
						t_ygz_ys.setDrsfjzfse(t_ygz_ys.getDrsfjzfse()+tz.getDrsfjzfse());
						
						
					}else{
						t_ygz_ms.setDrsfjzye(t_ygz_ms.getDrsfjzye()+tz.getDrsfjzye());
						t_ygz_ms.setDrsfjzfse(t_ygz_ms.getDrsfjzfse()+tz.getDrsfjzfse());
					}
				}
			}
		}
		
		//生成issuetype group的台账
		for(Map.Entry<String, YGZCalObj> entry:t_type_ygzHM.entrySet()){
			String issuetype=entry.getKey();
			YGZCalObj ygzobj=entry.getValue();
			if(yinshuiList.contains(issuetype)){
				String key=getKey(cfg.getPortFeeID(), issuetype, "", true);
				genYGZTaizhang(cfg, asOfDate, true, ygzobj, lasttMap.get(key), null, issuetype);
			}else{
				String key=getKey(cfg.getPortFeeID(), issuetype, "", false);
				genYGZTaizhang(cfg, asOfDate, false, ygzobj, lasttMap.get(key), null, issuetype);
			}
		}

		//生成总的台账
		String yskey=getKey(cfg.getPortFeeID(), "0", "", true);
		YGZTaiZhang taizhang=genYGZTaizhang(cfg, asOfDate, true, t_ygz_ys, lasttMap.get(yskey), null, "0");
		
		String mskey=getKey(cfg.getPortFeeID(), "0", "", true);
		genYGZTaizhang(cfg, asOfDate, false, t_ygz_ms, lasttMap.get(mskey), null, "0");
		
		if(taizhang!=null){
			double amt=taizhang.getDrpzjt();
			
			GLCOOABase expenseCooa=new GLCOOABase();
			jobCom.saveGL(expenseCooa, 
					DBCR.DEBIT, 
					amt,
					amt, 
					cfg.getDrAcct());
			
			jobCom.saveGL(expenseCooa, 
					DBCR.CREDIT, 
					amt,
					amt, 
					cfg.getCrAcct());
			Voucher voucher=jobCom.genVoucher(asOfDate, portfolio, JournalTypes.EXPEND, "", jobName());
			expenseCooa.setVoucher(voucher);
			
			jobCom.genVoucherDetails(expenseCooa, portfolio, taizhang.getDrsfjzfse(), cfg.getPortFeeName());
			return expenseCooa;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @param cfg
	 * @param asOfDate	
	 * @param ysflag	是否应税
	 * @param ygzobj	内部含有今天的基准科目值
	 * @param lastYgzTZ	上一天的台账记录
	 * @param sec		null 则代表汇总
	 * @param issueType	0 则代表整体汇总		如果sec=null并且issuetype=0的话，是总的汇总
	 * @return
	 */
	private YGZTaiZhang genYGZTaizhang(PortExpenseCfg cfg, Date asOfDate, boolean ysflag, YGZCalObj ygzobj, YGZTaiZhang lastYgzTZ, IssueInfo sec, String issueType){
		YGZTaiZhang tz=new YGZTaiZhang();
		tz.setLedgerDate(DateUtil.ConvertUtil2Sql(asOfDate));
		tz.setTaxFeeType(cfg.getPortFeeID());
		tz.setPortnum(cfg.getPortfolio().getId());
		if(sec!=null){
			tz.setRpSecType(sec.getRpSecType().getId());
			tz.setCusip(sec.getId());
			if(sec.getExchangeCode()!=null)
				tz.setExchangeID(sec.getExchangeCode());
		}
		tz.setIssueType(issueType);
		tz.setYsfs(ysflag);
		tz.setDrsfjzfse(ygzobj.getDrsfjzfse());
		tz.setDrsfjzye(ygzobj.getDrsfjzye());
		
		double balance=tz.getDrsfjzye();
		double rate=cfg.getExpenseAmount();
		int caldays=incomeExpenseCal.getIntCodeYearDays(cfg.getIntCode(), asOfDate);
		double taxbalance=RoundUtil.roundMoney2Dec(balance*(rate/caldays)/(1+rate/caldays));
		
		tz.setDrsfye(taxbalance);
		
		if(lastYgzTZ==null){
			lastYgzTZ=new YGZTaiZhang();
		}
		
		tz.setSyrsfjzye(lastYgzTZ.getDrsfjzye());
		tz.setSyrsfye(lastYgzTZ.getDrsfye());
		
		tz.setDrsffse(RoundUtil.roundMoney2Dec(taxbalance-lastYgzTZ.getDrsfye()));
		tz.setSyrkmye(lastYgzTZ.getDrkmye());
		tz.setDrkmye(taxbalance < 0 ? 0 : taxbalance);
		tz.setDrpzjt(RoundUtil.roundMoney2Dec(tz.getDrkmye()-tz.getSyrkmye()));
		
		if(tz.getDrsfye()==0 && tz.getSyrsfye()==0 && tz.getDrsfjzfse()==0){
			return null;
		}else{
			ygzTaiZhangDao.saveEntity(tz);
			return tz;
		}
	}
	
	@Override
	public String jobDesc() {
		String desc="计算公式： 科目基准余额*(税率/计算天数)/(1+税率/计算天数)。 \r\n "
				+ "基础科目: 科目基准余额 | 任务后计算: 开支日记账执行/事后任务执行 | 资产类别:用于判断该证券是否应税  \r\n"
				+ "描述: 第一天上线时，当历史的基准余额余额小于0时，则迭代累加到当日的基准发生额，用于抵扣；当历史基准余额大于0，则不计入计息的历史基准余额。";
		return desc;
	}
}
