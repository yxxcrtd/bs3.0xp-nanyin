/*
 * 文件名：PaRskCustomerRatingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.mstmarketinstitutions.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.valuation.basicdata.mstmarketinstitutions.dto.MstMarketInstitutionsDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.basicdata.mstmarketinstitutions.dto.PaRskCustomerRatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 
 * 客户评级
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see PaRskCustomerRatingService
 * @since 1.0
 */
@Service
public class PaRskCustomerRatingService extends CommonExportService<PaRskCustomerRatingDto>{
    
    /**
     * 市场机构mapper注入
     */
    @Autowired
    private MstMarketInstitutionsService mstMarketInstitutionsService;
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
    public Page<MstBondCreditRating> selectMstBondCreditPage(Map<String, Object> pageParam)
        throws Exception {
        String workflowId = (String)pageParam.get("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            MstMarketInstitutionsDto mstMarketInstitutionsDto = mstMarketInstitutionsService.getBussinessEntityByWorkflowId(
                workflowId);
            Page<MstBondCreditRating> page = new Page<>();
            page.setRoot(mstMarketInstitutionsDto.getMstBondCreditRating());
            page.setRecords(mstMarketInstitutionsDto.getMstBondCreditRating().size());
            return page;
        }
        else {
            return restTemplate.postForPage(
                ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/MstMarketInstitutions/selectMstBondCreditPage",
                pageParam, MstBondCreditRating.class);
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
    public List<PaRskCustomerRatingDto> selectPaRskCustomerRatingByPrimaryKey(String customerId){
        return restTemplate.postForList(
            ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/MstMarketInstitutions/selectPaRskCustomerRatingByPrimaryKey",
            customerId, PaRskCustomerRatingDto.class);
    }
}
