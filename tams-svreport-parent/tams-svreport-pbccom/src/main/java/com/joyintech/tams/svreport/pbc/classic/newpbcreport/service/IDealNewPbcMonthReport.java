package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Set;

public interface IDealNewPbcMonthReport {

    List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto);

    String sheetName();
}
