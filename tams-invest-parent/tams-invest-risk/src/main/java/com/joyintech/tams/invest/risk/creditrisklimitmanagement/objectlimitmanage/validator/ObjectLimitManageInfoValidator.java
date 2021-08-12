/*
 * 文件名：CreditRightsManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.validator;

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
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Component
public class ObjectLimitManageInfoValidator implements BeanValidator{
	

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		 ObjectLimitManageInfoDto dto = (ObjectLimitManageInfoDto)value;
		if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            //判断流程中是否存在相同的数据
            WflValidInputParameter param = new WflValidInputParameter();
            if(!WorkFlowUtil.isNotFromToDoList() &&StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            	param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
            }
            param.setPageId("ObjectLimitManage002");
            if(null != mstWorkflowCommonService && 
            		mstWorkflowCommonService.isInWflProcess(param)){
            	errorList.add(
                    new ValidMessage(this.getMessage("COMMON_E_0012",dto.getPflsh())));
            }
		}
		else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT) && !StringUtil.equals(dto.getOperateType(),"edit")){
            //判断流程中是否存在相同的数据
            WflValidInputParameter param = new WflValidInputParameter();
            if(!WorkFlowUtil.isNotFromToDoList() &&StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            	param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
            }
            param.setPageId("ObjectLimitManage002");
            if(null != mstWorkflowCommonService && 
            		mstWorkflowCommonService.isInWflProcess(param)){
            	errorList.add(
                    new ValidMessage(this.getMessage("COMMON_E_0012",dto.getPflsh())));
            }
		}
		return errorList;
	}

}
