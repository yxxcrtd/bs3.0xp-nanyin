package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBookdetailAll;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBookdetailAllMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(BokBookdetailAll record);

    int insertSelective(BokBookdetailAll record);

    int insertBasicByBasicInfo(BokBookdetailAll record);

    BokBookdetailAll selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(BokBookdetailAll record);

    int updateByPrimaryKey(BokBookdetailAll record);
}