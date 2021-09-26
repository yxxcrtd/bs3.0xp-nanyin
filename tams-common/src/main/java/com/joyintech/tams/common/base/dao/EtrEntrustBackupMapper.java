package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustBackupMapper {

    /**
     * 撤回或退回再启动流程时转移至废弃备份表
     * @param entrustId
     * @return
     */
    int insertFromQuery(String entrustId);

    int insertAddFromQuery(String entrustId);
}