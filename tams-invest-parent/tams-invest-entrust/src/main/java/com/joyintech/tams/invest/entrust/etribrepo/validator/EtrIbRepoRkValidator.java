/*
 * 文件名：CePreOccupancyManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etribrepo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRkDto;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepoRk002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 资产系列校验
 * @author dongzhiling
 * @version 1.0 2019年1月10日
 * @see EtrIbRepoRkValidator
 * @since 1.0
 */
@Component
public class EtrIbRepoRkValidator implements BeanValidator{
    
	@Autowired
    private EtrIbRepoRk002Service etrIbRepoRk002Service;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	EtrIbRepoRkDto dto = (EtrIbRepoRkDto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            // 判断是否选择质押库
            if(dto.getPledgeStockId()==null||"".equals(dto.getPledgeStockId())) {
            	validList.add(new ValidMessage(this.getMessage("请选择质押券库")));
            }
            
        }
        return validList;
    }

}
