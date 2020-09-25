/*
 * 文件名：FinSubmittedModule001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule001Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule001Service;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 申报方案Validator
 * 
 * @author liuwei
 * @version 1.0 2018年8月22日
 * @see FinSubmittedModule001Validator
 * @since 1.0
 */
@Component
public class FinSubmittedModule001Validator implements BeanValidator{

    /**
     * 申报附件管理查询Mapper
     */
    @Autowired
    private FinSubmittedModule001Service finSubmittedModule001Service;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        FinSubmittedModule001Dto dto = (FinSubmittedModule001Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if(finSubmittedModule001Service.deleteCheck(dto.getSchemeId())!= null){
                    errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0004",getMessage("FinSubmittedModule001.finprodId"),
                            finSubmittedModule001Service.deleteCheck(dto.getSchemeId()))));
                }
            }
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)&&!StringUtil.equals(dto.getOperateType(), "submit")) {
                if (!StringUtil.isNotEmpty(dto.getSchemeId())) {
                    errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0066")));
                }
            }
            if (StringUtil.equals(dto.getOperateType(), "submit")) {
                Map<String,Object> map = new HashMap<>();
                map.put("schemeName",dto.getSchemeName());
                map.put("schemeId",dto.getSchemeId());
                if(StringUtil.isNotEmpty(finSubmittedModule001Service.selectSchemeName(map))){
                    errorList.add(new ValidMessage("schemeName",getMessage("FIN_PROD_E_0061")));
                }
            }
        }
        return errorList;
    }
}
