package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfBranch;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfBranchMapper {
    int insert(InterfBranch record);

    int insertSelective(InterfBranch record);

    int insertBasicByBasicInfo(InterfBranch record);
}