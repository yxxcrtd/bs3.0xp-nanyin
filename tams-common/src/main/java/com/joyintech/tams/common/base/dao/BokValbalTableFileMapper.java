package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValbalTableFile;
import com.joyintech.tams.common.base.entity.BokValbalTableFileKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValbalTableFileMapper {
    int deleteByPrimaryKey(BokValbalTableFileKey key);

    int insert(BokValbalTableFile record);

    int insertSelective(BokValbalTableFile record);

    int insertBasicByBasicInfo(BokValbalTableFile record);

    BokValbalTableFile selectByPrimaryKey(BokValbalTableFileKey key);

    int updateByPrimaryKeySelective(BokValbalTableFile record);

    int updateByPrimaryKey(BokValbalTableFile record);
}