package com.joyintech.service.valuation.basicdata.base.dao;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanya
 * @create 2021-03-03 18:52
 */
@Repository
public interface BaseFinProductCashInfoMapper {

    /**
     *
     * 删除金融产品现金流信息
     * <功能详细描述>
     * @param finCashInfo FinCashInfo
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(FinCashInfo finCashInfo);

    /**
     *
     * 查询已生效的金融产品现金流信息
     * <功能详细描述>
     * @param dto BaseFinProductCashInfoDto
     * @return List
     * @see
     * @since 1.0
     */
    List<FinCashInfo> selectDetail(FinCashInfo finCashInfo);


}
