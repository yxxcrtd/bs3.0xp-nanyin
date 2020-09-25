package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfStdsecConRadio;
import com.joyintech.tams.common.base.entity.InterfStdsecConRadioKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfStdsecConRadioMapper {
    int deleteByPrimaryKey(InterfStdsecConRadioKey key);

    int insert(InterfStdsecConRadio record);

    int insertSelective(InterfStdsecConRadio record);

    int insertBasicByBasicInfo(InterfStdsecConRadio record);

    InterfStdsecConRadio selectByPrimaryKey(InterfStdsecConRadioKey key);

    int updateByPrimaryKeySelective(InterfStdsecConRadio record);

    int updateByPrimaryKey(InterfStdsecConRadio record);
}