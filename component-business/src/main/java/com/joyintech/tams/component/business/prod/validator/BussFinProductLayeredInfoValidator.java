/*
 * 文件名：BussFinProductLayeredInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussFinProductLayeredInfoDto;

/**
 * 产品投向信息业务验证
 * 
 * @author 张亮
 * @version 1.0 2018年10月8日
 * @see BussFinProductLayeredInfoValidator
 * @since 1.0
 */
@Component
public class BussFinProductLayeredInfoValidator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinProductLayeredInfoDto bussFinProductLayeredInfoDto = (BussFinProductLayeredInfoDto)value;

        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(bussFinProductLayeredInfoDto.getPageId(), "FinProductBuild002")) {
           

        }

        return validList;
    }

}
