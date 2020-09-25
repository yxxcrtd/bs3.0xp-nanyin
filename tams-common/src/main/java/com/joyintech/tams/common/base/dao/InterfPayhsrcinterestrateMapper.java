package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfPayhsrcinterestrate;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfPayhsrcinterestrateMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfPayhsrcinterestrate record);

    int insertSelective(InterfPayhsrcinterestrate record);

    int insertBasicByBasicInfo(InterfPayhsrcinterestrate record);

    InterfPayhsrcinterestrate selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfPayhsrcinterestrate record);

    int updateByPrimaryKey(InterfPayhsrcinterestrate record);
}