/*
 * 文件名：ValSubjectBalanceAbnormalQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.financemanage.valsubjectbalanceabnormalquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.financemanage.valsubjectbalanceabnormalquery.dao.ValSubjectBalanceAbnormalQuery001Mapper;
import com.joyintech.tams.valuation.financemanage.valsubjectbalanceabnormalquery.dto.ValSubjectBalanceAbnormalQuery001Dto;

/**
 * 到期产品科目余额异常查询-service
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月28日
 * @see ValSubjectBalanceAbnormalQuery001Service
 * @since 1.0
 */
@Service
public class ValSubjectBalanceAbnormalQuery001Service extends CommonExportService<ValSubjectBalanceAbnormalQuery001Dto> {

    @Autowired
    private ValSubjectBalanceAbnormalQuery001Mapper valSubjectBalanceAbnormalQuery001Mapper;

    public Page<ValSubjectBalanceAbnormalQuery001Dto> queryPageList(Map<String, Object> params) throws Exception {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectBalanceAbnormalQuery001/findPage",
                params, ValSubjectBalanceAbnormalQuery001Dto.class);
    }

    public List<ValSubjectBalanceAbnormalQuery001Dto> getAllList(Map<String, Object> params) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectBalanceAbnormalQuery001/getAllList",
                params, ValSubjectBalanceAbnormalQuery001Dto.class);
    }
}
