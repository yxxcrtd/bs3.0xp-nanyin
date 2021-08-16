package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.*;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.util.MonthReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 表1-1资管产品募集余额导入
 */
@Service
public class NewPbcReport1_3ProdDealService extends AbstractNewPbcMonthProdReport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_1_3;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportProdDat1_3QMCXDto> dataFromExcel = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportProdDat1_3QMCXDto.class);
            List<ExcelPbcReportProdDat1_3DQXFDto> dataFromExcel1 = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportProdDat1_3DQXFDto.class);
            List<ExcelPbcReportProdDat1_3DQZZDto> dataFromExcel2 = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportProdDat1_3DQZZDto.class);
            List<PbcReportProdDatDto> pbcReportProdDatDtos = new ArrayList<>();
            for (ExcelPbcReportProdDat1_3QMCXDto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportProdDatDto pbcReportProdDatDto = new PbcReportProdDatDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,pbcReportProdDatDto);
                pbcReportProdDatDto.setDetailuuid(UUID.randomUUID().toString());
                pbcReportProdDatDto.setReportuuid(paramDto.getReportUUID());
                pbcReportProdDatDto.setReporttype(PBC_REPORT);
                pbcReportProdDatDto.setDatZipCode(paramDto.getDatZipCode());
                pbcReportProdDatDtos.add(pbcReportProdDatDto);
            }
            for (ExcelPbcReportProdDat1_3DQXFDto excelPbcReportProdDatDto : dataFromExcel1) {
                PbcReportProdDatDto pbcReportProdDatDto = new PbcReportProdDatDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,pbcReportProdDatDto);
                pbcReportProdDatDto.setDetailuuid(UUID.randomUUID().toString());
                pbcReportProdDatDto.setReportuuid(paramDto.getReportUUID());
                pbcReportProdDatDto.setReporttype(PBC_REPORT);
                pbcReportProdDatDto.setDatZipCode(paramDto.getDatZipCode());
                pbcReportProdDatDtos.add(pbcReportProdDatDto);
            }
            for (ExcelPbcReportProdDat1_3DQZZDto excelPbcReportProdDatDto : dataFromExcel2) {
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
            String message = e.getMessage();
            if(StrUtil.isNotEmpty(message)){
                dto.setErrorInfo(message);
            }else{
                dto.setErrorInfo("导入"+sheetName()+"失败");
            }
            excelValidDtos.add(dto);
            logger.error("导入"+sheetName()+"异常",e);
        }

        return excelValidDtos;
    }

    @Override
    public String sheetName() {
        return SHEET_1_3;
    }

    @Override
    public Map<String, String> excelBaseInfo() {
        Map<String, String> baseMap = new HashMap<>();
        //发行机构代码
        baseMap.put(ISSUE_PARTYID,"");
        //发行机构名称
        baseMap.put(ISSUE_PARTY_NAME,"");
        //填报机构名称
        baseMap.put(REPORT_ORG_NAME,"2,2");
        //社会信用代码
        baseMap.put(SOCIAL_CREDIT_CODE,"2,6");
        //金融机构编码
        baseMap.put(FININSTITUTION_CODE,"2,11");
        //产品品种
        baseMap.put(PROD_VARIETY,"3,1");
        //责任人
        baseMap.put(PERSON_LIABLE,"32,1");
        //联系方式
        baseMap.put(CONTACT,"32,5");
        //制表日期
        baseMap.put(REPORT_DATE,"32,11");
        return baseMap;
    }
}
