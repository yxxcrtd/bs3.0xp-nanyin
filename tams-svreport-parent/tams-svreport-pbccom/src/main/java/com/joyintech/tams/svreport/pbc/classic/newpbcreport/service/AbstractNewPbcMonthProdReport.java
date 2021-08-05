package com.joyintech.tams.svreport.pbc.classic.newpbcreport.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto.ReportDealParamDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractNewPbcMonthProdReport extends AbstractDealNewPbcMonthReport{

    @Autowired
    private PbcReportProdDatMapper pbcReportProdDatMapper;

    @Autowired
    private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

    public void deleteData(ReportDealParamDto paramDto,String reportType){
        if(paramDto.isHasRecord()){
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("reportType",reportType);
            paramsMap.put("reportUUID",paramDto.getReportUUID());
            pbcReportProdDatMapper.deleteByUUIDAndType(paramsMap);
            Map<String,Object> pMap = new HashMap<>();
            pMap.put("datZipDatType",reportType);
            pMap.put("statusVdate",reportType);
            pMap.put("reportUUID",paramDto.getReportUUID());
            pbcSendZipDatFileMapper.deleteByUUIDAndType(pMap);
        }
    }

    public void saveZipDatFile(List<PbcReportProdDatDto> pbcReportProdDatDtos, PbcSendZipDatFile pbcSendZipDatFile){
        if(CollectionUtil.isNotEmpty(pbcReportProdDatDtos)){
            for (PbcReportProdDatDto pbcReportProdDatDto : pbcReportProdDatDtos) {
                pbcReportProdDatMapper.insert(pbcReportProdDatDto);
            }
        }
        if(pbcSendZipDatFile != null){
            pbcSendZipDatFileMapper.insert(pbcSendZipDatFile);
        }
    }


}
