package com.joyintech.tams.disclosure.information.config.repInvestAccount.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dto.RepInvestAccountDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RepInvestAccount001Mapper {
    List<SelectOptionDto> getProdInsId(Map<String, Object> paramMap);

    List<SelectOptionDto> getProdInsId2(Map<String, Object> paramMap);

    List<RepInvestAccountDto> selectInfo(Map<String, Object> param);

    int insert(RepInvestAccountDto record);

    int update(RepInvestAccountDto record);

    int delete(RepInvestAccountDto record);

    RepInvestAccountDto selectData(Map<String, Object> param);

    Map<String,String> selectProdName(@Param("prodId")String prodId);

    RepInvestAccountDto selectByProdID(RepInvestAccountDto record);

    String getProdId(@Param("prodId") String prodId);

    RepInvestAccountDto checkExistData(RepInvestAccountDto record);

    List<RepInvestAccountDto> selectInvestAccountList(@Param("list") List<RepInvestAccountDto> list);
}
