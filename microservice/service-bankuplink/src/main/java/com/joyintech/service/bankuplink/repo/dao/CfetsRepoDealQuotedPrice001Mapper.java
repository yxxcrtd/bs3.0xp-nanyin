/*
 * 文件名：CfetsRepoDealQuotedPrice001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.repo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.bankuplink.repo.dto.CfetsRepoDealQuotedPrice001Dto;
import com.joyintech.service.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author yufei
 * @version 1.0 2020年5月14日
 * @see CfetsRepoDealQuotedPrice001Mapper
 * @since 1.0
 */
@Repository
public interface CfetsRepoDealQuotedPrice001Mapper {
    /**
     * 委托指令查询
     * 
     * @param pageParam 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    List<CfetsRepoDealQuotedPrice001Dto> queryEntrustOrderList(Map<String, Object> pageParam);
    /**
     * 正回购可质押查询
     * 
     * @param pageParam 入参
     * @return 可质押
     * @see
     * @since 1.0
     */
    List<CfetsRepoDealQuotedPrice003Dto> queryPledgeBondList(Map<String, Object> pageParam);
    /**
     * 质押券明细查询
     * 
     * @param pageParam 入参
     * @return  质押券明细
     * @see
     * @since 1.0
     */
    List<CfetsRepoDealQuotedPrice003Dto> getPledgeBondList(Map<String, Object> pageParam);
    /**
     * 逆回购可质押查询
     * 
     * @param pageParam 入参
     * @return 可质押
     * @see
     * @since 1.0
     */
    List<CfetsRepoDealQuotedPrice003Dto> queryDefyPledgeBondList(Map<String, Object> pageParam);
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


    BigDecimal selectUsedAmt(Map<String, Object> map);
    
    /**
     * 查询质押式回购报价历史
     * @param map
     * @return
     */
    List<CfetsRepoDealQuotedPrice001Dto> queryCfetsRepoHis(Map<String, Object> map);
    
    /**
     * 获得交易员姓名
     * @param tradeId
     * @return
     */
    String getTraderName(String traderId);

    /**
     * 新建会话  根据委托编号 初始化组合分配列表
     * @param map
     * @return
     */
    List<PtlAllocationDto>  selectPortfolioInit(Map<String,Object> map);

    /**
     * 根据报价编号 初始化组合分配列表
     * @param map
     * @return
     */
    List<PtlAllocationDto>  selectPortfolioByQuoteIdInit(Map<String,Object> map);

    List<PtlAllocationDto> selectEtrReByQuoteId(Map<String,Object> map);

    List<CfetsRepoDealQuotedPrice003Dto>  selectQuoteCrepPledgeForDzh(Map<String,Object> map);

    /***
     * 对手方质押券信息
     * @param map
     * @return
     */
    List<CfetsRepoDealQuotedPrice003Dto> selectQuoteCrepPledgeForDsf(Map<String,Object> map);

    /**
     * 是否展示对手方质押券
     * @param map
     * @return
     */
    String getIsShowDsfPledge(Map<String,Object> map);

    List<CfetsRepoDealQuotedPrice001Dto> getQualifiedByentrustIds(Map<String,Object> map);
}
