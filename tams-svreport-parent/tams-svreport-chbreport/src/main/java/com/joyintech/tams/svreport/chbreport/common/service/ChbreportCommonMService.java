package com.joyintech.tams.svreport.chbreport.common.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.PrdBalance;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportFileDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import com.joyintech.tools.util.ExcelTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see ChbreportCommonMService
 * @since 1.0
 */
@Service
public class ChbreportCommonMService extends CommonExportService<FinAssetLiabilityStatementQuery001Dto> {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(ChbreportCommonMService.class);

    /**
     * 查询产品代码和产品名称
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPrdBaseinfo(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbreportCommon/getPrdBaseinfo", param,
                SelectOptionDto.class);
    }
}
