package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlOrdersMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface StlOrdersMappingMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(StlOrdersMapping record);

    int insertSelective(StlOrdersMapping record);

    int insertBasicByBasicInfo(StlOrdersMapping record);

    StlOrdersMapping selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(StlOrdersMapping record);

    int updateByPrimaryKey(StlOrdersMapping record);
}