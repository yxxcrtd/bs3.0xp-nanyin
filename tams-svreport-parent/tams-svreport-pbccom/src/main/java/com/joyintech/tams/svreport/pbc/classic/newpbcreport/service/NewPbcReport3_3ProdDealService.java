package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportDebtTermDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportDebtTermDatDetail;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportDebtTermDat3_3AssetDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ExcelPbcReportDebtTermDat3_3DebtDto;
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
public class NewPbcReport3_3ProdDealService extends AbstractDealNewPbcMonthReport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PBC_REPORT = PbcReportType.PBC_REPORT_3_3;

    @Autowired
    private PbcReportDebtTermDatDetailMapper pbcReportDebtTermDatDetailMapper;

    @Autowired
    private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

    @Override
    public List<ExcelValidDto> dealExcelData(ReportDealParamDto paramDto) {
        List<ExcelValidDto> excelValidDtos = new ArrayList<>();
        try {
            List<ExcelPbcReportDebtTermDat3_3AssetDto> dataFromExcel = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportDebtTermDat3_3AssetDto.class);
            List<ExcelPbcReportDebtTermDat3_3DebtDto> dataFromExcel1 = MonthReportUtil
                    .createDataFromExcel(paramDto.getHssfSheet(), PBC_REPORT, ExcelPbcReportDebtTermDat3_3DebtDto.class);
            List<PbcReportDebtTermDatDetail> termDatDetails = new ArrayList<>();
            for (ExcelPbcReportDebtTermDat3_3AssetDto excelPbcReportProdDatDto : dataFromExcel) {
                PbcReportDebtTermDatDetail termDatDetail = new PbcReportDebtTermDatDetail();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,termDatDetail);
                termDatDetail.setDetailuuid(UUID.randomUUID().toString());
                termDatDetail.setReportuuid(paramDto.getReportUUID());
                termDatDetail.setReporttype(PBC_REPORT);
                termDatDetails.add(termDatDetail);
            }
            for (ExcelPbcReportDebtTermDat3_3DebtDto excelPbcReportProdDatDto : dataFromExcel1) {
                PbcReportDebtTermDatDetail termDatDetail = new PbcReportDebtTermDatDetail();
                BeanUtils.copyProperties(excelPbcReportProdDatDto,termDatDetail);
                termDatDetail.setDetailuuid(UUID.randomUUID().toString());
                termDatDetail.setReportuuid(paramDto.getReportUUID());
                termDatDetail.setReporttype(PBC_REPORT);
                termDatDetails.add(termDatDetail);
            }
            PbcSendZipDatFile sendZipDatFile = newPbcSendZipDatFile(paramDto,termDatDetails.size());
            sendZipDatFile.setDatZipDatType(PBC_REPORT);
            deleteData(paramDto,PBC_REPORT);
            saveZipDatFile(termDatDetails,sendZipDatFile);
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
        return SHEET_3_3;
    }

    public void deleteData(ReportDealParamDto paramDto,String reportType){
        if(paramDto.isHasRecord()){
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("reportType",reportType);
            paramsMap.put("reportUUID",paramDto.getReportUUID());
            pbcReportDebtTermDatDetailMapper.deleteByUUIDAndType(paramsMap);
            Map<String,Object> pMap = new HashMap<>();
            pMap.put("datZipDatType",reportType);
            pMap.put("statusVdate",paramDto.getStatusVdate());
            pMap.put("reportUUID",paramDto.getReportUUID());
            pbcSendZipDatFileMapper.deleteByUUIDAndType(pMap);
        }
    }
    public void saveZipDatFile(List<PbcReportDebtTermDatDetail> pbcReportProdDatDtos, PbcSendZipDatFile pbcSendZipDatFile){
        if(CollectionUtil.isNotEmpty(pbcReportProdDatDtos)){
            for (PbcReportDebtTermDatDetail termDatDetail : pbcReportProdDatDtos) {
                pbcReportDebtTermDatDetailMapper.insert(termDatDetail);
            }
        }
        if(pbcSendZipDatFile != null){
            pbcSendZipDatFileMapper.insert(pbcSendZipDatFile);
        }
    }
}
