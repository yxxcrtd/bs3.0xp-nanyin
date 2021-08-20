
package com.joyintech.tams.invest.bankuplink.reponew.vaildator;


import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.bankuplink.reponew.dto.CfetsRepoDealQuotedPr006Dto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 质押式回购->质押券置换-》画面check验证
 *
 * @author chenjiaxing
 * @version 1.0 2019年11月9日
 * @see CfetsRepoDealQuoted006DtoValidator
 * @since 1.0
 */
@Component
public class CfetsRepoDealQuoted006DtoValidator{


    /**
     * {@inheritDoc}
     */
    public List<ValidMessage> bussValid(Object value) {
        CfetsRepoDealQuotedPr006Dto inputDto = (CfetsRepoDealQuotedPr006Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        // 起息日<置换日期<到期日
        if (inputDto != null
                && inputDto.getVdate() != null
                && inputDto.getMdate() != null
                && inputDto.getSubstitutionDate() != null) {
            if (DateUtil.compareDate(inputDto.getSubstitutionDate(),
                    inputDto.getVdate()) <=0 || DateUtil.compareDate(inputDto.getMdate(),
                    inputDto.getSubstitutionDate()) <=0) {
                ValidMessage validMessage = new ValidMessage("置换日期需在起息日与到期日之间!");
                errorList.add(validMessage);
            }
        }
        //最终质押券列表不能为空
        if(StringUtil.isEmptyList(inputDto == null?null:inputDto.getBussResultRepoSec())||inputDto == null?null:inputDto.getBussResultRepoSec().size()==0)
        {
            ValidMessage validMessage = new ValidMessage("最终质押券列表不能为空!");
            errorList.add(validMessage);
        }
        else
        {
           for(PledgeBondInfoDto pledgeBondInfoDto : inputDto.getBussResultRepoSec()) {
               if(pledgeBondInfoDto.getPledgePrin()==null) {
                   ValidMessage validMessage = new ValidMessage("最终质押券列表中质押券面总额不能为空!");
                   errorList.add(validMessage);
                   break;
               }
           }
        }

        return errorList;
    }
}
