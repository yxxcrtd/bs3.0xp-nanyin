package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.EtrEntrustWwDetail;
import com.joyintech.tams.common.base.entity.EtrEntrustWwDetailKey;

@Repository
public interface EtrEntrustWwDetailMapper {
    int deleteByPrimaryKey(String entrustId);

    int insert(EtrEntrustWwDetail record);

    int insertSelective(EtrEntrustWwDetail record);

    int insertBasicByBasicInfo(EtrEntrustWwDetail record);

    EtrEntrustWwDetail selectByPrimaryKey(EtrEntrustWwDetailKey key);

    int updateByPrimaryKeySelective(EtrEntrustWwDetail record);

    int updateByPrimaryKey(EtrEntrustWwDetail record);
}