package com.joyintech.tams.common.base.dao;

import java.util.List;

import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;

public interface ChbTypeMappingExMapper {
    int deleteByPrimaryKey(String mappingid);

    int insert(ChbTypeMappingEx record);

    int insertSelective(ChbTypeMappingEx record);

    ChbTypeMappingEx selectByPrimaryKey(String mappingid);

    int updateByPrimaryKeySelective(ChbTypeMappingEx record);

    int updateByPrimaryKey(ChbTypeMappingEx record);
    
    List<ChbTypeMappingEx> select();
    
    List<ChbTypeMappingEx> selectByMappingcode(String mappingcode);
}