package com.joyintech.tams.svreport.chbreport.prdprofit.dao;

import com.joyintech.tams.common.base.entity.PrdProfit;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品资产负债表查询Mapper
 *
 * @author WanShiWei
 * @version 1.0 2021年4月19日
 * @see PrdProfitQuery001Mapper
 * @since 1.0
 */
@Repository
public interface PrdProfitQuery001Mapper {

    /**
     * 根据条件查询理财产品利润表画面数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ChbreportPrdInfoDto> selectAllData(Map<String, Object> map);

    /**
     * 查询导出数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<PrdProfit> selectPrdProfitList(Map<String, Object> map);
}
