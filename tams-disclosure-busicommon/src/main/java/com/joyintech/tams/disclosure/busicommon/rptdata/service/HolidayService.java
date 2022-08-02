package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.busicommon.report.service.*;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.util.Calendar;
import com.joyintech.tams.common.base.dao.ChbMktHolidayMapper;
import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.common.base.entity.ChbMktHolidayKey;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.utils.echarts.EchartsException;
import com.joyintech.tams.utils.echarts.EchartsUtils;

/**
 * 报表数据获取服务总分发入口服务
 * 
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @see
 * @since 1.0
 */
@Service
public class HolidayService {

	@Autowired
	private ChbMktHolidayMapper chbMktHolidayMapper;
	
	public Date getLastWordDateOfWeek(Date currDate) {
		//找到本周日
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);//周日
		try {
			//从周日向前，找到第一个工作日
			Date iDate = cal.getTime();
			
			ChbMktHolidayKey key = new ChbMktHolidayKey();
			do {
				key.setBusiDate(iDate);
				key.setMarketCode("01");
				ChbMktHoliday holiday = chbMktHolidayMapper.selectByPrimaryKey(key);
				if ( "0".equals(holiday.getIsHoliday()) )  //工作日
					break;
				iDate = DateUtil.addDay(iDate, -1);
			} while (true);
			return iDate;
		} catch (Exception e ) {
			return DateUtil.addDay(cal.getTime(),-1);  //异常则返回本周五
		}
	}
	
	/**
	 * 获取前一个工作日，不包含当日
	 * @param currDate
	 * @return
	 */
	public Date getPreWorkDate(Date currDate ) {
		ChbMktHolidayKey key = new ChbMktHolidayKey();
		Date iDate = currDate;
		do {
			iDate = DateUtil.addDay(iDate, -1);
			key.setBusiDate(iDate);
			key.setMarketCode("01");
			ChbMktHoliday holiday = chbMktHolidayMapper.selectByPrimaryKey(key);
			if ( holiday==null ) {
				return iDate;
			}
			if ( "0".equals(holiday.getIsHoliday()) ) {  //工作日
				return iDate;
			}
		} while (true);

	}
}
