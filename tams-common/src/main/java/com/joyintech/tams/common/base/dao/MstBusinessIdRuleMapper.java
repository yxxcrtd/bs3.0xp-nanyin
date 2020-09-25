package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstBusinessIdRule;
import org.springframework.stereotype.Repository;

@Repository
public interface MstBusinessIdRuleMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(MstBusinessIdRule record);

    int insertSelective(MstBusinessIdRule record);

    int insertBasicByBasicInfo(MstBusinessIdRule record);

    MstBusinessIdRule selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(MstBusinessIdRule record);

    int updateByPrimaryKey(MstBusinessIdRule record);
}