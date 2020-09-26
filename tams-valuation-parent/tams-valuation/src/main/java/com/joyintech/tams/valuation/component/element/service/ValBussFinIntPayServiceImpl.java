/*
 * 文件名：BussFinIntPayServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.element.dto.ValBussFinIntPayDto;
import com.joyintech.tams.valuation.component.element.interf.ValBussFinIntPayService;

/**
 * 分红计划实现类
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月20日
 * @see ValBussFinIntPayServiceImpl
 * @since 1.0
 */
@Service
public class ValBussFinIntPayServiceImpl extends BaseService<ValBussFinIntPayDto> implements ValBussFinIntPayService{

    /**
     * 通过金融产品代码粗挨训分红计划
     */
    @Override
    public ValBussFinIntPayDto getByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValFinFundTarget/getIntPayByFinprodId?finprodId="
                +finprodId,ValBussFinIntPayDto.class);
    }

}
