package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdFileFeeInfoDto;
import com.joyintech.tams.common.base.entity.RepProdFileFeeInfoDtoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdFileFeeInfoDtoMapper {
    int deleteByPrimaryKey(RepProdFileFeeInfoDtoKey key);

    int insert(RepProdFileFeeInfoDto record);

    int insertSelective(RepProdFileFeeInfoDto record);

    RepProdFileFeeInfoDto selectByPrimaryKey(RepProdFileFeeInfoDtoKey key);

    int updateByPrimaryKeySelective(RepProdFileFeeInfoDto record);

    int updateByPrimaryKey(RepProdFileFeeInfoDto record);
}