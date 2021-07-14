package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustAddMapper {
    int deleteByPrimaryKey(String entrustId);

    int insert(EtrEntrustAdd record);

    int insertSelective(EtrEntrustAdd record);

    int insertBasicByBasicInfo(EtrEntrustAdd record);

    EtrEntrustAdd selectByPrimaryKey(String entrustId);

    int updateByPrimaryKeySelective(EtrEntrustAdd record);

    int updateByPrimaryKey(EtrEntrustAdd record);

    /**
     * 撤回或退回再启动流程时转移至废弃备份表
     * @param entrustId
     * @return
     */
    int insertFromQuery(String entrustId);
}