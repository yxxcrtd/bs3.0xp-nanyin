package com.joyintech.tams.disclosure.information.config.accountamount.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.accountamount.service.AccountAmount001Service;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.service.SubBalanceInfoPro001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AccountAmountValidator implements BeanValidator {

    @Autowired
    private AccountAmount001Service accountAmount001Service;
    @Override
    public List<ValidMessage> valid(Object o) {
        AccountAmount001Dto dto = (AccountAmount001Dto)o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(),"new")){
            HashMap map =new HashMap();
            map.put("booksetId", dto.getBooksetId());
            map.put("dateStart", dto.getDateStartStr());
            map.put("dateEnd", dto.getDateEndStr());
            map.put("subjectNo", dto.getSubjectNo());
            AccountAmount001Dto accountAmount001Dto = accountAmount001Service.selectByPrimaryKey(map);
            if (accountAmount001Dto!=null && StringUtil.equals(accountAmount001Dto.getStatus(),"E")){
                errorList.add(new ValidMessage("", "“产品主代码+开始日期+结束日期+科目号”不能同时与已有数据重复"));
            }
        }
        if(StringUtil.isNotEmpty(dto.getDateStartStr())&&StringUtil.isNotEmpty(dto.getDateEndStr())) {
            if (!DateS(dto.getDateStartStr())) {
                errorList.add(new ValidMessage("开始日期只能是xxxx-01-01。"));
            }
            if (!DateE(dto.getDateEndStr())) {
                errorList.add(new ValidMessage("结束日期只能是xxxx-06-30或者是xxxx-12-31。"));
            }
        }
        return errorList;

    }
    public static boolean DateS(String str) {
        boolean result=false;
        String monthStr=str.split("-")[1];
        String dayStr=str.split("-")[2];
        if((StringUtil.equals(monthStr,"01")||StringUtil.equals(monthStr,'1'))&&(StringUtil.equals(dayStr,"01")||StringUtil.equals(dayStr,'1'))){
            result=true;
        }
        return result;
    }

    public static boolean DateE(String str) {
        boolean result=false;
        String monthStr=str.split("-")[1];
        String dayStr=str.split("-")[2];
        if(((StringUtil.equals(monthStr,"06")||StringUtil.equals(monthStr,'6'))&&StringUtil.equals(dayStr,"30"))||(StringUtil.equals(monthStr,"12")&&StringUtil.equals(dayStr,"31"))){
            result=true;
        }
        return result;
    }
}
