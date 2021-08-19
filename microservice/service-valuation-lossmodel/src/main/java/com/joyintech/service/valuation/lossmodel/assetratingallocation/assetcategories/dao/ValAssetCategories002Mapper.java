
package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dao;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto.ValAssetCategories002Dto;
import com.joyintech.tams.valuation.common.dto.modelDto.AssetCategories;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huangjian
 * @version 1.0 2018年5月10日
 * @see ValAssetCategories002Mapper
 * @since 1.0
 */
@Repository(value="serviceValAssetCategories002Mapper")
public interface ValAssetCategories002Mapper {
    /**
     * 通过流水号查询资产大类评级配置信息
     * @param seqNo 流水号
     * @return ValAssetCategories002Dto
     * @see
     * @since 1.0
     */
    ValAssetCategories002Dto getAssetCategoriesBySeqNo(@Param("seqNo") String seqNo);

    /**
     * 通过资产类型和资产分类查询
     * @param param 账套代码
     * @return ValAssetCategories002Dto
     * @see
     * @since 1.0
     */
    AssetCategories getAssetCategoriesSetByTypes(Map<String, Object> param);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(AssetCategories record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(AssetCategories record);

    int deleteByPrimaryKey(String seqno);

    int checkAssetData(ValAssetCategories002Dto dto);
}
