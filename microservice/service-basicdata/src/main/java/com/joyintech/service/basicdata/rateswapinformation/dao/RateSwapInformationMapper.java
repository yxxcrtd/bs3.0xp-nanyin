package com.joyintech.service.basicdata.rateswapinformation.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.rateswapinformation.dto.RateSwapInformationDto;
import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RateSwapInformationMapper {

    List<MstYieldCurveInfo> selectByParams(RateSwapInformationDto dto);

    void insert(RateSwapInformationDto dto);

    void insertSelective(RateSwapInformationDto dto);

    void updateSelective(RateSwapInformationDto dto);

    void deleteSelective(RateSwapInformationDto dto);

    List<SelectOptionDto> getCurveType(Map<String, Object> param);
}
