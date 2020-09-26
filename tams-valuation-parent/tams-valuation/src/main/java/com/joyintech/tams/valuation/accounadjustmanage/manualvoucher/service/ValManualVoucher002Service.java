/*
 * 文件名：ValManualVoucher002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */


package com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dao.ValManualVoucher002Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 手工凭证详情查看service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher002Service
 * @since 2.0
 */
@Service
public class ValManualVoucher002Service extends CommonExportService<ValManualVoucher002Dto> {
	
    /**
     * ValManualVoucher002Mapper
     */
    @Autowired
	private ValManualVoucher002Mapper mapper;
	
    /**
     * 获取详情中列表信息
     * @param tradeId 凭证编号
     * @return 详情中列表信息
     * @throws Exception 异常
     */
    public List<ValManualVoucher002Dto> searchList(String tradeId){
        //只查询手工凭证的
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucher002/searchList", tradeId, ValManualVoucher002Dto.class);
//    	return mapper.searchList(tradeId);
    }
    
    /**
     * 格式化创建SelectOptionDto对象
     * <功能详细描述>
     * @param selectId 下拉Id值
     * @param text  下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text){
        SelectOptionDto dto=new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }
}
