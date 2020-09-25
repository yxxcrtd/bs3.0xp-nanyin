/*
 * 文件名：BussFinRecommentValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.asset.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinProductRecomment;
import com.joyintech.tams.component.business.asset.dto.BussFinRecommentDto;

/**
 * 资产推荐方及费率信息--业务验证 〈功能详细描述〉
 * 
 * @author renping
 * @version 1.0 2019年10月10日
 * @see BussFinRecommentValidator
 * @since 1.0
 */
@Component
public class BussFinRecommentValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinRecommentDto dto = (BussFinRecommentDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (!StringUtil.isEmptyList(dto.getFinProductRecommentList())) {
            for (FinProductRecomment entity : dto.getFinProductRecommentList()) {
                if (entity != null && StringUtil.isEmpty(entity.getRecommender())) {
                    resultList.add(new ValidMessage("finProductRecommentList",
                        getMessage("FW_E_0006", getMessage("BussFinRepayment.recommender"))));
                }
            }
        }
        return resultList;
    }

}
