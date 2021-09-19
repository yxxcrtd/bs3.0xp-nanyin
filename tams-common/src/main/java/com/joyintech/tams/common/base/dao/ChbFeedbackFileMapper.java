package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbFeedbackFile;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbFeedbackFileMapper {
    int deleteByPrimaryKey(String rtnRepMesNo);

    int insert(ChbFeedbackFile record);

    int insertSelective(ChbFeedbackFile record);

    ChbFeedbackFile selectByPrimaryKey(String rtnRepMesNo);

    int updateByPrimaryKeySelective(ChbFeedbackFile record);

    int updateByPrimaryKeyWithBLOBs(ChbFeedbackFile record);

    int updateByPrimaryKey(ChbFeedbackFile record);
}