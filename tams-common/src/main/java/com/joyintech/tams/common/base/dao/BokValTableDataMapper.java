package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValTableData;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValTableDataMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(BokValTableData record);

    int insertSelective(BokValTableData record);

    int insertBasicByBasicInfo(BokValTableData record);

    BokValTableData selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(BokValTableData record);

    int updateByPrimaryKey(BokValTableData record);
}