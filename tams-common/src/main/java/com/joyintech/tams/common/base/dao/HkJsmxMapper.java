package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.HkJsmx;
import org.springframework.stereotype.Repository;

@Repository
public interface HkJsmxMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(HkJsmx record);

    int insertSelective(HkJsmx record);

    int insertBasicByBasicInfo(HkJsmx record);

    HkJsmx selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(HkJsmx record);

    int updateByPrimaryKey(HkJsmx record);
}