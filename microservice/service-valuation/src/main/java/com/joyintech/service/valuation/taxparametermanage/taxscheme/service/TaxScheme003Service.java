/*
 * 文件名：TaxScheme003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.taxparametermanage.taxscheme.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dao.TaxScheme003Mapper;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme003Dto;
import com.joyintech.tams.common.base.dao.TaxVatScheduleMapper;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 增值税则表
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme003Service
 * @since 1.0
 */
@Service(value="serviceTaxScheme003Service")
@Transactional(rollbackFor = Exception.class)
public class TaxScheme003Service extends CommonExportService<TaxScheme003Dto> implements BaseProcessService<TaxScheme003Dto> {

	@Autowired
	private TaxScheme003Mapper taxScheme003Mapper;
	
	/**
	 * 增值税则表
	 */
	@Autowired
	private TaxVatScheduleMapper taxVatScheduleMapper;
	
	
    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(TaxScheme003Dto entity, OperationType type) {
        if (OperationType.NEW.equals(type)) {
            this.insertSelective(entity);
        }
        else if (OperationType.MODIFY.equals(type)) {
            this.updateByPrimaryKeySelective(entity);
        }
        else if (OperationType.DELETE.equals(type)) {
            this.deleteByBatch(entity.getSeqNo());
        }
    }
	
	
	/**
	 * 新建 增值税则表
	 * @param dto
	 * @return
	 */
	public int insertSelective(TaxScheme003Dto dto) {
		return taxScheme003Mapper.insertSelective(dto);
	}
	
	/**
	 * 查询 增值税则表
	 * @param seqNo
	 * @return
	 */
	public TaxScheme003Dto findByPrimaryKey(String seqNo) {
		return taxScheme003Mapper.selectByPrimaryKey(seqNo);
	}
	
	/**
	 * 修改 增值税则表
	 * @param dto
	 * @return
	 */
	public int updateByPrimaryKeySelective(TaxScheme003Dto dto) {
		return taxScheme003Mapper.updateByPrimaryKeySelective(dto);
	}

	/**
	 * 删除、批量删除 增值税则表
	 * @param seqNo
	 * @return
	 */
	public int deleteByBatch(String seqNo) {
		int row = 0;
		for(String item: seqNo.split(",")) {
			row += taxVatScheduleMapper.deleteByPrimaryKey(item);
		}
		return row;
	}
	
	/**
     * 增值税则表check(去除本金收益特征不能为空的校验后添加本方法)
     * @return
     * @throws Exception
     */
	public List<TaxScheme003Dto> checkVatTax(Map<String, Object> parameters) throws Exception {
		return taxScheme003Mapper.checkVatTax(parameters);
	}



	
	
}
