package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinReduceShareInfo;
import com.joyintech.tams.common.base.entity.FinReduceShareInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinReduceShareInfoMapper {
    int deleteByPrimaryKey(FinReduceShareInfoKey key);

    int insert(FinReduceShareInfo record);

    int insertSelective(FinReduceShareInfo record);

    int insertBasicByBasicInfo(FinReduceShareInfo record);

    FinReduceShareInfo selectByPrimaryKey(FinReduceShareInfoKey key);

    int updateByPrimaryKeySelective(FinReduceShareInfo record);

    int updateByPrimaryKey(FinReduceShareInfo record);
}