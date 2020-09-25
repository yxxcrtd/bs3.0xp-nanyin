package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisPrams;
import org.springframework.stereotype.Repository;

@Repository
public interface DisPramsMapper {
    int deleteByPrimaryKey(String disId);

    int insert(DisPrams record);

    int insertSelective(DisPrams record);

    int insertBasicByBasicInfo(DisPrams record);

    DisPrams selectByPrimaryKey(String disId);

    int updateByPrimaryKeySelective(DisPrams record);

    int updateByPrimaryKey(DisPrams record);
}