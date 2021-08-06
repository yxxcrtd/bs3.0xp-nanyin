package com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement001Dto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.service.valuation.lossmodel.batchtask.dto.BatchAssetInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 * @see AssetImpairmentManagement001Mapper
 * @since 1.0
 */
@Repository(value="serviceAssetImpairmentManagement001Mapper")
public interface AssetImpairmentManagement001Mapper {
    /**
     * 查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     */
    List<AssetImpairmentManagement001Dto> queryList(Map<String, Object> params);

    /**
     * 查询页面业务编号
     *
     * @return 查询页面业务编号
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBusiNum(Map<String, Object> params);

    /**
     * 主键查询
     *
     * @return 主键查询
     * @see
     * @since 1.0
     */
    AssetImpairmentManagement002Dto findByPrimaryKey(Map<String, Object> params);

    /**
     * 查询数据是否存在
     *
     * @return 查询数据是否存在
     * @see
     * @since 1.0
     */
    int selectDuplicateData(AssetImpairmentManagement002Dto dto);

    List<SelectOptionDto> getFinprodIdForImpairment(Map<String, Object> param);

    List<SelectOptionDto> getProdIdForImpairment(Map<String, Object> param);

    List<SelectOptionDto> getBusiNoForImpairment(Map<String, Object> param);


    List<BatchAssetInfoDto> getAssetInfo(Map<String, Object> param);

    List<BatchAssetInfoDto> getAssetInfoImpairment(Map<String, Object> param);
}
