package com.joyintech.tams.disclosure.information.config.repFloatFee.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dto.RepFloatFeeDto;
import com.joyintech.tams.disclosure.information.config.repFloatFee.service.RepFloatFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RepFloatFeeValidator implements BeanValidator {
    @Autowired
    RepFloatFeeService repFloatFeeService;
    @Override
    public List<ValidMessage> valid(Object o) {
        RepFloatFeeDto dto = (RepFloatFeeDto) o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();

        if(!"delete".equals(dto.getOperateType())) {
            if(StringUtil.isEmpty(dto.getProdId())){
                errorList.add(new ValidMessage("", "子产品代码不能为空"));
            }
            if(StringUtil.isEmpty(dto.getProdName())){
                errorList.add(new ValidMessage("", "产品名称不能为空"));
            }
            if(dto.getAffirmDate()== null){
                errorList.add(new ValidMessage("", "确认日不能为空"));
            }else{
                Date platDate = DateUtil.platDateFromCache();
                if(DateUtil.compareDate(dto.getAffirmDate(), platDate) > 0){
//                    errorList.add(new ValidMessage("", "确认日不能大于平台日"));
                }
            }

            if(dto.getAffirmNum()== null){
                errorList.add(new ValidMessage("", "确认份额不能为空!"));
            }else if(dto.getAffirmNum().compareTo(new BigDecimal(0))<=0){
                errorList.add(new ValidMessage("", "确认份额只能填写大于0的数值!"));
            }

            if(dto.getAffirmAmt()== null){
                errorList.add(new ValidMessage("", "确认金额不能为空!"));
            }else if(dto.getAffirmAmt().compareTo(new BigDecimal(0))<=0){
                errorList.add(new ValidMessage("", "确认金额只能填写大于0的数值!"));
            }

            if(dto.getAboveAmt()== null){
                errorList.add(new ValidMessage("", "超额报酬不能为空!"));
            }else if(dto.getAboveAmt().compareTo(new BigDecimal(0))<0){
                errorList.add(new ValidMessage("", "超额报酬只能填写大于等于0的数值!"));
            }

            if(StringUtil.isNotEmpty(dto.getProdId()) && dto.getAffirmDate() != null ){
                RepFloatFeeDto repFloatFeeDto= repFloatFeeService.selectByProdIdAndDate(dto);
                if(repFloatFeeDto != null && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                    errorList.add(new ValidMessage("", "“子产品代码+确认日”不能同时与已有数据重复"));
                }
            }
        }
        return errorList;
    }
}
