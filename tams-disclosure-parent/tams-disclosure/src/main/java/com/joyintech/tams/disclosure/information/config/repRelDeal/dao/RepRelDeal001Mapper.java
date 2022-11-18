package com.joyintech.tams.disclosure.information.config.repRelDeal.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.repRelDeal.dto.RepRelDealDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RepRelDeal001Mapper {

    List<SelectOptionDto> getProdInsId(Map<String, Object> paramMap);

    List<RepRelDealDto> selectInfo(Map<String, Object> param);

    RepRelDealDto selectData(Map<String, Object> param);

    int insert(RepRelDealDto record);

    int update(RepRelDealDto record);

    int delete(RepRelDealDto record);

    List<RepRelDealDto> selectExist(RepRelDealDto record);

    String selectPProdId(@Param("prodId") String prodId);

    List<RepRelDealDto> selectExistInfo(RepRelDealDto record);

    RepRelDealDto selectData2(Map<String, Object> param);

    int importInfo(RepRelDealDto record);

    List<RepRelDealDto> selectRelDealList(@Param("list") List<RepRelDealDto> list);

    RepRelDealDto selectRelDealdto(RepRelDealDto dto);
}