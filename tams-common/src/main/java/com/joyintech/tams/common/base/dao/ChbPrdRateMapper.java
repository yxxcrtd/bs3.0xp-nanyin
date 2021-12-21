package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdRate;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdRateMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ChbPrdRate record);

    int insertSelective(ChbPrdRate record);

    int insertBasicByBasicInfo(ChbPrdRate record);

    ChbPrdRate selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbPrdRate record);

    int updateByPrimaryKey(ChbPrdRate record);
}