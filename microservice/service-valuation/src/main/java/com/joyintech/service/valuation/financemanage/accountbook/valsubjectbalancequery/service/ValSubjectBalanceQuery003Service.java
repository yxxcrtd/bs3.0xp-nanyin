/*
 * 文件名：ValVoucherQuery003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */


package com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery002Dto;

import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dao.ValSubjectWaterQuery001Mapper;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;

/**
 * 记账凭证详情查看service
 * 
 * @author 
 * @version 2.0 2018年5月3日
 * @see ValSubjectBalanceQuery003Service
 * @since 2.0
 */
@Service(value="serviceValSubjectBalanceQuery003Service")
@Transactional(rollbackFor = Exception.class)
public class ValSubjectBalanceQuery003Service extends CommonExportService<ValVoucherQuery002Dto> {
	
    /**
     * ValSubjectWaterQuery001Mapper
     */
    @Autowired
	private ValSubjectWaterQuery001Mapper mapper;
	
    /**
     * 
     * 科目流水查询
     * @param vouchNum 凭证编号
     * @param bookDate 入账日期
     * @return 科目流水List
     * @see
     * @since 1.0
     */
    public List<ValSubjectWaterQuery001Dto> getSubjectWater(ValSubjectWaterQuery001SearchDto dto) throws Exception {
        return mapper.getSubjectWater(dto);
    }
}
