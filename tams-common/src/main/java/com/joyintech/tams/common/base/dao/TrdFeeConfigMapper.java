package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdFeeConfig;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface TrdFeeConfigMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdFeeConfig record);

    int insertSelective(TrdFeeConfig record);

    int insertBasicByBasicInfo(TrdFeeConfig record);

    TrdFeeConfig selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdFeeConfig record);

    int updateByPrimaryKey(TrdFeeConfig record);
    
	List<String> findWindtypetwo(String windtypeone);
}