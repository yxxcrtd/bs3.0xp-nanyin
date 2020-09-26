/*
 * 文件名：ValBondassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.loanmark.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.assets.loanmark.dao.ValLoanMarkquery002Mapper;
import com.joyintech.tams.valuation.assets.loanmark.dto.ValLoanMarkquery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 拆借标的查询-双击查看
 * 
 * @author huwei
 * @version 1.0 2018年10月23日
 * @see ValLoanMarkquery002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValLoanMarkquery002Service extends CommonExportService<ValLoanMarkquery001Dto> {

    /**
     * ValLoanMarkquery001Mapper
     */
    @Autowired
    private ValLoanMarkquery002Mapper mapper;

    /**
     * 拆借标的查询-双击查看
     * 
     * @param finprodId finprodId
     * @param finprodType finprodType
     * @return ValLoanMarkquery001Dto
     * @see
     * @since 1.0
     */
    public ValLoanMarkquery001Dto queryFundAccountInfo(String finprodId, String finprodType) {
    	Map<String,Object> params = new HashMap<>();
    	params.put("finprodId", finprodId);
    	params.put("finprodType", finprodType);
//        return mapper.queryLoanMarkInfo(finprodId, finprodType);
    	 return restTemplate.postForObject(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/ValLoanMarkquery002/queryFundAccountInfo",
                 params, ValLoanMarkquery001Dto.class);
    }
}
