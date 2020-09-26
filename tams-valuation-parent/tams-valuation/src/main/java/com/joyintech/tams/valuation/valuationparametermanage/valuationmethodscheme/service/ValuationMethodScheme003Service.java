/*
 * 文件名：ValuationMethodScheme003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dao.ValuationMethodScheme003Mapper;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 估值方法方案 - 资产分类估值
 * @author zhangwei
 * @version 1.0 2018年8月28日
 * @see ValuationMethodScheme003Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValuationMethodScheme003Service extends CommonExportService<ValuationMethodScheme003Dto> implements BaseProcessService<ValuationMethodScheme003Dto>{

	@Autowired
	private ValuationMethodScheme003Mapper valuationMethodScheme003Mapper;
	
	@Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @DataSource(DataSourceType.OTHER)
	public void doComplete(ValuationMethodScheme003Dto entity, OperationType type) {
//		if(OperationType.NEW.equals(type)){
//			valuationMethodScheme003Mapper.insert(entity);
//		}else if(OperationType.MODIFY.equals(type)){
//		    valuationMethodScheme003Mapper.updateByPrimaryKeySelective(entity);
//        }else if(OperationType.DELETE.equals(type)) {
//            valuationMethodScheme003Mapper.deleteByPrimaryKey(entity.getSchemeDetailId());
//        }
		
		String action = StringUtil.EMPTY;
	   	 if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	        }
	        else if(OperationType.MODIFY.equals(type)){
	            action = "update";
	        }
	   	  restTemplate.postForObject(
	   	            ServiceApplication.VALUATION_SERVICE_NAME + "/ValuationMethodScheme003/" + action, entity,
	   	            Integer.class);
	}
	
	/**
	 * 通过方案详情代码查询 
	 * @param schemeId
	 * @return
	 */
	public ValuationMethodScheme003Dto findBySchemeDetailId(String schemeDetailId) {
//		return valuationMethodScheme003Mapper.selectByPrimaryKey(schemeDetailId);
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme003/findBySchemeDetailId",
                schemeDetailId, ValuationMethodScheme003Dto.class);
	}

	/**
	 * 删除，支持批量删除
	 * @param schemeDetailId
	 * @return
	 */
	public int deleteByBatch(String schemeDetailId) {
		return valuationMethodScheme003Mapper.deleteByBatch(schemeDetailId);
	}

	/**
	 * 新增
	 * @param dto
	 */
	public void insert(ValuationMethodScheme003Dto dto) {
		valuationMethodScheme003Mapper.insert(dto);
	}

	/**
	 * 修改
	 * @param dto
	 */
	public void updateByPrimaryKeySelective(ValuationMethodScheme003Dto dto) {
		valuationMethodScheme003Mapper.updateByPrimaryKeySelective(dto);
	}
	




}
