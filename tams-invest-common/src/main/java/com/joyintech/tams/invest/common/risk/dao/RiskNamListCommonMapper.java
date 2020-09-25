package com.joyintech.tams.invest.common.risk.dao;


import com.joyintech.tams.invest.common.risk.dto.RiskNamListCommonDto;
import com.joyintech.tams.invest.common.risk.dto.RiskNameListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 名单公共校验
 */
@Repository
public interface RiskNamListCommonMapper {

    /**
     * 标的
     * @param param
     * @return
     */
    List<RiskNamListCommonDto> getAllInfoByFinprodId(Map<String, Object> param);
    /**
     *  无标的
     * @param param
     * @return
     */
    List<RiskNamListCommonDto> getAllInfoByNoFinprodId(Map<String, Object> param);
    List<RiskNamListCommonDto> selectAssetCount(Map<String, Object> param);/*edit by songyawei 20200701*/
    RiskNamListCommonDto selectCustomerInfo(@Param("customerId") String customerId);
    RiskNamListCommonDto selectCustomerInfo2(@Param("counterId") String counterId,@Param("counterType") String counterType);
    List<RiskNameListDto> getNameListByFinprodId(Map<String, Object> param);
}
