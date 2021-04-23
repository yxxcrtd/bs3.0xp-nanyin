package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValTableScoe;
import com.joyintech.tams.common.base.entity.ValTableScoeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValTableScoeMapper {
    int deleteByPrimaryKey(ValTableScoeKey key);

    int insert(ValTableScoe record);

    int insertSelective(ValTableScoe record);

    int insertBasicByBasicInfo(ValTableScoe record);

    ValTableScoe selectByPrimaryKey(ValTableScoeKey key);

    int updateByPrimaryKeySelective(ValTableScoe record);

    int updateByPrimaryKey(ValTableScoe record);
}