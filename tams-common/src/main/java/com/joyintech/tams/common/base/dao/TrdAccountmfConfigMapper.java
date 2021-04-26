package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdAccountmfConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdAccountmfConfigMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdAccountmfConfig record);

    int insertSelective(TrdAccountmfConfig record);

    int insertBasicByBasicInfo(TrdAccountmfConfig record);

    TrdAccountmfConfig selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdAccountmfConfig record);

    int updateByPrimaryKey(TrdAccountmfConfig record);
}