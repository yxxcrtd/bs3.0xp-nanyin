package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdFileTemplat;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdFileTemplatMapper {
    int deleteByPrimaryKey(String revealFileId);

    int insert(PrdFileTemplat record);

    int insertSelective(PrdFileTemplat record);

    int insertBasicByBasicInfo(PrdFileTemplat record);

    PrdFileTemplat selectByPrimaryKey(String revealFileId);

    int updateByPrimaryKeySelective(PrdFileTemplat record);

    int updateByPrimaryKey(PrdFileTemplat record);
}