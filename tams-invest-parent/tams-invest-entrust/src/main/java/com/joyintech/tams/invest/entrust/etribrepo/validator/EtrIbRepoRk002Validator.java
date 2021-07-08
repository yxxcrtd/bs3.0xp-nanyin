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
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepoRk002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 资产系列校验
 * @author dongzhiling
 * @version 1.0 2019年1月10日
 * @see EtrIbRepoRk002Validator
 * @since 1.0
 */
@Component
public class EtrIbRepoRk002Validator implements BeanValidator{
    
	@Autowired
    private EtrIbRepoRk002Service etrIbRepoRk002Service;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	EtrIbRepoRk004Dto dto = (EtrIbRepoRk004Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            // 判断系列名称是否重复
            int count = etrIbRepoRk002Service.checkIsExistByPledgeStockName(
                StringUtil.EMPTY, dto.getPledgeStockName());
            if (count > 0) {
                validList.add(new ValidMessage(this.getMessage("质押券库名称重复")));
            } 
            // 判断是否输入系列名称
            if(dto.getPledgeStockName()==null||"".equals(dto.getPledgeStockName())) {
            	validList.add(new ValidMessage(this.getMessage("请输入质押券库名称")));
            }
            //判断流程中是否存在相同的数据
            WflValidInputParameter param = new WflValidInputParameter();
            param.setBusinessDataPk2(dto.getPledgeStockName());
            param.setPageId("EtrIbRepoRk002");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(param)) {
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0012",dto.getPledgeStockName())));
            }
            
        }
        //修改时验证
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            if (etrIbRepoRk002Service.checkBusinessDataInFlow(dto)) {
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0012",dto.getPledgeStockId())));
            }
            if (!StringUtil.isNotEmpty(etrIbRepoRk002Service.getBusinessInfoByPledgeStockId(dto.getPledgeStockId()))) {
                validList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
            }
            return validList;
        }
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 判断该资产系列是否存在明细
//            if (etrIbRepoRk002Service.checkIsExistDetail(dto.getPledgeStockId()) > 0) {
//                validList.add(new ValidMessage(this.getMessage("质押库存在明细数据")));
//            }
        }
        return validList;
    }

}
