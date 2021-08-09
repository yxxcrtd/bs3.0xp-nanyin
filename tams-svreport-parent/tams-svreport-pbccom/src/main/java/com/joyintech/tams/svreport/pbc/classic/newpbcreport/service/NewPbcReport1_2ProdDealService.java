package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.lang.UUID;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportProdDat1_1Dto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportProdDat1_2DQDFDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportProdDat1_2DQMJDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.util.MonthReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 表1-2募集及兑付发生额
 */
@Service
public class NewPbcReport1_2ProdDealService extends AbstractNewPbcMonthProdReport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_1_2;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportProdDat1_2DQMJDto> dataFromExcel = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportProdDat1_2DQMJDto.class);
            List<ExcelPbcReportProdDat1_2DQDFDto> dataFromExcel1 = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportProdDat1_2DQDFDto.class);
            List<PbcReportProdDatDto> pbcReportProdDatDtos = new ArrayList<>();
            for (ExcelPbcReportProdDat1_2DQMJDto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportProdDatDto pbcReportProdDatDto = new PbcReportProdDatDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,pbcReportProdDatDto);
                pbcReportProdDatDto.setDetailuuid(UUID.randomUUID().toString());
                pbcReportProdDatDto.setReportuuid(paramDto.getReportUUID());
                pbcReportProdDatDto.setReporttype(PBC_REPORT);
                pbcReportProdDatDto.setDatZipCode(paramDto.getDatZipCode());
                pbcReportProdDatDtos.add(pbcReportProdDatDto);
            }
            for (ExcelPbcReportProdDat1_2DQDFDto excelPbcReportProdDatDto : dataFromExcel1) {
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
        return SHEET_1_2;
    }
}
