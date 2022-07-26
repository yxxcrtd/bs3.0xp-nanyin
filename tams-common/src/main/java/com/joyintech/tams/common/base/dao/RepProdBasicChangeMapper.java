package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdBasicChangeMapper {
    int deleteByPrimaryKey(String basicChangeId);

    int insert(RepProdBasicChange record);

    int insertSelective(RepProdBasicChange record);

    int insertBasicByBasicInfo(RepProdBasicChange record);

    RepProdBasicChange selectByPrimaryKey(String basicChangeId);

    int updateByPrimaryKeySelective(RepProdBasicChange record);

    int updateByPrimaryKey(RepProdBasicChange record);
}