package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBalTableData;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBalTableDataMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(BokBalTableData record);

    int insertSelective(BokBalTableData record);

    int insertBasicByBasicInfo(BokBalTableData record);

    BokBalTableData selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(BokBalTableData record);

    int updateByPrimaryKey(BokBalTableData record);
}