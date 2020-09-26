/*
 * 文件名：SaveCheckValidate.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年2月7日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry003Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry003Service;
import com.joyintech.tams.common.base.entity.BokSceneconfig;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.ValStringUtil;

/**
*
* 文件名：SaveCheckValidate.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：保存check
* 系统名称：估值核算系统V1.0
*/
@Component
public class SaveCheckValidate implements BeanValidator {

    /**
     * AccountsAdjustAccountEntry003Service
     */
    @Autowired
    private ValAccountsAdjustAccountEntry003Service valAccountsAdjustAccountEntry003Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        ValAccountsAdjustAccountEntry003Dto dto = (ValAccountsAdjustAccountEntry003Dto)value;
        // 数据重复性检查：
        int count = valAccountsAdjustAccountEntry003Service.selectExistsConfig(dto);
        if (count > 0) {
            String msg = this.getMessage("VAL_COMMON_E_0027",
                getMessage("accountsadjust.accountentry.label.node"));
            errorList.add(new ValidMessage("nodeValue", msg));
            return errorList;
        }

        if (ValStringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT,
            GlobalConstant.DETAIL_PAGE_MODEL_ADD, GlobalConstant.DETAIL_PAGE_MODEL_COPY)) {
            BokSceneconfig config = valAccountsAdjustAccountEntry003Service.findSceneConfig(
                dto.getSceneNodeId());
            if (config != null && config.getUpdateTime().getTime() != dto.getUpdateTimeLong()) {
                String msg = this.getMessage("VAL_COMMON_E_0065");
                errorList.add(new ValidMessage(msg));
                return errorList;
            }
        }

        return errorList;
    }

}
