package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokFileInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BokFileInfoMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(BokFileInfo record);

    int insertSelective(BokFileInfo record);

    int insertBasicByBasicInfo(BokFileInfo record);

    BokFileInfo selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(BokFileInfo record);

    int updateByPrimaryKey(BokFileInfo record);
}