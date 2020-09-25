/*
 * 文件名：FinProductBmQuotationValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.rddesign.productelementdes.validate;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author jiangyunlong
 * @version 1.0 2019年9月25日
 * @see ProductelementdesValidator
 * @since 1.0
 */
@Component
public class ProductelementdesValidator implements BeanValidator {



    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object o) {
        return null;
    }

    /**
     * {@inheritDoc}
     */


}
