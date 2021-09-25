package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblStructMapDtl;
import com.joyintech.tams.common.base.entity.RskValtblStructMapDtlKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RskValtblStructMapDtlMapper {
    int deleteByPrimaryKey(RskValtblStructMapDtlKey key);

    int insert(RskValtblStructMapDtl record);

    int insertSelective(RskValtblStructMapDtl record);

    int insertBasicByBasicInfo(RskValtblStructMapDtl record);

    RskValtblStructMapDtl selectByPrimaryKey(RskValtblStructMapDtlKey key);

    int updateByPrimaryKeySelective(RskValtblStructMapDtl record);

    int updateByPrimaryKey(RskValtblStructMapDtl record);

    void insertList(List<RskValtblStructMapDtl> list);

}