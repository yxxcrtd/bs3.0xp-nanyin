package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCmfundnav;
import com.joyintech.tams.common.base.entity.InterfCmfundnavKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCmfundnavMapper {
    int deleteByPrimaryKey(InterfCmfundnavKey key);

    int insert(InterfCmfundnav record);

    int insertSelective(InterfCmfundnav record);

    int insertBasicByBasicInfo(InterfCmfundnav record);

    InterfCmfundnav selectByPrimaryKey(InterfCmfundnavKey key);

    int updateByPrimaryKeySelective(InterfCmfundnav record);

    int updateByPrimaryKey(InterfCmfundnav record);
}