package com.joyintech.tams.disclosure.information.config.repProdIndexGz.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.repProdIndexGz.dto.RepProdIndexGzDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RepProdIndexGzMapper {
    List<SelectOptionDto> getProdId(Map<String, Object> paramMap);

    List<SelectOptionDto> getProdId2(Map<String, Object> paramMap);

    List<RepProdIndexGzDto> selectInfo(Map<String, Object> param);

    int insert(RepProdIndexGzDto record);
    int insert2(RepProdIndexGzDto record);
    int update(RepProdIndexGzDto record);
    int update2(RepProdIndexGzDto record);
    int delete(RepProdIndexGzDto record);
    int selectExits(RepProdIndexGzDto record);
    int delete2(RepProdIndexGzDto record);

    RepProdIndexGzDto selectProdInfo(@Param("prodId") String prodId);

    RepProdIndexGzDto selectByProdIDAndValueDate(RepProdIndexGzDto record);

    RepProdIndexGzDto selectPProdInfo(RepProdIndexGzDto record);

    RepProdIndexGzDto selectPProd(RepProdIndexGzDto record);

    RepProdIndexGzDto selectData(Map<String, Object> param);

    String selectProdId(@Param("prodId") String prodId);

    List<String> selectpProdId(@Param("pProdId") String pProdId);

    RepProdIndexGzDto checkExistData(RepProdIndexGzDto record);

    List<RepProdIndexGzDto> selectProdIndexList(@Param("list") List<RepProdIndexGzDto> list);
}
