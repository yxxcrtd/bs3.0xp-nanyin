package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinStdsecConverseRadio;
import com.joyintech.tams.common.base.entity.FinStdsecConverseRadioKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinStdsecConverseRadioMapper {
    int deleteByPrimaryKey(FinStdsecConverseRadioKey key);

    int insert(FinStdsecConverseRadio record);

    int insertSelective(FinStdsecConverseRadio record);

    int insertBasicByBasicInfo(FinStdsecConverseRadio record);

    FinStdsecConverseRadio selectByPrimaryKey(FinStdsecConverseRadioKey key);

    int updateByPrimaryKeySelective(FinStdsecConverseRadio record);

    int updateByPrimaryKey(FinStdsecConverseRadio record);
}