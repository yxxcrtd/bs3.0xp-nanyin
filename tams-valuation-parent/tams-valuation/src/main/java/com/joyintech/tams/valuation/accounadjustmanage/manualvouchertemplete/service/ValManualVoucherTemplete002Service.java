/*
 * 文件名：ValManualVoucher002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */


package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dao.ValManualVoucherTemplete002Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 手工凭证模板详情查看service
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete002Service
 * @since 2.0
 */
@Service
public class ValManualVoucherTemplete002Service extends CommonExportService<ValManualVoucherTemplete002Dto> {
	
    /**
     * ValManualVoucherTemplete002Mapper
     */
    @Autowired
	private ValManualVoucherTemplete002Mapper mapper;
	
    /**
     * 获取详情中列表信息
     * @param tradeId 凭证编号
     * @return 详情中列表信息
     * @throws Exception 异常
     */
    public List<ValManualVoucherTemplete002Dto> searchList(String tradeId){
        //只查询手工凭证的
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete002/searchList", tradeId, ValManualVoucherTemplete002Dto.class);
    }
}
