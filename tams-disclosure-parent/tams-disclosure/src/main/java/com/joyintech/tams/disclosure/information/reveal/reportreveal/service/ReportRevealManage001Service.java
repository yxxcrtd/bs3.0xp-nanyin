/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.reveal.reportreveal.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageParamDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 金融产品交易查询Service
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
public class ReportRevealManage001Service extends CommonExportService<ReportConfirmManage001Dto> implements BaseProcessService<ReportConfirmManage001Dto> {

    /**
     * 报表下载临时路径
     */
    public static final String KEY_REPORT_DOWNLOAD_PATH = "REPORT_DOWNLOAD_PATH";

    /**
     * 日志
     */
    @SuppressWarnings("unused")
    private static Logger log = LoggerFactory.getLogger(ReportRevealManage001Service.class);

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ReportConfirmManage001Dto> findPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/findPage",
            params, ReportConfirmManage001Dto.class);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportConfirmManage001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/queryPageList", params,
            ReportConfirmManage001Dto.class);
    }

    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     */
    @Override
    public void doComplete(ReportConfirmManage001Dto entity, OperationType type) {
        entity.setRevealUser(SecurityUtil.getLoginUser().getUserName());
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/doComplete/" + type.name(), entity,
            AjaxResult.class);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     * @throws IOException
     * @see
     * @since 1.0
     */
    public boolean updateRevealStatus(ReportConfirmManageParamDto dto)
        throws IOException {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/updateRevealStatus", dto,
            Boolean.class);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     * @throws IOException
     * @see
     * @since 1.0
     */
    public boolean updateConfirmStatusWithGoBack(ReportConfirmManageParamDto dto)
        throws IOException {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/updateConfirmStatusWithGoBack", dto,
            Boolean.class);
    }
}
