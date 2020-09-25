package com.jixu.common.types;

import java.util.Date;

/** 
 * @ClassName 		InterestCycleDate
 * @author  		Pan Wei 
 * @Date 			2016年11月22日 上午11:58:34 	
 * @Description		[对于银行和债券,这三个字段代表的意义不一样。 
 * 					以3/20为例:
 * 					对于银行,3.21是第一个计息日,3.20是结息日，并且3.20计算结转利息的时候，是包括3.20当天利息的
 * 					对于债券,3.20是第一个计息日,3.19是上一轮的最后一天计息日。3.20债券结转利息,并于最近一个工作日结转银行]  
 */

public class InterestCycleDate {
	private Date lastInteEndDate;
	private Date startInteDate;
	//债券等，需要比较当天是否等于endIInteDate,如果相等，起息日=enIInteDate
	private Date endIInteDate;
	
	/**
	 * 本周期第一天计息日，也是上一周期的结息日
	 * @return
	 */
	public Date getLastInteEndDate() {
		return lastInteEndDate;
	}
	public void setLastInteEndDate(Date lastInteEndDate) {
		this.lastInteEndDate = lastInteEndDate;
	}
	
	/**
	 * 本利息周期的第一天
	 * @return
	 */
	public Date getStartInteDate() {
		return startInteDate;
	}
	public void setStartInteDate(Date startInteDate) {
		this.startInteDate = startInteDate;
	}
	
	/**
	 * 本利息周期的最后一天;
	 * @return
	 */
	public Date getEndIInteDate() {
		return endIInteDate;
	}
	public void setEndIInteDate(Date endIInteDate) {
		this.endIInteDate = endIInteDate;
	}
}
