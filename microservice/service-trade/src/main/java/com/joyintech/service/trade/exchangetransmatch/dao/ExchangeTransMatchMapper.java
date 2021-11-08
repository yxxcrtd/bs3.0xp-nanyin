package com.joyintech.service.trade.exchangetransmatch.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.exchangetransmatch.dto.ExchangeTransMatch002Dto;
import com.joyintech.service.trade.exchangetransmatch.dto.ExchangeTransMatch003Dto;
import com.joyintech.service.trade.exchangetransmatch.dto.ExchangeTransMatch004Dto;
import com.joyintech.service.trade.exchangetransmatch.dto.ExchangeTransMatch005Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExchangeTransMatchMapper {

    /**
     * 查询成交信息
     * @param map
     * @return
     */
    List<ExchangeTransMatch002Dto> findPage(Map<String, Object> map);

    List<ExchangeTransMatch004Dto> findTransMatchInfoPage(Map<String, Object> map);

    /**
     * 查询委托交易数据
     * @param map
     * @return
     */
    List<ExchangeTransMatch003Dto> findEntrustPage(Map<String, Object> map);

    /**
     * 获取资产代码下拉框
     * @param map
     * @return
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> map);

    /**
     * 获取交易编号下拉框
     * @param map
     * @return
     */
    List<SelectOptionDto> getBusiCode(Map<String, Object> map);

    /**
     * 获取申请编号下拉框
     * @param map
     * @return
     */
    List<SelectOptionDto> getApplyNo(Map<String, Object> map);

    /**
     * 获取证券账户下拉框
     * @param map
     * @return
     */
    List<SelectOptionDto> getSecAcctNo(Map<String, Object> map);

    /**
     * 获取成交编号下拉框
     * @param map
     * @return
     */
    List<SelectOptionDto> getDealId(Map<String, Object> map);

    /**
     * 获取指令列表
     * @param seqNo
     * @return
     */
    List<ExchangeTransMatch005Dto> findEntrustList(String seqNo);

    /**
     * 获取成交信息
     * @param seqNo
     * @return
     */
    List<ExchangeTransMatch005Dto> findDealList(String seqNo);
}
