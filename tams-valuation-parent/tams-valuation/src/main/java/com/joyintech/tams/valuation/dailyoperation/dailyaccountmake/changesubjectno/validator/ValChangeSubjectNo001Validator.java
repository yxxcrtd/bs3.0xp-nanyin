/*
 * 文件名：ValAssetsFundQuery003Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.dto.ChangeSubjectNo001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.changesubjectno.service.ValChangeSubjectNo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 科目号变更-校验
 * @author yanya
 * @version 1.0 2020-12-29
 * @see ValChangeSubjectNo001Validator
 * @since 1.0
 */
@Component
public class ValChangeSubjectNo001Validator implements BeanValidator {


    @Autowired
    private ValChangeSubjectNo001Service valChangeSubjectNo001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        ChangeSubjectNo001Dto dto = (ChangeSubjectNo001Dto) value;

        if(StringUtil.equals(DictCodeListExt3.科目号变更处理方式_批量.getValue(), dto.getChangeSubjectType())) {
            if(StringUtil.isEmpty(dto.getFileName())) {
                validMessageList.add(new ValidMessage("文件不能为空！"));
            }
            else {
                try {
                    List<List<String>> dataList = valChangeSubjectNo001Service.analysisFile(dto.getFileName());
                    if(StringUtil.isEmptyList(dataList)) {
                        validMessageList.add(new ValidMessage("上传的数据文件无数据！"));
                    }
                    else {
                        //excel行号
                        int rowindex = 1;
                        for(List<String> item : dataList) {
                            String 提示 = "EXCEL中第" + rowindex + "行，";
                            if(StringUtil.isEmpty(item.get(0))) {
                                validMessageList.add(new ValidMessage(提示 + "【行号为空】"));
                            }
                            else {
                                提示 = "EXCEL中行号为【" + item.get(0) + "】，";
                            }
                            if(StringUtil.isEmpty(item.get(2))) {
                                validMessageList.add(new ValidMessage(提示 + "【原科目号为空】"));
                            }
                            rowindex++;
                        }
                    }
                }
                catch (Exception e) {
                    validMessageList.add(new ValidMessage("读取文件出现错误：" + e));
                }
            }
        }
        else {
            //原科目号不可为空
            if(StringUtil.isEmpty(dto.getOriSubjectNo())) {
                validMessageList.add(new ValidMessage("oriSubjectNo", "原科目号不可为空！"));
            }
            //替换科目号不可为空
            if(StringUtil.isEmpty(dto.getChangeSubjectNo())) {
                validMessageList.add(new ValidMessage("changeSubjectNo", "替换科目号不可为空！"));
            }
        }
        return validMessageList;
    }


}
