package com.joyintech.service.valuation.lossmodel.pddefaultmanager.ckgradepdconfig.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import org.springframework.stereotype.Repository;


/**
 * 评级PD表查询Mapper
 * 
 * @author yuzj
 * @version 1.0 2021年6月23日
 * @see ModCkGradePdConfigMapper
 * @since 1.0
 */
@Repository(value="serviceCkModGradePdConfigMapper")
public interface ModCkGradePdConfigMapper {

    List<ModGradePdConfigDto> selectAll(Map<String, Object> pageParam);
    List<ModGradePdConfigDto> selectSum(Map<String, Object> pageParam);

    ModGradePdConfigDto selectByPrimaryKey(ModGradePdConfigDto key);

    int deleteByPrimaryKey(String seqNo);

    int insert(ModGradePdConfigDto dto);

    int  updateByPrimaryKey(ModGradePdConfigDto dto);

    List<ModGradePdConfigDto> selectExportList(ModGradePdConfigDto dto);

    List<ModGradePdConfigDto> selectByMap(Map<String, Object> pageParam);

    int getRepeatCheckList(ModGradePdConfigDto dto);


    int updateRepProdIssue(RepProdIssue dto);


    List<ModGradePdConfigDto> selectRepetList(ModGradePdConfigDto dto);

    void updateRepetDto(ModGradePdConfigDto dto);

    int checkBySeqNoAndType(Map<String, Object> map);


}
