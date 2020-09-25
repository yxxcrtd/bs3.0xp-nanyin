package com.jixu.adapter.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName WorkFlowSteps
 * @author Pan Wei
 * @Date 2016年9月15日 下午10:16:03
 * @Description []
 */

public class WorkFlowSteps {
	public final static int smf = 1;
	public final static int price = 2;
	public final static int fxRate = 3;
	public final static int tran = 4;
	public final static int corpAction = 5;
	public final static int fxTrade = 6;
	public final static int ta = 7;
	public final static int cash = 8;
	public final static int manual = 9;

	public final static int autojob = 501;
	public final static int dailyClose = 502;
	public final static int daily = 503;
	public final static int monthly = 504;
	public final static int nav = 505;
	public final static int generalClose = 506;

	public final static int operation_marketData = 0;
	public final static int operation_tranDate = 1;
	public final static int operation_accounting = 2;

	public final static String runAllJobs = "runAllJobs";
	public final static String redoAllJobs = "redoAllJobs";

	public final static HashMap<Integer, String> operationRuleHM = new HashMap<Integer, String>();
	static {
		operationRuleHM.put(operation_marketData, "行情操作");
		operationRuleHM.put(operation_tranDate, "交易操作");
		operationRuleHM.put(operation_accounting, "基金会计");
	}

	public final static HashMap<Integer, String> operations = new HashMap<Integer, String>();
	static {
		operations.put(smf, "证券文件处理");
		operations.put(price, "行情数据处理");
		operations.put(fxRate, "汇率数据处理");
		operations.put(tran, "投资组合交易");
		operations.put(corpAction, "公司行为交易");
		operations.put(fxTrade, "外汇交易操作");
		operations.put(ta, "申购赎回交易");
		operations.put(cash, "现金交易操作");
		operations.put(autojob, "到期自动作业");
		operations.put(dailyClose, "交易关帐");
		operations.put(daily, "每日会计作业");
		operations.put(monthly, "每月会计作业");
		operations.put(nav, "基金净值计算");
		operations.put(generalClose, "会计总账关帐");
	}

	public final static HashMap<String, List<Integer>> accountOperations = new HashMap<String, List<Integer>>();
	static {
		List<Integer> runAllJobsList = new ArrayList<Integer>();
		runAllJobsList.add(autojob);
		runAllJobsList.add(dailyClose);
		runAllJobsList.add(daily);
		runAllJobsList.add(monthly);
		runAllJobsList.add(nav);
		runAllJobsList.add(generalClose);
		accountOperations.put(runAllJobs, runAllJobsList);

		List<Integer> redoAllJobsList = new ArrayList<Integer>();
		redoAllJobsList.add(generalClose);
		redoAllJobsList.add(nav);
		redoAllJobsList.add(monthly);
		redoAllJobsList.add(daily);
		redoAllJobsList.add(dailyClose);
		redoAllJobsList.add(autojob);
		accountOperations.put(redoAllJobs, redoAllJobsList);
	}

	public static HashMap<Integer, List<Integer>> subJobslOptions = new HashMap<Integer, List<Integer>>();
	static {
		List<Integer> dailySubList = new ArrayList<Integer>();
		dailySubList.add(JobsType.CASH);
		dailySubList.add(JobsType.SUBRED);
		dailySubList.add(JobsType.GENERAL);
		dailySubList.add(JobsType.PURSALEJNL);
		dailySubList.add(JobsType.EXPEND);
		dailySubList.add(JobsType.INCOME);
		subJobslOptions.put(daily, dailySubList);

		List<Integer> autoJobList = new ArrayList<Integer>();
		autoJobList.add(JobsType.autoMaturity);
		autoJobList.add(JobsType.ContractCash);
		subJobslOptions.put(autojob, autoJobList);

		List<Integer> navJobList = new ArrayList<Integer>();
		navJobList.add(JobsType.calNav);
		subJobslOptions.put(nav, navJobList);

		List<Integer> monthlyJobList = new ArrayList<Integer>();
		monthlyJobList.add(JobsType.PeriodSet);
		subJobslOptions.put(monthly, monthlyJobList);

		List<Integer> dailycloseJobList = new ArrayList<Integer>();
		dailycloseJobList.add(JobsType.closeTran);
		subJobslOptions.put(dailyClose, dailycloseJobList);

		List<Integer> gencloseJobList = new ArrayList<Integer>();
		gencloseJobList.add(JobsType.clostGL);
		subJobslOptions.put(generalClose, gencloseJobList);
	}
}
