/*
 * 文件名：TaxScheme004Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxscheme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dao.TaxScheme004Mapper;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme004Dto;
import com.joyintech.tams.common.base.dao.TaxAdtScheduleMapper;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 附加税则表
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme004Service
 * @since 1.0
 */
@Service(value="serviceTaxScheme004Service")
@Transactional(rollbackFor = Exception.class)
public class TaxScheme004Service extends CommonExportService<TaxScheme004Dto> implements BaseProcessService<TaxScheme004Dto> {

	@Autowired
	private TaxScheme004Mapper taxScheme004Mapper;

	/**
	 * 附加税则表
	 */
	@Autowired
	private TaxAdtScheduleMapper taxAdtScheduleMapper;

	/**
	 * 流程审核完成时执行 {@inheritDoc}
	 */
	@Override
	public void doComplete(TaxScheme004Dto entity, OperationType type) {
		if (OperationType.NEW.equals(type)) {
			this.insertSelective(entity);
		}
		else if (OperationType.MODIFY.equals(type)) {
			this.updateByPrimaryKeySelective(entity);
		}
		else if (OperationType.DELETE.equals(type)) {
			this.deleteByBatch(entity.getTaxId());
		}
	}


	/**
	 * 新建 附加税则表
	 * @param dto
	 * @return
	 */
	public int insertSelective(TaxScheme004Dto dto) {
		return taxScheme004Mapper.insertSelective(dto);
	}

	/**
	 * 查询 附加税则表
	 * @param seqNo
	 * @return
	 */
	public TaxScheme004Dto findByPrimaryKey(String seqNo) {
		return taxScheme004Mapper.selectByPrimaryKey(seqNo);
	}

	/**
	 * 修改 附加税则表
	 * @param dto
	 * @return
	 */
	public int updateByPrimaryKeySelective(TaxScheme004Dto dto) {
		return taxScheme004Mapper.updateByPrimaryKeySelective(dto);
	}

	/**
	 * 删除、批量删除 附加税则表
	 * @param seqNo
	 * @return
	 */
	public int deleteByBatch(String taxId) {
		int row = 0;
		for(String item: taxId.split(",")) {
			row += taxAdtScheduleMapper.deleteByPrimaryKey(item);
		}
		return row;
	}

	public String getPrimaryKey() {
		return taxScheme004Mapper.getPrimaryKey();
	}



}
