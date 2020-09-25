package com.joyintech.tams.invest.common.trade.dao;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface PreTrdCommonMapper {
    /**
     * 查询金融产品交易预录入表
     * @return
     */
    List<String> selectByTotleTradeId(Map<String,Object> params);

    void insertWflTrdProductDealPre(Map<String, Object> params1);

    void insertWflTrdProductDealAddPre(Map<String, Object> params1);

    void insertTrdProductDealPre(Map<String, Object> params1);

    void insertTrdProductDealAddPre(Map<String, Object> params1);

    void updateTrdProductDealPre(Map<String, Object> params1);

    void deleteFinDealPledgePre(Map<String, Object> params);

    BigDecimal getEntrustSurAmt(Map<String, Object> params);

    String getPortfolioName(Map<String, Object> params);

    String getFinprodAbbr(Map<String, Object> params);

    void deletePtlSecPositionFlow(Map<String, Object> params);

    void deletePtlCashPositionFlow(Map<String, Object> params);

    void deleteEtrSecPositionFlow(Map<String, Object> params);

    void deleteEtrCashPositionFlow(Map<String, Object> params);
}
