/*
 * 文件名：RskValTableImportParamValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.valtblimport.valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.valtblimport.dto.RskValTableImportParamDto;

/**
 *  估值表预览参数验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月29日
 * @see RskValTableImportParamValidator
 * @since 1.0
 */
@Component
public class RskValTableImportParamValidator implements BeanValidator{

    @Override
    public List<ValidMessage> valid(Object value) {
        RskValTableImportParamDto dto = (RskValTableImportParamDto)value;
        ArrayList<ValidMessage> resultList = new ArrayList<>();
        if(StringUtil.isEmpty(dto.getPtlId())){
            resultList.add(new ValidMessage("ptlId",
                getMessage("COMMON_E_0057", getMessage("RskValTableImport001.ptlId"))));
        }
        if(null==dto.getValDate()){
            resultList.add(new ValidMessage("valDate",
                getMessage("COMMON_E_0057", getMessage("RskValTableImport001.valDate"))));
        }
        if(StringUtil.isEmpty(dto.getValTableFileName())){
            resultList.add(new ValidMessage("valTableFileName",
                getMessage("COMMON_E_0057", getMessage("RskValTableImport001.valTableFileName"))));
        }
        return resultList;
    }
}
