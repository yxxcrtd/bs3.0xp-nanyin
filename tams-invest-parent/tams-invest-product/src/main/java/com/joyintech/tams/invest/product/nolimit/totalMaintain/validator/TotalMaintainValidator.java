/*
 * 文件名：FinPayPlanAdjustmentValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.nolimit.totalMaintain.validator;


import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.service.TotalMaintain002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author jyl
 * @version 1.0 2019年12月19日
 * @see TotalMaintainValidator
 * @since 1.0
 */
@Component
public class TotalMaintainValidator implements BeanValidator {

    /**
     * 非标限额新建Service
     */
    @Autowired
    private TotalMaintain002Service totalMaintain002Service;

    /**
     * 非标限额
     *
     * @param value
     * @return List
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        TotalMaintain001Dto dto = (TotalMaintain001Dto) value;
        List<ValidMessage> erroList = new ArrayList<ValidMessage>();
        //主键effDate唯一校验
        String operateType = dto.getOperateType();

        if (StringUtil.equals(operateType, "0") && totalMaintain002Service.effDateCheck(dto.getEffDate()) > 0) {
            erroList.add(new ValidMessage("", "该生效日当天已存在数据"));
        }

        return erroList;
    }

}
