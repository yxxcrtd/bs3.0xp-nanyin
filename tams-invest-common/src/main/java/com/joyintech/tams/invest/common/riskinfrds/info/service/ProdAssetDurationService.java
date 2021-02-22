package com.joyintech.tams.invest.common.riskinfrds.info.service;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetDuration;
import org.springframework.stereotype.Service;

/**
 * 资产现金流及其它信息-Service
 * 
 * @author 裴家俊
 *
 */
@Service
public class ProdAssetDurationService extends RedisBaseService<IAssetDuration> {

    public IAssetDuration getAssetDuration(String assetCode){
        return getInfoFromRedis(RedisKeyEnum.资产久期附属信息.key(), assetCode);
    }
}
