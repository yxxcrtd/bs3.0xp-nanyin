/*
 * 文件名：BussSubscriptionInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinSubscriptionInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.project.BussSubscriptionInfoService;
import com.joyintech.tams.component.business.project.dto.BussSubscriptionInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 *  认购信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see BussSubscriptionInfoImplService
 * @since 1.0
 */
@Service
public class BussSubscriptionInfoImplService extends BaseService<BussSubscriptionInfoDto> implements BussSubscriptionInfoService{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinSubscriptionInfo> getBussSubscriptionInfoByProductId(String finprodId) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussSubscriptionInfo/getBussSubscriptionInfoByProductId?finprodId="
            + finprodId,FinSubscriptionInfo.class);
    }

}
