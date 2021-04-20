package com.joyintech.tams.invest.common.cstp.dao;

import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.common.base.entity.InterfTradSecdeal;
import com.joyintech.tams.invest.common.dto.cstp.EtrEntrustExt;
import com.joyintech.tams.invest.common.dto.cstp.InterfTradRepoasstDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CstpCommonMatchMapper {

    String selectSeqNo(@Param("sysDate") String sysDate);
    /**
     * 成交编号 查找对应的报价及指令信息
     * @param execId
     * @return
     */
    EtrEntrustExt getQuoteInfoByexecId(@Param("execId") String execId);

    List<EtrEntrustExt> getQualifiedEtrEntrust(Map<String,Object> map);

    InterfTradSecdeal getInterfTradSecdealByExecId(@Param("execId") String execId);

    InterfOpicsTradRepo getInterfOpicsTradRepoByExecId(@Param("execId") String execId);

    List<InterfTradRepoasstDto> getInterfTradRepoasst(@Param("tradeId") String tradeId);
}
