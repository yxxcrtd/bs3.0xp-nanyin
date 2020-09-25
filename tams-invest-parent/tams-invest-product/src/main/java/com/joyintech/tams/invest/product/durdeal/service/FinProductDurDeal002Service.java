/*
 * 文件名：FinProductDurDeal002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.durdeal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.durdeal.dto.FinProductDurDeal002Dto;

/**
 * 产品存续期交易查询 明细画面Service
 *
 * @author etliu
 * @version 1.0 2018年12月25日
 * @see FinProductDurDeal002Service
 * @since 1.0
 */
@Service
public class FinProductDurDeal002Service extends BaseService<FinProductDurDeal002Dto> {

    /**
     * 检索产品存续期交易信息
     *
     * @param pageParam 查询参数
     * @return 产品存续期交易信息
     * @see
     * @since 1.0
     */
    public Page<FinProductDurDeal002Dto> findPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/findDetailData",
                pageParam, FinProductDurDeal002Dto.class);
    }
}
