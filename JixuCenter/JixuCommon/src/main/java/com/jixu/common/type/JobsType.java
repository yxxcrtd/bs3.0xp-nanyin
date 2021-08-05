package com.jixu.common.type;

import java.util.HashMap;

/**
 * @ClassName JobsType
 * @author Pan Wei
 * @Date 2016年10月24日 下午2:21:01
 * @Description []
 */

public class JobsType {
	public final static int closeTran = 101;
	public final static int clostGL = 102;
	public final static int calNav = 103;
	public final static int ContractCash = 104;
	public final static int autoMaturity = 105;
	public final static int interestMaturity = 106;
	public final static int GENERAL = 151;
	public final static int EXPEND = 152;
	public final static int SUBRED = 153;
	public final static int CASH = 154;
	public final static int PURSALEJNL = 155;
	public final static int INCOME = 156;
	public final static int PeriodSet = 157;
	public final static int AfterJob = 158;
	public final static int removeDailyVoucherJob = 170;

	public static HashMap<Integer, String> jobsTypeHM = new HashMap<Integer, String>();
	static {
		jobsTypeHM.put(ContractCash, "结转现金");
		jobsTypeHM.put(autoMaturity, "自动到期债券/回购");
		jobsTypeHM.put(closeTran, "交易关帐");
		jobsTypeHM.put(clostGL, "总账关帐");
		jobsTypeHM.put(calNav, "计算净值");
		jobsTypeHM.put(GENERAL, "估增日记账");
		jobsTypeHM.put(EXPEND, "费用日记账");
		jobsTypeHM.put(SUBRED, "申赎日记账");
		jobsTypeHM.put(CASH, "现金日记账");
		jobsTypeHM.put(PURSALEJNL, "交易日记账");
		jobsTypeHM.put(INCOME, "收入日记账");
		jobsTypeHM.put(PeriodSet, "期末结转");
		jobsTypeHM.put(interestMaturity, "自动结息");
		jobsTypeHM.put(AfterJob, "帐后统计");
	}
}
