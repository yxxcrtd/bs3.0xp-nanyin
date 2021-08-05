package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.util.MonthReportUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 表1-1资管产品募集余额导入
 */
@Service
public class NewPbcReport1_1ProdDealService extends AbstractDealNewPbcMonthReport{

    @Autowired
    private PbcReportProdDatMapper pbcReportProdDatMapper;

    @Override
    public List<ExcelValidDto> dealExcelData(Sheet sheet) {
        try {
            List<ExcelPbcReportProdDatDto> dataFromExcel = MonthReportUtil.createDataFromExcel(sheet, PbcReportType.PBC_REPORT_1_1, ExcelPbcReportProdDatDto.class);
            List<PbcReportProdDatDto> pbcReportProdDatDtos = new ArrayList<>();
            for (ExcelPbcReportProdDatDto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportProdDatDto pbcReportProdDatDto = new PbcReportProdDatDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,pbcReportProdDatDto);
                pbcReportProdDatDtos.add(pbcReportProdDatDto);
            }
        }catch (Exception e){

        }

        return null;
    }

    @Override
    public Set<String> sheetName() {
        Set<String> sheetName = new HashSet<>();
        sheetName.add(SHEET_1_1);
        return sheetName;
    }
}
