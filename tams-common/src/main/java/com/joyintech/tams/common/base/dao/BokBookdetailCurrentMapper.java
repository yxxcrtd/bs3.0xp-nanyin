package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBookdetailCurrentMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(BokBookdetailCurrent record);

    int insertSelective(BokBookdetailCurrent record);

    int insertBasicByBasicInfo(BokBookdetailCurrent record);

    BokBookdetailCurrent selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(BokBookdetailCurrent record);

    int updateByPrimaryKey(BokBookdetailCurrent record);
}