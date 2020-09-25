package com.joyintech.tams.disclosure.busicommon.common.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.busicommon.common.dto.BokBookset;

@Repository
public interface BokBookset001Mapper {
    int deleteByPrimaryKey(String booksetId);

    int insert(BokBookset record);

    int insertSelective(BokBookset record);

    int insertBasicByBasicInfo(BokBookset record);

    BokBookset selectByPrimaryKey(String booksetId);

    int updateByPrimaryKeySelective(BokBookset record);

    int updateByPrimaryKey(BokBookset record);
}