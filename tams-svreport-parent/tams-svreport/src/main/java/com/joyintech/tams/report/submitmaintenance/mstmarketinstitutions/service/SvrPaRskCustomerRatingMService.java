/*
 * 文件名：PaRskCustomerRatingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrMstMarketInstitutionsDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrPaRskCustomerRatingDto;
/**
 * 
 * 客户评级
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see SvrPaRskCustomerRatingMService
 * @since 1.0
 */
@Service
public class SvrPaRskCustomerRatingMService extends CommonExportService<SvrPaRskCustomerRatingDto>{
    
    /**
     * 市场机构mapper注入
     */
    @Autowired
    private SvrMstMarketInstitutionsService mstMarketInstitutionsService;
    /**
     * 
     * 编辑查询页面客户评级表查询分页
     * <功能详细描述>
     * @param pageParam pageParam
     * @return Page<PaRskCustomerRating>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<SvrPaRskCustomerRatingDto> selectPaRskCustomerPage(Map<String, Object> pageParam)
        throws Exception {
//        String workflowId = (String)pageParam.get("workflowId");
//        if (StringUtil.isNotEmpty(workflowId)) {
//            MstMarketInstitutionsDto mstMarketInstitutionsDto = mstMarketInstitutionsService.getBussinessEntityByWorkflowId(
//                workflowId);
//            Page<PaRskCustomerRatingDto> page = new Page<>();
//            page.setRoot(mstMarketInstitutionsDto.getPaRskCustomer());
//            page.setRecords(mstMarketInstitutionsDto.getPaRskCustomer().size());
//            return page;
//        }
//        else {
            return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/selectPaRskCustomerPage",
                pageParam, SvrPaRskCustomerRatingDto.class);
//        }
    }
    
    /**
     * 
     * 通过客户代码查询客户评级表信息 
     * <功能详细描述>
     * @param customerId String
     * @return List<PaRskCustomerRating>
     * @see
     * @since 1.0
     */
    public List<SvrPaRskCustomerRatingDto> selectPaRskCustomerRatingByPrimaryKey(String customerId){
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/selectPaRskCustomerRatingByPrimaryKey",
            customerId, SvrPaRskCustomerRatingDto.class);
    }
}
