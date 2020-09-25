/*
 * 文件名：BussSubscriptionInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;

/**
 *  认购信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see BussSubscriptionInfoService
 * @since 1.0
 */
public interface BussSubscriptionInfoService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussSubscriptionInfoDto";
    
    List<FinSubscriptionInfo>  getBussSubscriptionInfoByProductId(String finprodId);
}
