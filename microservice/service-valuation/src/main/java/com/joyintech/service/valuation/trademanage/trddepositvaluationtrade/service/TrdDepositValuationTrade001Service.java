/*
 * 文件名：TrdDepositInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.trddepositvaluationtrade.service;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.service.valuation.trademanage.trddepositvaluationtrade.dao.TrdDepositValuationTrade001Mapper;
import com.joyintech.service.valuation.trademanage.trddepositvaluationtrade.dto.TrdDepositValuationTrade001Dto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 存款类投资交易Service
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositValuationTrade001Service
 * @since 1.0
 */
@Service("backTrdDepositValuationTrade001Service")
@Transactional(rollbackFor=Exception.class)
public class TrdDepositValuationTrade001Service extends BaseService<TrdDepositValuationTrade001Dto> {

    /**
     * 注入存款类投资交易mapper
     */
    @Autowired
    private TrdDepositValuationTrade001Mapper trdDepositValuationTrade001Mapper;


    /**
     * 平台日获取接口
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;


    /**
     * 策略代码生成
     *
     * @return
     * @see
     * @since 1.0
     */
    public String createPlanTradeId() {
        String tradeId = null;
        while (true) {
            // 获取自增数
            String nextId = this.getNextId("PLANTRADEID_SEQUENCE");
            //平台日
            String dateStr= DateUtil.format(iPlatformDate.getSysDate(),DateUtil.DEFAULT_OTHER_FORMAT);//yyyyMMdd
            // 拼接
            tradeId = "JY"+"FICC"+dateStr+ nextId;
            // 查询是否重复
            int num = trdDepositValuationTrade001Mapper.selectCountByTradeId(tradeId);
            // 如果自增的策略代码不存在，则跳出循环
            if (num==0) {
                break;
            }
        }
        return tradeId;
    }

    /**
     * 获取自增序列值
     *
     * @param sequenceName 序列名
     * @return
     */
    public String getNextId(String sequenceName) {
        return trdDepositValuationTrade001Mapper.getNextId(sequenceName);
    }




    /**
     * 产品代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 16:47
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findPortfolioId(int page, int length,Map<String, Object> params) {
        return this.queryForPageForSelectReturn(page, length, ()->trdDepositValuationTrade001Mapper.findPortfolioId(params));
    }

    //资产代码下拉框
    public Page<SelectOptionDto> findFinprodId(int page, int length,Map<String, Object> params){
        return this.queryForPageForSelectReturn(page, length, ()->trdDepositValuationTrade001Mapper.findFinprodId(params));
    }

    //交易对手
    public Page<SelectOptionDto> getCounterId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> trdDepositValuationTrade001Mapper.getCounterId(param));
    }

    /**
     *
     * 获取分页查询结果（sharesId  dealDateString）
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstSharePriceDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<TrdDepositValuationTrade001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {

        //产品代码
        String portfolioId = (String)pageParam.get("portfolioId");
        String[] portfolioIdArr = null;
        if(StringUtil.isNotEmpty(portfolioId)){
            portfolioIdArr = portfolioId.split(",");
        }
        pageParam.put("portfolioIdArr", portfolioIdArr);
        //资产代码
        String finprodMarketId = (String)pageParam.get("finprodMarketId");
        String[] finprodMarketIdArr = null;
        if(StringUtil.isNotEmpty(finprodMarketId)){
            finprodMarketIdArr = finprodMarketId.split(",");
        }
        pageParam.put("finprodMarketIdArr", finprodMarketIdArr);
        // 获取交易方向
        String ps = (String)pageParam.get("ps");
        // 交易方向多选，转为数组
        String[] psArr = null;
        if (StringUtil.isNotEmpty(ps)) {
            psArr = ps.split(",");
        }
        pageParam.put("psArr", psArr);


        return this.queryForPage(page, length,
                () -> trdDepositValuationTrade001Mapper.selectDeal(pageParam));
    }


}
