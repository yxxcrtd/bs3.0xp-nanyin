package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAstValueSub;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAstValueSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbAstValueSub record);

    int insertSelective(ChbAstValueSub record);

    ChbAstValueSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbAstValueSub record);

    int updateByPrimaryKey(ChbAstValueSub record);
}