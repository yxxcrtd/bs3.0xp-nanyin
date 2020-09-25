package com.joyintech.tams.disclosure.busicommon.common.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.common.dto.FinProduct;
import com.joyintech.tams.disclosure.busicommon.common.dto.FinProductKey;

@Repository
public interface FinProduct001Mapper {
    int deleteByPrimaryKey(FinProductKey key);

    int insert(FinProduct record);

    int insertSelective(FinProduct record);

    int insertBasicByBasicInfo(FinProduct record);

    FinProduct selectByPrimaryKey(FinProductKey key);

    int updateByPrimaryKeySelective(FinProduct record);

    int updateByPrimaryKey(FinProduct record);
}