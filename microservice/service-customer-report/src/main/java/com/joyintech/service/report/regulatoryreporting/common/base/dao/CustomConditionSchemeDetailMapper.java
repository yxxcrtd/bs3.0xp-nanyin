package com.joyintech.service.report.regulatoryreporting.common.base.dao;

import com.joyintech.service.report.regulatoryreporting.common.base.entity.CustomConditionSchemeDetail;
import com.joyintech.service.report.regulatoryreporting.common.base.entity.CustomConditionSchemeDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomConditionSchemeDetailMapper {
    int deleteByPrimaryKey(CustomConditionSchemeDetailKey key);

    int insert(CustomConditionSchemeDetail record);

    int insertSelective(CustomConditionSchemeDetail record);

    int insertBasicByBasicInfo(CustomConditionSchemeDetail record);

    CustomConditionSchemeDetail selectByPrimaryKey(CustomConditionSchemeDetailKey key);

    int updateByPrimaryKeySelective(CustomConditionSchemeDetail record);

    int updateByPrimaryKey(CustomConditionSchemeDetail record);
}