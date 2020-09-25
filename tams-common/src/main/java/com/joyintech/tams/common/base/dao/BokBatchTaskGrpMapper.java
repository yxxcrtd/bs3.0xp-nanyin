package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBatchTaskGrp;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBatchTaskGrpMapper {
    int deleteByPrimaryKey(String taskGrpId);

    int insert(BokBatchTaskGrp record);

    int insertSelective(BokBatchTaskGrp record);

    int insertBasicByBasicInfo(BokBatchTaskGrp record);

    BokBatchTaskGrp selectByPrimaryKey(String taskGrpId);

    int updateByPrimaryKeySelective(BokBatchTaskGrp record);

    int updateByPrimaryKey(BokBatchTaskGrp record);
}