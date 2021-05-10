package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.valuation.common.auxiliary.dto.BokBookdetailCurrentDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ValSubDevelopment001Mapper {
    List<SelectOptionDto> selectBooksetId(Map<String, Object> params);
    List<SelectOptionDto> selectThirdSubjectId(Map<String, Object> params);
    int deleteBokBussDetSub(Map<String, Object> param);
    int insertBokBussDetSub(BokBussDetsub sub);
    int updateBokDetailId(BokBookdetailCurrentDto dto);
    int updateBokBookDetailAll(BokBookdetailCurrentDto dto);
    int updateBokBookDetailCurrent(BokBookdetailCurrentDto dto);
}
