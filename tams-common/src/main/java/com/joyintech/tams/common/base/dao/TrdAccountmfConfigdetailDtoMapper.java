package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;

public interface TrdAccountmfConfigdetailDtoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdAccountmfConfigdetailDto record);

    int insertSelective(TrdAccountmfConfigdetailDto record);

    TrdAccountmfConfigdetailDto selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdAccountmfConfigdetailDto record);

    int updateByPrimaryKey(TrdAccountmfConfigdetailDto record);
}