/*
 * 文件名：BussSubscriptionInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.project.validator.BussSubscriptionInfoValidator;

/**
 * 认购信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see BussSubscriptionInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussSubscriptionInfoValidator.class,groups = {ValidateGroupEdit.class})
public class BussSubscriptionInfoDto extends FinSubscriptionInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private List<FinSubscriptionInfo> finSubscriptionInfoList;

    
    /**
     * 获取 finSubscriptionInfoList
     * @return finSubscriptionInfoList.
     */
    public List<FinSubscriptionInfo> getFinSubscriptionInfoList() {
        return finSubscriptionInfoList;
    }

    
    /**
     * 设置 finSubscriptionInfoList
     * @param finSubscriptionInfoList finSubscriptionInfoList
     */
    public void setFinSubscriptionInfoList(List<FinSubscriptionInfo> finSubscriptionInfoList) {
        this.finSubscriptionInfoList = finSubscriptionInfoList;
    }
    
    

}
