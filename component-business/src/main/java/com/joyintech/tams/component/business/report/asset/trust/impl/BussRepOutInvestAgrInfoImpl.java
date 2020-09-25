/*
 * 文件名：BussRepOutInvestAgrInfoImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.trust.impl;


import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepTrustInvestMode;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.report.asset.trust.BussRepOutInvestAgrInfoService;
import com.joyintech.tams.component.business.report.asset.trust.dto.BussRepOutInvestAgrInfo;
import com.joyintech.tams.component.business.report.asset.trust.dto.BussRepOutInvestAgrInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 委外投资—协议方式中债报送信息组件实现类
 *
 * @author liuwei
 * @version 1.0 2018年9月19日
 * @see BussRepOutInvestAgrInfoImpl
 * @since 1.0
 */
@Service
public class BussRepOutInvestAgrInfoImpl extends BaseService<BussRepOutInvestAgrInfo> implements BussRepOutInvestAgrInfoService {
    /**
     * 根据输入的dto输出资产负债报送信息dto、资产管理计划报送信息dto
     * @param dto
     * @return
     */
    @Override
    public BussRepOutInvestAgrInfoDto getBussRepOutInvestAgrInfoDto(BussRepOutInvestAgrInfo dto){
        BussRepOutInvestAgrInfoDto entity = new BussRepOutInvestAgrInfoDto();
        RepAssetDept repAssetDept = new RepAssetDept();
        RepTrustInvestMode repTrustInvestMode = new RepTrustInvestMode();
        BeanUtil.copyPropertiesIgnoreNull(repAssetDept, dto);
        BeanUtil.copyPropertiesIgnoreNull(repTrustInvestMode, dto);
        if(StringUtil.isNotEmpty(dto.getRepMaxExpectedYield())) {
            repTrustInvestMode.setRepMaxExpectedYield(dto.getRepMaxExpectedYield());
        }
        if(StringUtil.isNotEmpty(dto.getRepMinExpectedYield())) {
            repTrustInvestMode.setRepMinExpectedYield(dto.getRepMinExpectedYield());
        }
        repTrustInvestMode.setRepManagementRate(dto.getRepManagementRate());
        repTrustInvestMode.setRepTrustRate(dto.getRepTrustRate());
        repTrustInvestMode.setRepTradeSumRate(dto.getRepTradeSumRate());
        repTrustInvestMode.setRepIntermediarySumRate(dto.getRepIntermediarySumRate());
        repTrustInvestMode.setRepOtherSumRate(dto.getRepOtherSumRate());
        entity.setRepAssetDept(repAssetDept);
        entity.setRepTrustInvestMode(repTrustInvestMode);
        return entity;
    }
    /**
     * 根据金融产品代码获取页面值
     * @param finprodId 金融产品代码
     * @return dto
     */
    @Override
    public BussRepOutInvestAgrInfo getByFinprodId(String finprodId) {
        return restTemplate.getForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/FinPlanNetAsset/getRepOutInvestAgrInfoByFinprodId?finprodId="
                        +finprodId,BussRepOutInvestAgrInfo.class);
    }
}
