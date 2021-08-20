package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportAreaDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportCompanyDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAreaDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportCompanyDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportAreaDat2_3Dto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportCompanyDat2_2Dto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportProdDat1_1Dto;
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
 * 表1-1资管产品募集余额导入
 */
@Service
public class NewPbcReport2_3ProdDealService extends AbstractDealNewPbcMonthReport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_2_3;

    @Autowired
    private PbcReportAreaDatDetailMapper pbcReportAreaDatDetailMapper;

    @Autowired
    private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportAreaDat2_3Dto> dataFromExcel = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportAreaDat2_3Dto.class);
            List<PbcReportAreaDatDetailDto> areaDatDetailDtos = new ArrayList<>();
            for (ExcelPbcReportAreaDat2_3Dto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportAreaDatDetailDto areaDatDetailDto = new PbcReportAreaDatDetailDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,areaDatDetailDto);
                areaDatDetailDto.setDetailuuid(UUID.randomUUID().toString());
                areaDatDetailDto.setReportuuid(paramDto.getReportUUID());
                areaDatDetailDto.setReporttype(PBC_REPORT);
                areaDatDetailDtos.add(areaDatDetailDto);
            }
            PbcSendZipDatFile sendZipDatFile = newPbcSendZipDatFile(paramDto,areaDatDetailDtos.size());
            sendZipDatFile.setDatZipDatType(PBC_REPORT);
            deleteData(paramDto,PBC_REPORT);
            saveZipDatFile(areaDatDetailDtos,sendZipDatFile);
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
        return SHEET_2_3;
    }

    public void deleteData(ReportDealParamDto paramDto,String reportType){
        if(paramDto.isHasRecord()){
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("reportType",reportType);
            paramsMap.put("reportUUID",paramDto.getReportUUID());
            pbcReportAreaDatDetailMapper.deleteByUUIDAndType(paramsMap);
            Map<String,Object> pMap = new HashMap<>();
            pMap.put("datZipDatType",reportType);
            pMap.put("statusVdate",paramDto.getStatusVdate());
            pMap.put("reportUUID",paramDto.getReportUUID());
            pbcSendZipDatFileMapper.deleteByUUIDAndType(pMap);
        }
    }
    public void saveZipDatFile(List<PbcReportAreaDatDetailDto> pbcReportProdDatDtos, PbcSendZipDatFile pbcSendZipDatFile){
        if(CollectionUtil.isNotEmpty(pbcReportProdDatDtos)){
            for (PbcReportAreaDatDetailDto areaDatDetailDto : pbcReportProdDatDtos) {
                pbcReportAreaDatDetailMapper.insert(areaDatDetailDto);
            }
        }
        if(pbcSendZipDatFile != null){
            pbcSendZipDatFileMapper.insert(pbcSendZipDatFile);
        }
    }

    @Override
    public Map<String, String> excelBaseInfo() {
        Map<String, String> baseMap = new HashMap<>();
        //发行机构代码
        baseMap.put(ISSUE_PARTYID,"");
        //发行机构名称
        baseMap.put(ISSUE_PARTY_NAME,"");
        //填报机构名称
        baseMap.put(REPORT_ORG_NAME,"2,1");
        //社会信用代码
        baseMap.put(SOCIAL_CREDIT_CODE,"2,3");
        //金融机构编码
        baseMap.put(FININSTITUTION_CODE,"2,5");
        //产品品种
        baseMap.put(PROD_VARIETY,"3,1");
        //责任人
        baseMap.put(PERSON_LIABLE,"44,1");
        //联系方式
        baseMap.put(CONTACT,"44,3");
        //制表日期
        baseMap.put(REPORT_DATE,"44,5");
        return baseMap;
    }
}
