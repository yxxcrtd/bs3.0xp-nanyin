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

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dao.SvrMstMarketInstitutionsMapper;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrPaRskCustomerRatingDto;
/**
 * 
 * 客户评级
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see SvrPaRskCustomerRatingService
 * @since 1.0
 */
@Service
public class SvrPaRskCustomerRatingService extends CommonExportService<SvrPaRskCustomerRatingDto>{
    
    /**
     * 市场机构mapper注入
     */
    @Autowired
    private SvrMstMarketInstitutionsMapper mstMarketInstitutionsMapper;
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
//            return simpleRestTemplate.postForPage(
//                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstMarketInstitutions/selectPaRskCustomerPage",
//                pageParam, PaRskCustomerRatingDto.class);
//        }
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        	Page<SvrPaRskCustomerRatingDto> page = queryForPage(pagenum,legth,()->mstMarketInstitutionsMapper.selectPaRskCustomerPage(pageParam));
        	return page;
        } else {
            Page<SvrPaRskCustomerRatingDto> page = queryForPage(SvrMstMarketInstitutionsMapper.class,"selectPaRskCustomerPage",pageParam);
            return page;
        }
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
//        return simpleRestTemplate.postForList(
//            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstMarketInstitutions/selectPaRskCustomerRatingByPrimaryKey",
//            customerId, PaRskCustomerRatingDto.class);
    	return null;
    }
}
