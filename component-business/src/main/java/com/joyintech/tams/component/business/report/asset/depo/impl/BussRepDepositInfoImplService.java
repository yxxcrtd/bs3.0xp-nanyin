/*
 * 文件名：BussRepDepositInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.depo.impl;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RepAssetBankDeposit;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDepositInfoService;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDepositInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 存款类资产中债报送
 * @author zhangnian
 * @version 1.0 2018年9月17日
 * @see BussRepDepositInfoImplService
 * @since 1.0
 */
@Service
public class BussRepDepositInfoImplService extends BaseService<BussRepDepositInfoDto> implements BussRepDepositInfoService{

    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.标的类别,DictCode.资产_负债二级类别,
            DictCode.是否_中债,DictCode.交易流通场所};
    }
    
    @Override
    public BussRepDepositInfoDto getBussRepDepositInfoDto(BussRepDepositInfoDto dto){
        //资产负债报送信息   存款资产报送信息
        RepAssetDept repAssetDept = new RepAssetDept();
        RepAssetBankDeposit  repAssetBankDeposit = new RepAssetBankDeposit();
        BeanUtil.copyPropertiesIgnoreNull(repAssetDept, dto);
        BeanUtil.copyPropertiesIgnoreNull(repAssetBankDeposit, dto);
        dto.setRepAssetDept(repAssetDept);dto.setRepAssetBankDeposit(repAssetBankDeposit);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussRepDepositInfoDto getBussRepDepositInfoByProductId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussRepDepositInfo/getBussRepDepositInfoByProductId?finprodId="
            + finprodId,BussRepDepositInfoDto.class);
    }
}
