package com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.repFloatFee.dto.RepFloatFeeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SvrProdFeeDealInterfMapper {
    List<SelectOptionDto> getProdInsId(Map<String, Object> paramMap);

    List<SelectOptionDto> getProdInsId2(Map<String, Object> paramMap);

    int insertPrdFeeDeal(SvrProdFeeDealInterfDto record);

    int update(SvrProdFeeDealInterfDto record);

    int delete(SvrProdFeeDealInterfDto record);

    List<SvrProdFeeDealInterfDto> selectInfo(Map<String, Object> param);

//    List<SvrProdFeeDealInterfDto> selectForExport(Map<String, Object> param);
    List<SvrProdFeeDealInterfDto> selectForExport(@Param("selectData")List<SvrProdFeeDealInterfDto> keylist);

    SvrProdFeeDealInterfDto selectData(Map<String, Object> param);

    SvrProdFeeDealInterfDto selectByProdID(SvrProdFeeDealInterfDto record);

    Map<String,String> selectProdName(@Param("prodId")String prodId);

    String getFeeTYPE(@Param("feetype") String feetype);

    String getProdId(@Param("prodId") String prodId);

    SvrProdFeeDealInterfDto checkExistData(SvrProdFeeDealInterfDto record);
}
