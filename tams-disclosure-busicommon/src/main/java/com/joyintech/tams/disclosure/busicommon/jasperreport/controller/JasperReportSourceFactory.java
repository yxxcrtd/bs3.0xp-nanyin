package com.joyintech.tams.disclosure.busicommon.jasperreport.controller;
import java.sql.Date;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.busicommon.jasperreport.service.JasperReportDataSourceListService;

import net.sf.jasperreports.engine.JRDataSource; 

public class JasperReportSourceFactory { 
    public static JRDataSource createDatasource() throws Exception{
        return new JasperReportDataSourceListService("DK3LK180003", 
        		new Date((DateUtil.parse("2018-05-09")).getTime()), 
        		new Date((DateUtil.parse("2018-06-30")).getTime()),"pAjZ_TWO");
    }
    
    public static void main(String[] args) throws Exception {
    	createDatasource();
    }
}
    