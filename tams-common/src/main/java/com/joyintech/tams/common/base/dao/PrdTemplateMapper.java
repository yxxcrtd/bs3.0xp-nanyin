package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdTemplate;
import com.joyintech.tams.common.base.entity.PrdTemplateKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdTemplateMapper {
    int deleteByPrimaryKey(PrdTemplateKey key);

    int insert(PrdTemplate record);

    int insertSelective(PrdTemplate record);

    int insertBasicByBasicInfo(PrdTemplate record);

    PrdTemplate selectByPrimaryKey(PrdTemplateKey key);

    int updateByPrimaryKeySelective(PrdTemplate record);

    int updateByPrimaryKeyWithBLOBs(PrdTemplate record);

    int updateByPrimaryKey(PrdTemplate record);
}