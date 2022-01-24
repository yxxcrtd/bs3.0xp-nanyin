package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportAssetConfigDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetConfigDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportAssetConfigDat4_1Dto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.util.MonthReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewPbcReport4_1ProdDealService extends AbstractDealNewPbcMonthReport{

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private PbcReportAssetConfigDatMapper pbcReportAssetConfigDatMapper;

    @Autowired
    private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;


    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_4_1;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportAssetConfigDat4_1Dto> dataFromExcel = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportAssetConfigDat4_1Dto.class);
            List<PbcReportAssetConfigDatDto> assetConfigDatDtos = new ArrayList<PbcReportAssetConfigDatDto>();
            for (ExcelPbcReportAssetConfigDat4_1Dto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportAssetConfigDatDto assetConfigDatDto = new PbcReportAssetConfigDatDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,assetConfigDatDto);
                assetConfigDatDto.setDetailuuid(UUID.randomUUID().toString());
                assetConfigDatDto.setReportuuid(paramDto.getReportUUID());
                assetConfigDatDto.setReporttype(PBC_REPORT);
                assetConfigDatDtos.add(assetConfigDatDto);
            }
            PbcSendZipDatFile sendZipDatFile = newPbcSendZipDatFile(paramDto,assetConfigDatDtos.size());
            sendZipDatFile.setDatZipDatType(PBC_REPORT);
            deleteData(paramDto,PBC_REPORT);
            saveZipDatFile(assetConfigDatDtos,sendZipDatFile);
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
        return SHEET_4_1;
    }

    public void deleteData(ReportDealParamDto paramDto,String reportType){
        if(paramDto.isHasRecord()){
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("reportType",reportType);
            paramsMap.put("reportUUID",paramDto.getReportUUID());
            pbcReportAssetConfigDatMapper.deleteByUUIDAndType(paramsMap);
            Map<String,Object> pMap = new HashMap<>();
            pMap.put("datZipDatType",reportType);
            pMap.put("statusVdate",paramDto.getStatusVdate());
            pMap.put("reportUUID",paramDto.getReportUUID());
            pbcSendZipDatFileMapper.deleteByUUIDAndType(pMap);
        }
    }

    public void saveZipDatFile(List<PbcReportAssetConfigDatDto> pbcReportProdDatDtos, PbcSendZipDatFile pbcSendZipDatFile){
        if(CollectionUtil.isNotEmpty(pbcReportProdDatDtos)){
            for (PbcReportAssetConfigDatDto assetConfigDatDto : pbcReportProdDatDtos) {
                pbcReportAssetConfigDatMapper.insert(assetConfigDatDto);
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
        baseMap.put(PERSON_LIABLE,"64,1");
        //联系方式
        baseMap.put(CONTACT,"64,3");
        //制表日期
        baseMap.put(REPORT_DATE,"64,5");
        //数据日期
        baseMap.put(DATA_DATE,"1,3");
        return baseMap;
    }
}
