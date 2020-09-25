/*
 * 文件名：BussFinProductSaleParamImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.impl;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.common.base.dao.PrdSaleParamMapper;
import com.joyintech.tams.component.business.prod.BussFinProductSaleParamService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductSaleParamDto;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 销售参数业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月24日
 * @see BussFinProductSaleParamImplService
 * @since 1.0
 */
@Service
public class BussFinProductSaleParamImplService implements BussFinProductSaleParamService {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BussFinProductSaleParamDto querySaleParamInfoByFinprodId(String finprodId) {
        BussFinProductSaleParamDto bussFinProductSaleParamDto = bussCommonMapperDelegateService.selectBySinglePrimaryKey(PrdSaleParamMapper.class,
            finprodId, BussFinProductSaleParamDto.class);
        if (bussFinProductSaleParamDto != null){
            bussFinProductSaleParamDto.setHugeRedRatio(ArithmeticUtil.multiply(bussFinProductSaleParamDto.getHugeRedRatio(), new BigDecimal(100)));
            bussFinProductSaleParamDto.setMaxPlgeRate(ArithmeticUtil.multiply(bussFinProductSaleParamDto.getMaxPlgeRate(), new BigDecimal(100)));
            //募集金额  募集上限 募集下限 单位改成元 yzg 20191126 start 
//            bussFinProductSaleParamDto.setSaleMax(ArithmeticUtil.divide(bussFinProductSaleParamDto.getSaleMax(), new BigDecimal(10000), 2));
//            bussFinProductSaleParamDto.setSaleMin(ArithmeticUtil.divide(bussFinProductSaleParamDto.getSaleMin(), new BigDecimal(10000), 2));
//            bussFinProductSaleParamDto.setRaiseAmtPlan(ArithmeticUtil.divide(bussFinProductSaleParamDto.getRaiseAmtPlan(), new BigDecimal(10000), 2));
            //募集金额  募集上限 募集下限 单位改成元 yzg 20191126 end
        }
        return bussFinProductSaleParamDto;
    }

}
