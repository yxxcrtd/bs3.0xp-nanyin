package com.joyintech.service.valuation.basedata.marketdata.valrateswapinformation.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.valrateswapinformation.dto.ValRateSwapInformationDto;
import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ValRateSwapInformationMapper {

    List<MstYieldCurveInfo> selectByParams(ValRateSwapInformationDto dto);

    void insert(ValRateSwapInformationDto dto);

    void insertSelective(ValRateSwapInformationDto dto);

    void updateSelective(ValRateSwapInformationDto dto);

    void deleteSelective(ValRateSwapInformationDto dto);

    List<SelectOptionDto> getCurveType(Map<String, Object> param);
}
