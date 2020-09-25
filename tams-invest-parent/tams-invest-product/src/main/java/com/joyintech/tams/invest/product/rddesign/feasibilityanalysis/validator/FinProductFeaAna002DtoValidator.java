package com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.dto.FinProductFeaAna002Dto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FinProductFeaAna002DtoValidator implements BeanValidator {


    @Override
    public List<ValidMessage> valid(Object o) {
        FinProductFeaAna002Dto dto = (FinProductFeaAna002Dto) o;
        ArrayList<ValidMessage> errorList = new ArrayList<>();

        if(!StringUtil.isNotEmpty(dto.getFeasibilityResult())){
            errorList.add(new ValidMessage("feasibilityResult",getMessage("FIN_PROD_E_0090",
                    getMessage("FinProductFeaAna002.feasibilityResult"))));
        }
        if(!StringUtil.isNotEmpty(dto.getFeasibilityStatus())){
            errorList.add(new ValidMessage("feasibilityStatus",getMessage("FIN_PROD_E_0090",
                                            getMessage("FinProductFeaAna002.feasibilityStatus"))));
        }
        //可行需求分类 当“分析结论”为“可行”时必填
        if(StringUtil.isNotEmpty(dto.getFeasibilityStatus())
                && StringUtil.equals(dto.getFeasibilityStatus(),DictCodeListExt2.可行性状态_可行.getValue())
                && !StringUtil.isNotEmpty(dto.getFeasibilityType())){
            errorList.add(new ValidMessage("feasibilityType",getMessage("FIN_PROD_E_0090",
                    getMessage("FinProductFeaAna002.feasibilityType"))));
        }

        return errorList;
    }
}
