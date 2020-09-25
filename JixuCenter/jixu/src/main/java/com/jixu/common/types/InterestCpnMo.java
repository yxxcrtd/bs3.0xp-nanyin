package com.jixu.common.types;

import java.util.HashMap;

public class InterestCpnMo {
	public final static int At_Mat = 0;
	public final static int January=1;
	public final static int February=2;
	public final static int March=3;
	public final static int April=4;
	public final static int May=5;
	public final static int June=6;
	public final static int July=7;
	public final static int August=8;
	public final static int September=9;
	public final static int October=10;
	public final static int November=11;
	public final static int December=12;
	public final static int Jan_Jul=13;
	public final static int Feb_AUG=14;
	public final static int Mar_Sep=15;
	public final static int Apr_oct=16;
	public final static int May_Nov=17;
	public final static int Jun_Dec=18;
	public final static int J_A_J_O=19;
	public final static int F_M_A_N=20;
	public final static int M_J_S_D=21;
	public final static int Monthly=22;
	public final static int Scheduled=23;
	public final static int Weekly=24;
	public final static int IMM_Roll=25;
	
	public final static HashMap<Integer, String> quarterHM=new HashMap<Integer, String>();
	static{	
		quarterHM.put(J_A_J_O, "1月-4月-7月-10月");
		quarterHM.put(F_M_A_N, "2月-5月-8月-11月");
		quarterHM.put(M_J_S_D, "3月-6月-9月-12月");
	}
	
	public final static HashMap<Integer, String> semiAnnualHM=new HashMap<Integer, String>();
	static{
		semiAnnualHM.put(Jan_Jul, "1月-7月");
		semiAnnualHM.put(Feb_AUG, "2月-8月");
		semiAnnualHM.put(Mar_Sep, "3月-9月");
		semiAnnualHM.put(Apr_oct, "4月-10月");
		semiAnnualHM.put(May_Nov, "5月-11月");
		semiAnnualHM.put(Jun_Dec, "6月-12月");
	}
	
	public final static HashMap<Integer, String> annualHM=new HashMap<Integer, String>();
	static{
		annualHM.put(January, "1月");
		annualHM.put(February, "2月");
		annualHM.put(March, "3月");
		annualHM.put(April, "4月");
		annualHM.put(May, "5月");
		annualHM.put(June, "6月");
		annualHM.put(July, "7月");
		annualHM.put(August, "8月");
		annualHM.put(September, "9月");
		annualHM.put(October, "10月");
		annualHM.put(November, "11月");
		annualHM.put(December, "12月");
	} 
	
	public final static HashMap<Integer, String> monthlyHM=new HashMap<Integer, String>();
	static{
		monthlyHM.put(Monthly, "每月");
	}
}
