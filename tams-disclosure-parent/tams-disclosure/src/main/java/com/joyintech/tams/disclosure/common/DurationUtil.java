/**
 * 
 */
package com.joyintech.tams.disclosure.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.icu.util.Calendar;

/**
 * @author Administrator
 *
 */
public class DurationUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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

	public static Date getQuarterEnd(int year,int quarter) {
		Calendar eDate = Calendar.getInstance();
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
	
	public static Date getQuarterStart(int year,int quarter) {
		Calendar sDate = Calendar.getInstance();
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
	
}
