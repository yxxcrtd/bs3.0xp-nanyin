package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlSecReconDetail;
import com.joyintech.tams.common.base.entity.StlSecReconDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlSecReconDetailMapper {
    int deleteByPrimaryKey(StlSecReconDetailKey key);

    int insert(StlSecReconDetail record);

    int insertSelective(StlSecReconDetail record);

    int insertBasicByBasicInfo(StlSecReconDetail record);

    StlSecReconDetail selectByPrimaryKey(StlSecReconDetailKey key);

    int updateByPrimaryKeySelective(StlSecReconDetail record);

    int updateByPrimaryKey(StlSecReconDetail record);
}