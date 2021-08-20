package com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.AssetInfoDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.AssetSideDetialRepoDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;

public interface ModAssetGradeSpecialConfigDtoMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModAssetGradeSpecialConfigDto record);

    int insertSelective(ModAssetGradeSpecialConfigDto record);

    ModAssetGradeSpecialConfigDto selectByPrimaryKey(String seqno);
    
    List<ModAssetGradeSpecialConfigDto> selectForPage(Map<String, Object> parm);

    int updateByPrimaryKeySelective(ModAssetGradeSpecialConfigDto record);

    int updateByPrimaryKey(ModAssetGradeSpecialConfigDto record);
    int checkIfHashData(String finprodId);
    List<SelectOptionDto> getFinprodIdForImpairment(Map<String, Object> param);

    List<AssetInfoDto> selectAssetForImpairment(AssetInfoDto assetInfoDto);

    /**
     * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
     * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepAssetCode(Map<String, Object> map);

    List<AssetSideDetialRepoDto> queryRegistList(Map<String, Object> map);

    List<AssetSideDetialRepoDto> queryDeadLineTermList(Map<String, Object> map);

    List<AssetSideDetialRepoDto> queryRegistListChl(Map<String, Object> map);

    List<AssetSideDetialRepoDto> queryRegistListValue(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryDurationList(Map<String, Object> map);

    List<AssetSideDetialRepoDto> queryMyieldList(Map<String, Object> map);
    AssetSideDetialRepoDto selectAllShareAmtAndDate(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryChlMainBody(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryChbBotAstPosList(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryChlPbcScale(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryChlValue(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryChlMduration(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryDeadLineList(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryYieldList(Map<String, Object> map);
    List<AssetSideDetialRepoDto> queryGyieldList(Map<String, Object> map);

    String getSecTypeByFinprodId(Map<String, Object> map);

}