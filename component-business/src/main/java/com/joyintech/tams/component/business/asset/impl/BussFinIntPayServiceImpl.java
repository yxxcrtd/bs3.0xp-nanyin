/*
 * 文件名：BussFinIntPayServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.asset.impl;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.asset.BussFinIntPayService;
import com.joyintech.tams.component.business.asset.dto.BussFinIntPayDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 分红计划实现类
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月20日
 * @see BussFinIntPayServiceImpl
 * @since 1.0
 */
@Service
public class BussFinIntPayServiceImpl extends BaseService<BussFinIntPayDto> implements BussFinIntPayService{

    /**
     * {@inheritDoc}
     */
    @Override
    public BussFinIntPayDto getByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/getIntPayByFinprodId?finprodId="
                +finprodId,BussFinIntPayDto.class);
    }

}
