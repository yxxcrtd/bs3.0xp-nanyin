/*
 * 文件名：SceneBatchValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2019年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.valid;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 情景管理验证类（删除、启用停用）
 * @author Jiaoxujin
 * @version 1.0 2019年8月26日
 * @see
 * @since 1.0
 */
@Transactional
@Component
public class FlowRiskSceneManageValidator implements BeanValidator {


    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FlowRiskSceneManage001Dto dto = (FlowRiskSceneManage001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (CollectionUtils.isEmpty(dto.getSelectedItem())) {
                if (StringUtil.equals(dto.getOperateType(), "delete")) {
                    errorList.add(new ValidMessage("FlowRiskSceneManage001_btn_delete", getMessage("FlowRiskSceneManage001.atleastOneScene")));
                }
                else {
                    if (StringUtil.equals(dto.getElementId(), "FlowRiskSceneManage001_btn_effective")) {
                        errorList.add(new ValidMessage("FlowRiskSceneManage001_btn_effective", getMessage("FlowRiskSceneManage001.atleastOneScene")));
                    }
                    else  if (StringUtil.equals(dto.getElementId(), "FlowRiskSceneManage001_btn_neffective")) {
                        errorList.add(new ValidMessage("FlowRiskSceneManage001_btn_neffective", getMessage("FlowRiskSceneManage001.atleastOneScene")));
                    }
                }
            }else{

                if (StringUtil.equals(dto.getOperateType(), "delete")  ) {
                    for (FlowRiskSceneManage001Dto temp : dto.getSelectedItem()) {
                        WflValidInputParameter param = new WflValidInputParameter();
                        param.setBusinessDataPk1(temp.getSceneId());
                        param.setPageId("FlowRiskSceneManage002");
                        if (null != mstWorkflowCommonService &&
                                mstWorkflowCommonService.isInWflProcess(param)) {
                            errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", temp.getSceneName())));
                        }

                    }
                }

            }
        }

        return errorList;
    }

}
