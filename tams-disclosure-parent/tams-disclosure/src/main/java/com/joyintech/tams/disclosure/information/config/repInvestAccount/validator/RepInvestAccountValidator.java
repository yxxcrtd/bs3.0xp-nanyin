package com.joyintech.tams.disclosure.information.config.repInvestAccount.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dto.RepInvestAccountDto;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.service.RepInvestAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepInvestAccountValidator implements BeanValidator {
    @Autowired
    private RepInvestAccountService repInvestAccountService;

    @Override
    public List<ValidMessage> valid(Object o) {
        RepInvestAccountDto dto = (RepInvestAccountDto) o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        if(!"delete".equals(dto.getOperateType())) {
            if(StringUtil.isEmpty(dto.getProdId())){
                errorList.add(new ValidMessage("", "产品主代码不能为空"));
            }else{
                RepInvestAccountDto repInvestAccountDto = repInvestAccountService.selectByProdID(dto);
                if(repInvestAccountDto != null && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                    errorList.add(new ValidMessage("", "“产品主代码”不能与已有数据重复"));
                }
            }
            if(StringUtil.isEmpty(dto.getProdName())){
                errorList.add(new ValidMessage("", "产品名称不能为空"));
            }
            if(StringUtil.isEmpty(dto.getAccountType())){
                errorList.add(new ValidMessage("", "账户类型不能为空"));
            }
            if(StringUtil.isEmpty(dto.getAccountNum())){
                errorList.add(new ValidMessage("", "账号不能为空"));
            }
            if(StringUtil.isEmpty(dto.getAccountName())){
                errorList.add(new ValidMessage("", "账户名称不能为空"));
            }
            if(StringUtil.isEmpty(dto.getAccountOpener())){
                errorList.add(new ValidMessage("", "开户单位不能为空"));
            }

        }

        return errorList;
    }
}
