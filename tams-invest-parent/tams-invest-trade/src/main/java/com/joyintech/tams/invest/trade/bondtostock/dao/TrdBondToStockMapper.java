package com.joyintech.tams.invest.trade.bondtostock.dao;

import com.joyintech.tams.invest.trade.bondtostock.dto.TrdBondToStock001Dto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface TrdBondToStockMapper {

    /**
     * 查询画面数据
     * 
     * @param pageParam 界面参数
     * @return 查询的结果
     */
    List<TrdBondToStock001Dto> findPage(Map<String, Object> pageParam);

    TrdBondToStock001Dto findEffectData(Map<String, Object> map);

    BigDecimal getPositionInfo(Map<String, Object> pageParam);

    TrdBondToStock001Dto findTradeInfoByEntrustId(Map<String, Object> map);
}
