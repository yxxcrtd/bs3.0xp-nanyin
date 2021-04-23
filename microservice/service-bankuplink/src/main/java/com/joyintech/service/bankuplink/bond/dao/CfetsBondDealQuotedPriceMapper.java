/*
 * 文件名：CfetsBondDealQuotedPriceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.bond.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.bankuplink.bond.dto.CfetsBondDealDialogueQuoteDto;
import com.joyintech.service.bankuplink.bond.dto.CfetsBondDealEntrustOrderDto;
import com.joyintech.service.bankuplink.bond.dto.CfetsBondDealRequestQuoteDto;
import com.joyintech.service.bankuplink.bond.dto.CounterInfoDto;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

/**
 * CFETS现券买卖报价
 * 
 * @author zhangliang
 * @version 1.0 2020年5月9日
 * @see CfetsBondDealQuotedPriceMapper
 * @since 1.0
 */
@Repository
public interface CfetsBondDealQuotedPriceMapper {
    
    /**
     * 委托指令查询
     * 
     * @param pageParam 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    List<CfetsBondDealEntrustOrderDto> queryEntrustOrderList(Map<String, Object> pageParam);
    
    /**
     * 根据查询查询符合条件的委托列表
     * 
     * @param map 入参
     * @return 委托列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryEntrustInfoList(Map<String, Object> map);
    
    /**
     * 根据查询查询符合条件的报价编号
     * 
     * @param map 入参
     * @return 报价编号
     * @see
     * @since 1.0
     */
    List<SuggestDto> queryCfetsQuoteIdList(Map<String, Object> map);
    
    /**
     * 查询委托已完成额度
     * 
     * @param map 入参
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal queryEntrustDealedAmt(Map<String, Object> map);
    
    // add by wangzhuangzhuang 报价双击查看详情 20200521 start
    /**
     * 根据查询查询符合条件的对手方信息
     * 
     * @param map 入参
     * @return 对手方信息
     * @see
     * @since 1.0
     */
    CounterInfoDto selectCounterInfo(Map<String, Object> map);
    
    // add by wangzhuangzhuang 报价双击查看详情 20200521 end
    
    /**
     * 查询债券最新估值
     * 
     * @param map 入参
     * @return 最新估值
     * @see
     * @since 1.0
     */
    MstBondValuationInfo queryLastBondValuation(Map<String, Object> map);
    
    /**
     * 获取节假日列表
     * 
     * @param map 入参
     * @return 节假日列表
     * @see
     * @since 1.0
     */
    List<String> getDealCalendarHoliday(Map<String, Object> map);

	List<CfetsBondDealDialogueQuoteDto> getCfetsBondDealDialogueQuoteDtoList(Map<String, Object> param);
	
	List<CfetsBondDealRequestQuoteDto> getCfetsBondDealRequestQuoteDtoList(Map<String, Object> param);
    
}
