package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdFeeConfigDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdFeeConfigDetailMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdFeeConfigDetail record);

    int insertSelective(TrdFeeConfigDetail record);

    int insertBasicByBasicInfo(TrdFeeConfigDetail record);

    TrdFeeConfigDetail selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdFeeConfigDetail record);

    int updateByPrimaryKey(TrdFeeConfigDetail record);
}