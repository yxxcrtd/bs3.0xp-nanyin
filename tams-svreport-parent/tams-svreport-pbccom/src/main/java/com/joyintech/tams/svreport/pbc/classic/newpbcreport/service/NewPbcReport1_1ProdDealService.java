package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.lang.UUID;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportProdDat1_1Dto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.util.MonthReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 表1-1资管产品募集余额导入
 */
@Service
public class NewPbcReport1_1ProdDealService extends AbstractNewPbcMonthProdReport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_1_1;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportProdDat1_1Dto> dataFromExcel = MonthReportUtil.createDataFromExcel(paramDto.getHssfSheet(), PbcReportType.PBC_REPORT_1_1, ExcelPbcReportProdDat1_1Dto.class);
            List<PbcReportProdDatDto> pbcReportProdDatDtos = new ArrayList<>();
            for (ExcelPbcReportProdDat1_1Dto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportProdDatDto pbcReportProdDatDto = new PbcReportProdDatDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,pbcReportProdDatDto);
                pbcReportProdDatDto.setDetailuuid(UUID.randomUUID().toString());
                pbcReportProdDatDto.setReportuuid(paramDto.getReportUUID());
                pbcReportProdDatDto.setReporttype(PBC_REPORT);
                pbcReportProdDatDto.setDatZipCode(paramDto.getDatZipCode());
                pbcReportProdDatDtos.add(pbcReportProdDatDto);
            }
            PbcSendZipDatFile sendZipDatFile = newPbcSendZipDatFile(paramDto,pbcReportProdDatDtos.size());
            sendZipDatFile.setDatZipDatType(PBC_REPORT);
            deleteData(paramDto,PBC_REPORT);
            saveZipDatFile(pbcReportProdDatDtos,sendZipDatFile);
        }catch (Exception e){
            ExcelValidDto dto = new ExcelValidDto();
            dto.setErrorInfo("导入"+sheetName()+"失败");
            excelValidDtos.add(dto);
            logger.error("导入"+sheetName()+"异常",e);
        }

        return excelValidDtos;
    }

    @Override
    public String sheetName() {
        return SHEET_1_1;
    }
}
