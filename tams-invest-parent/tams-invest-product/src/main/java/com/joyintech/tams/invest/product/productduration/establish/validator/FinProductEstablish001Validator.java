/*
 * 文件名：FinProductEstablish001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.establish.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablish002Dto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablishDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品成立查询
 * 
 * @author zhangliang
 * @version 1.0 2018年11月13日
 * @see FinProductEstablish001Validator
 * @since 1.0
 */
@Component
public class FinProductEstablish001Validator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductEstablish002Dto finProductEstablish002Dto = (FinProductEstablish002Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        if (StringUtil.equals(finProductEstablish002Dto.getOperateType(), "revoke")) {
            // 数据状态必须为有效
            if (!StringUtil.equals(finProductEstablish002Dto.getEffectStatusStr(),
                DictCodeListExt1.有效状态_有效.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_有效.getCodeName())));
            }

            // 存在流程中数据
            if (WorkFlowUtil.isNotFromToDoList()) {
                if (productCommonService.isInFlowDataExisted(
                		finProductEstablish002Dto.getBussId())) {
                    validList.add(new ValidMessage(
                        getMessage("COMMON_E_0012", finProductEstablish002Dto.getBussId())));
                }
            }

            // 删除状态不允许撤回
            if (productCommonService.isRevokeDataExisted(finProductEstablish002Dto.getBussId())) {
                validList.add(new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke"))));
            }
        }

        return validList;
    }

}
