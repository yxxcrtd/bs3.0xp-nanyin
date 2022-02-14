/*
 * 文件名：EtrBondFirstBiddingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto.EtrExchangeAgreRepTrade001Dto;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto.EtrExchangeAgreRepTrade002Dto;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto.EtrExchangeAgreRepTrade003Dto;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto.EtrExchangeOnWayRep002Dto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  场内交易委托
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年11月14日
 * @see EtrExchangeAgreRepTradeMapper
 * @since 1.0
 */
@Repository
public interface EtrExchangeAgreRepTradeMapper {



    /**
     * 对手方交易员联想文本框初始化。
     * 
     * @param map 检索参数
     * @return 联想文本框
     */
    List<SuggestDto> selectCounterTrader(Map<String, Object> map);

    /**
     * 本方交易员联想文本框初始化。
     * 
     * @param map 检索参数
     * @return 联想文本框
     */
    List<SuggestDto> selectTrader(Map<String, Object> map);

    List<EtrExchangeOnWayRep002Dto> findOnWayRepPage(Map<String, Object> pageParam);

    List<EtrExchangeAgreRepTrade002Dto> findPositionsPage(Map<String, Object> pageParam);

    /**
     * 获取指令信息
     *
     * @param map 检索参数
     * @return 联想文本框
     */
    List<EtrExchangeAgreRepTrade001Dto> getEntrustInfoByEntrustId(Map<String, Object> map);
    /**
     * 查询原成交信息
     *
     * @param map 检索参数
     * @return 联想文本框
     */
    List<EtrExchangeAgreRepTrade001Dto> getEntrustInfoByTradeId(Map<String, Object> map);
    /**
     * 场内交易委托列表查询
     * @param param
     * @return
     */
    List<EtrExchangeAgreRepTrade003Dto> selectEtrEntrust(Map<String, Object> param);

    List<SelectOptionDto> getptlAcctNo(Map<String, Object> map);

    Date selectSecPositionMaxCdate(Map<String, Object> map);

    BigDecimal selectAvailablePositionAmount(Map<String, Object> param);
}
