/*
 * 文件名：MstOpicsParaManteValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstopicsparamante.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstopicsparamante.dto.MstOpicsParaManteDto;
import com.joyintech.tams.invest.basicdata.mstopicsparamante.service.MstOpicsParaManteService;

/**
 * OPICS参数维护校验
 * 
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaManteValidator
 * @since 1.0
 */
@Transactional
@Component
public class MstOpicsParaManteValidator implements BeanValidator {

    /**
     * service
     */
    @Autowired
    private MstOpicsParaManteService mstOpicsParaManteService;

    /**
     * valid{@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        MstOpicsParaManteDto dto = (MstOpicsParaManteDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if ((StringUtil.equals(dto.getOperateType(), "save")
             || StringUtil.equals(dto.getOperateType(), "submit"))
            && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            // 保存check
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("itemId", dto.getItemId());
            map.put("paraType", dto.getParaType());
            // 判断操作的数据是否存在
            if (mstOpicsParaManteService.selectByPrimaryKey(map) != null) {
                StringBuffer sb = new StringBuffer();
                sb.append(getMessage("MstOpicsParaMante001.itemId")).append(
                    GlobalConstant.COLON).append(dto.getItemId()).append(
                        GlobalConstant.COMMA).append(
                            getMessage("MstOpicsParaMante001.paraType")).append(
                                GlobalConstant.COLON).append(dto.getParaType());
                errorList.add(
                    new ValidMessage("itemId", getMessage("BUSS_COM_E_0006", sb.toString())));
            }

        }
        return errorList;
    }

}
