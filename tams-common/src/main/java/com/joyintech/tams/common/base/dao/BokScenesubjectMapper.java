package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokScenesubject;
import com.joyintech.tams.common.base.entity.BokScenesubjectKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokScenesubjectMapper {
    int deleteByPrimaryKey(BokScenesubjectKey key);

    int insert(BokScenesubject record);

    int insertSelective(BokScenesubject record);

    int insertBasicByBasicInfo(BokScenesubject record);

    BokScenesubject selectByPrimaryKey(BokScenesubjectKey key);

    int updateByPrimaryKeySelective(BokScenesubject record);

    int updateByPrimaryKey(BokScenesubject record);
}