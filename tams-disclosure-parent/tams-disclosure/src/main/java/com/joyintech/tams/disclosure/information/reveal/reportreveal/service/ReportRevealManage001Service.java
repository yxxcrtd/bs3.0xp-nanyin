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
import java.util.stream.Collectors;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.information.reveal.reportreveal.dao.ReportRevealManageEX001Mapper;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReportRevealManage001Service extends CommonExportService<ReportReview001Dto> implements BaseProcessService<ReportReview001Dto> {

    /**
     * 报表下载临时路径
     */
    public static final String KEY_REPORT_DOWNLOAD_PATH = "REPORT_DOWNLOAD_PATH";

    @Autowired
    private ReportRevealManageEX001Mapper reportRevealManageEX001Mapper;

    /**
     * 日志
     */
    @SuppressWarnings("unused")
    private static Logger log = LoggerFactory.getLogger(ReportRevealManage001Service.class);


    @Autowired
    private ReportRevealManage002Service reportRevealManage002Service;
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ReportReview001Dto> findPage(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/findPage",
            params, ReportReview001Dto.class);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportReview001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/queryPageList", params,
                ReportReview001Dto.class);
    }

    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     */
    @Override
    public void doComplete(ReportReview001Dto entity, OperationType type) {
//        entity.setRevealUser(SecurityUtil.getLoginUser().getUserName());
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
        restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ReportRevealManage001/updateRevealStatus", dto,
            Boolean.class);
        String data=dto.getData();
        List<ReportConfirmManage001Dto> listdto = JsonUtils.json2List(data, ReportConfirmManage001Dto.class);
        List<String> prodIds = listdto.stream().distinct().map(ReportConfirmManage001Dto::getProdid).collect(Collectors.toList());
        return  reportRevealManage002Service.genFile(prodIds);
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
    public void updateConfirmStatusWithGoBack(ReportReview001Dto dto) throws IOException {
        //需要判断上一步是托管还是普通复核，然后退到对应节点
        String data = dto.getData();
        List<ReportReview001Dto> rDtoList = JsonUtils.json2List(data, ReportReview001Dto.class);
        for (ReportReview001Dto tmp : rDtoList){
            if(StringUtil.isNotEmpty(tmp.getReviewTgGroup())){
                //上一步是托管
                //复核表数据更新
                tmp.setReviewTgStatus(DictCodeListExt5.托管复核状态_被披露退回.getValue());
                reportRevealManageEX001Mapper.backToTgReview(tmp);
            }else{
                //上一步是复核
                tmp.setReviewStatus(DictCodeListExt5.复核状态_被披露退回.getValue());
                reportRevealManageEX001Mapper.backToReview(tmp);
            }
        }
    }
}
