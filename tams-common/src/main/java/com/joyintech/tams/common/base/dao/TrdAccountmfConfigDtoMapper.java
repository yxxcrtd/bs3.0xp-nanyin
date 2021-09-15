package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdAccountmfConfigDto;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdAccountmfConfigDtoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdAccountmfConfigDto record);

    int insertSelective(TrdAccountmfConfigDto record);

    TrdAccountmfConfigDto selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdAccountmfConfigDto record);

    int updateByPrimaryKey(TrdAccountmfConfigDto record);
}