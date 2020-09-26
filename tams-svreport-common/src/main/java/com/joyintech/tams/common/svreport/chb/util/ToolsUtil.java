package com.joyintech.tams.common.svreport.chb.util;

import java.util.Calendar;
import java.util.Date;

import com.joyintech.framework.util.DateUtil;

/**
 * 共通工具类<br>
 * @author lingxq
 * @version 1.0 2019年03月06日
 * @see DataUtil
 * @since 1.0
 */
public class ToolsUtil {
	
	 /**
	 * 获取当前时间
	 * @param date
	 * @return
	 */
     public static Date getCurrentTime(Date date){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
        if(null == date){
        	return  calendar.getTime();
    	}
    	int hour = calendar.get(Calendar.HOUR_OF_DAY);
 		int min = calendar.get(Calendar.MINUTE);
 		int sec = calendar.get(Calendar.SECOND);
    	 
 		calendar.setTime(DateUtil.parse(DateUtil.format(date) +" "+ hour + ":" + min + ":"+ sec));
 		
 		return calendar.getTime();
     }
	
}
