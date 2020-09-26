package com.joyintech.tams.svreport.interf.dis.assetinfo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.assetinfo.dto.DisRepAssetInfo;

@Repository
public interface DisRepAssetInfoMapper {
    int insert(DisRepAssetInfo record);

    int insertSelective(DisRepAssetInfo record);

    int insertBasicByBasicInfo(DisRepAssetInfo record);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int mergeRepAssetInfo(List<DisRepAssetInfo> dtoList);
}