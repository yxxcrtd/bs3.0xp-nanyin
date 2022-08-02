package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisSaleAgency;
import org.springframework.stereotype.Repository;

@Repository
public interface DisSaleAgencyMapper {
    int deleteByPrimaryKey(String sequenceNo);

    int insert(DisSaleAgency record);

    int insertSelective(DisSaleAgency record);

    int insertBasicByBasicInfo(DisSaleAgency record);

    DisSaleAgency selectByPrimaryKey(String sequenceNo);

    int updateByPrimaryKeySelective(DisSaleAgency record);

    int updateByPrimaryKey(DisSaleAgency record);
}