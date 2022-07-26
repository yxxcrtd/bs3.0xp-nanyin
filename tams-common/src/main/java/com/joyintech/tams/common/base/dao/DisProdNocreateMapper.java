package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdNocreate;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdNocreateMapper {
    int deleteByPrimaryKey(String prodRegistNo);

    int insert(DisProdNocreate record);

    int insertSelective(DisProdNocreate record);

    int insertBasicByBasicInfo(DisProdNocreate record);

    DisProdNocreate selectByPrimaryKey(String prodRegistNo);

    int updateByPrimaryKeySelective(DisProdNocreate record);

    int updateByPrimaryKey(DisProdNocreate record);
}