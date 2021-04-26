package com.joyintech.tams.svreport.chbreport.hxprofit.dao;


import com.joyintech.tams.common.base.entity.HxProfit;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品资产负债表查询Mapper
 *
 * @author WanShiWei
 * @version 1.0 2021年4月19日
 * @see HXProfitQuery001Mapper
 * @since 1.0
 */
@Repository
public interface HXProfitQuery001Mapper {

    /**
     * 根据条件查询华夏资产负债表画面数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ChbreportPrdInfoDto> selectAllData(Map<String, Object> map);

    /**
     * 查询华夏利润表数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<HxProfit> selectHxProfitData(Map<String, Object> map);

}
