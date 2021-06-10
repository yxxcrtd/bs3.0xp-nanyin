package com.joyintech.service.valuation.component.asset.dao;

import com.joyintech.service.valuation.component.asset.dto.FinInterestFactorDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yanya
 * @create 2021-03-03 10:13
 */
@Repository
public interface FinInterestFactorMapper {

    FinInterestFactorDto getFinInterestFactorByProductId(@Param("finprodId") String finprodId, @Param("vdate") String vdate, @Param("cashType") String cashType);
}
