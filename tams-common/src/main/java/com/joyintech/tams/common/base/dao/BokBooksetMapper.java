package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBookset;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBooksetMapper {
    int deleteByPrimaryKey(String booksetId);

    int insert(BokBookset record);

    int insertSelective(BokBookset record);

    int insertBasicByBasicInfo(BokBookset record);

    BokBookset selectByPrimaryKey(String booksetId);

    int updateByPrimaryKeySelective(BokBookset record);

    int updateByPrimaryKey(BokBookset record);
}