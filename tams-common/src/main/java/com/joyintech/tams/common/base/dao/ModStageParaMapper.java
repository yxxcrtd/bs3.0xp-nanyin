package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModStagePara;
import org.springframework.stereotype.Repository;

@Repository
public interface ModStageParaMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModStagePara record);

    int insertSelective(ModStagePara record);

    int insertBasicByBasicInfo(ModStagePara record);

    ModStagePara selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ModStagePara record);

    int updateByPrimaryKey(ModStagePara record);
}