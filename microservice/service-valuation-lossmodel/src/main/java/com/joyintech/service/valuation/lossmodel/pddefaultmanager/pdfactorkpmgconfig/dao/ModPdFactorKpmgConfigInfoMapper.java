package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.ModPdFactorKpmgConfig;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.ModPdFactorKpmgConfigInfoDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.ModPdFactorKpmgConfigKey;
import org.springframework.stereotype.Repository;


@Repository 
public interface ModPdFactorKpmgConfigInfoMapper {
	
	 /**
     * 根据条件查找数据
     * @param param 查询条件
     * @return 
     */
    List<ModPdFactorKpmgConfigInfoDto> selectForPage(Map<String, Object> param);
	
    List<ModPdFactorKpmgConfigInfoDto> selectByYear(Map<String, Object> map);
    
    ModPdFactorKpmgConfigInfoDto selectByPrimaryKey(Map<String, Object> map);

    int checkBySeqNo(Map<String, Object> param);

    int insertSelective(ModPdFactorKpmgConfig record);

    int updateByPrimaryKey(ModPdFactorKpmgConfig record);

    int deleteByPrimaryKey(ModPdFactorKpmgConfigKey key);
	
}
