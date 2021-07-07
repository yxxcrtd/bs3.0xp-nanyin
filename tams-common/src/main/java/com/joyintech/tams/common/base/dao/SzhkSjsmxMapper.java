package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzhkSjsmx;
import org.springframework.stereotype.Repository;

@Repository
public interface SzhkSjsmxMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzhkSjsmx record);

    int insertSelective(SzhkSjsmx record);

    int insertBasicByBasicInfo(SzhkSjsmx record);

    SzhkSjsmx selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzhkSjsmx record);

    int updateByPrimaryKey(SzhkSjsmx record);
}