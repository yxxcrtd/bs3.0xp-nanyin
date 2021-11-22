package com.joyintech.tams.cfets.bankuplink.bond.dao;

import com.joyintech.tams.cfets.bankuplink.bond.dto.CfetsBondDealEntrustOrderDto;
import com.joyintech.tams.cfets.bankuplink.bond.dto.DealTransactionDetailsDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CfetsIbuQuoteBondMapper {

    /**
     * 委托列表初始化
     * @param param
     * @return
     */
    List<CfetsBondDealEntrustOrderDto> listEntrust(Map<String,Object> param);

    /**
     * 是否有行权计划
     * @param param
     * @return
     */
    int countBondHasEexrPlan(Map<String,Object> param);

    /**
     * 获取证券账户
     * @param portfolioId
     * @param finprodId
     * @return
     */
    String getSecAcctId(@Param("portfolioId") String portfolioId,@Param("finprodId") String finprodId);

    /**
     * 获取资金账户
     * @param portfolioId
     * @param acctType
     * @return
     */
    String getACCTNO(@Param("portfolioId") String portfolioId,@Param("acctType") String acctType);

    List<DealTransactionDetailsDto> listDealTransactionDetails(@Param("entrustId") String entrustId);

    int selectBondHasEexrPlanCount(Map<String, Object> param);
}
