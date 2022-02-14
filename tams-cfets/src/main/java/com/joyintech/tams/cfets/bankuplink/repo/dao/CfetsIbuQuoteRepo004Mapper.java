package com.joyintech.tams.cfets.bankuplink.repo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 本币交易-质押式回购交易 Mapper
 *
 * @author jiangpeng
 * @date 2021/12/4 15:39
 */
@Repository
public interface CfetsIbuQuoteRepo004Mapper {
    String findTraderByPartyId(@Param("partyId") String partyId);

    String findTraderByCounterId(@Param("counterId") String counterId);
}
