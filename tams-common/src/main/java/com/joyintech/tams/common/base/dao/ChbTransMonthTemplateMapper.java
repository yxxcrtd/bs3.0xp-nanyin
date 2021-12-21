package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbTransMonthTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbTransMonthTemplateMapper {
    int deleteByPrimaryKey(String tempuuid);

    int insert(ChbTransMonthTemplate record);

    int insertSelective(ChbTransMonthTemplate record);

    int insertBasicByBasicInfo(ChbTransMonthTemplate record);

    ChbTransMonthTemplate selectByPrimaryKey(String tempuuid);

    int updateByPrimaryKeySelective(ChbTransMonthTemplate record);

    int updateByPrimaryKey(ChbTransMonthTemplate record);
}