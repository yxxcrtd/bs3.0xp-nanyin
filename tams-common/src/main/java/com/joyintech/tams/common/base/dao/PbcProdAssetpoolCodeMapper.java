package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcProdAssetpoolCode;
import com.joyintech.tams.common.base.entity.PbcProdAssetpoolCodeKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PbcProdAssetpoolCodeMapper {
    int deleteByPrimaryKey(PbcProdAssetpoolCodeKey key);

    int insert(PbcProdAssetpoolCode record);

    int insertSelective(PbcProdAssetpoolCode record);

    int insertBasicByBasicInfo(PbcProdAssetpoolCode record);

    PbcProdAssetpoolCode selectByPrimaryKey(PbcProdAssetpoolCodeKey key);

    int updateByPrimaryKeySelective(PbcProdAssetpoolCode record);

    int updateByPrimaryKey(PbcProdAssetpoolCode record);

    List<PbcProdAssetpoolCode> selectByInsideProdList(@Param("list") List<String> list,@Param("orgCode")String orgCode);

    int batchInsertPbcCode(List<PbcProdAssetpoolCode> list);
}