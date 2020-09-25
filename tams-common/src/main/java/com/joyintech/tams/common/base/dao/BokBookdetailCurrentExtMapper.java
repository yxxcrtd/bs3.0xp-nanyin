package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrentExt;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBookdetailCurrentExtMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(BokBookdetailCurrentExt record);

    int insertSelective(BokBookdetailCurrentExt record);

    int insertBasicByBasicInfo(BokBookdetailCurrentExt record);

    BokBookdetailCurrentExt selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(BokBookdetailCurrentExt record);

    int updateByPrimaryKey(BokBookdetailCurrentExt record);
}