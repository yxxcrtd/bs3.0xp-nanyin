package com.joyintech.tams.valuation.common.batch.tradecommon.dao;

import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.valuation.common.batch.tradecommon.dto.ValTrdProductDealInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yanya
 * @create 2021-03-03 9:35
 */
@Repository
public interface ValTradeCommonMapper {

    /**
     * 入账交易信息查询
     * @param paramsMap
     * @return
     */
    List<ValTrdProductDealInfoDto> findEntryTradeInfo(Map<String, Object> paramsMap);

    /**
     * 根据交易编号查询交易费用明细
     * @param tradeId
     * @return
     */
    List<TrdTradeFeeDetail> findTrdTradeFeeDetailInfo(String tradeId);

    /**
     * 行权回售申请交易-重新估值
     * @param paramsMap
     * @return
     */
    List<ValTrdProductDealInfoDto> findExecApplyInfoAgain(Map<String, Object> paramsMap);

    /**
     * 行权回售申请交易-保留原数据
     * @param paramsMap
     * @return
     */
    List<ValTrdProductDealInfoDto> findExecApplyInfo(Map<String, Object> paramsMap);

}
