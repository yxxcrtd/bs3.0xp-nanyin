package com.joyintech.service.entrust.etrmoneycalculation.dao;

import com.joyintech.service.entrust.etrmoneycalculation.dto.EtrMoneyCalculationDto;
import com.joyintech.service.entrust.etrmoneycalculation.dto.RatingDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface EtrMoneyCalculationMapper {
    /**
     *
     * 可投金额试算-> 选择资产代码后查询出相关属性项内容
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    EtrMoneyCalculationDto getFinprodMsg(@Param("finprodId") String finprodId);
    EtrMoneyCalculationDto getFinprodByFinprod(@Param("finprodId") String finprodId);
    //当前为债券时获取 债项评级和主体评级结果值， 规则：获取每项最新评级的结果
    List<RatingDto> getRatingList(@Param("finprodId") String finprodId);

    String getCreditName(@Param("creditMstId") String creditMstId);
}
