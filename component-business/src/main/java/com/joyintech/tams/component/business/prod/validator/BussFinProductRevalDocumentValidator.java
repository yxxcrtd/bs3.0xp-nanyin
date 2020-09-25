/*
 * 文件名：BussFinProductRevalDocumentValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussFinProductRevalDocumentDto;


/**
 * 信息披露验证
 * 
 * @author zhangliang
 * @version 1.0 2018年11月27日
 * @see BussFinProductRevalDocumentValidator
 * @since 1.0
 */
@Component
public class BussFinProductRevalDocumentValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        
        BussFinProductRevalDocumentDto bussFinProductRevalDocumentDto = (BussFinProductRevalDocumentDto)value;
        if (bussFinProductRevalDocumentDto != null){
            List<BussFinProductRevalDocumentDto> finProductRevalDocumentDtoList = bussFinProductRevalDocumentDto.getFinProductRevalDocumentDtoList();
            if (!StringUtil.isEmptyList(finProductRevalDocumentDtoList)){
                for (BussFinProductRevalDocumentDto finProductRevalDocumentDto : finProductRevalDocumentDtoList){
                    if (StringUtil.isEmpty(finProductRevalDocumentDto.getFileType())){
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductRevalDocumentDto.fileType"))));
                    }
                    if (StringUtil.isEmpty(finProductRevalDocumentDto.getFileName())){
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductRevalDocumentDto.fileName"))));
                    }
                }
                // 判断费用类型是否重复
                Map<String, BussFinProductRevalDocumentDto> goalInfoMap = finProductRevalDocumentDtoList.stream().collect(
                    Collectors.toMap(BussFinProductRevalDocumentDto::getFileType, a -> a,
                        (k1, k2) -> k1));
                if (goalInfoMap.size() != finProductRevalDocumentDtoList.size()) {
                    validList.add(new ValidMessage("finProductRevalDocumentDtoList",
                        this.getMessage("FIN_PROD_E_0020")));
                }
            }
        }
        
        return validList;
    }

}
