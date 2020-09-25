/*
 * 文件名：RskPortfolioPoolConfigValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioPoolConfigDto;
import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.PtlParamPoolConfig;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 组合包明细验证
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2018年11月13日
 * @see RskPortfolioPoolConfigValidator
 * @since 1.0
 */
@Component
public class RskPortfolioPoolConfigValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        RskPortfolioPoolConfigDto dto = (RskPortfolioPoolConfigDto)value;
        List<PtlParamPoolConfig> ptlParamPoolConfigList = dto.getPtlParamPoolConfigList();
        if(null!=ptlParamPoolConfigList&&!ptlParamPoolConfigList.isEmpty()){
            for(PtlParamPoolConfig entity:ptlParamPoolConfigList){
            	//分类维度不可为空
                if(StringUtil.isEmpty(entity.getClassifyDimension())){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("RskSupervisionPortfPoint003.sortType"))));
                    break;
                }
                //分类维度枚举值不可为空
                if(StringUtil.isEmpty(entity.getClassifyDimensionVal())){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("RskSupervisionPortfPoint003.sortTypeValu"))));
                    break;
                }
            }
        }
        return resultList;
    }

}
