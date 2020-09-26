package com.joyintech.tams.valuation.report.jasperreport.controller;
import java.sql.Date;

import com.joyintech.tams.valuation.report.common.constant.DateUtil;
import com.joyintech.tams.valuation.report.jasperreport.service.JasperReportDataSourceListService;

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
    