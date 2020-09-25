package com.joyintech.tams.invest.common.risktrialrds.dict;

public enum SupervisionFrequency {

	//new
	DAY_1(1)
	, DAY_7(7)
	, DAY_30(30)
	, DAY_90(90)
	, DAY_182(182)
	, DAY_365(365)
	//old
	, @Deprecated DAILY(1)
	, @Deprecated WEEKLY(7)
	, @Deprecated MONTHLY(30)
	, @Deprecated QUARTERLY(90)
	, @Deprecated SEMIANNUALLY(182)
	, @Deprecated YEARLY(365)
	;
	
	private int days;
	private SupervisionFrequency(int days) {
		this.days = days;
	}
	
	public int getDays() {
		return days;
	}
}
