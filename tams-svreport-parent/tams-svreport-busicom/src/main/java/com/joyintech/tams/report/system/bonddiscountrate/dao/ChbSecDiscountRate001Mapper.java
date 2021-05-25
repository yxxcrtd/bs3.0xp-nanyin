package com.joyintech.tams.report.system.bonddiscountrate.dao;

import com.joyintech.tams.report.system.bonddiscountrate.dto.ChbSecDiscountRateDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbSecDiscountRate001Mapper {

    List<ChbSecDiscountRateDto> queryForPage(Map<String, Object> param);

    ChbSecDiscountRateDto selectOneDate(Map<String, Object> param);

    void updateOne(ChbSecDiscountRateDto dto);

}
