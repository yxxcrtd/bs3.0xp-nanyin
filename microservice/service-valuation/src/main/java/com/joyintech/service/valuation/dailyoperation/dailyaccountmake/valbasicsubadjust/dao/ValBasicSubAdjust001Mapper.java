package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dto.ValBasicSubAdjust001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ValBasicSubAdjust001Mapper {
    List<SelectOptionDto> selectSubjectNo(Map<String, Object> params);
    List<SelectOptionDto> selectBaseSubjectNature(Map<String, Object> params);
    int updateBokBookdetailAll(ValBasicSubAdjust001Dto dto);
    int updateBokBookdetailCurrent(ValBasicSubAdjust001Dto dto);
    int updateBokSubjectDetail(ValBasicSubAdjust001Dto dto);
}
