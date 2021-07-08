package com.joyintech.service.entrust.etribrepo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoLib001Dto;
import com.joyintech.tams.common.base.entity.EtrEntrustPledge;

@Repository
public interface EtrIbRepoLib001Mapper {


    /**
     * 质押券入库画面，查询可质押债券列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondInDto>
     */
    List<EtrIbRepoLib001Dto> selectAvaPledgeBondList(Map<String, Object> pageParam);
    
    /**
     * 质押券入库画面，查询已入库债券列表
     *
     * @param pageParam pageParam
     * @return List<TrdStandardBondInDto>
     */
    List<EtrIbRepoLib001Dto> selectPledgeBondList(Map<String, Object> pageParam);
    
    
    EtrEntrustPledge selectEtrEntrustPledge(EtrEntrustPledge etrEntrustPledge);
    
    int deleteEtrEntrustPledge(EtrEntrustPledge etrEntrustPledge); 
}
