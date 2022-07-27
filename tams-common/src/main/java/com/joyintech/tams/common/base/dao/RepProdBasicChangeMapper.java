package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import com.joyintech.tams.common.base.entity.RepProdBasicChangeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdBasicChangeMapper {
    int deleteByPrimaryKey(RepProdBasicChangeKey key);

    int insert(RepProdBasicChange record);

    int insertSelective(RepProdBasicChange record);

    int insertBasicByBasicInfo(RepProdBasicChange record);

    RepProdBasicChange selectByPrimaryKey(RepProdBasicChangeKey key);

    int updateByPrimaryKeySelective(RepProdBasicChange record);

    int updateByPrimaryKey(RepProdBasicChange record);
}