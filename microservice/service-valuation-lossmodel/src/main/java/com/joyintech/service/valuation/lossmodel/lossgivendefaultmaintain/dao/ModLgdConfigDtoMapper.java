package com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.dto.ModLgdConfigDto;

public interface ModLgdConfigDtoMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModLgdConfigDto record);

    int insertSelective(ModLgdConfigDto record);

    ModLgdConfigDto selectByPrimaryKey(String seqno);
    
    int checkIfUsed(String seqno);

    int updateByPrimaryKeySelective(ModLgdConfigDto record);

    int updateByPrimaryKey(ModLgdConfigDto record);
    List<ModLgdConfigDto> selectForPage(Map<String, Object> parm);
}