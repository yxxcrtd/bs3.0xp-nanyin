package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdFeeDealMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ChbPrdFeeDeal record);

    int insertSelective(ChbPrdFeeDeal record);

    ChbPrdFeeDeal selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbPrdFeeDeal record);

    int updateByPrimaryKey(ChbPrdFeeDeal record);
}