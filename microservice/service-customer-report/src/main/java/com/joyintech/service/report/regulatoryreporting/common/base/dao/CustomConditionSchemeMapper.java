package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.CustomConditionScheme;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomConditionSchemeMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CustomConditionScheme record);

    int insertSelective(CustomConditionScheme record);

    int insertBasicByBasicInfo(CustomConditionScheme record);

    CustomConditionScheme selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CustomConditionScheme record);

    int updateByPrimaryKey(CustomConditionScheme record);
}