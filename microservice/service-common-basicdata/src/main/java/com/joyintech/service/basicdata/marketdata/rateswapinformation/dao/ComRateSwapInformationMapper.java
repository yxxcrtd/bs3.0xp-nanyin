package com.joyintech.service.basicdata.marketdata.rateswapinformation.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.rateswapinformation.dto.ComRateSwapInformationDto;
import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ComRateSwapInformationMapper {

    List<MstYieldCurveInfo> selectByParams(ComRateSwapInformationDto dto);

    void insert(ComRateSwapInformationDto dto);

    void insertSelective(ComRateSwapInformationDto dto);

    void updateSelective(ComRateSwapInformationDto dto);

    void deleteSelective(ComRateSwapInformationDto dto);

    List<SelectOptionDto> getCurveType(Map<String, Object> param);
}
