package com.joyintech.tams.svreport.pbc.assetdebtdat.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAreaDatDetailDto;
import com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.PbcReportProdDatTotalService;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcDatTemplateName;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tools.util.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 功能:资管产品统计模板明细
 * @since 1.6.0
 * @version 1.6.0
 * @作者：meifaxin
 * @创建时间：2021年8月2日
 * @修改记录：
 */
@Service
@TemplateName(PbcDatTemplateName.PBC_DAT_REPORT_PIE)
public class PbcReportPrdDatService extends PbcReportProdDatTotalService {


    /**
     * @Description 生成bat
     * @param param
     * @param data
     * @return void
     * @version 1.7 2021年8月6日
     */
    public void generateDatFile(List<PbcSendZipDatFile> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //筛选出相同zipCode
        List<String> zipCodeList=new ArrayList<>();
        list.stream().forEach( datFile -> {
            if (!zipCodeList.contains(datFile.getDatZipCode())) {
                zipCodeList.add(datFile.getDatZipCode());
            }
        });

    }

}
