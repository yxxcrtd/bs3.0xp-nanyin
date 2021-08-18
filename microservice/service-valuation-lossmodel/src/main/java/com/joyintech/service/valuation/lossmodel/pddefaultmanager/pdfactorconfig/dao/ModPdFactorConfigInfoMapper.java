package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorConfig;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorConfigInfoDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorConfigKey;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorWeightConfigDto;
import org.springframework.stereotype.Repository;


@Repository 
public interface ModPdFactorConfigInfoMapper{
	
	 /**
     * 根据条件查找数据
     * @param param 查询条件
     * @return 
     */
    List<ModPdFactorConfigInfoDto> selectForPage(Map<String, Object> param);
	
    List<ModPdFactorConfigInfoDto> selectByYearAndQuarter(Map<String, Object> map);
    
    ModPdFactorConfigInfoDto selectByPrimaryKey(Map<String, Object> map);
	
    ModPdFactorWeightConfigDto selectLastWeightInfo(Map<String, Object> map);

    int deletePdWeight();

    int insertWeightSelective(ModPdFactorWeightConfigDto record);
    
    int checkBySeqNo(Map<String, Object> param);

    int insertSelective(ModPdFactorConfig record);

    int updateByPrimaryKey(ModPdFactorConfig record);

    int deleteByPrimaryKey(ModPdFactorConfigKey key);
	
}
