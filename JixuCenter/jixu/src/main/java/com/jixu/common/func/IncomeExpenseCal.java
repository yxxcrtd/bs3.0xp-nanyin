package com.jixu.common.func;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.BookNDaysType;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.TradeMarket;
import com.jixu.common.types.ActiveType;
import com.jixu.common.types.CalInterestModel;
import com.jixu.common.types.CashFlowCfgType;
import com.jixu.common.types.CashFlowFloatMethod;
import com.jixu.common.types.CouponData;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.InterestCpnMo;
import com.jixu.common.types.InterestCycleDate;
import com.jixu.common.types.InterestInitCode;
import com.jixu.common.types.LocalFuncResult;
import com.jixu.common.types.ParChange;
import com.jixu.common.types.TradeSettleLedger;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.CashFlowFloatRateDao;
import com.jixu.dao.intf.CashFlowPaymentDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.SecPriceDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.mvc.control.model.IntCodeModel;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CashFlowPar;
import com.orm.obj.CashFlowRate;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;

@Service("incomeExpenseCal")
public class IncomeExpenseCal {
	
	@Resource(name="commfuncService")
	private CommFuncService comService;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Autowired
	private SecPositionCenter secPositionCenter;
	
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	private SecPriceDao secPriceDao;
	
	@Autowired
	private CashFlowFloatRateDao cashFlowFloatRateDao;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Autowired
	private CashFlowPaymentDao cashFlowPaymentDao;
	
	@Autowired(required=false)
	private FinProductCommonBatchService finProductCommonBatchService;
	
	
	/**
	 * 获取选择计算方式的当年总天数
	 * @param intCode	30/360,30/365等选择方式
	 * @param asOfDate	当天日期
	 * @return	分母总的天数
	 */
	public int getIntCodeYearDays(int intCode, Date asOfDate){
		int totalDays=365;
		
		switch (intCode) {
		case InterestInitCode.Coupons_30_360:
		case InterestInitCode.Coupons_Act_360:
			totalDays=360;
			break;
		case InterestInitCode.Coupons_30_365:
		case InterestInitCode.Coupons_Act_365:
			totalDays=365;
			break;
		case InterestInitCode.Coupons_30_Act:
		case InterestInitCode.Coupons_Act_Act:
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(asOfDate);
			totalDays=calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
			break;
		default:
			totalDays=365;
			break;
		}
		
		return totalDays;
	}
	
	/**
	 * 获取选择计算方式的当年总天数和计算天数
	 * @param intCode	计算方式,30/360,30/365等
	 * @param startDate	开始日期
	 * @param ledgerDate	出账日期
	 * @return	分子/分母的天数
	 */
	public IntCodeModel getIntCodeYearDays(int intCode, Date startDate, Date ledgerDate) {
		int calDays=0;
		if(ledgerDate==null){
			calDays=0;
		}else if(startDate.after(ledgerDate)) {
			calDays=0;
		}else if(ledgerDate.after(startDate)){
			calDays= DateUtil.getDiffDays(startDate, ledgerDate)+1;
			
			if(intCode==InterestInitCode.Coupons_30_360 
					|| intCode==InterestInitCode.Coupons_30_365 
					|| intCode==InterestInitCode.Coupons_30_Act){
				Calendar cal1=Calendar.getInstance();
		        Calendar cal2=Calendar.getInstance();
		        cal1.setTime(startDate);
		        cal2.setTime(ledgerDate);
		        
		        int startDayNum=cal1.get(Calendar.DAY_OF_MONTH);
				int appendDays1=30-startDayNum+1;
				
				//去除第一个月和最后一个月
				int mon1=cal1.get(Calendar.MONTH);
				int mon2=cal2.get(Calendar.MONTH);

				int gapMonth=mon2-mon1;
				if(gapMonth<0){
					gapMonth=gapMonth+12;
				}
				
				calDays=30*(gapMonth-1);
				int monthTotalDays=cal2.getActualMaximum(Calendar.DAY_OF_MONTH);
				int appendDays2=cal2.get(Calendar.DAY_OF_MONTH);
				if(monthTotalDays==appendDays2){
					appendDays2=30;
				}
				calDays=calDays+appendDays1+appendDays2;
			}else if(intCode==InterestInitCode.Coupons_Act_365_CY){
				//以下功能：2月29日，不计息
		        int totalRemoveDays=0;
		        Calendar cal=Calendar.getInstance();
		        Date speDate=null;
		        while(startDate.before(ledgerDate)){
		        	cal.setTime(startDate);
		        	int startYear=cal.get(Calendar.YEAR);
		        	//当年的最后一天
		        	int days=cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		        	if(days==366){
		        		speDate=DateUtil.convertStr2Date2(startYear+"-02-29");
		        		if(startDate.before(speDate) && !ledgerDate.before(speDate)){
		        			totalRemoveDays=totalRemoveDays+1;
		        		}
		        	}
		        	startDate=DateUtil.getNextyear(startDate);
		        }
		        calDays=calDays-totalRemoveDays;
			}
		}else if(ledgerDate.compareTo(startDate)==0){
			calDays=1;
		}
		
		
		int totalDays=getIntCodeYearDays(intCode,ledgerDate);
		IntCodeModel icm=new IntCodeModel();
		icm.setCalDays(calDays);
		icm.setTotalDays(totalDays);
		return icm;
	}
	
	/**
	 * 获取摊销利率
	 * 在现金流里，配置了从交易日/结算日+N天开始计息
	 * @param cfc			现金流
	 * @param tradeDate		交易日期
	 * @param settleDate	结算日期
	 * @param expireDate	证券到期日期
	 * @param buyprice		买入价格
	 * @param perValue		票面价格
	 * @param holidayList	假期
	 * @return
	 */
	public double calAmortRateByTrade(CashFlowCfg cfc, Date tradeDate, Date settleDate, Date expireDate, double buyprice, double perValue, List<Date> holidayList){
		Date startDate=getStartInterestDate(cfc, tradeDate, settleDate, holidayList);
		return calAmortRate(cfc, startDate, expireDate, buyprice, perValue);
	}
	
	/**
	 * 获取交易后，开始计息日
	 * @param cfc	现金流配置
	 * @param tradeDate	交易日
	 * @param settleDate	结算日
	 * @param dateList	节假日
	 * @return	开始计息日
	 */
	public Date getStartInterestDate(CashFlowCfg cfc, Date tradeDate, Date settleDate, List<Date> dateList){
		Date baseDate=settleDate;
		if(cfc.getStartNDaysBase()==TradeSettleLedger.TradeDate){
			baseDate=tradeDate;
		}
		Date startDate=getStartInterestDate(cfc, baseDate, dateList);
		return startDate;
	}
	
	/**
	 * 计算摊销的有效利率/实际利率
	 * @param cfc	现金流配置
	 * @param startDate	开始日期
	 * @param buyprice	买入价格
	 * @param perValue	票面价格
	 * @return
	 */
	private double calAmortRate(CashFlowCfg cfc, Date startDate, Date expireDate, double buyprice, double parValue){
		//double coupon=RoundUtil.roundMoney2Dec(couponrate*perValue/icm.getTotalDays());	//计算每天的应收利息，这里可能还需要改进.全冲全提的话，每天的营收利息不一致
		//如果开始日在结束日之后，返回0
		if(startDate.after(expireDate) || buyprice==0 || parValue==0)
			return 0;
		else if(buyprice==parValue)
			return 0;
		
		if(buyprice<0 && parValue<0){
			//负数转成正值计算
			buyprice=-1*buyprice;
			parValue=-1*parValue;
		}
		double high=10;	
		double low=-10;
		double rate=0;
		int maxCalNum=200;
		double couponPerDay=0;
		LocalFuncResult lastIntResult=null;
		
		List<Double> couponList=new ArrayList<Double>();
		Date coupDate=startDate;
		
		while(coupDate.before(expireDate)){
			List<LocalFuncResult> totalResult=GetInteresForAmort(cfc, parValue, coupDate, lastIntResult);
			LocalFuncResult lastResult=totalResult.get(0);
			LocalFuncResult todayResult=totalResult.get(1);
			couponPerDay=RoundUtil.roundMoney2Dec(todayResult.getLocal()-lastResult.getLocal());
			couponList.add(couponPerDay);
			
			lastIntResult=todayResult;
			coupDate=DateUtil.getNextDay(coupDate);
		}
		
		int num=couponList.size();
		int j=0;
		while(j<maxCalNum){
			double calValue=0;
			int i=0;
			for(double coupon:couponList){
				i++;
				rate=(high+low)/2;
				double tmpValue=RoundUtil.roundMoney2Dec(coupon/Math.pow((1+rate),i));
				
				if(i==num){
					tmpValue=RoundUtil.roundMoney2Dec((coupon+parValue)/Math.pow((1+rate),i));
				}
				calValue=calValue+tmpValue;
			}
				
			calValue=RoundUtil.roundMoney2Dec(calValue);
			buyprice=RoundUtil.roundMoney2Dec(buyprice);
			if(calValue==buyprice)
				break;
			else if(calValue>buyprice){
				low=rate;
			}else{
				high=rate;
			}
			j++;
		}
		if(j==maxCalNum){
			return low;
		}else{
			return rate;	
		}
	}
	
	/**
	 * 计算利息，或者费用
	 * @param intCode	计息类型，比如30/360
	 * @param startDate	计息开始时间
	 * @param ledgerDate 出账时间
	 * @param acRate	 利率
	 * @param amount	 总利息
	 * @return	利息
	 */
	public double incomeCal(int intCode, Date startDate, Date ledgerDate, double acRate, double amount) {
		if(amount==0){
			return 0;
		}
		double totalInterest=0;
		Calendar cal1=Calendar.getInstance();
		Calendar cal2=Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(ledgerDate);
		int startYearEndDays=cal1.getActualMaximum(Calendar.DAY_OF_YEAR);
		int ledgerYearEndDays=cal2.getActualMaximum(Calendar.DAY_OF_YEAR);

		if(startYearEndDays==ledgerYearEndDays){
			IntCodeModel icm=getIntCodeYearDays(intCode,startDate,ledgerDate);
			totalInterest=RoundUtil.roundMoney2Dec(amount*(acRate/100)*icm.getCalDays()/icm.getTotalDays());
		}else{
			//如果是?/act,遇到跨年的时候，需要拆分成两段计算
			//目前只支持开始日和结束日跨一年
			int yearNum=cal1.get(Calendar.YEAR);
			Date startEndYear=DateUtil.convertStr2Date2(yearNum+"-12-31");
			IntCodeModel icm1=getIntCodeYearDays(intCode,startDate,startEndYear);
			double total1=RoundUtil.roundMoney2Dec(amount*(acRate/100)*icm1.getCalDays()/icm1.getTotalDays());
			
			IntCodeModel icm2=getIntCodeYearDays(intCode,DateUtil.getNextDay(startEndYear),ledgerDate);
			double total2=RoundUtil.roundMoney2Dec(amount*(acRate/100)*icm2.getCalDays()/icm2.getTotalDays());
			totalInterest=RoundUtil.roundMoney2Dec(total1+total2);
		}
		return totalInterest;
	}
	
	
	/**
	 * 贴现债利息计算
	 * @param intCode	计息类型，比如30/360
	 * @param startDate	计息开始时间
	 * @param ledgerDate 出账时间
	 * @param acRate	 利率
	 * @param amount	 总利息
	 * @return	利息
	 */
	public double incomeTXZCal(int intCode, Date startDate, Date ledgerDate, double acRate, double amount,IssueInfo sec,CashFlowCfg cfc) {
		if(amount==0){
			return 0;
		}
		IntCodeModel icm=getIntCodeYearDays(intCode,startDate,ledgerDate);
		acRate=sec.getEndPrice()-sec.getStartPrice();
		int totalDays=DateUtil.getDiffDays(cfc.getFirstIntDate(), sec.getExpireDate());
		double totalInterest=RoundUtil.roundMoney2Dec(amount*(acRate/100)*icm.getCalDays()/totalDays);
		return totalInterest;
	}
	
	/**
	 * 计算固定收益的总利息/费用
	 * @param startDate	开始日期
	 * @param ledgerDate	出账日期
	 * @param fixedamount	固定收益/费用
	 * @return	总收益/费用
	 */
	public double incomeFixedCal(Date startDate, Date ledgerDate, double fixedamount) {
		int totalDays=1;
		if(ledgerDate==null){
			totalDays=0;
		}else if(ledgerDate.after(startDate)){
			totalDays= DateUtil.getDiffDays(startDate, ledgerDate)+1;
		}
		return RoundUtil.roundMoney2Dec(totalDays*fixedamount);
	}
	
	/**
	 * 全冲全提: 计算当天的利息收入
	 * @param intModel	计算利息自定义的数据参数bean
	 * @return	当天利息收入
	 */
	public LocalFuncResult getInterest(CalInterestModel intModel){
		double curInterest=0;
		double curFuncInterest=0;
		Date startDate=getStartInterestDate(intModel.getCashFlowCfg(), intModel.getTradeDate(), intModel.getHolidayList());
		Date endDate=null;
		if(intModel.getAcuDate()!=null){
			endDate=getCpnMaturitySetDate(intModel.getCashFlowCfg(), intModel.getAcuDate());
		}else{
			//永远计息
			endDate=DateUtil.convertStr2Date("29991231");
		}
		CashFlowCfg cfc=intModel.getCashFlowCfg();
		if(intModel.getCurDate().before(startDate) || intModel.getCurDate().after(endDate)){
			curInterest=0;
			curFuncInterest=0;
		}else{
			//
			double acrate=0;
			//是否使用外部利率,如果是,取tsum alrate.如果不是,取smf现金流里配置的利率
			if(cfc.isExternalRateFlag()){
				if(intModel.getYield()>0){
					acrate=intModel.getYield();
				}else{
					acrate=intModel.getAcRate();
				}
			}else{
				acrate=cfc.getCoupon();
			}
			double lastTotalInterest=0;
			if(startDate.before(intModel.getCurDate()))
				lastTotalInterest=incomeCal(cfc.getIntCode(), startDate, intModel.getLastCalDate(), acrate, intModel.getAmount());
			double curTotalInterest=0;
			if(intModel.getBookNBDays()==BookNDaysType.back){
				curTotalInterest=incomeCal(cfc.getIntCode(), startDate, intModel.getCurDate(), acrate, intModel.getAmount());
			}else if(intModel.getBookNBDays()==BookNDaysType.front){
				curTotalInterest=incomeCal(cfc.getIntCode(), startDate, DateUtil.getNextBusinessDay(intModel.getCurDate(), intModel.getHolidayList()), acrate, intModel.getAmount());
			}
			curInterest=RoundUtil.roundMoney2Dec(curTotalInterest-lastTotalInterest);
			//这里已经计算了利息的估增
			//curFuncInterest=RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(curTotalInterest*intModel.getTodayFxRate())-RoundUtil.roundMoney2Dec(lastTotalInterest*intModel.getLastFxRate()));
			curFuncInterest=RoundUtil.roundMoney2Dec(curInterest*intModel.getTodayFxRate());
			
		}
		LocalFuncResult lf=new LocalFuncResult();
		
		if(cfc.getRecOrPay()==IncomeExpenseType.expense){
			lf.setLocal(-1*curInterest);
			lf.setFunc(-1*curFuncInterest);
		}else{
			lf.setLocal(curInterest);
			lf.setFunc(curFuncInterest);
		}
		return lf;
	}
	
	/**
	 * 获取债券根据面值计算的利率。返回截至到昨天总的利息和截至到今天总的利息
	 * @param cfc	现金流
	 * @param perValue	票面价值
	 * @param currentDate	当前日期
	 * @param lastTotalInt	截至上一个计息日，应收利息余额
	 * @return 返回截至到昨天和今天，总的利息
	 */
	private List<LocalFuncResult> GetInteresForAmort(CashFlowCfg cfc, double perValue, Date currentDate, LocalFuncResult lastTotalInt){
		Date lastDay=DateUtil.getLastDay(currentDate);
		//采用迭代的算法，如果上一日的应收利息余额为空，则计算上一日的应收利息
		if(lastTotalInt==null){
			//获取上一个记账日的开始记账日起
			InterestCycleDate lastInteDates = getPeriodCycleDate(lastDay, cfc, CashFlowCfgType.Coupon);	
			Date lastStartDate=null;
			if(lastInteDates.getEndIInteDate().compareTo(lastDay)==0){
				lastStartDate=currentDate;
			}else{
				lastStartDate=lastInteDates.getLastInteEndDate();
			}
			lastTotalInt=new LocalFuncResult();
			double lastTotalIntLocal=incomeCal(cfc.getIntCode(), lastStartDate, lastDay, cfc.getCoupon(), perValue);
			lastTotalInt.setLocal(lastTotalIntLocal);
			lastTotalInt.setFunc(lastTotalIntLocal*1);//TODO	汇率
		}
		
		InterestCycleDate inteDates = getPeriodCycleDate(currentDate, cfc, CashFlowCfgType.Coupon);		
		Date todayStartDate=null;
		if(inteDates.getEndIInteDate().compareTo(currentDate)==0){
			todayStartDate=currentDate;
			//如果今天是计息第一天，则把原来老的利息设为0
			lastTotalInt=new LocalFuncResult();
		}else{
			todayStartDate=inteDates.getLastInteEndDate();
		}
		double todayTotalIntLocal=incomeCal(cfc.getIntCode(), todayStartDate, currentDate, cfc.getCoupon(), perValue);
		double todayTotalIntFunc=todayTotalIntLocal*1;	//TODO	汇率
		
		LocalFuncResult todayTotalResult=new LocalFuncResult();
		todayTotalResult.setLocal(todayTotalIntLocal);
		todayTotalResult.setFunc(todayTotalIntFunc);
		
		List<LocalFuncResult> totalResults=new ArrayList<LocalFuncResult>();
		totalResults.add(lastTotalInt);
		totalResults.add(todayTotalResult);
		
		return totalResults;
	}
	
	
	/**
	 * 计算债券当天应收利息
	 * @param clo	当天的关帐数据，里面应该已经包括截至到今天的应收利息
	 * @return	当天利息
	 */
	public double getInterestForAll(TranClo clo){
		IssueInfo sec=issueInfoDao.loadIssueByCusip(clo.getCusip());
		if(!clo.getCloseDate().before(sec.getExpireDate())){
			//可能出现证券的截至日和结息日不相等的情况,证券截止日之后，就强制不计息了
			return 0;
		}
		
		String cashflowID=sec.getCashFlowCfgID();
		if(sec.getIssueType().equals(AssetTypes.SWAP_INTEREST) && clo.getLsindicator()==LongShortType.ShortTerm){
			cashflowID=sec.getCashFlowCfgID2();
		}
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(cashflowID);
		if(cfc.getActiveFlag()==ActiveType.inActive) {
			return 0;
		}
		
		List<Date> holidayList=comService.getHolidays(clo.getPortfolio());
		//根据现金流T+N配置,查找N天前的计息基准日。以当天的数量为基准
		//接下去会二次计算，这个基准日是基于交易日还是结算日
		//以下是获取今天计息的基准数量
		Date basicDate=getStartBasicAmountDate(cfc, clo.getCloseDate(), holidayList);
		double basicCost=0;
		double basicAmount=0;
		if(cfc.getStartNDaysBase()==TradeSettleLedger.TradeDate){
			//交易为计息基准
			//如果是交易日,可以从clo表里去拿已有的头寸
			TranClo basicClo=null;
			if(clo.getCloseDate().compareTo(basicDate)==0){
				//如果是当天的头寸,直接去当天clo的头寸，防止循环执行 getSecPosition
				basicClo=clo;
			}else{
				basicClo=secPositionCenter.getSecPosition(clo.getPortfolio(), clo.getCusip(), clo.getLotnum(), basicDate, false);
			}
			if(basicClo!=null){
				basicAmount=basicClo.getAmount();
				basicCost=basicClo.getgCost();
			}
		}else{
			if(!clo.getLstSettledate().after(basicDate)){
				//如果最后一次交易的结算日在<=基准日,则可以直接用当前头寸的数量
				basicAmount=clo.getAmount();
				basicCost=clo.getgCost();
			}else{
				//这里可以优化，目前是把当天是结算日之前的交易查询出来再一次计算。可以在tranclo设值lastsetdate来判断
				TranSum tsum=secPositionCenter.getSecPositionBySetDate(clo.getPortfolio(), clo.getCusip(), clo.getLotnum(), basicDate);
				if(tsum!=null){
					basicAmount=tsum.getAmount();
					basicCost=tsum.getgCost();
				}
			}
		}
		//基准数量已获取
		double amount=0;
		if(sec.getIssueType().equals(AssetTypes.Bond)){
			ParChange parchange=getPayParChange(sec.getContractSize(), cfc, clo.getCloseDate());
			double todayPar=parchange.getTodayParValue();
			amount=basicAmount*todayPar;
		}else{
			amount=basicCost;
		}
		
		double curInterest=0;	//今天的利息
		
		if(amount!=0){
			//获取利率
			double acrate=0;
			//是否使用外部利率,如果用外部利率，决定了该证券本轮的第一个计息日是交易日或者结算日+N
			//如果没有使用外部利率，该证券本轮的第一个计息日是计息周期的第一天。比如债券的计息周期是3/6/9/12月的21日，今天是4月5日，则第一个计息日是3月21日
			Date adjustCycleStartDate=null;	//如果以下的可调整利率，按阶等类型的利率，有日期配置的，则赋值当天使用利率的开始日
			if(cfc.isExternalRateFlag()){
				acrate=clo.getAltRate();
				if(acrate==0){
					//获取价格的yield
					acrate=secPriceDao.loadCusipYield(clo.getPortfolio(), clo.getCusip(), clo.getCloseDate());
				}
			}else{
				if(cfc.getCpnType()==CouponData.cpn_fixed){
					//固定利率，直接取coupon
					acrate=cfc.getCoupon();
				}else if(cfc.getCpnType()==CouponData.cpn_adjust){
					//可调整利率/利率重置
					//获取当天用于计息的利率
					CashFlowRate cfr=cashFlowFloatRateDao.getCashFlowRate(cfc.getCashFlowID(), clo.getCloseDate());
					//bug 如果查不到cfr呢???  19号做的一笔利率重置交易    现金流里配的重置利率日期是21号  
					if(cfr==null){
						//查不到暂时给Coupon
						acrate=cfc.getCoupon();
					}else{
						adjustCycleStartDate=cfr.getCfDate();
						acrate=cfr.getCfRate();
					}
				}else if(cfc.getCpnType()==CouponData.cpn_float){
					//浮动利率
					acrate=calCashFloatRate(clo.getPortfolio(), cfc, clo.getCloseDate());
				}else if(cfc.getCpnType()==CouponData.cpn_step){
					//按阶，尚未启用
				}
					
			}
			
			PortfolioHdr portfolio=comService.loadPortfolio(clo.getPortfolio());
			//-----取得开始计息日
			//重新计算截至上一天总利息，因为有结转利息等情形，前一天的clo.accrued利息不一定是正确的计算利息
			Date todayStartDate=null;
			//首先根据现金流频率，获取开始日。 到期，计划，每N天，每月，季度，半年，年
			if(cfc.getCpnFreq()==CouponData.AtMaturity){
				//选择"到期", 是没有一个日期配置的。设定开始日是交易日，截至日是证券到期日或者回购等交易的到期日
				Tran tran=tranDao.loadTranLot1(portfolio.getId(), clo.getCusip(), clo.getLotnum());
				todayStartDate=getStartInterestDate(cfc, tran.getTradeDate(), tran.getSettleDate(), holidayList);
			}else{
				InterestCycleDate inteDates = getPeriodCycleDate(clo.getCloseDate(), cfc, CashFlowCfgType.Coupon);
				//其余则是getCpnFreq有常规的计息周期，比如月，季度，半年，年等
				if(inteDates.getEndIInteDate().compareTo(clo.getCloseDate())==0){
					todayStartDate=clo.getCloseDate();
				}else if(cfc.isExternalRateFlag()){
					//如果使用外部利率，则找到该头寸的第一笔交易的计息开始日作为本轮计息周期的第一个计息日
					Tran tran=tranDao.loadTranLot1(portfolio.getId(), clo.getCusip(), clo.getLotnum());
					todayStartDate=getStartInterestDate(cfc, tran.getTradeDate(), tran.getSettleDate(), holidayList);
					if(todayStartDate.before(inteDates.getLastInteEndDate())){
						//如果头寸第一笔交易的开始计息日，在本轮计息周期之前。则认为已经结息过了，这轮计息跟随计息周期配置。 目前一般会出现在时间跨度较长的定期存款
						todayStartDate=inteDates.getLastInteEndDate();;
					}
				}else{
					//如果没有使用外部利率，则使用现金流配置里的本轮计息周期的开始计息日作为本轮的第一个计息日
					todayStartDate=inteDates.getLastInteEndDate();
				}
				
				//浮动利率，再去取一次重置日，和付息频率比较，哪个日期近取哪个
				if(cfc.getCpnType()==CouponData.cpn_float) {
					Date floatTodayStartDate=null;
					InterestCycleDate floatinteDates = getPeriodCycleDate(clo.getCloseDate(), cfc, CashFlowCfgType.FloatCf);
					//其余则是getCpnFreq有常规的计息周期，比如月，季度，半年，年等
					if(floatinteDates.getEndIInteDate().compareTo(clo.getCloseDate())==0){
						floatTodayStartDate=clo.getCloseDate();
					}else if(cfc.isExternalRateFlag()){
						//如果使用外部利率，则找到该头寸的第一笔交易的计息开始日作为本轮计息周期的第一个计息日
						Tran tran=tranDao.loadTranLot1(portfolio.getId(), clo.getCusip(), clo.getLotnum());
						floatTodayStartDate=getStartInterestDate(cfc, tran.getTradeDate(), tran.getSettleDate(), holidayList);
						if(floatTodayStartDate.before(floatinteDates.getLastInteEndDate())){
							//如果头寸第一笔交易的开始计息日，在本轮计息周期之前。则认为已经结息过了，这轮计息跟随计息周期配置。 目前一般会出现在时间跨度较长的定期存款
							floatTodayStartDate=floatinteDates.getLastInteEndDate();;
						}
					}else{
						//如果没有使用外部利率，则使用现金流配置里的本轮计息周期的开始计息日作为本轮的第一个计息日
						floatTodayStartDate=floatinteDates.getLastInteEndDate();
					}
					if(floatTodayStartDate.after(todayStartDate)) {
						//如果浮动利率日期更近，采用浮动利率的开始日期
						todayStartDate=floatTodayStartDate;
					}
				}
			}
			if(adjustCycleStartDate!=null){
				//获取最大值,max(todayStartDate,adjustCycleStartDate)
				todayStartDate=todayStartDate.compareTo(adjustCycleStartDate)==1?todayStartDate:adjustCycleStartDate;
			}
			//已获取本计息周期的开始计息日
			curInterest=getInterestForAll_Final(clo.getPortfolio(), sec, cfc, acrate, amount, clo.getValuationType(), todayStartDate, clo.getCloseDate(), holidayList);
		}
		
		//利率互换新界面  支出端现金流选择应付  这地方*-1会导致凭证借贷方反了  暂时注释
		if(!sec.getIssueType().equals(AssetTypes.SWAP_INTEREST)) {  
			//joyin利率互换已经上线，凭证正确。这里排除利率互换
			if(cfc.getRecOrPay()==IncomeExpenseType.expense){ 
				curInterest=-1*curInterest;
			}
		}
		 
		return curInterest;
	}
	
	/**
	 * 根据交易的数量，获取每天的利息。目前用于倒起息
	 * @param tran	交易数据
	 * @return	当天利息
	 */
	public double getInterestForAll(Tran tran, Date endDate){
		String cusip=tran.getCusip().getId();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		
		Date tradeDate=tran.getTradeDate();
		Date settleDate=tran.getSettleDate();
		Date ledgerDate=tran.getLedgerDate();
		
		String cashflowID=sec.getCashFlowCfgID();
		if(sec.getIssueType().equals(AssetTypes.SWAP_INTEREST) && tran.getLsindicator()==LongShortType.ShortTerm){
			cashflowID=sec.getCashFlowCfgID2();
		}
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(cashflowID);
		List<Date> holidayList=comService.getHolidays(tran.getPortfolio());
		
		//获取开始计息日期
		Date startDate=getStartInterestDate(cfc, tradeDate, settleDate, holidayList);
		
		//开始计息日在
		if(!startDate.before(ledgerDate) || startDate.after(endDate)){
			return 0;
		}

		//计息的基准本金
		double amount=0;
		if(sec.getIssueType().equals(AssetTypes.Bond)){
			ParChange parchange=getPayParChange(sec.getContractSize(), cfc, endDate);
			double todayPar=parchange.getTodayParValue();
			amount=tran.getAmount()*todayPar;
		}else{
			amount=tran.getgCost();
		}
		double curInterest=0;	//今天的利息
		
		if(amount!=0){
			//获取利率
			double acrate=0;
			//是否使用外部利率,如果用外部利率，决定了该证券本轮的第一个计息日是交易日或者结算日+N
			//如果没有使用外部利率，该证券本轮的第一个计息日是计息周期的第一天。比如债券的计息周期是3/6/9/12月的21日，今天是4月5日，则第一个计息日是3月21日
			Date adjustCycleStartDate=null;	//如果以下的可调整利率，按阶等类型的利率，有日期配置的，则赋值当天使用利率的开始日
			if(cfc.isExternalRateFlag()){
				TranBasic tranbasic=tran.getTanum();
				acrate=tranbasic.getAltRate();
				if(acrate==0){
					//获取价格的yield
					acrate=secPriceDao.loadCusipYield(tran.getPortfolio(), cusip, endDate);
				}
			}else{
				if(cfc.getCpnType()==CouponData.cpn_fixed){
					//固定利率，直接取coupon
					acrate=cfc.getCoupon();
				}else if(cfc.getCpnType()==CouponData.cpn_adjust){
					//可调整利率/利率重置
					//获取当天用于计息的利率
					CashFlowRate cfr=cashFlowFloatRateDao.getCashFlowRate(cfc.getCashFlowID(), endDate);
					//bug 如果查不到cfr呢???  19号做的一笔利率重置交易    现金流里配的重置利率日期是21号  
					if(cfr==null){
						//查不到暂时给Coupon
						acrate=cfc.getCoupon();
					}else{
						adjustCycleStartDate=cfr.getCfDate();
						acrate=cfr.getCfRate();
					}
				}else if(cfc.getCpnType()==CouponData.cpn_float){
					//浮动利率
					acrate=calCashFloatRate(tran.getPortfolio(), cfc, endDate);
				}else if(cfc.getCpnType()==CouponData.cpn_step){
					//按阶，尚未启用
				}
					
			}
			
			//-----取得开始计息日
			//重新计算截至上一天总利息，因为有结转利息等情形，前一天的clo.accrued利息不一定是正确的计算利息
			Date todayStartDate=null;
			//首先根据现金流频率，获取开始日。 到期，计划，每N天，每月，季度，半年，年
			if(cfc.getCpnFreq()==CouponData.AtMaturity){
				//选择"到期", 是没有一个日期配置的。设定开始日是交易日，截至日是证券到期日或者回购等交易的到期日
				todayStartDate=startDate;
			}else{
				//用ledgerdate去获取本轮周期，当开始计息日在本轮周期开始日之前，则返回0
				InterestCycleDate inteDates=getPeriodCycleDate(ledgerDate, cfc, CashFlowCfgType.Coupon);
				//如果计息日是上一个计息周期，则返回0
				if(endDate.before(inteDates.getLastInteEndDate())){
					return 0;
				}else if(inteDates.getEndIInteDate().compareTo(endDate)==0){
					todayStartDate=startDate;
				}else if(cfc.isExternalRateFlag()){
					//如果使用外部利率，则找到该头寸的第一笔交易的计息开始日作为本轮计息周期的第一个计息日
					if(startDate.before(inteDates.getLastInteEndDate())){
						//如果头寸第一笔交易的开始计息日，在本轮计息周期之前。则认为已经结息过了，这轮计息跟随计息周期配置。 目前一般会出现在时间跨度较长的定期存款
						todayStartDate=inteDates.getLastInteEndDate();;
					}
				}else{
					//如果没有使用外部利率，则使用现金流配置里的本轮计息周期的开始计息日作为本轮的第一个计息日
					todayStartDate=inteDates.getLastInteEndDate();
				}
				
				if(cfc.getCpnType()==CouponData.cpn_float) {
					//浮动利率，获取浮动利率的开始计息时间
					Date floatTodayStartDate=null;
					InterestCycleDate floatinteDates = getPeriodCycleDate(ledgerDate, cfc, CashFlowCfgType.FloatCf);
					if(endDate.before(floatinteDates.getLastInteEndDate())){
						return 0;
					}else if(floatinteDates.getEndIInteDate().compareTo(endDate)==0){
						floatTodayStartDate=startDate;
					}else if(cfc.isExternalRateFlag()){
						//如果使用外部利率，则找到该头寸的第一笔交易的计息开始日作为本轮计息周期的第一个计息日
						if(startDate.before(floatinteDates.getLastInteEndDate())){
							//如果头寸第一笔交易的开始计息日，在本轮计息周期之前。则认为已经结息过了，这轮计息跟随计息周期配置。 目前一般会出现在时间跨度较长的定期存款
							floatTodayStartDate=floatinteDates.getLastInteEndDate();;
						}
					}else{
						//如果没有使用外部利率，则使用现金流配置里的本轮计息周期的开始计息日作为本轮的第一个计息日
						floatTodayStartDate=floatinteDates.getLastInteEndDate();
					}
					
					if(floatTodayStartDate.after(todayStartDate)) {
						//如果浮动利率日期更近，采用浮动利率的开始日期
						todayStartDate=floatTodayStartDate;
					}
				}
			}
			
			if(adjustCycleStartDate!=null){
				//获取最大值,max(todayStartDate,adjustCycleStartDate)
				todayStartDate=todayStartDate.compareTo(adjustCycleStartDate)==1?todayStartDate:adjustCycleStartDate;
			}
			//已获取本计息周期的开始计息日
			curInterest=getInterestForAll_Final(tran.getPortfolio(), sec, cfc, acrate, amount, tran.getValuationType(), todayStartDate, endDate, holidayList);
		}
		if(cfc.getRecOrPay()==IncomeExpenseType.expense){
			curInterest=-1*curInterest;
		}
		
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
		boolean buysalefalg=secBasic.buySaleFlag();
		if(!buysalefalg){
			curInterest=-1*curInterest;
		}
		
		return curInterest;
	}
	
	private double getInterestForAll_Final(String portnum, IssueInfo sec, CashFlowCfg cfc, double acrate, double amount, int valuationType, Date todayStartDate, Date asOfDate, List<Date> holidayList){
		PortfolioHdr portfolio=comService.loadPortfolio(portnum);
		//截至前一天的总利息
		double lastTotalInt=0;
		//当判断是公允价值贴现债时 
		if(sec.getFixIncomeType()==TradeMarket.TX_BOND&&valuationType==ValuationType.FV){
			lastTotalInt=incomeTXZCal(cfc.getIntCode(), todayStartDate, portfolio.getLastGLClose(), acrate, amount, sec, cfc);
		}else{
			lastTotalInt=incomeCal(cfc.getIntCode(), todayStartDate, portfolio.getLastGLClose(), acrate, amount);
		}
		double todayTotalInt=0;	//本计息周期截至今天的总利息
		if(portfolio.getBookNBDays()==BookNDaysType.back){
			if(sec.getFixIncomeType()==TradeMarket.TX_BOND&&valuationType==ValuationType.FV){
				todayTotalInt=incomeTXZCal(cfc.getIntCode(), todayStartDate, asOfDate, acrate, amount, sec, cfc);
			}else{
				todayTotalInt=incomeCal(cfc.getIntCode(), todayStartDate, asOfDate, acrate, amount);
			}
		}else if(portfolio.getBookNBDays()==BookNDaysType.front){
			if(sec.getFixIncomeType()==TradeMarket.TX_BOND&&valuationType==ValuationType.FV){
				todayTotalInt=incomeTXZCal(cfc.getIntCode(), todayStartDate, DateUtil.getNextBusinessDay(asOfDate, holidayList), acrate, amount, sec, cfc);
			}else{
				todayTotalInt=incomeCal(cfc.getIntCode(), todayStartDate, DateUtil.getNextBusinessDay(asOfDate, holidayList), acrate, amount);
			}
		}
		return RoundUtil.roundMoney2Dec(todayTotalInt-lastTotalInt);
	}
	
	/**
	 * 计算逆回购总的利息
	 * @param cashFlowCfg
	 * @param tradeDate		交易日期
	 * @param acuDate		付息日
	 * @param amount		总金额
	 * @param rate			利率
	 * @param holidayList	假期
	 * @return	逆回购总的利息
	 */
	public double getRepoMaturity(CashFlowCfg cashFlowCfg, Date tradeDate, Date acuDate, double amount, double rate, List<Date> holidayList){
		Date startDate=getStartInterestDate(cashFlowCfg, tradeDate, holidayList);
		Date endDate=getCpnMaturitySetDate(cashFlowCfg, acuDate);
		double acrate=0;
		if(cashFlowCfg.isExternalRateFlag()){
			acrate=rate;
		}else{
			acrate=cashFlowCfg.getCoupon();
		}
		double accrued=incomeCal(cashFlowCfg.getIntCode(), startDate, endDate, acrate, amount);;
		return accrued;
	}
	
	/**
	 * 获取计息开始时间
	 * @param cashFlowCfg	现金流配置
	 * @param tradeDate			交易日期
	 * @return	开始计息时间
	 */
	public Date getStartInterestDate(CashFlowCfg cashFlowCfg, Date tradeDate, List<Date> holidayList){
		Date startCalIntDate=null;
		if(cashFlowCfg.getStartNDaysType()==CouponData.NaturalDay){
			startCalIntDate=DateUtil.getNextNDays(tradeDate, cashFlowCfg.getStartNDays());
		}else if(cashFlowCfg.getStartNDaysType()==CouponData.BusinessDay){
			startCalIntDate=DateUtil.getNextNBusinessDays(tradeDate, cashFlowCfg.getStartNDays(), holidayList);
		}else{
			startCalIntDate=DateUtil.getNextNDays(tradeDate, cashFlowCfg.getStartNDays());
		}
		return startCalIntDate;
	}
	
	/**
	 * 查看做账日应该以哪天得数量为基准来计息。比如债券是以交易日T+2日开始计息,那么今天计息的基准数量应该是ledgerdate-2
	 * @param cashFlowCfg	现金流配置
	 * @param ledgerDate	出账日期
	 * @param holidayList	假期
	 * @return	计息的基准日，获取哪天得数量来计算
	 */
	public Date getStartBasicAmountDate(CashFlowCfg cashFlowCfg, Date ledgerDate, List<Date> holidayList){
		Date baseCalIntDate=null;
		if(cashFlowCfg.getStartNDaysType()==CouponData.NaturalDay){
			baseCalIntDate=DateUtil.getNextNDays(ledgerDate, cashFlowCfg.getStartNDays()*-1);
		}else if(cashFlowCfg.getStartNDaysType()==CouponData.BusinessDay){
			baseCalIntDate=DateUtil.getNextNBusinessDays(ledgerDate, cashFlowCfg.getStartNDays()*-1, holidayList);
		}else{
			baseCalIntDate=DateUtil.getNextNDays(ledgerDate, cashFlowCfg.getStartNDays());
		}
		return baseCalIntDate;
	}
	
	/**
	 * 获取最后一个付息日
	 * @param cfc	现金流配置
	 * @param tradeDate	交易日
	 * @param setDate	结算日
	 * @param holidayList	节假日
	 * @return
	 */
	public Date getEndInterestDate(CashFlowCfg cfc, Date tradeDate, Date setDate, List<Date> holidayList){
		Date endDate=null;
		if(cfc.getStartNDaysBase()==TradeSettleLedger.TradeDate){
			endDate=getEndInterestDate(cfc, tradeDate, holidayList);
		}else{
			endDate=getEndInterestDate(cfc, setDate, holidayList);
		}
		return endDate;
	}

	private Date getEndInterestDate(CashFlowCfg cashFlowCfg, Date acuDate, List<Date> holidayList){
		Date endCalIntDate=null;
		int endDelayDays=cashFlowCfg.getEndNDays();
		if(cashFlowCfg.getCpnDelayDateType()==CouponData.NaturalDay){
			endCalIntDate=DateUtil.getNextNDays(acuDate, endDelayDays);
		}else if(cashFlowCfg.getCpnDelayDateType()==CouponData.BusinessDay){
			endCalIntDate=DateUtil.getNextNBusinessDays(acuDate, endDelayDays, holidayList);
		}else{
			endCalIntDate=DateUtil.getNextNDays(acuDate, endDelayDays);;
		}
		return endCalIntDate;
	}
	
	/**
	 * 获取利息的现金结转日期
	 * @param cashFlowCfg	现金流配置
	 * @param altDate	截止日
	 * @return	现金结转日期
	 */
	public Date getCpnSetDate(CashFlowCfg cashFlowCfg, Date altDate){
		Date setDate=null;
		List<Date> holidayList=holidayLSDao.loadHolidayList(cashFlowCfg.getHolidaySD());
		if(cashFlowCfg.getCpnDelayDateNum()>0){
			if(cashFlowCfg.getCpnDelayDateType()==CouponData.NaturalDay){
				setDate=DateUtil.getNextNDays(altDate, cashFlowCfg.getCpnDelayDateNum());
			}else if(cashFlowCfg.getCpnDelayDateType()==CouponData.BusinessDay){
				setDate=DateUtil.getNextNBusinessDays(altDate, cashFlowCfg.getCpnDelayDateNum(), holidayList);
			}else{
				setDate=DateUtil.getNextNDays(altDate, cashFlowCfg.getCpnDelayDateNum());
			}
		}else{
			setDate=altDate;
		}
		if(cashFlowCfg.getCpnDelayDateType()==CouponData.BusinessDay && !DateUtil.isBusinessDay(setDate, holidayList)){
			setDate=DateUtil.getNextBusinessDay(setDate, holidayList);
		}
		return setDate;
	}
	
	/**
	 * 获取自动到期，交易的结算日
	 * @param cashFlowCfg	现金流配置
	 * @param altDate	截止日
	 * @return	现金结转日期
	 */
	public Date getCpnMaturitySetDate(CashFlowCfg cashFlowCfg, Date altDate){
		Date setDate=null;
		List<Date> holidayList=holidayLSDao.loadHolidayList(cashFlowCfg.getHolidaySD());
		if(cashFlowCfg.getEndNDays()!=0){
			if(cashFlowCfg.getEndNdaysType()==CouponData.NaturalDay){
				setDate=DateUtil.getNextNDays(altDate, cashFlowCfg.getEndNDays());
			}else if(cashFlowCfg.getEndNdaysType()==CouponData.BusinessDay){
				setDate=DateUtil.getNextNBusinessDays(altDate, cashFlowCfg.getEndNDays(), holidayList);
			}else{
				setDate=DateUtil.getNextNDays(altDate, cashFlowCfg.getEndNDays());
			}
		}else{
			setDate=altDate;
		}
		
		if(cashFlowCfg.getEndNdaysType()==CouponData.BusinessDay && !DateUtil.isBusinessDay(setDate, holidayList)){
			setDate=DateUtil.getNextBusinessDay(setDate, holidayList);
		}
		return setDate;
	}
	
	/**
	 * 判断当天是否出结息凭证
	 * @param cfg	现金流配置
	 * @param lastGlCloseDate	上一个总账关帐日
	 * @param ledgerDate	出账日
	 * @return	是否已经出结息凭证
	 */
	public boolean checkGenSetInterestVoucher(CashFlowCfg cfg, Date lastGlCloseDate, Date ledgerDate){
		Date endDate=getNextSetInterestDate(cfg, lastGlCloseDate, ledgerDate);
		if(!ledgerDate.before(endDate)){
			if(cfg.isAutoInterestFlag())
				return true;
			else
				return false;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取下一个结息日
	 * @param cfg	现金流配置
	 * @param lastGlCloseDate	上一个总账关帐日
	 * @param ledgerDate	出账日
	 * @return	下一个结息日
	 */
	private Date getNextSetInterestDate(CashFlowCfg cfc, Date lastGlCloseDate, Date ledgerDate){
		Calendar ledgerCA=Calendar.getInstance(); 
		ledgerCA.setTime(ledgerDate);
		Date endDate=DateUtil.convertStr2Date("20000101");
		int acuMonth=0;
		int acuDay=cfc.getCpnDate();
		int curDay=ledgerCA.get(Calendar.DAY_OF_MONTH);
		int curYear=ledgerCA.get(Calendar.YEAR);
		
		if(acuDay==31){
			//当填写31号,默认为每个月的最后一天
			acuDay=ledgerCA.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		
		String acuDayStr=String.valueOf(acuDay);
		if(acuDay<10){
			acuDayStr="0"+acuDayStr;
		}
		
		switch(cfc.getCpnFreq()){
		case CouponData.AtMaturity:
			return DateUtil.convertStr2Date("29991231");
		case CouponData.EveryNDays:
			int diffDays=DateUtil.getDiffDays(lastGlCloseDate, ledgerDate);
			if(diffDays>=cfc.getCpnDate()){
				return DateUtil.convertStr2Date(DateUtil.convertD2YYYYMMDD(ledgerCA.getTime()));
			}
			break;
		case CouponData.SemiAnnual:
			String Sdate0=(curYear-1)+"07"+acuDayStr;
			String Sdate1=curYear+"01"+acuDayStr;
			String Sdate2=curYear+"07"+acuDayStr;
			String Sdate3=(curYear+1)+"01"+acuDayStr;
			if(cfc.getCpnMo()==InterestCpnMo.Feb_AUG){
				 Sdate0=(curYear-1)+"08"+acuDayStr;
				 Sdate1=curYear+"02"+acuDayStr;
				 Sdate2=curYear+"08"+acuDayStr;
				 Sdate3=(curYear+1)+"02"+acuDayStr;
			}else if(cfc.getCpnMo()==InterestCpnMo.Mar_Sep){
				 Sdate0=(curYear-1)+"09"+acuDayStr;
				 Sdate1=curYear+"03"+acuDayStr;
				 Sdate2=curYear+"09"+acuDayStr;
				 Sdate3=(curYear+1)+"03"+acuDayStr;
			}else if(cfc.getCpnMo()==InterestCpnMo.Apr_oct){
				 Sdate0=(curYear-1)+"10"+acuDayStr;
				 Sdate1=curYear+"04"+acuDayStr;
				 Sdate2=curYear+"10"+acuDayStr;
				 Sdate3=(curYear+1)+"04"+acuDayStr;
			}else if(cfc.getCpnMo()==InterestCpnMo.May_Nov){
				 Sdate0=(curYear-1)+"11"+acuDayStr;
				 Sdate1=curYear+"05"+acuDayStr;
				 Sdate2=curYear+"11"+acuDayStr;
				 Sdate3=(curYear+1)+"05"+acuDayStr;
			}else if(cfc.getCpnMo()==InterestCpnMo.Jun_Dec){
				 Sdate0=(curYear-1)+"12"+acuDayStr;
				 Sdate1=curYear+"06"+acuDayStr;
				 Sdate2=curYear+"12"+acuDayStr;
				 Sdate3=(curYear+1)+"06"+acuDayStr;
			}
			String[] Sdates={Sdate0,Sdate1,Sdate2,Sdate3,};
			return fetchPeriodEndDate(ledgerDate, Sdates);
		case CouponData.Monthly:
			//如果日期大于月交割日 cpnDate
			/*	ledgerCA.set(Calendar.YEAR, Calendar.MONTH, acuDay);*/
			//如果当天已经在
			int curMonth=ledgerCA.get(Calendar.MONTH);
			ledgerCA.set(curYear, curMonth, acuDay);
			if(curDay>acuDay){
				ledgerCA.add(Calendar.MONTH,1);
				return DateUtil.convertStr2Date(DateUtil.convertD2YYYYMMDD(ledgerCA.getTime()));
			}else{
				return DateUtil.convertStr2Date(DateUtil.convertD2YYYYMMDD(ledgerCA.getTime()));
			}
		case CouponData.Quarterly:
			String date0=(curYear-1)+"10"+acuDayStr;
			String date1=curYear+"01"+acuDayStr;
			String date2=curYear+"04"+acuDayStr;
			String date3=curYear+"07"+acuDayStr;
			String date4=curYear+"10"+acuDayStr;
			String date5=(curYear+1)+"01"+acuDayStr;
			if(cfc.getCpnMo()==InterestCpnMo.F_M_A_N){
				date0=(curYear-1)+"11"+acuDayStr;
				date1=curYear+"02"+acuDayStr;
				date2=curYear+"05"+acuDayStr;
				date3=curYear+"08"+acuDayStr;
				date4=curYear+"11"+acuDayStr;
				date5=(curYear+1)+"02"+acuDayStr;
			}else if(cfc.getCpnMo()==InterestCpnMo.M_J_S_D){
				date0=(curYear-1)+"12"+acuDayStr;
				date1=curYear+"03"+acuDayStr;
				date2=curYear+"06"+acuDayStr;
				date3=curYear+"09"+acuDayStr;
				date4=curYear+"12"+acuDayStr;
				date5=(curYear+1)+"03"+acuDayStr;
			}
			String[] dates={date0,date1,date2,date3,date4,date5};
			return fetchPeriodEndDate(ledgerDate, dates);
		case CouponData.Annual:
			//cal的月份时需要减1的
			acuMonth=cfc.getCpnMo()-1;
			
			Calendar thisYearCal=Calendar.getInstance(); 
			thisYearCal.set(curYear, acuMonth, acuDay);
			if(ledgerCA.compareTo(thisYearCal)>0){
				thisYearCal.add(Calendar.YEAR, 1);
			}
			return DateUtil.convertStr2Date(DateUtil.convertD2YYYYMMDD(thisYearCal.getTime()));
		case CouponData.Scheduled:
			List<Date> sechDateList=cashFlowPaymentDao.loadCashFlowSchedule(cfc.getCashFlowID());
			String[] schDates=new String[sechDateList.size()];
			for(int i=0;i<sechDateList.size();i++){
				schDates[i]=DateUtil.convertD2YYYYMMDD(sechDateList.get(i));
			}
			return fetchPeriodEndDate(ledgerDate, schDates);
		default:
			break;
		}
		
		return DateUtil.convertStr2Date(DateUtil.convertD2YYYYMMDD(endDate));
	}
	
	/**
	 * 获取目前所在日期的上一个最后一个计息日,和本期的最后一个计息日
	 * @param ledgerDate	出账日
	 * @param cfc			现金流配置
	 * @param cfgType		coupone/float等
	 * @return 目前所在日期的上一个最后一个计息日,和本期的最后一个计息日
	 */
	public InterestCycleDate getPeriodCycleDate(Date ledgerDate, CashFlowCfg cfc, int cfgType){
		int cpnFreq=cfc.getCpnFreq();
		int cpnMo=cfc.getCpnMo();
		int cpnDate=cfc.getCpnDate();
		
		if(cfgType==CashFlowCfgType.FloatCf){
			cpnFreq=cfc.getVrResetFreq();
			cpnMo=cfc.getVrResetMo();
			cpnDate=cfc.getVrResetDate();
		}
		
		InterestCycleDate intecycle=new InterestCycleDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(ledgerDate);
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		
		String cpnDateStr=String.valueOf(cpnDate);
		if(cpnDate<10){
			cpnDateStr="0"+cpnDateStr;
		}
		
		Date lastInteDate=null;
		Date startInteDate=null;
		Date endInteDate=null;
		
		if(cpnFreq==CouponData.Annual || cpnFreq==CouponData.SemiAnnual || cpnFreq==CouponData.Quarterly 
				|| cpnFreq==CouponData.Monthly){
			String date0=(year-1)+"12"+cpnDateStr;
			String date1=year+"03"+cpnDateStr;
			String date2=year+"06"+cpnDateStr;
			String date3=year+"09"+cpnDateStr;
			String date4=year+"12"+cpnDateStr;
			String date5=(year+1)+"03"+cpnDateStr;
			String[] dates=null;
			
			switch(cpnMo){
				case InterestCpnMo.Apr_oct:{
					date0=(year-1)+"10"+cpnDateStr;
					date1=year+"04"+cpnDateStr;
					date2=year+"10"+cpnDateStr;
					date3=(year+1)+"04"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3};
					break;
				}
				case InterestCpnMo.April:{
					date0=(year-1)+"04"+cpnDateStr;
					date1=year+"04"+cpnDateStr;
					date2=(year+1)+"04"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.At_Mat:
				case InterestCpnMo.August:{
					date0=(year-1)+"08"+cpnDateStr;
					date1=year+"08"+cpnDateStr;
					date2=(year+1)+"08"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.December:{
					date0=(year-1)+"12"+cpnDateStr;
					date1=year+"12"+cpnDateStr;
					date2=(year+1)+"12"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.F_M_A_N:{
					date0=(year-1)+"11"+cpnDateStr;
					date1=year+"02"+cpnDateStr;
					date2=year+"05"+cpnDateStr;
					date3=year+"08"+cpnDateStr;
					date4=year+"11"+cpnDateStr;
					date5=(year+1)+"01"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3,date4,date5};
					break;
				}
				case InterestCpnMo.February:{
					date0=(year-1)+"02"+cpnDateStr;
					date1=year+"02"+cpnDateStr;
					date2=(year+1)+"02"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.IMM_Roll:
				case InterestCpnMo.J_A_J_O:{
					date0=(year-1)+"10"+cpnDateStr;
					date1=year+"01"+cpnDateStr;
					date2=year+"04"+cpnDateStr;
					date3=year+"07"+cpnDateStr;
					date4=year+"10"+cpnDateStr;
					date5=(year+1)+"01"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3,date4,date5};
					break;
				}
				case InterestCpnMo.Jan_Jul:{
					date0=(year-1)+"07"+cpnDateStr;
					date1=year+"01"+cpnDateStr;
					date2=year+"07"+cpnDateStr;
					date3=(year+1)+"01"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3};
					break;
				}
				case InterestCpnMo.January:{
					date0=(year-1)+"01"+cpnDateStr;
					date1=year+"01"+cpnDateStr;
					date2=(year+1)+"01"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.July:{
					date0=(year-1)+"07"+cpnDateStr;
					date1=year+"07"+cpnDateStr;
					date2=(year+1)+"07"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.Jun_Dec:{
					date0=(year-1)+"12"+cpnDateStr;
					date1=year+"06"+cpnDateStr;
					date2=year+"12"+cpnDateStr;
					date3=(year+1)+"06"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3};
					break;
				}
				case InterestCpnMo.June:{
					date0=(year-1)+"06"+cpnDateStr;
					date1=year+"06"+cpnDateStr;
					date2=(year+1)+"06"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.M_J_S_D:{
					date0=(year-1)+"12"+cpnDateStr;
					date1=year+"03"+cpnDateStr;
					date2=year+"06"+cpnDateStr;
					date3=year+"09"+cpnDateStr;
					date4=year+"12"+cpnDateStr;
					date5=(year+1)+"03"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3,date4,date5};
					break;
				}
				case InterestCpnMo.Mar_Sep:{
					date0=(year-1)+"09"+cpnDateStr;
					date1=year+"03"+cpnDateStr;
					date2=year+"09"+cpnDateStr;
					date3=(year+1)+"03"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3};
					break;
				}
				case InterestCpnMo.March:{
					date0=(year-1)+"03"+cpnDateStr;
					date1=year+"03"+cpnDateStr;
					date2=(year+1)+"03"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.May:{
					date0=(year-1)+"05"+cpnDateStr;
					date1=year+"05"+cpnDateStr;
					date2=(year+1)+"05"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.Feb_AUG:{
					date0=(year-1)+"08"+cpnDateStr;
					date1=year+"02"+cpnDateStr;
					date2=year+"08"+cpnDateStr;
					date3=(year+1)+"02"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3};
					break;
				}
				case InterestCpnMo.May_Nov:{
					date0=(year-1)+"11"+cpnDateStr;
					date1=year+"05"+cpnDateStr;
					date2=year+"11"+cpnDateStr;
					date3=(year+1)+"05"+cpnDateStr;
					dates=new String[]{date0,date1,date2,date3};
					break;
				}
				case InterestCpnMo.Monthly:{
					if(month==1){
						date0=(year-1)+"12"+cpnDateStr;
					}else{
						date0=year+String.valueOf(month-1)+cpnDateStr;
					}
					if(date0.length()==7){
						date0=date1.substring(0, 4)+"0"+date0.substring(4, 7);
					}
					date1=year+String.valueOf(month)+cpnDateStr;
					if(date1.length()==7){
						date1=date1.substring(0, 4)+"0"+date1.substring(4, 7);
					}
					if(month==12){
						date2=(year+1)+"01"+cpnDateStr;
					}else{
						date2=year+String.valueOf(month+1)+cpnDateStr;
					}
					if(date2.length()==7){
						date2=date2.substring(0, 4)+"0"+date2.substring(4, 7);
					}
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.November:{
					date0=(year-1)+"09"+cpnDateStr;
					date1=year+"09"+cpnDateStr;
					date2=(year+1)+"09"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.October:{
					date0=(year-1)+"10"+cpnDateStr;
					date1=year+"10"+cpnDateStr;
					date2=(year+1)+"10"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.September:{
					date0=(year-1)+"09"+cpnDateStr;
					date1=year+"09"+cpnDateStr;
					date2=(year+1)+"09"+cpnDateStr;
					dates=new String[]{date0,date1,date2};
					break;
				}
				case InterestCpnMo.Weekly:
				default:{
					if(month==1){
						date0=(year-1)+"12"+cpnDateStr;
					}else{
						date0=year+String.valueOf(month-1)+cpnDateStr;
					}
					date1=year+String.valueOf(month)+cpnDateStr;
					if(month==12){
						date2=(year+1)+"01"+cpnDateStr;
					}else{
						date2=year+String.valueOf(month+1)+cpnDateStr;
					}
					if(date2.length()==7){
						date2=date2.substring(0, 4)+"0"+date2.substring(4, 7);
					}
					dates=new String[]{date0,date1,date2};
					break;
				}
			}
			
			lastInteDate=fetchLastInteDate(ledgerDate, dates);
			if(lastInteDate.before(cfc.getFirstIntDate())){
				lastInteDate=cfc.getFirstIntDate();
			}
			startInteDate=DateUtil.getNextDay(lastInteDate);
			endInteDate=fetchPeriodEndDate(ledgerDate, dates);
		}else if(cfc.getCpnFreq()==CouponData.Scheduled){
			List<Date> sechDateList=null;
			if(cfgType==CashFlowCfgType.FloatCf) {
				sechDateList=cashFlowPaymentDao.loadCashFlowRestSchedule(cfc.getCashFlowID());
			}else {
				sechDateList=cashFlowPaymentDao.loadCashFlowSchedule(cfc.getCashFlowID());
			}
				
			String[] schDates=new String[sechDateList.size()];
			for(int i=0;i<sechDateList.size();i++){
				schDates[i]=DateUtil.convertD2YYYYMMDD(sechDateList.get(i));
			}
			lastInteDate=fetchLastInteDate(ledgerDate, schDates);
			if(lastInteDate.before(cfc.getFirstIntDate())){
				lastInteDate=cfc.getFirstIntDate();
			}
			startInteDate=DateUtil.getNextDay(lastInteDate);
			endInteDate=fetchPeriodEndDate(ledgerDate, schDates);
		}else if(cfc.getCpnFreq()==CouponData.FollowPayDate){
			InterestCycleDate floatCycle=getPeriodCycleDate(ledgerDate, cfc, CashFlowCfgType.Coupon);
			return floatCycle;
		}else{
			lastInteDate=DateUtil.convertStr2Date("20000101");
			startInteDate=DateUtil.getNextDay(lastInteDate);
			endInteDate=DateUtil.convertStr2Date("29991231");
		}
		intecycle.setLastInteEndDate(lastInteDate);
		intecycle.setStartInteDate(startInteDate);
		intecycle.setEndIInteDate(endInteDate);
		
		return intecycle;
	}
	
	private Date fetchPeriodEndDate(Date ledgerDate, String[] datesRul){
		String lastDate="";
		String curDate=DateUtil.convertD2YYYYMMDD(ledgerDate);
		for(int i=0;i<datesRul.length;i++){
			if(curDate.compareTo(datesRul[i])<=0){
				lastDate=datesRul[i];
				break;
			}
		}
		if(lastDate.equals("")){
			//付息计划，到期等，设置没有结束日
			return DateUtil.convertStr2Date("29991231");
		}else{
			return DateUtil.convertStr2Date(lastDate);
		}
	}
	
	/**
	 * 获取最后一个计息日
	 * @param ledgerDate
	 * @param datesRul
	 * @return
	 */
	private Date fetchLastInteDate(Date ledgerDate, String[] datesRul){
		String periodDate="";
		String curDate=DateUtil.convertD2YYYYMMDD(ledgerDate);
		for(int i=1;i<datesRul.length;i++){
			if(curDate.compareTo(datesRul[i])<=0){
				periodDate=datesRul[i-1];
				break;
			}
		}
		if(periodDate.equals("")){
			return DateUtil.convertStr2Date("20000101");
		}else{
			return DateUtil.convertStr2Date(periodDate);
		}
	}
	
	/**
	 * 面值的变动
	 * @param initParValue	初始面值
	 * @param cfc	现金流配置
	 * @param asOfDate	当前做账日
	 * @return
	 */
	public ParChange getPayParChange(double initParValue, CashFlowCfg cfc, Date asOfDate){
		ParChange par=new ParChange(initParValue);
		
		Set<CashFlowPar> parscfg=cfc.getCashFlowPar();
		if(parscfg!=null){
			double todayparValue=initParValue;	//当天的面值
			double yesterdayParValue=initParValue;	//前一天的面值
			double payParRate=0;	//当天降低面值的比例
			double payParToday=0;	//当天降低面值的数量
			
			double parPercent=0;
			double yesterdayParPercent=0;
			Iterator<CashFlowPar> parsIter=parscfg.iterator();
			while(parsIter.hasNext()){
				CashFlowPar newPar=parsIter.next();
				if(!newPar.getPaymentDate().after(asOfDate)){
					parPercent=parPercent+newPar.getPaymentRate();
					
					if(newPar.getPaymentDate().before(asOfDate)){
						yesterdayParPercent=yesterdayParPercent+newPar.getPaymentRate();
					}else{
						payParRate=newPar.getPaymentRate();
						payParToday=RoundUtil.roundMoney2Dec(initParValue*newPar.getPaymentRate());
					}
						
				}
			}
			todayparValue=RoundUtil.roundMoney2Dec(todayparValue*(1-parPercent));
			yesterdayParValue=RoundUtil.roundMoney2Dec(yesterdayParValue*(1-yesterdayParPercent));
			par.setTodayParValue(todayparValue);
			par.setYesterdayParValue(yesterdayParValue);
			par.setParParRateToday(payParRate);
			par.setPayParToday(payParToday);
		}
		return par;
	}
	
	/**
	 * 计算浮动利率
	 * @param portnum
	 * @param cfg
	 * @param asOfDate
	 * @return
	 */
	private double calCashFloatRate(String portnum, CashFlowCfg cfc, Date asOfDate){
		InterestCycleDate cycleDate=getPeriodCycleDate(asOfDate, cfc, CashFlowCfgType.FloatCf);
		//获取本轮的开始日
		Date startDate=cycleDate.getLastInteEndDate();
		if(JixuUserUtil.isJoyinTablesFlag()) {
			startDate=cashFlowPaymentDao.getCashFlowResetRateDate(cfc.getCashFlowID(), asOfDate);
		}
		if(cycleDate.getEndIInteDate().compareTo(asOfDate)==0) {
			startDate=asOfDate;
		}
		if(cfc.getVrResetNDays()!=0){
			List<Date> holidayList=holidayLSDao.loadHolidayList(cfc.getResetHolidaySched());
			startDate=DateUtil.getNextNBusinessDays(startDate, cfc.getVrResetNDays(), holidayList);
		}
		
		startDate=DateUtil.getNextNMonths(startDate, cfc.getVrDeterAdjMo());
		startDate=DateUtil.getNextNDays(startDate, cfc.getVrDeterAdjDays());
		String cusip=cfc.getVrBase();
		double rate=0;
		//获取基准利率 比如shibo当前行情
		if(JixuUserUtil.isJoyinTablesFlag()) {
			MstIntRateInfo reateInfo=finProductCommonBatchService.getValIntRateInfo(startDate, cusip).get(cusip);
			if(reateInfo!=null) {
				rate=reateInfo.getIntRate().doubleValue()*100;
			}
		}else {
			rate=comService.loadCusipPrice(portnum, cusip, startDate);
		}
		if(cfc.getVrMethod()==CashFlowFloatMethod.PlusCal){
			rate=rate+cfc.getVrBasisPtAdjustment();
		}else if(cfc.getVrMethod()==CashFlowFloatMethod.MulCal){
			rate=rate*(1+cfc.getVrBasisPtAdjustment());
		}
		
		if(cfc.getVrMax()!=0 && rate>cfc.getVrMax()){
			rate=cfc.getVrMax();
		}else if(cfc.getVrMin()!=0 && rate<cfc.getVrMin()){
			rate=cfc.getVrMin();
		}
		
		return rate;
	}
}
