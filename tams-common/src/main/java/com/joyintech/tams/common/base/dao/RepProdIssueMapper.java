package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepProdIssue;
import org.springframework.stereotype.Repository;

@Repository
public interface RepProdIssueMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepProdIssue record);

    int insertSelective(RepProdIssue record);

    int insertBasicByBasicInfo(RepProdIssue record);

    RepProdIssue selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepProdIssue record);

    int updateByPrimaryKey(RepProdIssue record);
}