package com.joyintech.tams.disclosure.information.config.bonuscalist.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dto.BonusCalist001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BonusCalist001Mapper {
    List<BonusCalist001Dto> select(Map<String, Object> param);

    int deleteByKey(String bonusListId);

    int insert(BonusCalist001Dto bonusCalist001Dto);

    int updateByKey(BonusCalist001Dto dto);

    List<BonusCalist001Dto> selectBonusCaList(@Param("list") List<BonusCalist001Dto> list);

    int updateByBusKey(BonusCalist001Dto item);

    BonusCalist001Dto selectByBusPK(Map map);

    BonusCalist001Dto selectByPrimaryKey(String bonusListId);

    List<SelectOptionDto> getProdId(Map<String, Object> map);

    int updateStatus(BonusCalist001Dto bonusCalist001Dto);

    int deleteByBusKey(BonusCalist001Dto bonusCalist001Dto);

    String findRe(String prodId);

    List<String> isRepeated(BonusCalist001Dto bonusCalist001Dto);
}
