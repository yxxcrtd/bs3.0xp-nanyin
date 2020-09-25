/*
 * 文件名：ValProductInfoQuery005Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.productinfoquery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.productinfoquery.dao.ValProductInfoQuery001Mapper;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;

@Service(value="serviceValProductInfoQuery005Service")
public class ValProductInfoQuery005Service extends BaseService<ValProductInfoFeeDto>{

	/**
     * ValProductInfoQuery001Mapper
     */
    @Autowired
    private ValProductInfoQuery001Mapper mapper;
	
	/**
     * 查看费用调节 - 分页
     * @param pageParam
     * @return
	 * @throws Exception 
     */
	public Page<ValProductInfoFeeDto> findFeeAdjust(int page,int length,Map<String, Object> pageParam) throws Exception {
		return super.queryForPageForSelectReturn(page,length,()->mapper.selectFeeAdjust(pageParam));
	}
	
}
