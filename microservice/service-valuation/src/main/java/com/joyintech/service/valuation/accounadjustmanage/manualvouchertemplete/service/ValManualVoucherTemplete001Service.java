/*
 * 文件名：ValManualVoucher001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dao.ValManualVoucherTemplete001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001SearchDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 手工凭证模板查询service
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete001Service
 * @since 2.0
 */
@Service(value="serviceValManualVoucherTemplete001Service")
@Transactional(rollbackFor = Exception.class)
public class ValManualVoucherTemplete001Service extends CommonExportService<ValManualVoucherTemplete001Dto> implements BaseProcessService<ValManualVoucherTemplete001Dto> {
	
    /**
     * ValManualVoucherTemplete001Mapper
     */
    @Autowired
	private ValManualVoucherTemplete001Mapper mapper;

    /**
     * 根据条件获取表格信息
     * 
     * @param dto 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValManualVoucherTemplete001Dto> findPage(int page, int length, ValManualVoucherTemplete001SearchDto dto) throws Exception {
    	return super.queryForPage(page,length,()-> mapper.queryPageList(dto));
    }

    /**
     *
     * 根据条件查询全部信息
     * @param dto 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public List<ValManualVoucherTemplete001Dto> selectAll(ValManualVoucherTemplete001SearchDto dto){
        return mapper.queryPageList(dto);
    }
    

    /**
     * 流程审核完成
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValManualVoucherTemplete001Dto valManualVoucherTemplete001Dto, OperationType operationType) {

    }
}
