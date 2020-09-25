/*
 * 文件名：BussRepAssetMngProdInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.plan.impl;


import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.report.asset.plan.BussRepAssetMngProdInfoService;
import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfo;
import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 资产管理计划报送信息组件实现类
 *
 * @author liuwei
 * @version 1.0 2018年9月18日
 * @see BussRepAssetMngProdInfoImpl
 * @since 1.0
 */
@Service
public class BussRepAssetMngProdInfoImpl extends BaseService<BussRepAssetMngProdInfo> implements BussRepAssetMngProdInfoService {
    /**
     * 根据输入的dto输出资产负债报送信息dto、资产管理计划报送信息dto
     * @param dto BussRepAssetMngProdInfo
     * @return BussRepAssetMngProdInfoDto
     */
    @Override
    public BussRepAssetMngProdInfoDto getBussRepAssetMngProdInfoDto(BussRepAssetMngProdInfo dto){
        BussRepAssetMngProdInfoDto entity = new BussRepAssetMngProdInfoDto();
        RepAssetDept repAssetDept = new RepAssetDept();
        RepAssetMngPlan repAssetMngPlan = new RepAssetMngPlan();
        BeanUtil.copyPropertiesIgnoreNull(repAssetDept, dto);
        BeanUtil.copyPropertiesIgnoreNull(repAssetMngPlan, dto);
        if(StringUtil.isNotEmpty(dto.getRepMaxRate())) {
            repAssetMngPlan.setRepMaxRate(dto.getRepMaxRate());
        }
        if(StringUtil.isNotEmpty(dto.getRepMinRate())) {
            repAssetMngPlan.setRepMinRate(dto.getRepMinRate());
        }
        repAssetMngPlan.setRepMgrFeeRate(dto.getRepMgrFeeRate());
        repAssetMngPlan.setRepTruFeeRate(dto.getRepTruFeeRate());
        repAssetMngPlan.setRepTradeFeeRate(dto.getRepTradeFeeRate());
        repAssetMngPlan.setRepAgencyFeeRate(dto.getRepAgencyFeeRate());
        repAssetMngPlan.setRepOtherFeeRate(dto.getRepOtherFeeRate());
        entity.setRepAssetDept(repAssetDept);
        entity.setRepAssetMngPlan(repAssetMngPlan);
        return entity;
    }
    /**
     * 根据金融产品代码获取页面值
     * @param finprodId 金融产品代码
     * @return dto
     */
    @Override
    public BussRepAssetMngProdInfo getByFinprodId(String finprodId) {
        return restTemplate.getForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/FinPlanNetAsset/getRepAssetMngProdInfoByFinprodId?finprodId="
                        +finprodId,BussRepAssetMngProdInfo.class);
    }

}
