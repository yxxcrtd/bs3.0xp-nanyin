package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstCounterParty;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MstCounterPartyMapper {
    int deleteByPrimaryKey(String counterId);

    int insert(MstCounterParty record);

    int insertSelective(MstCounterParty record);

    int insertBasicByBasicInfo(MstCounterParty record);

    MstCounterParty selectByPrimaryKey(String counterId);

    int updateByPrimaryKeySelective(MstCounterParty record);

    int updateByPrimaryKey(MstCounterParty record);
    
    MstCounterParty selectPType(@Param("counterId") String counterId);
}