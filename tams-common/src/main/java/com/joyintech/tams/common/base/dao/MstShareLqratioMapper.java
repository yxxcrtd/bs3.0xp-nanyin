package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstShareLqratio;
import com.joyintech.tams.common.base.entity.MstShareLqratioKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstShareLqratioMapper {
    int deleteByPrimaryKey(MstShareLqratioKey key);

    int insert(MstShareLqratio record);

    int insertSelective(MstShareLqratio record);

    int insertBasicByBasicInfo(MstShareLqratio record);

    MstShareLqratio selectByPrimaryKey(MstShareLqratioKey key);

    int updateByPrimaryKeySelective(MstShareLqratio record);

    int updateByPrimaryKey(MstShareLqratio record);
}