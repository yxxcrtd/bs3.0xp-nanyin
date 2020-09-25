package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValInterfDtoMatch;
import com.joyintech.tams.common.base.entity.ValInterfDtoMatchKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValInterfDtoMatchMapper {
    int deleteByPrimaryKey(ValInterfDtoMatchKey key);

    int insert(ValInterfDtoMatch record);

    int insertSelective(ValInterfDtoMatch record);

    int insertBasicByBasicInfo(ValInterfDtoMatch record);

    ValInterfDtoMatch selectByPrimaryKey(ValInterfDtoMatchKey key);

    int updateByPrimaryKeySelective(ValInterfDtoMatch record);

    int updateByPrimaryKey(ValInterfDtoMatch record);
}