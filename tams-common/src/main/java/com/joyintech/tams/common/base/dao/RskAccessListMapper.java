package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskAccessList;
import org.springframework.stereotype.Repository;

@Repository
public interface RskAccessListMapper {
    int deleteByPrimaryKey(String listId);

    int insert(RskAccessList record);

    int insertSelective(RskAccessList record);

    int insertBasicByBasicInfo(RskAccessList record);

    RskAccessList selectByPrimaryKey(String listId);

    int updateByPrimaryKeySelective(RskAccessList record);

    int updateByPrimaryKey(RskAccessList record);
}