package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdDeclare;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdDeclareMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepProdDeclare record);

    int insertSelective(RepProdDeclare record);

    int insertBasicByBasicInfo(RepProdDeclare record);

    RepProdDeclare selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepProdDeclare record);

    int updateByRepFinprodInsId(RepProdDeclare record);

    int updateByPrimaryKey(RepProdDeclare record);
}