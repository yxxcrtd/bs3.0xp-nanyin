/*
 * 文件名：BussFinRecommentImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.asset.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinProductRecomment;
import com.joyintech.tams.component.business.asset.BussFinRecommentService;
import com.joyintech.tams.component.business.asset.dto.BussFinRecommentDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 资产推荐方及费率信息
 * 
 * @author renping
 * @version 1.0 2019年10月10日
 * @see BussFinRecommentImplService
 * @since 1.0
 */
@Service
public class BussFinRecommentImplService extends BaseService<BussFinRecommentDto> implements BussFinRecommentService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinProductRecomment> getFinProductRecommentByProductId(String finprodId) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
                                       + "/BussFinRecomment/getFinProductRecommentByProductId?finprodId="
                                       + finprodId,
            FinProductRecomment.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SelectOptionDto> qryRecommenderSelect(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/BussFinRecomment/qryRecommenderSelect", param,
            SelectOptionDto.class);
    }
}
