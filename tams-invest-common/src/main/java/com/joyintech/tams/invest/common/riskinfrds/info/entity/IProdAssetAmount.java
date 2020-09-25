package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import java.math.BigDecimal;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 资产的实时规模信息
 * 
 * @author zy_zhangrui
 *
 */
public interface IProdAssetAmount extends IBusiness {

    @Override
    default String getKey() {
        return RedisKeyEnum.资产实时规模信息.key();
    }

    @Override
    default String getField() {
        return getPortfolioCode() + GlobalConstant.COLON + getAssetCode();
    }

    /**
     * 取得 组合代码
     * 
     * @return 组合代码
     */
    String getPortfolioCode();

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    String getAssetCode();

    /**
     * 取得 资产规模-金额
     * 
     * @return 资产规模-金额
     */
    BigDecimal getAmount();

    /**
     * 取得 资产规模-数量
     * 
     * @return 资产规模-数量
     */
    BigDecimal getNum();
}
