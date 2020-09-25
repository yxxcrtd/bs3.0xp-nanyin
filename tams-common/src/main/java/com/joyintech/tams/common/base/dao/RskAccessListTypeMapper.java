package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskAccessListType;
import org.springframework.stereotype.Repository;

@Repository
public interface RskAccessListTypeMapper {
    int insert(RskAccessListType record);

    int insertSelective(RskAccessListType record);

    int insertBasicByBasicInfo(RskAccessListType record);
}