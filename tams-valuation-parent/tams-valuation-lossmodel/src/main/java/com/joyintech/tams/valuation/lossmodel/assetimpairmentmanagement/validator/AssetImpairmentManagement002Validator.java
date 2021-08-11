/*
 * 文件名：AssetImpairmentManagement002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-28
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.validator;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.tams.valuation.lossmodel.assetimpairmentmanagement.service.AssetImpairmentManagement002Service;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-28
 * @see AssetImpairmentManagement002Validator
 * @since 1.0
 */
@Component
public class AssetImpairmentManagement002Validator implements BeanValidator{

    @Autowired
    private AssetImpairmentManagement002Service assetImpairmentManagement002Service;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        AssetImpairmentManagement002Dto dto = (AssetImpairmentManagement002Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            // 判断当前流程是否存在：数据主键
            WflValidInputParameter validParam1 = new WflValidInputParameter();
            ProcessMark processMark1 = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
            validParam1.setWorkflowId(dto.getWorkflowId());
            validParam1.setPageId(processMark1.getPageId());

            validParam1.setBusinessDataPk2(dto.getBussNo());
            validParam1.setBusinessDataPk3(dto.getDecDateFmt());
            if(!"".equals(validParam1.getBusinessDataPk2()) && !"".equals(validParam1.getBusinessDataPk3())){
                if (mstWorkflowCommonService.isInWflProcess(validParam1)) {
                    // 流程存在情况
                    resultList.add(new ValidMessage(getMessage("COMMON_E_0021", "标识", dto.getBussNo() + ":" + dto.getDecDateFmt())));
                    return resultList;
                }
            }

            if(GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())){
                if(dto.getProdId() != null && dto.getBussNo() != null && dto.getDecDate() != null){
                    int count = assetImpairmentManagement002Service.findDuplicateData(dto);
                    if(count > 0){
                        resultList.add(new ValidMessage("[" + dto.getProdId() + "]产品的编号为[" + dto.getBussNo() + "]的交易在[" +DateUtil.format(dto.getDecDate(),"yyyy-MM-dd") + "]已存在减值数据，不可重复提交！"));
                    }
                }
            }
        }
        return resultList;
    }

}