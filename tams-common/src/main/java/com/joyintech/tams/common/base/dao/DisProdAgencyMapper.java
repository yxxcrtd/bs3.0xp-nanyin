package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdAgency;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdAgencyMapper {
    int deleteByPrimaryKey(DisProdAgency key);

    int insert(DisProdAgency record);

    int insertSelective(DisProdAgency record);

    int insertBasicByBasicInfo(DisProdAgency record);

    DisProdAgency selectByPrimaryKey(DisProdAgency key);

    int updateByPrimaryKeySelective(DisProdAgency record);

    int updateByPrimaryKey(DisProdAgency record);
}