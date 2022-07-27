package com.joyintech.tams.disclosure.information.config.repFloatFee.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dto.RepFloatFeeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RepFloatFee001Mapper {
    List<SelectOptionDto> getProdId(Map<String, Object> paramMap);

    List<SelectOptionDto> getProdId2(Map<String, Object> paramMap);

    List<RepFloatFeeDto> selectInfo(Map<String, Object> param);

    RepFloatFeeDto selectData(Map<String, Object> param);

    int insert(RepFloatFeeDto record);

    int delete(RepFloatFeeDto record);

    int update(RepFloatFeeDto record);

    Map<String,String> selectProdName(@Param("prodId")String prodId);

    RepFloatFeeDto selectByProdIdAndDate(RepFloatFeeDto record);

    String selectByProdId(@Param("prodId") String prodId);
}
