package com.joyintech.tams.disclosure.information.config.subbalanceinfopro.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.service.SubBalanceInfoPro001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class SubBalanceInfoProValidator implements BeanValidator {

    @Autowired
    private SubBalanceInfoPro001Service subBalanceInfoPro001Service;
    @Override
    public List<ValidMessage> valid(Object o) {
        SubBalanceInfoPro001Dto dto = (SubBalanceInfoPro001Dto)o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(),"new")){
            HashMap map =new HashMap();
            map.put("booksetId",dto.getBooksetId());
            map.put("balanceDate",dto.getBalanceDateStr());
            map.put("subjectNo",dto.getSubjectNo());
            SubBalanceInfoPro001Dto subBalanceInfoPro001Dto = subBalanceInfoPro001Service.selectByPrimaryKey(map);
            if (subBalanceInfoPro001Dto!=null){
                errorList.add(new ValidMessage("", "“产品主代码+科目号+余额日期”不能同时与已有数据重复"));

            }
        }
        return errorList;
    }
}
