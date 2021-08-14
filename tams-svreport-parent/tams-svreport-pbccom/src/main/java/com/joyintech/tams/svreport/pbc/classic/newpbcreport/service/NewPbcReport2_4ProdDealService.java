package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportAreaDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportAssetIncomeDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAreaDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetDebtDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetIncomeDatDetail;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportAreaDat2_3Dto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportAssetIncomeDat2_4Dto;
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
public class NewPbcReport2_4ProdDealService extends AbstractDealNewPbcMonthReport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_2_4;

    @Autowired
    private PbcReportAssetIncomeDatDetailMapper pbcReportAssetIncomeDatDetailMapper;

    @Autowired
    private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportAssetIncomeDat2_4Dto> dataFromExcel = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportAssetIncomeDat2_4Dto.class);
            List<PbcReportAssetIncomeDatDetail> assetIncomeDatDetails = new ArrayList<>();
            for (ExcelPbcReportAssetIncomeDat2_4Dto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportAssetIncomeDatDetail assetIncomeDatDetail = new PbcReportAssetIncomeDatDetail();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,assetIncomeDatDetail);
                assetIncomeDatDetail.setDetailuuid(UUID.randomUUID().toString());
                assetIncomeDatDetail.setReportuuid(paramDto.getReportUUID());
                assetIncomeDatDetail.setReporttype(PBC_REPORT);
                assetIncomeDatDetails.add(assetIncomeDatDetail);
            }
            PbcSendZipDatFile sendZipDatFile = newPbcSendZipDatFile(paramDto,assetIncomeDatDetails.size());
            sendZipDatFile.setDatZipDatType(PBC_REPORT);
            deleteData(paramDto,PBC_REPORT);
            saveZipDatFile(assetIncomeDatDetails,sendZipDatFile);
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
        return SHEET_2_4;
    }

    public void deleteData(ReportDealParamDto paramDto,String reportType){
        if(paramDto.isHasRecord()){
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("reportType",reportType);
            paramsMap.put("reportUUID",paramDto.getReportUUID());
            pbcReportAssetIncomeDatDetailMapper.deleteByUUIDAndType(paramsMap);
            Map<String,Object> pMap = new HashMap<>();
            pMap.put("datZipDatType",reportType);
            pMap.put("statusVdate",paramDto.getStatusVdate());
            pMap.put("reportUUID",paramDto.getReportUUID());
            pbcSendZipDatFileMapper.deleteByUUIDAndType(pMap);
        }
    }
    public void saveZipDatFile(List<PbcReportAssetIncomeDatDetail> pbcReportProdDatDtos, PbcSendZipDatFile pbcSendZipDatFile){
        if(CollectionUtil.isNotEmpty(pbcReportProdDatDtos)){
            for (PbcReportAssetIncomeDatDetail assetIncomeDatDetail : pbcReportProdDatDtos) {
                pbcReportAssetIncomeDatDetailMapper.insert(assetIncomeDatDetail);
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
        baseMap.put(REPORT_ORG_NAME,"2,2");
        //社会信用代码
        baseMap.put(SOCIAL_CREDIT_CODE,"2,5");
        //金融机构编码
        baseMap.put(FININSTITUTION_CODE,"2,9");
        //产品品种
        baseMap.put(PROD_VARIETY,"3,1");
        //责任人
        baseMap.put(PERSON_LIABLE,"14,1");
        //联系方式
        baseMap.put(CONTACT,"14,5");
        //制表日期
        baseMap.put(REPORT_DATE,"14,10");
        return baseMap;
    }
}
