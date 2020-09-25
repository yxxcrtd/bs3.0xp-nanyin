package com.joyintech.tams.invest.common.riskinfrds.info.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdAssetAmount;

/**
 * 资产实时规模信息-Service
 * 
 * @author zhangrui1
 *
 */
@Service
public class ProdAssetAmountService extends RedisBaseService<IProdAssetAmount> {

    public IProdAssetAmount getProdAssetAmount(String portfolioCode, String assetCode){
        return getInfoFromRedis(RedisKeyEnum.资产实时规模信息.key(), portfolioCode + GlobalConstant.COLON + assetCode);
    }
}
