package com.joyintech.service.valuation.lossmodel.phaseparameter.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.phaseparameter.dto.ModStageParaConfigInfoDto;
import org.springframework.stereotype.Repository;


@Repository 
public interface ModStageParaConfigInfoMapper {
	
	 /**
     * 根据条件查找数据
     * @param param 查询条件
     * @return 
     */
    List<ModStageParaConfigInfoDto> selectForPage(Map<String, Object> param);
    
    ModStageParaConfigInfoDto selectByPrimaryKey(Map<String, Object> map);
    
    ModStageParaConfigInfoDto selectByStageAndElements(Map<String, Object> map);
    
    int updateByPrimaryKey(ModStageParaConfigInfoDto record);
}
