package com.joyintech.tams.invest.settle.netting.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersProcessDto;
import com.joyintech.tams.invest.settle.dvporder.service.DvpOrdersManagement002Service;
import com.joyintech.tams.invest.settle.netting.dto.NettingProcessDto;
import com.joyintech.tams.invest.settle.netting.service.NettingManagement002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 姚轶文
 * @create 2019- 11-09 13:55
 */
@Component
public class NettingManagement001DtoValidator implements BeanValidator {

    @Autowired
    private NettingManagement002Service nettingManagement002Service;

    @Override
    public List<ValidMessage> valid(Object o) {
        List<ValidMessage> result = new ArrayList<>();
        NettingProcessDto dto = (NettingProcessDto)o;

        if(dto.getPurpose()==null || dto.getPurpose().isEmpty())
        {
            result.add(new ValidMessage("请填写用途"));
        }

        if (dto.getTradeDatas() == null || dto.getTradeDatas().isEmpty()) {
            if(!dto.getOperateType().equals("revoke")){
                result.add(new ValidMessage("没有交易数据"));
            }
        }
        else {
            int number = nettingManagement002Service.findMatchedNum(dto.getTradeDatas());
        }
        return result;
    }
}
