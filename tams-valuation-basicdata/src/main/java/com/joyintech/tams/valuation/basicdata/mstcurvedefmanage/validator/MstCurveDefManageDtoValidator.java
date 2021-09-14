package com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.basicdata.comtradeopponent.dto.FinCashDto;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:收益率管理检查
 * @auther: yuzj
 * @date: 2021/9/13
 * @param: 
 * @return: 
 */
@Transactional
@Component
public class MstCurveDefManageDtoValidator implements BeanValidator {
    /**
     * 传入参数检查 {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        MstCurveDefManageDto dto = (MstCurveDefManageDto)value;
        //如果是编辑或者新增
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT) || StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(StringUtil.equals(dto.getCurveGroupType(),DictCodeList.是否_否.getValue())){
                if(StringUtil.isEmpty(dto.getIntCurveType())){
                    errorList.add(new ValidMessage(getMessage("曲线类型不能为空！")));
                }
                if(StringUtil.isEmpty(dto.getCcy())){
                    errorList.add(new ValidMessage(getMessage("币种不能为空！")));
                }
                if(StringUtil.isEmpty(dto.getRemark())){
                    errorList.add(new ValidMessage(getMessage("描述不能为空！")));
                }
            }
        }

        return errorList;
    }

}
