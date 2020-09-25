/*
 * 文件名：MarketRiskSceneManage001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.valid;

import java.util.ArrayList;
import java.util.List;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage001Dto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 市场性情景管理验证类（删除、启用、停用）
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage001Validator
 * @since 1.0
 */
@Transactional
@Component
public class MarketRiskSceneManage001Validator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        MarketRiskSceneManage001Dto dto = (MarketRiskSceneManage001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        
        if(WorkFlowUtil.isNotFromToDoList()){
            if(CollectionUtils.isEmpty(dto.getSelectedItem())){
                if(StringUtil.equals(dto.getOperateType(), "delete")){
                    errorList.add(new ValidMessage("MarketRiskSceneManage001_btn_delete", getMessage("MarketRiskSceneManage001.atleastOneScene")));
                }else{
                    if(StringUtil.equals(dto.getElementId(), "MarketRiskSceneManage001_btn_effective")){
                        errorList.add(new ValidMessage("MarketRiskSceneManage001_btn_effective", getMessage("MarketRiskSceneManage001.atleastOneScene")));
                    }else if(StringUtil.equals(dto.getElementId(), "MarketRiskSceneManage001_btn_neffective")){
                        errorList.add(new ValidMessage("MarketRiskSceneManage001_btn_neffective", getMessage("MarketRiskSceneManage001.atleastOneScene"))); 
                    }
                }
            }
        }
        return errorList;
    }

}
