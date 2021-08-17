package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportAssetDebtDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetDebtDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportDebtTermDatDetail;
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
public class NewPbcReport2_1ProdDealService extends AbstractDealNewPbcMonthReport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_2_1;

    @Autowired
    private PbcReportAssetDebtDatDetailMapper pbcReportAssetDebtDatDetailMapper;

    @Autowired
    private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportProdDat2_1FZQYDto> dataFromExcel = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportProdDat2_1FZQYDto.class);
            List<ExcelPbcReportProdDat2_1ZCHJDto> dataFromExcel1 = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportProdDat2_1ZCHJDto.class);
            List<PbcReportAssetDebtDatDetailDto> debtDatDetailDtos = new ArrayList<>();
            for (ExcelPbcReportProdDat2_1FZQYDto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportAssetDebtDatDetailDto datDetailDto = new PbcReportAssetDebtDatDetailDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,datDetailDto);
                datDetailDto.setDetailuuid(UUID.randomUUID().toString());
                datDetailDto.setReportuuid(paramDto.getReportUUID());
                datDetailDto.setReporttype(PBC_REPORT);
                debtDatDetailDtos.add(datDetailDto);
            }
            for (ExcelPbcReportProdDat2_1ZCHJDto excelPbcReportProdDatDto : dataFromExcel1) {
                PbcReportAssetDebtDatDetailDto pbcReportProdDatDto = new PbcReportAssetDebtDatDetailDto();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,pbcReportProdDatDto);
                pbcReportProdDatDto.setDetailuuid(UUID.randomUUID().toString());
                pbcReportProdDatDto.setReportuuid(paramDto.getReportUUID());
                pbcReportProdDatDto.setReporttype(PBC_REPORT);
                debtDatDetailDtos.add(pbcReportProdDatDto);
            }
            PbcSendZipDatFile sendZipDatFile = newPbcSendZipDatFile(paramDto,debtDatDetailDtos.size());
            sendZipDatFile.setDatZipDatType(PBC_REPORT);
            deleteData(paramDto,PBC_REPORT);
            saveZipDatFile(debtDatDetailDtos,sendZipDatFile);
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
        return SHEET_2_1;
    }

    public void deleteData(ReportDealParamDto paramDto,String reportType){
        if(paramDto.isHasRecord()){
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("reportType",reportType);
            paramsMap.put("reportUUID",paramDto.getReportUUID());
            pbcReportAssetDebtDatDetailMapper.deleteByUUIDAndType(paramsMap);
            Map<String,Object> pMap = new HashMap<>();
            pMap.put("datZipDatType",reportType);
            pMap.put("statusVdate",paramDto.getStatusVdate());
            pMap.put("reportUUID",paramDto.getReportUUID());
            pbcSendZipDatFileMapper.deleteByUUIDAndType(pMap);
        }
    }
    public void saveZipDatFile(List<PbcReportAssetDebtDatDetailDto> pbcReportProdDatDtos, PbcSendZipDatFile pbcSendZipDatFile){
        if(CollectionUtil.isNotEmpty(pbcReportProdDatDtos)){
            for (PbcReportAssetDebtDatDetailDto datDetailDto : pbcReportProdDatDtos) {
                pbcReportAssetDebtDatDetailMapper.insert(datDetailDto);
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
        baseMap.put(SOCIAL_CREDIT_CODE,"2,8");
        //金融机构编码
        baseMap.put(FININSTITUTION_CODE,"2,18");
        //产品品种
        baseMap.put(PROD_VARIETY,"3,1");
        //责任人
        baseMap.put(PERSON_LIABLE,"81,1");
        //联系方式
        baseMap.put(CONTACT,"81,9");
        //制表日期
        baseMap.put(REPORT_DATE,"81,19");
        return baseMap;
    }

}
