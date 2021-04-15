package com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.validator;


import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.dto.StandardCouponReConversionRateDto;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.service.StandardCouponReConversionRateService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class StandardCouponReConversionRateValidator implements BeanValidator {

    @Autowired
    private StandardCouponReConversionRateService standardCouponReConversionRateService;

    @Override
    public List<ValidMessage> valid(Object o) {
        StandardCouponReConversionRateDto dto = (StandardCouponReConversionRateDto) o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage> ();
        if (StringUtil.equals(dto.getOperateType(), "new") || StringUtil.equals(dto.getOperateType(), "update")) {
            if(StringUtil.isNotEmpty(dto.getTandardRate ())){
                if(!NumberUtils.isNumber(dto.getTandardRate ())) {
                    resultList.add(new ValidMessage(this.getMessage("输入的标准券再折算率不是数字", dto.getTandardRate ())));
                }else {
                    BigDecimal tandardRate = new BigDecimal (dto.getTandardRate ());
                    BigDecimal tandardRate0 = new BigDecimal ("0");
                    BigDecimal tandardRate1 = new BigDecimal ("100");
                    if(tandardRate.compareTo (tandardRate0) < 0 || tandardRate.compareTo (tandardRate1) > 0){
                        resultList.add(new ValidMessage(this.getMessage("输入的标准券再折算率不在0和100之间", dto.getTandardRate ())));
                    }
                }
            }
            if(!StringUtil.equals(dto.getOperateType(), "update")){
                StandardCouponReConversionRateDto entity = standardCouponReConversionRateService.selectByPrimaryKey(dto);
                if(entity != null){
                    resultList.add(new ValidMessage(this.getMessage("该券商和资金账户的再折算率维护已存在")));
                }
            }

        }
        return resultList;
    }
}
