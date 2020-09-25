/*
 * 文件名：RskValtblAssetSubMapValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCode001Service;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCode002Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月26日
 * @see RskValtblAssetSubMapValidator
 * @since 1.0
 */
@Component
public class RskValtblAssetSubMapValidator implements BeanValidator{

    @Autowired
    private RskValAssetCode001Service rskValAssetCode001Service;
    @Autowired
    private RskValAssetCode002Service rskValAssetCode002Service;
    @Override
    public List<ValidMessage> valid(Object value) {
        RskValtblAssetSubMapDto dto = (RskValtblAssetSubMapDto)value;
        ArrayList<ValidMessage> resultList = new ArrayList<>();
        if(StringUtil.equals(dto.getOperateType(), "delete")){
            if(rskValAssetCode001Service.getCountByPtl(dto.getId())>0){
                resultList.add(new ValidMessage("name",
                    getMessage("RSK_W_0001", getMessage("RskValAssetCode002.id"),dto.getId())));
            }
        }
        if(StringUtil.isNotEmpty(dto.getName())){
            if(rskValAssetCode002Service.selecctCountByName(dto.getName(), dto.getId())>0){
                resultList.add(new ValidMessage("name",
                    getMessage("BUSS_COM_E_0006", getMessage("RskValAssetCode002.name"))));
            }
        }
        return resultList;
    }
}
