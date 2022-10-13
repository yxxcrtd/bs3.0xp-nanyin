package com.joyintech.tams.disclosure.information.config.repProdIndex.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RepProdIndexSMapper {
    List<SelectOptionDto> getProdId(Map<String, Object> paramMap);

    List<SelectOptionDto> getProdId2(Map<String, Object> paramMap);

    List<RepProdIndexDto> selectInfo(Map<String, Object> param);

    int insert(RepProdIndexDto record);
    int insert2(RepProdIndexDto record);
    int update(RepProdIndexDto record);
    int update2(RepProdIndexDto record);
    int delete(RepProdIndexDto record);
    int selectExits(RepProdIndexDto record);
    int delete2(RepProdIndexDto record);

    RepProdIndexDto selectProdInfo(@Param("prodId") String prodId);

    RepProdIndexDto selectByProdIDAndValueDate(RepProdIndexDto record);

    RepProdIndexDto selectPProdInfo(RepProdIndexDto record);

    RepProdIndexDto selectPProd(RepProdIndexDto record);

    RepProdIndexDto selectData(Map<String, Object> param);

    String selectProdId(@Param("prodId") String prodId);

    List<String> selectpProdId(@Param("pProdId") String pProdId);

    RepProdIndexDto checkExistData(RepProdIndexDto record);

    List<RepProdIndexDto> selectProdIndexList(@Param("list") List<RepProdIndexDto> list);

    int saveHisData(RepProdIndexDto dto);

    RepProdIndexDto selectHisData(String workflowId);

    int updateStatus(RepProdIndexDto repProdIndexDto);

    int updateByPrimaryKey(RepProdIndexDto repProdIndexDto);

    RepProdIndexDto selectCfsj(@Param("prodId") String prodId, @Param("valueDate") String valueDate);
}
