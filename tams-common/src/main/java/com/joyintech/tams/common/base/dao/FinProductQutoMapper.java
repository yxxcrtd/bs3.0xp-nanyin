package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductQuto;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductQutoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(FinProductQuto record);

    int insertSelective(FinProductQuto record);

    int insertBasicByBasicInfo(FinProductQuto record);

    FinProductQuto selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(FinProductQuto record);

    int updateByPrimaryKey(FinProductQuto record);
}