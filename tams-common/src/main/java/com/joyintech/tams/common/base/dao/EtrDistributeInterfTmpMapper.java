package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrDistributeInterfTmp;
import com.joyintech.tams.common.base.entity.EtrDistributeInterfTmpKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrDistributeInterfTmpMapper {
    int deleteByPrimaryKey(EtrDistributeInterfTmpKey key);

    int insert(EtrDistributeInterfTmp record);

    int insertSelective(EtrDistributeInterfTmp record);

    int insertBasicByBasicInfo(EtrDistributeInterfTmp record);

    EtrDistributeInterfTmp selectByPrimaryKey(EtrDistributeInterfTmpKey key);

    int updateByPrimaryKeySelective(EtrDistributeInterfTmp record);

    int updateByPrimaryKey(EtrDistributeInterfTmp record);
}