/*
 * 文件名：BussReduceShareInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinReduceShareInfo;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.project.dto.BussReduceShareInfoDto;

/**
 * 减持信息--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussReduceShareInfoValidator
 * @since 1.0
 */
@Component
public class BussReduceShareInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussReduceShareInfoDto dto = (BussReduceShareInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        Set<Date> set = new  HashSet<Date>();
        if(null!=dto.getFinReduceShareInfoList()&&!dto.getFinReduceShareInfoList().isEmpty()){
            for(FinReduceShareInfo entity:dto.getFinReduceShareInfoList()){
              if(null==entity.getReduceDate()){
                  resultList.add(new ValidMessage("reduceDate",
                      getMessage("COMMON_E_0057", getMessage("BussReduceShareInfo.reduceDate"))));
                  break;
              }
              if(null==entity.getReducePer()){
                  resultList.add(new ValidMessage("reducePer",
                      getMessage("COMMON_E_0057", getMessage("BussReduceShareInfo.reducePer"))));
                  break;
              }
              //减持日期不可重复
                if(!set.add(entity.getReduceDate())){
                    resultList.add(new ValidMessage("",
                        getMessage("BUSS_COM_E_0006", 
                            getMessage("BussReduceShareInfo.reduceDate")
                            )));
                }
            }
        }
        return resultList;
    }

}
