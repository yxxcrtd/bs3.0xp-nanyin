package com.joyintech.service.valuation.selectbokdetailcurrent.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.joyintech.service.valuation.selectbokdetailcurrent.dto.SelectBokdetailCurrent001Dto;

/**
 * @author tanxuan
 * @createTime 2019-10-16
 */
@Repository("serviceSelectBokdetailCurrent001Mapper")
public interface SelectBokdetailCurrent001Mapper {

    List<SelectBokdetailCurrent001Dto> selectList(Map<String, Object> params);

}
