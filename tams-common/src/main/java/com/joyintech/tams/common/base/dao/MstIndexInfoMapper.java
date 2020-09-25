package com.joyintech.tams.common.base.dao;


import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstIndexInfo;

@Repository
public interface MstIndexInfoMapper {
    int deleteByPrimaryKey(String indexId);

    int insert(MstIndexInfo record);

    int insertSelective(MstIndexInfo record);

    int insertBasicByBasicInfo(MstIndexInfo record);

    MstIndexInfo selectByPrimaryKey(String indexId);

    int updateByPrimaryKeySelective(MstIndexInfo record);

    int updateByPrimaryKey(MstIndexInfo record);
}