/*
 * 文件名：BussFinIntPayServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.asset.impl;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.component.business.asset.BussAssetFiveTypeService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 资产五级分类
 * 
 * @author renping
 * @version 1.0 2019年7月11日
 * @see BussFinIntPayServiceImpl
 * @since 1.0
 */
@Service
public class BussAssetFiveTypeServiceImpl extends BaseService<FinProductTypeConfig> implements BussAssetFiveTypeService {

    /**
     * {@inheritDoc}
     */
    @Override
    public FinProductTypeConfig doAssetFiveTypeChange(FinProductTypeConfig finProductTypeConfig) {
		return restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME + "/core/getAssetFiveType",
				finProductTypeConfig, FinProductTypeConfig.class);
    }

}
