package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.ValInterflog;

public class ValInterflogExt extends ValInterflog {
	
	public String getExDateFmt(){
    	return DateUtil.format(this.getExDate());
    }

}
