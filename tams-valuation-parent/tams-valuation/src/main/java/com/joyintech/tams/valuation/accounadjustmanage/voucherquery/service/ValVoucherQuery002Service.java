/*
 * 文件名：ValVoucherQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */


package com.joyintech.tams.valuation.accounadjustmanage.voucherquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dao.ValVoucherQuery002Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 记账凭证详情查看service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery002Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValVoucherQuery002Service extends CommonExportService<ValVoucherQuery002Dto> {
	
    /**
     * ValVoucherQuery002Mapper
     */
    @Autowired
	private ValVoucherQuery002Mapper mapper;
	
    /**
     * 获取详情中列表信息
     * @param bookId 凭证编号
     * @return 详情中列表信息
     * @throws Exception 异常
     */
    public List<ValVoucherQuery002Dto> searchList(String busVouchnum, String tableId,String booksetId,
                                                  String bookDate, String subjectNo){
    	return mapper.searchList(busVouchnum,tableId,booksetId,bookDate,subjectNo);
    }

    /**
     * 根据凭证编号查询业务凭证编号
     * 
     * @param vouchNum
     * @return
     */
	public String selectBusVouchNumByVouchNum(String vouchNum) {
		return mapper.selectBusVouchNumByVouchNum(vouchNum);
	}
	
	
    public List<ValVoucherQuery002Dto> searchList(Map<String , Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery002/searchList",params, ValVoucherQuery002Dto.class);
        
    }
}
