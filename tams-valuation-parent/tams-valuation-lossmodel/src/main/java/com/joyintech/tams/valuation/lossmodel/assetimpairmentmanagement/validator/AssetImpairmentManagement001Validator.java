/*
 * 文件名：AssetImpairmentManagement001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-28
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.validator;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement001Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.service.AssetImpairmentManagement002Service;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-28
 * @see AssetImpairmentManagement001Validator
 * @since 1.0
 */
@Component
public class AssetImpairmentManagement001Validator implements BeanValidator{
    @Autowired
    private AssetImpairmentManagement002Service assetImpairmentManagement002Service;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        AssetImpairmentManagement001Dto dto = (AssetImpairmentManagement001Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            AssetImpairmentManagement002Dto entity = assetImpairmentManagement002Service.findByPrimaryKey(dto.getSeqNo());
            if(entity == null){
                resultList.add(new ValidMessage("该条数据不存在！！"));
                return resultList;
            }
            if("edit".equals(dto.getOperateType())){
                Date currentDate = DateUtil.platDateFromCache();
                if(entity.getDecDate().compareTo(currentDate) < 0){
                    resultList.add(new ValidMessage("修改时,仅能修改大于等于平台日的数据!"));
                }
            }else if("delete".equals(dto.getOperateType())){
                // 判断当前流程是否存在：数据主键
                WflValidInputParameter validParam1 = new WflValidInputParameter();
                ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
                validParam1.setWorkflowId(dto.getWorkflowId());
                validParam1.setPageId(processMark1.getPageId());

//                validParam1.setBusinessDataPk2(entity.getBussNo());
                validParam1.setBusinessDataPk3(entity.getDecDateFmt());
                if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                    // 流程存在情况
                    resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", entity.getBussNo() + ":" + entity.getDecDateFmt())));
                    return resultList;
                }
                Date currentDate = DateUtil.platDateFromCache();
                if(entity.getDecDate().compareTo(currentDate) < 0){
                    resultList.add(new ValidMessage("删除时,仅能删除大于等于平台日的数据!"));
                }
            }
        }
        return resultList;
    }

}