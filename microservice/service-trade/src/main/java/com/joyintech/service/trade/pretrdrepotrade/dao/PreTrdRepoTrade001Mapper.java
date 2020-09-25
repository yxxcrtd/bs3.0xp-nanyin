
package com.joyintech.service.trade.pretrdrepotrade.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.pretrdrepotrade.dto.PreTrdRepoTrade001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeReferenceDto;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.common.base.entity.InterfTradRepoasst;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 回购预交易微服务TrdRepoTradeMapper
 */
@Repository
public interface PreTrdRepoTrade001Mapper {

    List<PreTrdRepoTrade001Dto> selectForPage(Map<String, Object> pageParam);
    /**
     * 获取交易对手列表
     * @param map 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */

    List<SelectOptionDto> getCounterAcct(Map<String,Object> map);
    /**
     * 获取资金账户下拉框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAcct(Map<String, Object> pageParam);
    List<SelectOptionDto> getSecAcct(Map<String, Object> pageParam);
    PreTrdRepoTrade001Dto getAcctId(Map<String, Object> pageParam);
    PreTrdRepoTrade001Dto getSecAcctDto(Map<String, Object> pageParam);

    List<Map<String, Object>> findTrdMatchInfo(Map<String, Object> params);

    PreTrdRepoTrade001Dto getPreTrdRepoTradeDto(Map<String, Object> params);
}
