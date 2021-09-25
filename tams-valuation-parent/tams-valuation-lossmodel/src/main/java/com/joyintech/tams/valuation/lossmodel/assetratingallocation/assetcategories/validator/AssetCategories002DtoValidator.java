package com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto.AssetCategories002Dto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssetCategories002DtoValidator implements BeanValidator {

    /**
     * service
     */
//    @Autowired(required = false)
//    private MstWorkflowCommonService mstWorkflowCommonServie;
    /**
     * 数据service
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        AssetCategories002Dto dto = (AssetCategories002Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

        if (StringUtil.equals("02",dto.getGradeOrgType())){
            if (StringUtil.isEmpty(dto.getGradeOrgId())){
                resultList.add(new ValidMessage("请输入指定评级机构。"));
            }
        }
        return resultList;
    }



}
