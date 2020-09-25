/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.OfficeMergeUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.PoiReportModelManage001Dto;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see PoiReportModelManage001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManage001Service extends CommonExportService<PoiReportModelManage001Dto> {
    
    
//    @Autowired
//    private RepInfoMapper repInfoMapper;
//    @Autowired
//    private ReportModelManage001Mapper reportModelManage001Mapper;
    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(PoiReportModelManage001Service.class);

 
        
    	public Page<DisPoiTab> findByPage(Map<String, Object> pageParam) throws Exception {
//    		return super.queryForPage(() -> reportModelManage001Mapper.searchReports(pageParam));
    		
    		return restTemplate.postForPage(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManage001/findPage",
                    pageParam, DisPoiTab.class);
    		
    		
    	}
    	
    	public int delete(String id) {
    		return restTemplate.getForObject(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManage001/delete?id="+id,Integer.class);

    	}
    	
}
