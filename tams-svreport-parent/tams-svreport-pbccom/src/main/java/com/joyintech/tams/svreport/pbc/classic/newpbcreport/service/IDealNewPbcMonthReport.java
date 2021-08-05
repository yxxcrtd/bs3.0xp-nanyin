package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import com.joyintech.tams.report.common.dto.ExcelValidDto;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Set;

public interface IDealNewPbcMonthReport {

    List<ExcelValidDto> dealExcelData(Sheet sheet);

    Set<String> sheetName();
}
