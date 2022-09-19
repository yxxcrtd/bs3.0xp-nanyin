/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author chenxc
 *
 */
public class DurationUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	


	public static Date getQuarterStart(int year,int quarter) {
		Calendar sDate = Calendar.getInstance();
		sDate.set(Calendar.HOUR_OF_DAY, 0);
		sDate.set(Calendar.MINUTE, 0);
		sDate.set(Calendar.SECOND, 0);
		switch (quarter ) {
		case 1:
			sDate.set(year, 0, 1);
			break;
		case 2:
			sDate.set(year, 3, 1);
			break;
		case 3:
			sDate.set(year, 6, 1);
			break;
		case 4:
			sDate.set(year, 9, 1);
			break;
		}
		
		return sDate.getTime();
	}
	
	public static Date getQuarterEnd(int year,int quarter) {
		Calendar eDate = Calendar.getInstance();
		eDate.set(Calendar.HOUR_OF_DAY, 0);
		eDate.set(Calendar.MINUTE, 0);
		eDate.set(Calendar.SECOND, 0);
		switch (quarter ) {
		case 1:
			eDate.set(year, 2, 31);
			break;
		case 2:
			eDate.set(year, 5, 30);
			break;
		case 3:
			eDate.set(year, 8, 30);
			break;
		case 4:
			eDate.set(year, 11, 31);
			break;
		}
		
		return eDate.getTime();
	}
	
	public static String getQuarterDuration(int year,int season,Date startDate) {
		String dur= "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date sDate = getQuarterStart(year,season);
		dur = sdf.format(sDate);
		if ( startDate!=null && sDate.compareTo(startDate)<0 ) {
			dur = sdf.format(startDate);
		}
		Date eDate = getQuarterEnd(year,season);
		dur = dur+" - " + sdf.format(eDate.getTime());
		return dur;
	}
	
	public static Date getHalfYearStart(int year,int halfYear) {
		Calendar eDate = Calendar.getInstance();
		eDate.set(Calendar.HOUR_OF_DAY, 0);
		eDate.set(Calendar.MINUTE, 0);
		eDate.set(Calendar.SECOND, 0);
		switch (halfYear ) {
		case 1:
			eDate.set(year, 0, 1);
			break;
		case 2:
			eDate.set(year,6,1);
			break;
		}
		return eDate.getTime();
	}
	
	public static Date getHalfYearEnd(int year,int halfYear) {
		Calendar eDate = Calendar.getInstance();
		eDate.set(Calendar.HOUR_OF_DAY, 0);
		eDate.set(Calendar.MINUTE, 0);
		eDate.set(Calendar.SECOND, 0);
		switch (halfYear ) {
		case 1:
			eDate.set(year, 5, 30);
			break;
		case 2:
			eDate.set(year,11,31);
			break;
		}
		return eDate.getTime();
	}
	
	public static String getHalfYearDuration(int year,int halfYear,Date startDate) {
		String dur= "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date sDate = getHalfYearStart(year,halfYear);
		dur = sdf.format(sDate);
		if ( startDate!=null && sDate.compareTo(startDate)<0 ) {
			dur = sdf.format(startDate);
		}
		Date eDate = getHalfYearEnd(year,halfYear);
		dur = dur+" - " + sdf.format(eDate.getTime());
		return dur;
	}
	
	public static Date getYearStart(int year) {
		Calendar eDate = Calendar.getInstance();
		eDate.set(Calendar.HOUR_OF_DAY, 0);
		eDate.set(Calendar.MINUTE, 0);
		eDate.set(Calendar.SECOND, 0);		
		eDate.set(year,0,1);
		return eDate.getTime();
	}
	
	public static Date getYearEnd(int year) {
		Calendar eDate = Calendar.getInstance();
		eDate.set(Calendar.HOUR_OF_DAY, 0);
		eDate.set(Calendar.MINUTE, 0);
		eDate.set(Calendar.SECOND, 0);		
		eDate.set(year,11,31);
		return eDate.getTime();
	}
	
	public static String getYearDuration(int year,Date startDate) {
		String dur= "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date sDate = getYearStart(year);
		dur = sdf.format(sDate);
		if ( startDate!=null && sDate.compareTo(startDate)<0 ) {
			dur = sdf.format(startDate);
		}
		Date eDate = getYearEnd(year);
		dur = dur+" - " + sdf.format(eDate.getTime());
		return dur;
	}
}
