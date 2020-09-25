/*
 * 文件名：FinSubmittedModule002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年8月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule002Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule002Service;

/**
 * 申报附件方案Validator
 * 包括文件上传的验证事件
 * @author liuwei
 * @version 1.0 2018年8月23日
 * @see FinSubmittedModule002Validator
 * @since 1.0
 */
@Component
public class FinSubmittedModule002Validator implements BeanValidator{
    /**
     * 申报附件管理查询Mapper
     */
    @Autowired
    private FinSubmittedModule002Service finSubmittedModule002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinSubmittedModule002Dto dto = (FinSubmittedModule002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            //申报附件模板添加时检查输入的附件方案是否唯一
            String seqno = finSubmittedModule002Service.selectByDocAndTpl(dto);
            if (StringUtil.isNotEmpty(seqno)) {
                errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0006")));
            }
        }
        return errorList;
    }
}
