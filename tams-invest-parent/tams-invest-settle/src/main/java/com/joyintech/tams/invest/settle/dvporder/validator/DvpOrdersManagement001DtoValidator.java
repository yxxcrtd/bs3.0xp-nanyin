package com.joyintech.tams.invest.settle.dvporder.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csii.dc.client.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersProcessDto;
import com.joyintech.tams.invest.settle.dvporder.service.DvpOrdersManagement002Service;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-13 15:26
 **/
@Component
public class DvpOrdersManagement001DtoValidator implements BeanValidator {

    @Autowired
    private DvpOrdersManagement002Service dvpOrdersManagement002Service;

    @Override
    public List<ValidMessage> valid(Object o) {
        List<ValidMessage> result = new ArrayList<>();
        DvpOrdersProcessDto dto = (DvpOrdersProcessDto)o;

        if (dto.getTradeDatas() == null || dto.getTradeDatas().isEmpty()) {
        	if(!dto.getOperateType().equals("revoke")){
            result.add(new ValidMessage("没有交易数据"));
        	}
        }
        else {
            int number = dvpOrdersManagement002Service.findMatchedNum(dto.getTradeDatas());
        }
        return result;
    }
}
