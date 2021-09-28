package com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:收益率曲线族管理检查
 * @auther: yuzj
 * @date: 2021/9/13
 * @param: 
 * @return: 
 */
@Transactional
@Component
public class MstCurveGroupManageDtoValidator implements BeanValidator {
    /**
     * 传入参数检查 {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        MstCurveGroupManageDto dto = (MstCurveGroupManageDto)value;
        return errorList;
    }

}
