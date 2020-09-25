package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIsupIndicatorPara;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIsupIndicatorParaMapper {
    int deleteByPrimaryKey(String paraCode);

    int insert(RskIsupIndicatorPara record);

    int insertSelective(RskIsupIndicatorPara record);

    int insertBasicByBasicInfo(RskIsupIndicatorPara record);

    RskIsupIndicatorPara selectByPrimaryKey(String paraCode);

    int updateByPrimaryKeySelective(RskIsupIndicatorPara record);

    int updateByPrimaryKey(RskIsupIndicatorPara record);
}