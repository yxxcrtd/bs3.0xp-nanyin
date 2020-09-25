/*
 * 文件名：BussSubscriptionInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.project.dto.BussSubscriptionInfoDto;

/**
 *  认购信息业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see BussSubscriptionInfoValidator
 * @since 1.0
 */
@Component
public class BussSubscriptionInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussSubscriptionInfoDto dto = (BussSubscriptionInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        Set<String> set = new  HashSet<String>();
        if(null!=dto.getFinSubscriptionInfoList()&&!dto.getFinSubscriptionInfoList().isEmpty()){
            for(FinSubscriptionInfo finSubscriptionInfo:dto.getFinSubscriptionInfoList()){
                if(StringUtil.isEmpty(finSubscriptionInfo.getInstitutionName())){
                    resultList.add(new ValidMessage("institutionName",
                        getMessage("COMMON_E_0057", getMessage("BussSubscriptionInfo.institutionName"))));
                    break;
                }
                if(null==finSubscriptionInfo.getSubscribDate()){
                    resultList.add(new ValidMessage("subscribDate",
                        getMessage("COMMON_E_0057", getMessage("BussSubscriptionInfo.subscribDate"))));
                    break;
                }
                if(null==finSubscriptionInfo.getSubscribAmout()){
                    resultList.add(new ValidMessage("subscribAmout",
                        getMessage("COMMON_E_0057", getMessage("BussSubscriptionInfo.subscribAmout"))));
                    break;
                }
                if(null==finSubscriptionInfo.getSubscribRate()){
                    resultList.add(new ValidMessage("subscribRate",
                        getMessage("COMMON_E_0057", getMessage("BussSubscriptionInfo.subscribRate"))));
                    break;
                }
              //名称不可重复
                if(!set.add(finSubscriptionInfo.getInstitutionName())){
                    resultList.add(new ValidMessage("",
                        getMessage("BUSS_COM_E_0006", 
                            getMessage("BussSubscriptionInfo.institutionName")
                            )));
                }
            }
        }
        return resultList;
    }

}
