package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepProdFileProdInfoDto;

@Repository
public interface RepProdFileProdInfoDtoMapper {
    int deleteByPrimaryKey(String produuid);

    int insert(RepProdFileProdInfoDto record);

    int insertSelective(RepProdFileProdInfoDto record);

    RepProdFileProdInfoDto selectByPrimaryKey(String produuid);

    int updateByPrimaryKeySelective(RepProdFileProdInfoDto record);

    int updateByPrimaryKey(RepProdFileProdInfoDto record);
}