/*
 * 文件名：ValuationMethodScheme002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.service;

import com.joyintech.tams.constant.DictCodeList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dao.ValuationMethodScheme002Mapper;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme002Dto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dao.ValuationMethodScheme003Mapper;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme003Dto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dao.ValuationMethodScheme004Mapper;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme004Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 估值方案
 * @author zhangwei
 * @version 1.0 2018年8月28日
 * @see ValuationMethodScheme002Service
 * @since 1.0
 */
@Service(value="serviceValuationMethodScheme002Service")
@Transactional(rollbackFor = Exception.class)
public class ValuationMethodScheme002Service extends CommonExportService<ValuationMethodScheme002Dto> implements BaseProcessService<ValuationMethodScheme002Dto>{

    @Autowired
    private ValuationMethodScheme003Mapper valuationMethodScheme003Mapper;
    
    @Autowired
    private ValuationMethodScheme004Mapper valuationMethodScheme004Mapper;
	
    @Autowired
    private ValuationMethodScheme002Mapper valuationMethodScheme001Mapper;
    
	@Autowired
	private ValuationMethodScheme002Mapper valuationMethodScheme002Mapper;
	
	/**
	 * 流程审核完成 {@inheritDoc}
	 */
	@Override
	public void doComplete(ValuationMethodScheme002Dto entity, OperationType type) {
		if(OperationType.NEW.equals(type)){
			valuationMethodScheme002Mapper.insert(entity);
            if (StringUtils.isNotEmpty(entity.getOldSchemeId())){
            	List<ValuationMethodScheme003Dto> resultList3 = valuationMethodScheme003Mapper.selectBySchemeId(entity.getOldSchemeId());
            	List<ValuationMethodScheme004Dto> resultList4 = valuationMethodScheme004Mapper.selectBySchemeId(entity.getOldSchemeId());
            	for (ValuationMethodScheme003Dto tododto:resultList3){
            		tododto.setSchemeDetailId(IDGenerator.generate());
        		    tododto.setSchemeId(entity.getSchemeId());
        		    valuationMethodScheme003Mapper.insert(tododto);
        		}
            	for (ValuationMethodScheme004Dto tododto:resultList4){
            		tododto.setSchemeDetailId(IDGenerator.generate());
        		    tododto.setSchemeId(entity.getSchemeId());
        		    tododto.setMdate("");
        		    valuationMethodScheme004Mapper.insert(tododto);
        		}
        }
		}else if(OperationType.MODIFY.equals(type)){
			// 当出现新的默认方案，将已存在默认方案设置为非默认。
			if (DictCodeList.是否_是.getValue().equals(entity.getIsdefault())) {
				this.updateIsDefault();
			}

		    valuationMethodScheme002Mapper.updateByPrimaryKeySelective(entity);
		}else if(OperationType.DELETE.equals(type)) {
		    valuationMethodScheme001Mapper.deleteByPrimaryKey(entity.getSchemeId());
		    valuationMethodScheme003Mapper.deleteBySchemeId(entity.getSchemeId());
		    valuationMethodScheme004Mapper.deleteBySchemeId(entity.getSchemeId());
		}
	}

	/**
	 * 通过 查询账套关联表 中 的税务方案;
	 * @return
	 */
	public int countBokBookSetPlan(String schId) {
		return valuationMethodScheme002Mapper.countBokBookSetPlan(schId);
	}

	/**
	 * 修改默认方案的状态
	 */
	private void updateIsDefault() {
		ValuationMethodScheme002Dto entity = new ValuationMethodScheme002Dto();
		entity.setIsdefault(DictCodeList.是否_否.getValue());
		valuationMethodScheme002Mapper.updateIsDefault(entity);
	}

	/**
	 * 通过方案代码查询 方案
	 * @param schemeId
	 * @return
	 */
	public ValuationMethodScheme002Dto findBySchemeId(String schemeId) {
		return valuationMethodScheme002Mapper.selectByPrimaryKey(schemeId);
	}

	/**
	 * 删除
	 * @param schemeId
	 * @return
	 */
	public int deleteByPrimaryKey(String schemeId) {
		return valuationMethodScheme002Mapper.deleteByPrimaryKey(schemeId);
	}

	/**
	 * 新增
	 * @param dto
	 */
	public void insert(ValuationMethodScheme002Dto dto) {
		valuationMethodScheme002Mapper.insert(dto);
	}

	/**
	 * 修改
	 * @param dto
	 */
	public void updateByPrimaryKeySelective(ValuationMethodScheme002Dto dto) {
		valuationMethodScheme002Mapper.updateByPrimaryKeySelective(dto);
	}

	

}
