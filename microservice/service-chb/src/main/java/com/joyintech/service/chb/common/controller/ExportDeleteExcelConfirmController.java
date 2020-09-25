/*
 * 文件名：EquityInfoRegist002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：导出删除模板共通页面
 * 修改人：Administrator
 * 修改时间：2019年5月30日
 * 修改内容：新建
 * 系统名称：中债报送监管系统
 */
 
package com.joyintech.service.chb.common.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.svreport.chb.common.dto.ChbDeleteExcelExportDto;
import com.joyintech.tams.svreport.chb.common.service.ExportDeleteExcelConfirm001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 导出删除模板共通逻辑
 * @author 魏玉强
 * @version 1.7.0 2019年5月30日
 * @see ExportDeleteExcelConfirmController
 * @since 1.0
 */
@RestController("ExportDeleteExcelConfirmCtr_dtx")
@RequestMapping("/ExportDeleteExcelConfirm")
public class ExportDeleteExcelConfirmController {
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 删除模板导出service
     */
    @Autowired
    private ExportDeleteExcelConfirm001Service exportDeleteExcelConfirm001Service;
    

    /**
     * 功能:导出删除excel
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @return 
     * ResponseEntity<byte[]>
     */
    @PostMapping("/searchByCondition")
    public ChbDeleteExcelExportDto searchByCondition(@RequestBody Map<String,Object> selectParam) {
    	String pageId = (String) selectParam.get("pageId");
    	String deleteReason = (String) selectParam.get("deleteReason");
        return exportDeleteExcelConfirm001Service.searchByCondition(pageId,selectParam,deleteReason);
    }
    
    /**
     * 功能:导出删除excel
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月31日
     * @return 
     * ResponseEntity<byte[]>
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/dealDeleteDataSendStatus")
    public void dealDeleteDataSendStatus(@RequestBody Map<String, Object> param) {
    	ChbDeleteExcelExportDto dataInfo = BeanUtil.mapToObject((Map<String,Object>)param.get("param_dto"), ChbDeleteExcelExportDto.class) ;
    	exportDeleteExcelConfirm001Service.dealDeleteDataSendStatus(dataInfo,param);
    }

}
