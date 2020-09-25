/*
 * 文件名：BussReduceShareInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinReduceShareInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.project.BussReduceShareInfoService;
import com.joyintech.tams.component.business.project.dto.BussReduceShareInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 减持信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussReduceShareInfoImplService
 * @since 1.0
 */
@Service
public class BussReduceShareInfoImplService extends BaseService<BussReduceShareInfoDto> implements BussReduceShareInfoService{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinReduceShareInfo> getBussReduceShareInfoByProductId(String finprodId) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussReduceShareInfo/getBussReduceShareInfoByProductId?finprodId="
            + finprodId,FinReduceShareInfo.class);
    }
}
