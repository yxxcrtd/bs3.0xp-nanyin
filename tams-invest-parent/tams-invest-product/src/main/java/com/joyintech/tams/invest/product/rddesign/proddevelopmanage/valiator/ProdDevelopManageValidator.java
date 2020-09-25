/*
 * 文件名：ProdDevelopManageValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.rddesign.proddevelopmanage.valiator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.product.rddesign.proddevelopmanage.dto.ProdDevelopManageDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhangJin
 * @version 1.0 2019年10月23日
 * @see ProdDevelopManageValidator
 * @since 1.0
 */
@Component
public class ProdDevelopManageValidator implements BeanValidator {

    /**
     * {@link MstWorkflowCommonService}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ProdDevelopManageDto entity = (ProdDevelopManageDto)value;
        // 验证信息
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(entity.getOperateType(), "edit")
            || StringUtil.equals(entity.getOperateType(), "delete")) {
            // 流程验证
            WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
            wflValidInputParameterIssue.setPageId(entity.getPageId());
            wflValidInputParameterIssue.setWorkflowId(entity.getWorkflowId());
            wflValidInputParameterIssue.setBusinessDataPk1(entity.getSeqNo());
            if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
                validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077", "")));
            }
        }
        return validList;
    }

}
