package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrustOperHis;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustOperHisMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(EtrEntrustOperHis record);

    int insertSelective(EtrEntrustOperHis record);

    int insertHisByBasicInfo(EtrEntrustOperHis record);

    EtrEntrustOperHis selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(EtrEntrustOperHis record);

    int updateByPrimaryKey(EtrEntrustOperHis record);
}