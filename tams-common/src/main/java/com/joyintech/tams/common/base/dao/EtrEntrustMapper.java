package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustMapper {
    int deleteByPrimaryKey(String entrustId);

    int insert(EtrEntrust record);

    int insertSelective(EtrEntrust record);

    int insertBasicByBasicInfo(EtrEntrust record);

    EtrEntrust selectByPrimaryKey(String entrustId);

    int updateByPrimaryKeySelective(EtrEntrust record);

    int updateByPrimaryKey(EtrEntrust record);

    /**
     * 撤回或退回再启动流程时转移至废弃备份表
     * @param entrustId
     * @return
     */
    int insertFromQuery(String entrustId);
}