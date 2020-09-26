/*
 * 文件名：ValVoucherQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valbokbalance.service;


import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.batch.common.accounting.entity.BalanceDetailInfoEntity;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月31日
 * @see ValBokBalanceService
 * @since 1.0
 */
@Service
public class ValBokBalanceService extends CommonExportService<BalanceDetailInfoEntity> {
	
    /**
     * 格式化创建SelectOptionDto对象
     * <功能详细描述>
     * @param selectId 下拉Id值
     * @param text	下拉文本值
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
