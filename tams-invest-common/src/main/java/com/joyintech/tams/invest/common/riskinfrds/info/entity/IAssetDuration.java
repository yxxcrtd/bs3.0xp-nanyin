package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 资产算久期相关信息
 * 
 * @author 裴家俊
 *
 */
public interface IAssetDuration extends IBusiness {

    @Override
    default String getKey() {
        return RedisKeyEnum.资产久期附属信息.key();
    }

    @Override
    default String getField() {
       return getAssetCode();
    }

    /**
     * 取得 资产代码
     *
     * @return 资产代码
     */
    String getAssetCode();


    /**
     * 取得 资产久期信息
     *
     * @return 资产久期信息
     */
    MockAssetInfoDto getMockAssetInfoDto();

}
