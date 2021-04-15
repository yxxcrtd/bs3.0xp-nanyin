package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductOthCode;
import com.joyintech.tams.common.base.entity.FinProductOthCodeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductOthCodeMapper {
    int deleteByPrimaryKey(FinProductOthCodeKey key);

    int insert(FinProductOthCode record);

    int insertSelective(FinProductOthCode record);

    int insertBasicByBasicInfo(FinProductOthCode record);

    FinProductOthCode selectByPrimaryKey(FinProductOthCodeKey key);

    int updateByPrimaryKeySelective(FinProductOthCode record);

    int updateByPrimaryKey(FinProductOthCode record);
}